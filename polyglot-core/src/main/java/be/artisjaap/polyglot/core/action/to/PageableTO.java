package be.artisjaap.polyglot.core.action.to;

public class PageableTO {
    private Integer page;
    private Integer pageSize;

    public Integer page() {
        return page;
    }

    public Integer pageSize() {
        return pageSize;
    }

    protected void buildCommon(AbstractBuilder builder) {
        page = builder.page;
        pageSize = builder.pageSize;
    }

    public static class AbstractBuilder<T> {
        private Integer page = 0;
        private Integer pageSize = 10;


        public T withPage(Integer val) {
            page = val;
            return (T)this;
        }

        public T withPageSize(Integer val) {
            pageSize = val;
            return (T)this;
        }

    }
}
