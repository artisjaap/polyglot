package be.artisjaap.polyglot.core.action.assembler;

import java.util.List;
import java.util.stream.Collectors;

public interface Assembler<TO, D> {

    TO assembleTO(D doc);

    D assembleEntity(TO to);

    default List<TO> assembleTOs(List<D> docs){
        return docs.stream().map(this::assembleTO).collect(Collectors.toList());
    }

    default List<D> assembleDocuments(List<TO> tos){
        return tos.stream().map(this::assembleEntity).collect(Collectors.toList());
    }
}
