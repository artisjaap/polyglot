package be.artisjaap.document.api.filegeneratie;

import be.artisjaap.document.api.DatasetProvider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GenericFileName implements FileName {

    private List<FilenamePart> filenameParts;
    private Boolean inUppercase;

    private GenericFileName(Builder builder) {
        filenameParts = builder.filenameParts;
        inUppercase = builder.inUppercase;
    }

    public static GenericFileName.Builder newBuilder() {
        return new Builder();
    }


    @Override
    public String filename(DatasetProvider datasetProvider) {
        String filename = filenameParts.stream().map(part -> findPart(part, datasetProvider)).map(Object::toString).collect(Collectors.joining("_"));
        return inUppercase ? filename.toUpperCase() : filename;
    }

    private Object findPart(FilenamePart filenamePart, DatasetProvider datasetProvider){
        Object dataSet = datasetProvider.get(filenamePart.dataset).data();

        return findValueIn(dataSet, filenamePart.property);
    }

    private Object findValueIn(Object dataset, String field){
        Class<?> clazz = dataset.getClass();
        try {
            Method method = clazz.getMethod(getterMethodFrom(field));
            return method.invoke(dataset);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String getterMethodFrom(String field){
        String first = field.substring(0, 1);
        String rest = field.substring(1);
        return "get" + first.toUpperCase() + rest;
    }

    static class FilenamePart {
        private String dataset;
        private String property;

        Pattern p = Pattern.compile("^([^\\.]+)\\.(.+)$");

        public FilenamePart(String property) {
            createFilepart(property);
        }

        private void createFilepart(String property) {
            Matcher matcher = p.matcher(property);
            if(matcher.find()){
                this.dataset = matcher.group(1);
                this.property = matcher.group(2);
            }else {

                throw new UnsupportedOperationException("property onjuist: " + property);
            }
        }
    }

    public static final class Builder {
        private List<FilenamePart> filenameParts = new ArrayList<>();
        private Boolean inUppercase = Boolean.TRUE;

        private Builder() {
        }

        public Builder withFilenameParts(String part) {
            this.filenameParts.add(new FilenamePart(part));
            return this;
        }

        public Builder withInUppercase(Boolean val) {
            this.inUppercase = val;
            return this;
        }


        public GenericFileName build() {
            return new GenericFileName(this);
        }
    }
}
