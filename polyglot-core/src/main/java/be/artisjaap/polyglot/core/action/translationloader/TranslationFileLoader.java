package be.artisjaap.polyglot.core.action.translationloader;


import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class TranslationFileLoader {

    private final List<TranslationTag> tags;
    private final byte[] translations;

    private TranslationFileLoader(Reader reader) {
        this.tags = new ArrayList<>();
        this.translations = parseTranslations(new BufferedReader(reader));
    }

    public Reader getReader() {

        return new InputStreamReader(new ByteArrayInputStream(translations), Charset.forName("UTF-8"));
    }

    private byte[] parseTranslations(BufferedReader reader) {
        String line = null;
        try {
            line = reader.readLine();
            
            StringBuilder sb = new StringBuilder();

            while (line != null) {
                if (line.startsWith("@")) {
                    tags.add(FileLoaderTag.of(line));

                } else {
                    sb.append(line).append("\r\n");
                }
                line = reader.readLine();
            }

            return sb.toString().getBytes(Charset.forName("UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static TranslationFileLoader  of(Reader reader) {
        return new TranslationFileLoader(reader);
    }

    public boolean isLesson(){
        return !tagsContain(TagName.LESSON).isEmpty();
    }

    public boolean isVerb(){
        return !tagsContain(TagName.VERB).isEmpty();
    }


    private List<TranslationTag> tagsContain(TagName tagname){
        return this.tags.stream()
                .filter(tag -> tag.tagName() == tagname)
                .collect(Collectors.toList());
    }

    public Optional<ParsedVerb> parsedVerb(){
        return tagsContain(TagName.VERB).stream()
                .map(t -> (ParsedVerb)t.parsedTag())
                .findFirst();
    }

    public Optional<ParsedTense> parsedTense(){
        return tagsContain(TagName.TENSE).stream()
                .map(t -> (ParsedTense)t.parsedTag())
                .findFirst();
    }

    public Optional<ParsedLesson> parsedLesson(){
        return tagsContain(TagName.LESSON).stream()
                .map(t -> (ParsedLesson)t.parsedTag())
                .findFirst();
    }


}
