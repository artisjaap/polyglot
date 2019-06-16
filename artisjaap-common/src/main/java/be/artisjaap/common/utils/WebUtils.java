package be.artisjaap.common.utils;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class WebUtils {

    public static byte[] getBytesFromMultipartFile(MultipartFile file){
        try{
            return file.getBytes();
        }catch(IOException ex){

        }
        return new byte[0];
    }

    public static OutputStream maakOutputstreamVoorFile(HttpServletResponse response, String filename, String extention) throws IOException {
        OutputStream out = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment; filename=\""+filename+"." +  extention + "\"");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        return out;
    }
}
