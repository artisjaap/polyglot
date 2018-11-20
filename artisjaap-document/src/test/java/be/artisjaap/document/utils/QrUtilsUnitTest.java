package be.artisjaap.document.utils;

import com.google.zxing.NotFoundException;
import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Assertions.assertThat;

public class QrUtilsUnitTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Test
    public void testCrappyDocument() throws IOException {
        URL resource = getClass().getClassLoader().getResource("documents/test.pdf");
        String s = null;
        try {
            s = QrUtils.readUniqueQrcodeDataFromPdf(FileUtils.readFileToByteArray(new File(resource.getFile())));
        } catch (NotFoundException e) {
            fail("Qrcode not detected while it was expected");
        }
        System.out.println(s);
    }

    @Test
    public void readUniqueQrcodeDataFromPdf_whenOneQrcodePresent_returnQrCodeData() throws IOException, NotFoundException {
        URL resource = getClass().getClassLoader().getResource("documents/doc_met_1_qr_code.pdf");
        String s = QrUtils.readUniqueQrcodeDataFromPdf(FileUtils.readFileToByteArray(new File(resource.getFile())));
        assertThat(s).isEqualTo("Hoera, deze QRcode is gelezen");
    }

    @Test(expected = RuntimeException.class)
    public void readUniqueQrcodeDataFromPdf_whenMultipleQrcodesPresent_throwsRuntimeException() throws IOException, NotFoundException {
        URL resource = getClass().getClassLoader().getResource("documents/doc_met_2_qr_codes.pdf");
        QrUtils.readUniqueQrcodeDataFromPdf(FileUtils.readFileToByteArray(new File(resource.getFile())));
    }

    @Test(expected = NotFoundException.class)
    public void readUniqueQrcodeDataFromPdf_whenNoQrcodesPresent_throwsNotFoundException() throws IOException, NotFoundException {
        URL resource = getClass().getClassLoader().getResource("documents/doc_met_0_qr_code.pdf");
        QrUtils.readUniqueQrcodeDataFromPdf(FileUtils.readFileToByteArray(new File(resource.getFile())));
    }

    @Test
    public void splitPdfInMultipleDocumentsWithOneQrCodePerDocument() throws IOException {
        URL resource = getClass().getClassLoader().getResource("documents/samengestelde_doc_met_5_documenten.pdf");
        File file = new File(resource.getFile());
        PdfQrCodeSplitResult pdfQrCodeSplitResult =
                QrUtils.splitPdfInMultipleDocumentsWithOneQrCodePerDocument(FileUtils.readFileToByteArray(file), Paths.get(file.getAbsolutePath()), 3, this::generateSplitDocumentSuccessPath, this::generateSplitDocumentErrorPath);
        assertThat(pdfQrCodeSplitResult.getSizeSuccessSplitPdfs()).isEqualTo(4);
        assertThat(pdfQrCodeSplitResult.getSizePdfsInError()).isEqualTo(1);
    }

    private Path generateSplitDocumentSuccessPath(Map<String, Object> stringObjectMap) {
        return Paths.get(testFolder.getRoot().getPath() + "succes");
    }

    private Path generateSplitDocumentErrorPath(Map<String, Object> stringObjectMap) {
        return Paths.get(testFolder.getRoot().getPath() + "error");
    }

}
