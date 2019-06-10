package be.artisjaap.schedule.action.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.schedule.action.to.TaakPropertyEntryTO;
import be.artisjaap.schedule.enums.PropertyType;
import be.artisjaap.schedule.model.TaakParameterEntry;
import org.springframework.stereotype.Component;

@Component
public class TaakParameterEntryAssembler implements Assembler<TaakPropertyEntryTO, TaakParameterEntry> {

	@Override
    public TaakParameterEntry assembleEntity(TaakPropertyEntryTO t) {
		return TaakParameterEntry.from(t.getKey(), t.getType().convert(t.getValue()));
	}

	@Override
	public TaakPropertyEntryTO assembleTO(TaakParameterEntry e) {
		return TaakPropertyEntryTO.newBuilder()
		.key(e.getKey())
		.propertyType(PropertyType.typeOf(e.getValue()))
		.value(e.getValue().toString())
		.build();
				
	}

}

