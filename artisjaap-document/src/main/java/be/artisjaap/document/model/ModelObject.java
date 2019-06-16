package be.artisjaap.document.model;

import java.lang.reflect.Field;

public abstract class ModelObject {
	
	@Override
    public String toString() {
        StringBuilder represenationBuilder = new StringBuilder();
        buildVisualRepresentation(this, 0, represenationBuilder);
        return represenationBuilder.toString();
    }

    private void buildVisualRepresentation(ModelObject valueObject, int valueObjectDepth,
            StringBuilder represenationBuilder) {
        addValueObjectNameToRepresentation(valueObject, represenationBuilder);
        addValueObjectFieldsToRepresentation(valueObject, valueObjectDepth, represenationBuilder);
    }

    private void addValueObjectNameToRepresentation(ModelObject valueObject, StringBuilder represenationBuilder) {
        represenationBuilder.append(valueObject.getClass().getSimpleName());
        represenationBuilder.append("\n");
    }

    private void addValueObjectFieldsToRepresentation(ModelObject valueObject, int valueObjectDepth,
            StringBuilder represenationBuilder) {
        int fieldDepth = valueObjectDepth + 1;
        String fieldDepthPrefix = repeatCharacter(' ', fieldDepth * 2);

        Class<?> clazz = valueObject.getClass();
        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            try {
                Object value = f.get(valueObject);
                represenationBuilder.append(fieldDepthPrefix);
                represenationBuilder.append(f.getName());
                represenationBuilder.append("=");

                if (ModelObject.class.isAssignableFrom(value.getClass())) {
                	buildVisualRepresentation((ModelObject) value, fieldDepth, represenationBuilder);
                } else  {
            	    String valueFieldString = value.toString();
                    String valueFieldFormattedString = formatValueFieldString(fieldDepthPrefix, valueFieldString);
                    represenationBuilder.append(valueFieldFormattedString);
                    represenationBuilder.append("\n");
                    
                    
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String repeatCharacter(char c, int depth) {
    	StringBuilder sb = new StringBuilder();
		for(int i = 0; i < depth; i++){
			sb.append(c);
		}
		return sb.toString();
	}

	private String formatValueFieldString(String fieldDepthPrefix, String valueFieldString) {
        if (valueFieldString == null) {
            return null;
        }
        // ---Specific formatting for ValueList fields
        return valueFieldString.replaceAll("\n", "\n" + fieldDepthPrefix);
    }

}
