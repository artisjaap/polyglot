package be.artisjaap.document.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.qrcode.EncodeHintType;
import com.itextpdf.text.pdf.qrcode.Encoder;
import com.itextpdf.text.pdf.qrcode.ErrorCorrectionLevel;
import com.itextpdf.text.pdf.qrcode.QRCode;
import com.itextpdf.text.pdf.qrcode.WriterException;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.function.Function;

import static be.artisjaap.document.utils.QrUtilsSplitErrorTypes.TOO_MANY_PAGES;

public class QrUtils {

    public static final String START_PAGE_BASE_DOCUMENT = "startPageBaseDocument";
    public static final String NTH_FILE_OF_BASE_DOCUMENT = "nThFileOfBaseDocument";
    public static final String ERROR_TYPE = "errorType";
    private static final int DPI = 400;

    private static final Logger LOGGER = LoggerFactory.getLogger(QrUtils.class);

    private static ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.M;
    private static final Map<EncodeHintType,Object> encodingHints = new HashMap<EncodeHintType,Object>()
     {{
        put(EncodeHintType.ERROR_CORRECTION, errorCorrectionLevel);
    }};

    public static byte[] maakQrMetData(String data) {
        Pair<Integer, Integer> dimensions = berekenOptimaleQRCodeAfmetingen(data);
        BarcodeQRCode my_code = new BarcodeQRCode(data, dimensions.getLeft(), dimensions.getRight(), encodingHints);
        Image image = my_code.createAwtImage(Color.BLACK, Color.WHITE);
        return getBytesFromAWTImage(image);
    }

    private static Pair<Integer, Integer> berekenOptimaleQRCodeAfmetingen(String data) {
        QRCode code = new QRCode();
        try {
            Encoder.encode(data, errorCorrectionLevel, null, code);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return Pair.of(code.getMatrix().getWidth(), code.getMatrix().getHeight());
    }

    private static byte[] getBytesFromAWTImage(Image image) {
        BufferedImage bufferedImage = toBufferedImage(image);
        return save(bufferedImage, "png");
    }


    private static byte[] save(BufferedImage image, String ext) {

        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ImageIO.write(image, ext, output);
            output.flush();
            return output.toByteArray();
        } catch (IOException e) {
            System.out.println("Write error: " + e.getMessage());
        }
        return new byte[]{};
    }

    private static BufferedImage toBufferedImage(Image src) {
        int w = src.getWidth(null);
        int h = src.getHeight(null);
        int type = BufferedImage.TYPE_INT_RGB;  // other options
        BufferedImage dest = new BufferedImage(w, h, type);
        Graphics2D g2 = dest.createGraphics();
        g2.drawImage(src, 0, 0, null);
        g2.dispose();
        return dest;
    }

    public static String readUniqueQrcodeDataFromPdf(PDDocument inputDocument) throws IOException, NotFoundException {
        BufferedImage bim;
        PDFRenderer pdfRenderer = new PDFRenderer(inputDocument);
        bim = pdfRenderer.renderImageWithDPI(0, DPI, ImageType.RGB);

        Hashtable<DecodeHintType, Object> hints = new Hashtable<>();
        hints.put(DecodeHintType.TRY_HARDER, BarcodeFormat.QR_CODE);
        LuminanceSource source = new BufferedImageLuminanceSource(bim);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeMultiReader multiReader = new QRCodeMultiReader();
        Result[] results = multiReader.decodeMultiple(bitmap, hints);

        if (results.length != 1) {
            throw new RuntimeException(String.format("Detected %d QR codes while expecting exactly 1 QR code", results.length));
        }

        return results[0].getText();
    }

    public static String readUniqueQrcodeDataFromPdf(byte[] pdfContent) throws IOException, NotFoundException {
        try (PDDocument readDocument = PDDocument.load(pdfContent)) {
            return readUniqueQrcodeDataFromPdf(readDocument);
        }
    }

