package be.artisjaap.core.validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DefaultAnnotationValidator {
    private final static Logger logger = LogManager.getLogger();

	public static Set<ValidationMessage> listErrors(Object entity) {
		
		Set<ValidationMessage> errorMessages = new HashSet<ValidationMessage>();
		annotationValidation(entity, errorMessages);
		return errorMessages;
	}
	
	public static void validate(Object entity) {
		Set<ValidationMessage> errorMessages = listErrors(entity);
		
		if (!errorMessages.isEmpty()) {
			logErrors(errorMessages);
			throw new ValidationException(errorMessages);
		}
	}
	
	private static void logErrors(Set<ValidationMessage> errorMessages) {
		errorMessages.forEach(error -> logger.info(error.getKey() + " - " + error.getMessage()));
		
	}

	private static void annotationValidation(Object entity, Set<ValidationMessage> errorMessages) {
		addDefaultValidations(entity, errorMessages);
	}
	public static void addDefaultValidations(Object entity, Set<ValidationMessage> errorMessages){
		addDefaultValidations(entity, errorMessages, new HashSet<Object>());
	}
	private static void addDefaultValidations(Object entity, Set<ValidationMessage> errorMessages, Set<Object> visited) {
		for (Field f : listAllFields(entity.getClass())) {
			if(!f.getType().isEnum() && f.getType().getName().startsWith("be.artisjaap")){
				try {
					String getMethod = berekenGetterMethod(f.getName());
					Method method = entity.getClass().getMethod(getMethod);
					Optional<Object> result = executeMethodObject(entity, method);
					if(result.isPresent()){
						if(visited.add(result.get())){
							addDefaultValidations(result.get(), errorMessages);
						}
					}
				} catch (NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				} 
			}
			validateRequired(entity, f, errorMessages);
			validateEqualsTo(entity, f, errorMessages);
			validateNotEqualsTo(entity, f, errorMessages);
			validateMatch(entity, f, errorMessages);
		}

	}
	
	private static List<Field> listAllFields(Class<?> clazz){
		List<Field> fields = new ArrayList<>();
		fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
		
		Class<?> superclass = clazz.getSuperclass();
		while(superclass != null){
			fields.addAll(Arrays.asList(superclass.getDeclaredFields()));
			superclass = superclass.getSuperclass();
		}
		return fields;
	}
	
	private static String methodToKey(String methodName) {
		return methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
	}
	
	private static String executeMethod(Object vo, Method method) {
		return executeMethodObject(vo, method).map(Object::toString).orElse("");
	}
	
	private static Optional<Object> executeMethodObject(Object vo, Method method) {
		try {
			return Optional.ofNullable(method.invoke(vo));
			
		} catch (SecurityException e) {
			throw new IllegalStateException(
					"Method " + method.getName() + " niet uitvoerbaar op " + vo.getClass().getCanonicalName());
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(
					"Method " + method.getName() + " niet beschikbaar op " + vo.getClass().getCanonicalName());
		} catch (IllegalArgumentException e) {
			throw new IllegalStateException("Method " + method.getName() + " niet met de juiste argumenten opgeroepen "
					+ vo.getClass().getCanonicalName());
		} catch (InvocationTargetException e) {
			throw new IllegalStateException(
					"Method " + method.getName() + " niet oproepbaar " + vo.getClass().getCanonicalName());
		}
	}
	
	private static String berekenGetterMethod(String field) {
		return "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
	}
	
	private static String executeMethod(Object vo, String getMethod) {
		try {
			Method method = vo.getClass().getMethod(getMethod);
			return executeMethod(vo, method);

		} catch (NoSuchMethodException e) {
			throw new IllegalStateException(
					"Method " + getMethod + " niet gevonden op " + vo.getClass().getCanonicalName());
		}

	}
	
	private static void validateRequired(Object vo, Field f, Set<ValidationMessage> errorMessages) {
		Required required = f.getAnnotation(Required.class);
		if (required != null) {
			String getMethod = berekenGetterMethod(f.getName());
			String result = executeMethod(vo, getMethod);
			if (!StringUtils.hasText(result)) {
				errorMessages.add(new ValidationMessage(f.getName(),
						!"".equals(required.errorKey()) ? required.errorKey() : "required"));
			}
		}
	}

	private static void validateMatch(Object vo, Field f, Set<ValidationMessage> errorMessages) {
		Match match = f.getAnnotation(Match.class);
		if (match != null) {
			String getMethod = berekenGetterMethod(f.getName());
			String result = executeMethod(vo, getMethod);

			String p = match.pattern();
			Pattern pattern = Pattern.compile(p);
			Matcher matcher = pattern.matcher(result);
			if (!matcher.find()) {
				errorMessages.add(
						new ValidationMessage(f.getName(), !"".equals(match.errorKey()) ? match.errorKey() : "match"));
			}
		}
	}

	private static void validateNotEqualsTo(Object vo, Field f, Set<ValidationMessage> errorMessages) {
		NotEqualsTo notEqualsTo = f.getAnnotation(NotEqualsTo.class);
		if (notEqualsTo != null) {
			String getMethod = berekenGetterMethod(f.getName());
			String getMethod2 = berekenGetterMethod(notEqualsTo.field());
			String result = executeMethod(vo, getMethod);
			String result2 = executeMethod(vo, getMethod2);
			if (result.equals(result2)) {
				errorMessages.add(new ValidationMessage(f.getName(),
						!"".equals(notEqualsTo.errorKey()) ? notEqualsTo.errorKey() : "equals"));
			}
		}
	}

	private static void validateEqualsTo(Object vo, Field f, Set<ValidationMessage> errorMessages) {
		EqualsTo equalsTo = f.getAnnotation(EqualsTo.class);
		if (equalsTo != null) {
			String getMethod = berekenGetterMethod(f.getName());
			String getMethod2 = berekenGetterMethod(equalsTo.field());
			String result = executeMethod(vo, getMethod);
			String result2 = executeMethod(vo, getMethod2);
			if (!result.equals(result2)) {
				errorMessages.add(new ValidationMessage(f.getName(),
						!"".equals(equalsTo.errorKey()) ? equalsTo.errorKey() : "equals"));
			}
		}
	}
}