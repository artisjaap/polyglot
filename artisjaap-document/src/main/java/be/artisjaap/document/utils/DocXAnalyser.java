package be.artisjaap.document.utils;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class DocXAnalyser { //$BriefInfo.BriefCode
	private final static Pattern MERGEFIELD_DOCX = Pattern.compile("MERGEFIELD\\s+(\\$([^.]+).([^\\s]+))");
	private final static Pattern MERGEFIELD_ODT = Pattern.compile("<text:database-display[^>]*column-name=\"(\\$([^.]+).([^\\s]+))\"");

	private final static int MERGEFIELD_EXPRESSION = 1;
	private final static int DATASET = 2;
	private final static int VARIABLE_NAME = 3;


	public static DatasetInfo findFields(DocType type, InputStream is)  {
		DatasetInfo mergeInfo = new DatasetInfo();

		if(type == DocType.DOCX){

			try {
				XWPFDocument document= new XWPFDocument(is);
				addMergefields(mergeInfo, document.getDocument().xmlText(), MERGEFIELD_DOCX);

			}catch(IOException e){

			}catch(Exception e){

			}

		}
		if(type == DocType.ODT){
			ZipInputStream zis = new ZipInputStream(is);

			ZipEntry z;
			try {
				while((z = zis.getNextEntry()) != null){
					if(z.getName().toLowerCase().endsWith(".xml")){
						ByteArrayOutputStream output = ZipUtils.readFileFromZipStream(zis);
						BufferedReader bin = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(output.toByteArray())));
						String line;
						while ((line = bin.readLine()) != null) {
							System.out.println(line);
							addMergefields(mergeInfo, line, MERGEFIELD_ODT);
						}
					}
				}
			}catch(IOException ex){

			}
		}
		return mergeInfo;

	}


	private static void addMergefields(DatasetInfo mergeInfo, String text, Pattern pattern) {
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()){

			mergeInfo.addMergefield(matcher.group(DATASET), matcher.group(VARIABLE_NAME));
		}
	}




}
