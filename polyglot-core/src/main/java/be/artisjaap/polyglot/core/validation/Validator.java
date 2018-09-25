package be.artisjaap.polyglot.core.validation;

import java.util.HashSet;
import java.util.Set;

import be.artisjaap.polyglot.core.model.Referenceable;
import be.artisjaap.polyglot.core.validators.ValidatorRecord;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public abstract class Validator<T extends Referenceable> {

	public void validateUpsert(T entity) {
        if (entity.isNieuw()) {
			validateInsert(entity);
		} else {
			validateUpdate(entity);
		}
	}

	public void validateInsert(T entity) {
		Set<ValidationMessage> errorMessages = DefaultAnnotationValidator.listErrors(entity);
		validateInsert(entity, errorMessages);

		if (!errorMessages.isEmpty()) {
			throw new ValidationException(errorMessages);
		}
	}

	public void validateUpdate(T entity) {
		T old = getEntityRepository().findById(entity.getId()).orElseThrow(() -> new ValidationException("Original entity not found"));
		Set<ValidationMessage> errorMessages =  DefaultAnnotationValidator.listErrors(entity);
		validateUpdate(old, entity, errorMessages);

		if (!errorMessages.isEmpty()) {
			throw new ValidationException(errorMessages);
		}
	}

	public void validateDelete(T entity) {
		Set<ValidationMessage> errorMessages = new HashSet<ValidationMessage>();
		validateDelete(entity, errorMessages);

		if (!errorMessages.isEmpty()) {
			throw new ValidationException(errorMessages);
		}
	}

	protected abstract void validateUpdate(T oldValue, T newValue, Set<ValidationMessage> errorMessages);

	protected abstract void validateDelete(T entity, Set<ValidationMessage> errorMessages);

	protected abstract void validateInsert(T entity, Set<ValidationMessage> errorMessages);

	protected abstract MongoRepository<T, ObjectId> getEntityRepository();

	protected void newMessage(Set<ValidationMessage> errorMessages, ValidatorRecord validatorRecord){
		errorMessages.add(new ValidationMessage(validatorRecord.key(), validatorRecord.value()));
	}

	protected void validateNotEmpty(Object object, String property, Set<ValidationMessage> errorMessages) {
		if (object == null || object.toString().trim().equals("")) {
			errorMessages.add(new ValidationMessage(property, "required"));
		}
	}


}