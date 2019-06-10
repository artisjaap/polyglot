package be.artisjaap.schedule.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TaakJournal {
    private Date uitgevoerdOp;
	private Long tijdGelopen;
	private List<String> messages = new ArrayList<>();
	private TaakConfig specifiekeConfig;
	
	public List<String> getMessages() {
		return Collections.unmodifiableList(messages);
	}
	public Date getUitgevoerdOp() {
		return uitgevoerdOp;
	}
	public void setUitgevoerdOp(Date uitgevoerdOp) {
		this.uitgevoerdOp = uitgevoerdOp;
	}
	public Long getTijdGelopen() {
		return tijdGelopen;
	}
	public void setTijdGelopen(Long tijdGelopen) {
		this.tijdGelopen = tijdGelopen;
	}
	public TaakConfig getSpecifiekeConfig() {
		return specifiekeConfig;
	}
	public void setSpecifiekeConfig(TaakConfig specifiekeConfig) {
		this.specifiekeConfig = specifiekeConfig;
	}
	
	private TaakJournal(){}
	
	private TaakJournal(Builder builder) {
		uitgevoerdOp = builder.uitgevoerdOp;
		tijdGelopen = builder.tijdGelopen;
		messages = builder.messages;
		specifiekeConfig = builder.specifiekeConfig;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static Builder newBuilder(TaakJournal copy) {
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
		private List<String> messages = new ArrayList<>();
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

		public TaakJournal build() {
			return new TaakJournal(this);
		}
	}

	
}
