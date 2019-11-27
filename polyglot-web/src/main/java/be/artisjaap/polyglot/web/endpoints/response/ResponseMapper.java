package be.artisjaap.polyglot.web.endpoints.response;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface ResponseMapper<TO, RESPONSE> {

    RESPONSE map(TO to);

    default List<RESPONSE> mapToResponse(Collection<TO> to){
        return to.stream().map(this::map).collect(Collectors.toList());
    }
}
