package be.artisjaap.schedule.action.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.schedule.action.to.TaakJournalTO;
import be.artisjaap.schedule.model.TaakJournal;
import org.springframework.stereotype.Component;

@Component
public class TaakJournaalAssembler implements Assembler<TaakJournalTO, TaakJournal> {

	@Override
    public TaakJournal assembleEntity(TaakJournalTO t) {
		throw new UnsupportedOperationException("kan niet van TO naar TaakJournaal");
	}

	@Override
	public TaakJournalTO assembleTO(TaakJournal e) {
		return TaakJournalTO.newBuilder()
		.uitgevoerdOp(e.getUitgevoerdOp())
		.tijdGelopen(e.getTijdGelopen())
		.messages(e.getMessages())
		.specifiekeConfig(e.getSpecifiekeConfig())
		.build();
	}

}
