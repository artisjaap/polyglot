package be.artisjaap.core.utils;

import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class WebUtils {

    public static OutputStream maakOutputstreamVoorXlsxFile(HttpServletResponse response, String filename) throws IOException {
        return maakOutputstreamVoorFile(response, filename, "xlsx");
    }

    public static OutputStream maakOutputstreamVoorPdfFile(HttpServletResponse response, String filename) throws IOException {
        return maakOutputstreamVoorFile(response, filename, "pdf");
    }

    public static OutputStream maakOutputstreamVoorFile(HttpServletResponse response, String filename, String extension) throws IOException {
        OutputStream out = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment; filename=\""+filename+"." + extension + "\"");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        return out;
    }
}
