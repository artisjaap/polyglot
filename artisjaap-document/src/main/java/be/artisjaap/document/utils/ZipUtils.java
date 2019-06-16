package be.artisjaap.document.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;

public class ZipUtils {
	public static ByteArrayOutputStream readFileFromZipStream(ZipInputStream zis) throws IOException {
		byte[] buffer = new byte[2048];

		ByteArrayOutputStream output = null;
		try {
			output = new ByteArrayOutputStream();
			int len = 0;
			while ((len = zis.read(buffer)) > 0) {
				output.write(buffer, 0, len);
			}

		} finally {
			// we must always close the output file
			if (output != null)
				output.close();
		}
		return output;
	}

}
