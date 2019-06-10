package be.artisjaap.schedule.service;

import com.mongodb.session.SessionContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;



@Service("sessionAwareExecutor")
@Lazy(true)
public class SessionAwareExecutor implements ExecutorService {
    private static final Logger LOGGER = LogManager.getLogger();

	@Resource
	private ContextManager contextManager;

	@Value("${threading.executorservice.numberofthreads:2}")
	private int numberOfThreads;

	private ScheduledExecutorService delegatorService;

	public static SessionAwareExecutor createExecutor(int numberOfThreads, ContextManager contextManager) {
		SessionAwareExecutor executor = new SessionAwareExecutor();
		executor.setNumberOfThreads(numberOfThreads);
		executor.setContextManager(contextManager);
		executor.initializeExecutorService();
		return executor;
	}

	@PostConstruct
	void initializeExecutorService() {
		delegatorService = Executors.newScheduledThreadPool(numberOfThreads);
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		return delegatorService.awaitTermination(timeout, unit);
	}

	@Override
	public void execute(Runnable command) {
		delegatorService.execute(new SessionAwareRunnable(command));
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
			throws InterruptedException {
		Collection<? extends Callable<T>> convertedTasks = convertCollection(tasks);
		return delegatorService.invokeAll(convertedTasks, timeout, unit);
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
		Collection<? extends Callable<T>> convertedTasks = convertCollection(tasks);
		return delegatorService.invokeAll(convertedTasks);
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
			throws InterruptedException, ExecutionException, TimeoutException {
		Collection<? extends Callable<T>> convertedTasks = convertCollection(tasks);
		return delegatorService.invokeAny(convertedTasks, timeout, unit);
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
		Collection<? extends Callable<T>> convertedTasks = convertCollection(tasks);
		return delegatorService.invokeAny(convertedTasks);
	}

	@Override
	public boolean isShutdown() {
		return delegatorService.isShutdown();
	}

	@Override
	public boolean isTerminated() {
		return delegatorService.isTerminated();
	}

	@Override
	public void shutdown() {
		delegatorService.shutdown();
	}

	@Override
	public List<Runnable> shutdownNow() {
		return delegatorService.shutdownNow();
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		SessionAwareCallable<T> sessionAwareCallable = new SessionAwareCallable<T>(task);
		return delegatorService.submit(sessionAwareCallable);
	}

	@Override
	public <T> Future<T> submit(Runnable task, T result) {
		return delegatorService.submit(new SessionAwareRunnable(task), result);
	}

	@Override
	public Future<?> submit(Runnable task) {
		return delegatorService.submit(new SessionAwareRunnable(task));
	}

	private <T> Collection<? extends Callable<T>> convertCollection(Collection<? extends Callable<T>> originalCollection) {
		Collection<SessionAwareCallable<T>> convertedCollection = new ArrayList<SessionAwareCallable<T>>();
		for (Callable<T> callable : originalCollection) {
			convertedCollection.add(new SessionAwareCallable<T>(callable));
		}
		return convertedCollection;
	}

	void setDelegatorService(ScheduledExecutorService delegatorService) {
		this.delegatorService = delegatorService;
	}

	ScheduledExecutorService getDelegatorService() {
		return delegatorService;
	}

	public void setContextManager(ContextManager contextManager) {
		this.contextManager = contextManager;
	}

	void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
	}

	class SessionAwareRunnable implements Runnable {
		private final SessionContext sessionContext;
		private final Runnable originalRunnable;
		//FIXME private final SecurityContext findSessionData;

		SessionAwareRunnable(Runnable originalRunnable) {
			this.originalRunnable = originalRunnable;
			//FIXME this.findSessionData = WebUtils.getSessionContext();
			if (contextManager.isContextSet()) {
				this.sessionContext = contextManager.getSessionContext();
			} else {
				sessionContext = null;
			}
		}

		@Override
		public void run() {
			try {
					//FIXME WebUtils.setSessionContext(findSessionData);
				originalRunnable.run();
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			} finally {
				contextManager.unbindContext();
			}

		}
	}

	class SessionAwareCallable<V> implements Callable<V> {
		private final SessionContext sessionContext;
		private final Callable<V> originalCallable;

		SessionAwareCallable(Callable<V> originalCallable) {
			this.originalCallable = originalCallable;
			if (contextManager.isContextSet()) {
				this.sessionContext = contextManager.getSessionContext();
			} else {
				sessionContext = null;
			}
		}

		@Override
		public V call() throws Exception {
			try {
				if (sessionContext != null) {
					contextManager.setContext(sessionContext);
				}
				return originalCallable.call();
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw e;
			} finally {
				contextManager.unbindContext();
			}
		}
	}

}