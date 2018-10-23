package be.artisjaap.polyglot.web.endpoints.request;

public abstract class PageableFilter {
    private Integer pageSize;
    private Integer pageNumber;



    protected void buildCommon(AbstractBuilder<?> builder) {
        pageSize = builder.pageSize;
        pageNumber = builder.pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }


    public static class AbstractBuilder<T> {
        private Integer pageSize;
        private Integer pageNumber;

        public T withPageSize(Integer val) {
            pageSize = val;
            return (T)this;
        }

        public T withPageNumber(Integer val) {
            pageNumber = val;
            return (T)this;
        }

    }
}
