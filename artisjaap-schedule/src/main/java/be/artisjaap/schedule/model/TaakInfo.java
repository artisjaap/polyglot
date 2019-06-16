package be.artisjaap.schedule.model;

import be.artisjaap.common.model.AbstractDocument;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection="taak_geplande_taken_info")
public class TaakInfo extends AbstractDocument {
	private String taakCode;
	private List<TaakJournal> journal = new ArrayList<>();
	private TaakConfig config = new TaakConfig();
	private NextRunInfo nextRunInfo = new NextRunInfo();

	protected TaakInfo() {

	}
	public TaakInfo(String taakCode){
		this.taakCode = taakCode;
	}

	public List<TaakJournal> getJournal() {
		return journal;
	}
	public void setJournal(List<TaakJournal> journal) {
		this.journal = journal;
	}
	public void addJournal(TaakJournal taakJournal){
		this.journal.add(taakJournal);
	}

	public TaakConfig getConfig() {
		return config;
	}
	public void setConfig(TaakConfig config) {
		this.config = config;
	}
	public String getTaakCode() {
		return taakCode;
	}
	public void setTaakCode(String taakCode) {
		this.taakCode = taakCode;
	}
	public NextRunInfo getNextRunInfo() {
		return nextRunInfo;
	}
	public void setNextRunInfo(NextRunInfo nextRunInfo) {
		this.nextRunInfo = nextRunInfo;
	}


}
