package be.artisjaap.schedule.service;

import com.mongodb.session.SessionContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("contextManager")
public class ContextManagerImpl implements ContextManager {

	private static final Logger LOGGER = LogManager.getLogger();



	private static ThreadLocal<SessionContext> sessionContext = new ThreadLocal<SessionContext>();

	public ContextManagerImpl() {

	}

	@Override
	public boolean isContextSet() {
		return false;
	}

	@Override
	public SessionContext getSessionContext() {
		return null;
	}

	@Override
	public void setContext(SessionContext sessionContext) {

	}

	@Override
	public void unbindContext() {
	}

}
