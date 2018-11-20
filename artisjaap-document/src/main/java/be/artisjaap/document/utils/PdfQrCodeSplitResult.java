package be.artisjaap.document.utils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PdfQrCodeSplitResult {

    private List<Path> successfullySplitPdfs = new ArrayList<>();
    private List<Path> pdfsInError = new ArrayList<>();

    public void addPdf(Path pdf, boolean success) {
        if(success) {
            successfullySplitPdfs.add(pdf);
        }else {
            pdfsInError.add(pdf);
        }
    }

    public int getSizeSuccessSplitPdfs() {
        return successfullySplitPdfs.size();
    }

    public int getSizePdfsInError() {
        return pdfsInError.size();
    }

    public void handleResults(Consumer<Path> successHandler, Consumer<Path> errorHandler) {
        successfullySplitPdfs.forEach(successHandler);
        pdfsInError.forEach(errorHandler);
    }
}
