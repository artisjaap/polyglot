package be.artisjaap.document.action;

import be.artisjaap.document.action.to.BriefCodeTO;
import be.artisjaap.document.model.mongo.DocumentCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindDocumentCodes {
    @Autowired
    private DocumentCodeRepository documentCodeRepository;


    public List<BriefCodeTO> allDocuments() {
        return documentCodeRepository.findAll().stream()
                .map(brief -> BriefCodeTO.newBuilder()
                        .withCode(brief.getCode())
                        .withDescription(brief.getDescription())
                        .build())
                .collect(Collectors.toList());
    }


}

