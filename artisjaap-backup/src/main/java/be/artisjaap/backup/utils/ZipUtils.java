package be.artisjaap.backup.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;

public class ZipUtils {

    private ZipUtils() {
    }

    public static ByteArrayOutputStream readFileFromZipStream(ZipInputStream zis) throws IOException {
        byte[] buffer = new byte[2048];


        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            int len = 0;
            while ((len = zis.read(buffer)) > 0) {
                output.write(buffer, 0, len);
            }
            return output;
        }

    }

}
