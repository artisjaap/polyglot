package be.artisjaap.polyglot.core.action.to.translationsfromfile;

import com.opencsv.bean.CsvBindByPosition;

public class TranslationRecord {
    @CsvBindByPosition(position = 0)
    private String languageA;

    @CsvBindByPosition(position = 1)
    private String languageB;

    public String getLanguageA() {
        return languageA;
    }

    public void setLanguageA(String languageA) {
        this.languageA = languageA;
    }

    public String getLanguageB() {
        return languageB;
    }

    public void setLanguageB(String languageB) {
        this.languageB = languageB;
    }
}
