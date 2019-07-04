package be.artisjaap.schedule.web.endpoints.response;

import be.artisjaap.schedule.action.to.TaakJournalTO;
import be.artisjaap.schedule.model.TaakConfig;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TaskJournalResponse {
    private Date uitgevoerdOp;
    private Long tijdGelopen;
    private List<String> messages = new ArrayList<>();
    private TaakConfig specifiekeConfig;

    private TaskJournalResponse(Builder builder) {
        uitgevoerdOp = builder.uitgevoerdOp;
        tijdGelopen = builder.tijdGelopen;
        messages = builder.messages;
        specifiekeConfig = builder.specifiekeConfig;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static TaskJournalResponse from(TaakJournalTO taskJournalTO) {
        return TaskJournalResponse.newBuilder()
                .withMessages(taskJournalTO.getMessages())
                .withSpecifiekeConfig(taskJournalTO.getSpecifiekeConfig())
                .withTijdGelopen(taskJournalTO.getTijdGelopen())
                .withUitgevoerdOp(taskJournalTO.getTijdGelopen())
                .build()
    }

    public static List<TaskJournalResponse> from(List<TaakJournalTO taakJournals>){
        return taakJournals.stream().map(TaskJournalResponse::from).collect(Collectors.toList());
    }

    public Date getUitgevoerdOp() {
        return uitgevoerdOp;
    }

    public Long getTijdGelopen() {
        return tijdGelopen;
    }

    public List<String> getMessages() {
        return messages;
    }

    public TaakConfig getSpecifiekeConfig() {
        return specifiekeConfig;
    }

    public static final class Builder {
        private Date uitgevoerdOp;
        private Long tijdGelopen;
        private List<String> messages;
        private TaakConfig specifiekeConfig;

        private Builder() {
        }

        public Builder withUitgevoerdOp(Date uitgevoerdOp) {
            this.uitgevoerdOp = uitgevoerdOp;
            return this;
        }

        public Builder withTijdGelopen(Long tijdGelopen) {
            this.tijdGelopen = tijdGelopen;
            return this;
        }

        public Builder withMessages(List<String> messages) {
            this.messages = messages;
            return this;
        }

        public Builder withSpecifiekeConfig(TaakConfig specifiekeConfig) {
            this.specifiekeConfig = specifiekeConfig;
            return this;
        }

        public TaskJournalResponse build() {
            return new TaskJournalResponse(this);
        }
    }
}
