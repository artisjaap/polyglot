package be.artisjaap.common.utils;

import java.io.*;

public class FileUtils {

    public static File writeBytesToTempFile(String filename, byte[] bytes){
        File file = createTempFileFor(filename);

        try (FileOutputStream fileWriter = new FileOutputStream(file)){
            fileWriter.write(bytes);
            fileWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static File createTempFileFor(String filename){
        return new File(System.getProperty("java.io.tmpdir"), filename);
    }
}
