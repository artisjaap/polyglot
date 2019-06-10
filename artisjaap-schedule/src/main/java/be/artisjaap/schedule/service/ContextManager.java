package be.artisjaap.schedule.service;


import com.mongodb.session.SessionContext;

public interface ContextManager {

	boolean isContextSet();

	SessionContext getSessionContext();

	void setContext(SessionContext sessionContext);

	void unbindContext();

}
