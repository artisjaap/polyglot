package be.artisjaap.document.assembler;

import java.util.List;
import java.util.stream.Collectors;

public interface Assembler<E, T> {
	E assembleEntity(T t);
	
	T assembleTO(E e);
	
	default List<T> assembleTO(List<E> e){
		return e.stream().map(this::assembleTO).collect(Collectors.toList());
	}
	
	default List<E> assembleEntity(List<T> t){
		return t.stream().map(this::assembleEntity).collect(Collectors.toList());
	}
}
