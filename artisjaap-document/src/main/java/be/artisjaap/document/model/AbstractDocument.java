package be.artisjaap.document.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

public abstract class AbstractDocument extends ModelObject implements Serializable, Referenceable {

	private static final long serialVersionUID = 7168501184569640497L;

	@Id
	private ObjectId id;

	private LocalDateTime timeStamp;

	void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	@Override
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	protected void buildCommon(AbstractBuilder<?> builder){
		id = builder.id.orElse(null);
		timeStamp = builder.timeStamp;
	}

	public static abstract class AbstractBuilder<T> {
		protected Optional<ObjectId> id = Optional.empty();

		protected LocalDateTime timeStamp = LocalDateTime.now();

		public Optional<ObjectId> getId() {
			return id;
		}

		public T id(Optional<ObjectId> id){
			this.id = id;
			return (T)this;
		}

		public T id(ObjectId id){
			this.id = Optional.of(id);
			return (T)this;
		}

		public T id(String id){
			this.id = id==null?Optional.empty():Optional.of(new ObjectId(id));
			return (T)this;
		}

		public T timeStamp(LocalDateTime val){
			this.timeStamp = val;
			return (T)this;
		}



	}
}