package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.polyglot.core.action.to.mistakes.LanguageMistakeTO;
import be.artisjaap.polyglot.core.model.LanguagePairMistakes;
import org.springframework.stereotype.Component;

@Component
public class LanguageMistakeAssembler implements Assembler<LanguageMistakeTO, LanguagePairMistakes> {

    private final LanguageMistakeDetailAssembler languageMistakeDetailAssembler;

    public LanguageMistakeAssembler(LanguageMistakeDetailAssembler languageMistakeDetailAssembler) {
        this.languageMistakeDetailAssembler = languageMistakeDetailAssembler;
    }

    @Override
    public LanguageMistakeTO assembleTO(LanguagePairMistakes doc) {
        return LanguageMistakeTO.builder()
                .languagePairId(doc.getLanguagePairId().toString())
                .userId(doc.getUserId().toString())
                .from(doc.getFrom())
                .until(doc.getTo())
                .mistakeDetails(languageMistakeDetailAssembler.assembleTOs(doc.getMistakes()))
                .build();
    }

    @Override
    public LanguagePairMistakes assembleEntity(LanguageMistakeTO languageMistakeTO) {
        throw new UnsupportedOperationException();
    }
}
