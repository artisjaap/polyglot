package be.artisjaap.mail.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class Attachment {
    private String filename;
	private String filetype;
	private InputStream inputstream;

	public Attachment(String filename, String filetype,
			ByteArrayOutputStream outputstream) {
		super();
		this.filename = filename;
		this.filetype = filetype;
		this.inputstream = new ByteArrayInputStream(outputstream.toByteArray());
	}

	public String getFilename() {
		return filename;
	}

	public String getFiletype() {
		return filetype;
	}

	public InputStream getInputstream() {
		return inputstream;
	}

}
