package be.artisjaap.document.utils;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.*;
import fr.opensagres.xdocreport.core.io.internal.ByteArrayOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class PDFUtils {

	public static void savePdf(String path, String filename, byte[] bytes){
		File file = new File(path);
		file.mkdirs();
		File fullPathToFile = new File(file, filename + ".pdf");
		System.out.println("Brief wordt opgeslagen in :" + fullPathToFile.getAbsoluteFile());
		try {
			FileOutputStream outputStream = new FileOutputStream(fullPathToFile);
			outputStream.write(bytes);
			outputStream.flush();
			outputStream.close();
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static byte[] stampDoc(byte[] doc, List<byte[]> stampers) {
		for(byte[] s : stampers) {
			try {
				doc = stamp(doc, s);
			}catch(IOException | DocumentException e){
				
			}
		}
		return doc;
           
	}

	public static byte[] stampDoc(List<byte[]> doc){
		if(doc.isEmpty()){
			throw new UnsupportedOperationException("lijst mag niet leeg zijn");
		}
		if(doc.size() == 1){
			return doc.get(0);
		}

		return stampDoc(doc.get(0), doc.subList(1, doc.size()));
	}
	
	public static byte[] equalPageStamper(byte[] briefDoc, byte[] stamperDoc) throws IOException, DocumentException {
		PdfReader briefPdf = new PdfReader(briefDoc);
		PdfReader stamperPdf = new PdfReader(stamperDoc);
		
		int aantalPaginasBrief = briefPdf.getNumberOfPages();
		int aantalPaginasStamper = stamperPdf.getNumberOfPages();
		
		if(aantalPaginasBrief == aantalPaginasStamper){
			 ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			 PdfStamper stamper = new PdfStamper(briefPdf, outputStream);
			 
			 for(int i = 1; i <= aantalPaginasBrief; ++i) {
			 	PdfImportedPage page = stamper.getImportedPage(stamperPdf, i);
	            PdfContentByte background = stamper.getUnderContent(i);
	            background.addTemplate(page, 0.0F, 7.0F);
	        }
			 
			stamper.close();
		    byte[] docStamped =  outputStream.toByteArray();
		    outputStream.close();
		    return docStamped;

		}
        throw new DocumentException("paginas in stamp moet even groot zijn als paginas in doc");
	}

	private static byte[] stamp(byte[] document, byte[] stamperDocument) throws IOException, DocumentException {
 
        PdfReader pdfStamperDocument = new PdfReader(stamperDocument);
        PdfReader pdfDocument = new PdfReader(document);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfStamper stamper = new PdfStamper(pdfDocument, outputStream);
        PdfImportedPage page = stamper.getImportedPage(pdfStamperDocument, 1);
        int n = pdfDocument.getNumberOfPages();

        for(int i = 1; i <= n; ++i) {
            PdfContentByte background = stamper.getUnderContent(i);
            background.addTemplate(page, 0.0F, 7.0F);
        }

        stamper.close();
        return outputStream.toByteArray();
    }



	public static Optional<byte[]> mergePdfs(List<byte[]> pdfdocs) {
		Document merged = new Document();

        try (ByteArrayOutputStream mergedOut = new ByteArrayOutputStream()){
            PdfCopy copy = new PdfCopy(merged, mergedOut);
            merged.open();
            pdfdocs
                .stream()
                .forEach(f -> {
                    try {
                        PdfReader doc = new PdfReader(f);
                        for (int i = 0; i < doc.getNumberOfPages(); ) {
                            copy.addPage(copy.getImportedPage(doc, ++i));
                        }
                    }catch(BadPdfFormatException | IOException ex) {
                        throw new IllegalStateException(ex);
                    }
                });

            merged.close();
            return Optional.of(mergedOut.toByteArray());
        } catch (IOException | DocumentException e) {
			return Optional.empty();
		} 
	}



}
