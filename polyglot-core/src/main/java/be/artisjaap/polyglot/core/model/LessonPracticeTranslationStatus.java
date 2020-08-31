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
            return customPercentage(5) + 10;
        }
        return customPercentage(5);
    }
    private int customPercentage(int minAsked) {
        return correct * 100 / Math.max(minAsked, asked);
    }
    
    public int percentage() {
        if(correct == 0){
            return 0;
        }
        return correct * 100 / asked;
    }
    
    public void updateWith(boolean correct){
        asked++;
        if(correct){
            this.correct++;
        }

        updateStatus();
    }

    private void updateStatus() {
        if(asked >= 8 && percentage() > 70){
            setStatus(ProgressStatus.KNOWN);
        }
    }

    public String print(){
        return String.format("%s[%s] - %s/%s - (%s/%s) ", translationId, status, asked, correct, percentage(), knowledgeStatus());
    }
    
}
