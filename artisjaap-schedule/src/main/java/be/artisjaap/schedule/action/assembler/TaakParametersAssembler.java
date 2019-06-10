package be.artisjaap.schedule.action.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.schedule.action.to.TaakParametersTO;
import be.artisjaap.schedule.action.to.TaakPropertyEntryTO;
import be.artisjaap.schedule.model.TaakConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaakParametersAssembler implements Assembler<TaakParametersTO, TaakConfig> {

	@Autowired
    private TaakParameterEntryAssembler taakParameterEntryAssembler;
	
	@Override
	public TaakConfig assembleEntity(TaakParametersTO t) {
		throw new UnsupportedOperationException("Taak config parameters kunnen nog niet gewijzigd worden!");
	}

	@Override
	public TaakParametersTO assembleTO(TaakConfig e){
		throw new UnsupportedOperationException("Gebruik: assembleTO(TaakConfig e, String code) !");
	}
	
	public TaakParametersTO assembleTO(TaakConfig e, String code) {
		List<TaakPropertyEntryTO> properties = e.alleProperties().stream().map(taakParameterEntryAssembler::assembleTO).collect(Collectors.toList());
		
		return TaakParametersTO.newBuilder()
		.properties(properties)
		.code(code)
		.build();
	
	}

}
