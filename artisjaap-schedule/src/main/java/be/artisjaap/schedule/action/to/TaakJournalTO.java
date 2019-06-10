package be.artisjaap.schedule.action.to;

import be.artisjaap.schedule.model.TaakConfig;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaakJournalTO {
    private Date uitgevoerdOp;
	private Long tijdGelopen;
	private List<String> messages = new ArrayList<>();
	private TaakConfig specifiekeConfig;
	
	
	
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

	private TaakJournalTO(Builder builder) {
		uitgevoerdOp = builder.uitgevoerdOp;
		tijdGelopen = builder.tijdGelopen;
		messages = builder.messages;
		specifiekeConfig = builder.specifiekeConfig;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static Builder newBuilder(TaakJournalTO copy) {
		Builder builder = new Builder();
		builder.uitgevoerdOp = copy.uitgevoerdOp;
		builder.tijdGelopen = copy.tijdGelopen;
		builder.messages = copy.messages;
		builder.specifiekeConfig = copy.specifiekeConfig;
		return builder;
	}

	public static final class Builder {
		private Date uitgevoerdOp;
		private Long tijdGelopen;
		private List<String> messages;
		private TaakConfig specifiekeConfig;

		private Builder() {
		}

		public Builder uitgevoerdOp(Date val) {
			uitgevoerdOp = val;
			return this;
		}

		public Builder tijdGelopen(Long val) {
			tijdGelopen = val;
			return this;
		}

		public Builder messages(List<String> val) {
			messages = val;
			return this;
		}

		public Builder specifiekeConfig(TaakConfig val) {
			specifiekeConfig = val;
			return this;
		}

		public TaakJournalTO build() {
			return new TaakJournalTO(this);
		}
	}

	
}
