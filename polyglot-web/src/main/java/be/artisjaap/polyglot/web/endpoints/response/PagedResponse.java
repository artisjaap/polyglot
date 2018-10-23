package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.PagedTO;

import java.util.List;
import java.util.stream.Collectors;

public class PagedResponse<T> {
    private Integer page;
    private Integer pageSize;
    private Integer numberOfPages;
    private Boolean lastPage;
    private List<T> data;

    public Integer getPage() {
        return page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public Boolean getLastPage() {
        return lastPage;
    }

    public List<T> getData() {
        return data;
    }

    private PagedResponse(Builder builder) {
        page = builder.page;
        pageSize = builder.pageSize;
        numberOfPages = builder.numberOfPages;
        lastPage = builder.lastPage;
        data = builder.data;
    }

    public static <TO, R> PagedResponse<R> from(PagedTO<TO> t, ResponseForTO<TO, R> data){
        return newBuilder()
                .withLastPage(t.lastPage())
                .withNumberOfPages(t.numberOfPages())
                .withPage(t.page())
                .withPageSize(t.pageSize())
                .withData(t.data().stream().map(data::from).collect(Collectors.toList()))
                .build();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder<T> {
        private Integer page;
        private Integer pageSize;
        private Integer numberOfPages;
        private Boolean lastPage;
        private List<T> data;

        private Builder() {
        }

        public Builder withPage(Integer val) {
            page = val;
            return this;
        }

        public Builder withPageSize(Integer val) {
            pageSize = val;
            return this;
        }

        public Builder withNumberOfPages(Integer val) {
            numberOfPages = val;
            return this;
        }

        public Builder withLastPage(Boolean val) {
            lastPage = val;
            return this;
        }

        public Builder withData(List<T> val) {
            data = val;
            return this;
        }

        public PagedResponse build() {
            return new PagedResponse<T>(this);
        }
    }
}
