package be.artisjaap.document.model;

import org.bson.types.ObjectId;

public interface Referenceable {
	ObjectId getId();

	default boolean isNieuw() {
		return getId() == null;
	}
}
