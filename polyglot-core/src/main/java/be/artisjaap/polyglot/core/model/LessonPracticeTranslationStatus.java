package be.artisjaap.polyglot.core.model;

import lombok.Data;
import lombok.Builder;
import org.bson.types.ObjectId;

@Data
@Builder
public class LessonPracticeTranslationStatus {
    
    private ObjectId translationId;
    private ProgressStatus status;
    @Builder.Default
    private int asked = 0;
    @Builder.Default
    private int correct = 0;
    
    public int knowledgeStatus() {
        if(percentage() > 70 && asked > 4){
            return percentage() + 10;
        }
        return percentage();
    }
    
    public int percentage() {
        if(correct == 0){
            return 0;
        }
        return asked * 100 / correct;
    }
    
    public void updateWith(boolean correct){
        asked++;
        if(correct){
            this.correct++;
        }
    }
    
    public String print(){
        return String.format("%s[%s] - %s/%s - (%s/%s) ", translationId, status, asked, correct, percentage(), knowledgeStatus());
    }
    
}
