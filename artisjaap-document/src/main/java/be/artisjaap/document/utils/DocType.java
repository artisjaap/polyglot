package be.artisjaap.document.utils;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum DocType {
	DOCX, ODT, PPTX, UNSUPPORTED;
	
	final static Pattern extension = Pattern.compile("\\.([a-zA-Z]+)$");
	
	public static DocType fromFilename(String filename){
		Matcher matcher = extension.matcher(filename);
		if(matcher.find()){
			return Optional.ofNullable(DocType.valueOf(matcher.group(1).toUpperCase())).orElse(UNSUPPORTED);
		}
		
		return DocType.UNSUPPORTED;
	}
}
