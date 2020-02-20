package be.artisjaap.document.utils;

import org.junit.Assert;
import org.junit.Test;

public class DocXAnalyserTest {

    @Test
    public void mergeFieldIsCorrectlyParsed(){

        String field = "MERGEFIELD\\s+(\\$([^.]+).([^\\s]+)) region=0,9589 lastmatch=MERGEFIELD  $</w:instrText></w:r><w:r w:rsidR=\"00837FE0\"><w:rPr><w:rFonts w:ascii=\"Bahnschrift SemiBold\" w:hAnsi=\"Bahnschrift SemiBold\"/><w:color w:val=\"4472C4\" w:themeColor=\"accent1\"/><w:lang w:val=\"en-US\"/></w:rPr><w:instrText>mistake</w:instrText></w:r><w:r w:rsidR=\"001C1A95\"><w:rPr><w:rFonts w:ascii=\"Bahnschrift SemiBold\" w:hAnsi=\"Bahnschrift SemiBold\"/><w:color w:val=\"4472C4\" w:themeColor=\"accent1\"/><w:lang w:val=\"en-US\"/></w:rPr><w:instrText>s</w:instrText></w:r><w:r><w:rPr><w:rFonts w:ascii=\"Bahnschrift SemiBold\" w:hAnsi=\"Bahnschrift SemiBold\"/><w:color w:val=\"4472C4\" w:themeColor=\"accent1\"/><w:lang w:val=\"en-US\"/></w:rPr><w:instrText xml:space=\"preserve\">.question]\n";
        DatasetInfo dsi = new DatasetInfo();

        DocXAnalyser.addMergefields(dsi, field, DocXAnalyser.MERGEFIELD_DOCX);

        Assert.assertNotNull(dsi.getDatasets().get("mistakes"));
    }
}