    public static PdfQrCodeSplitResult splitPdfInMultipleDocumentsWithOneQrCodePerDocument(byte[] pdfContent, Path pdfFilePath, int maxPagesPerSplitPdf, Function<Map<String, Object>, Path> generateSuccesPath, Function<Map<String, Object>, Path> generateErrorPath) throws IOException {
        PdfQrCodeSplitResult result = new PdfQrCodeSplitResult();

        PDDocument currentDocument = null;
        PDPage page;

        int amountOfDocuments = 0;
        try (PDDocument readDocument = PDDocument.load(pdfContent)) {
            Map<String, Object> fileNameParamMap = new HashMap<>();
            fileNameParamMap.put(START_PAGE_BASE_DOCUMENT, 1);
            fileNameParamMap.put(NTH_FILE_OF_BASE_DOCUMENT, 1);
            for (int i = 0; i < readDocument.getNumberOfPages(); i++) {
                page = readDocument.getPage(i);
                PDDocument pdDocument = new PDDocument();
                pdDocument.addPage(page);
                PDFRenderer pdfRenderer = new PDFRenderer(pdDocument);
                BufferedImage bim = pdfRenderer.renderImageWithDPI(0, DPI, ImageType.RGB);
                pdDocument.close();

                Hashtable<DecodeHintType, Object> hints = new Hashtable<>();
                hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
                hints.put(DecodeHintType.POSSIBLE_FORMATS, BarcodeFormat.QR_CODE);
                LuminanceSource source = new BufferedImageLuminanceSource(bim);
                BinaryBitmap bitmap = new BinaryBitmap(new GlobalHistogramBinarizer(source));
                QRCodeMultiReader multiReader = new QRCodeMultiReader();
                try {
                    multiReader.decodeMultiple(bitmap, hints);
                    generateDocument(maxPagesPerSplitPdf, generateSuccesPath, generateErrorPath, result, currentDocument, fileNameParamMap);

                    currentDocument = new PDDocument();
                    amountOfDocuments++;
                    fileNameParamMap.put(START_PAGE_BASE_DOCUMENT, i+1);
                    fileNameParamMap.put(NTH_FILE_OF_BASE_DOCUMENT, amountOfDocuments);
                    currentDocument.addPage(new PDPage(page.getCOSObject()));
                } catch (NotFoundException e) {
                    if (currentDocument != null) {
                        currentDocument.addPage(new PDPage(page.getCOSObject()));
                    } else {
                        LOGGER.error(String.format("Unable to detect QRcode on first page of document [%s]", pdfFilePath));
                    }
                }
            }
            generateDocument(maxPagesPerSplitPdf, generateSuccesPath, generateErrorPath, result, currentDocument, fileNameParamMap);
        }
        return result;
    }

    private static void generateDocument(int maxPagesPerSplitPdf,
                                         Function<Map<String, Object>, Path> generateSuccesPath,
                                         Function<Map<String, Object>, Path> generateErrorPath,
                                         PdfQrCodeSplitResult result,
                                         PDDocument currentDocument,
                                         Map<String, Object> fileNameParamMap) throws IOException {
        if (currentDocument != null) {
            Path savePath = calculatePath(maxPagesPerSplitPdf, generateSuccesPath, generateErrorPath, currentDocument, fileNameParamMap);
            currentDocument.save(savePath.toFile());
            currentDocument.close();
            result.addPdf(savePath, isSuccess(maxPagesPerSplitPdf, currentDocument));
        }
    }

    private static Path calculatePath(int maxPagesPerSplitPdf, Function<Map<String, Object>, Path> generateSuccesPath, Function<Map<String, Object>, Path> generateErrorPath, PDDocument currentDocument, Map<String, Object> fileNameParamMap) {
        if (isSuccess(maxPagesPerSplitPdf, currentDocument)) {
            return generateSuccesPath.apply(fileNameParamMap);
        } else {
            fileNameParamMap.put(ERROR_TYPE, TOO_MANY_PAGES);
            return generateErrorPath.apply(fileNameParamMap);
        }
    }

    private static boolean isSuccess(int maxPagesPerSplitPdf, PDDocument currentDocument) {
        return currentDocument.getNumberOfPages() <= maxPagesPerSplitPdf;
    }
}
