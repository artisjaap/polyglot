package be.artisjaap.polyglot.core.action.lesson;

import be.artisjaap.polyglot.core.action.assembler.LessonHeaderAssembler;
import be.artisjaap.polyglot.core.action.to.LessonHeaderTO;
import be.artisjaap.polyglot.core.model.LessonRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindLessonHeader {
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private LessonHeaderAssembler lessonHeaderAssembler;

    public List<LessonHeaderTO> forUser(String userId){
        return lessonHeaderAssembler.assembleTOs(lessonRepository.findByUserId(new ObjectId(userId)));
    }

    public List<LessonHeaderTO> forLanguagePair(String languagePairId) {
        return lessonHeaderAssembler.assembleTOs(lessonRepository.findByLanguagePairId(new ObjectId(languagePairId)));
    }




}
