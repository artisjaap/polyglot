package be.artisjaap.polyglot.core.action.to;

import be.artisjaap.common.action.Assembler;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class PagedTO<T> {
    private Integer page;
    private Integer pageSize;
    private Integer numberOfPages;
    private Boolean lastPage;
    private List<T> data;

    public Integer page() {
        return page;
    }

    public Integer pageSize() {
        return pageSize;
    }

    public Integer numberOfPages() {
        return numberOfPages;
    }

    public Boolean lastPage() {
        return lastPage;
    }

    public List<T> data() {
        return data;
    }

    private PagedTO(Builder builder) {
        page = builder.page;
        pageSize = builder.pageSize;
        numberOfPages = builder.numberOfPages;
        data = builder.data;
        lastPage = builder.lastPage;
    }

    public static <T,D> PagedTO<T> from(Page<D> page, Assembler<T, D> assembler){
        return newBuilder()
                .withNumberOfPages(page.getTotalPages())
                .withPage(page.getNumber())
                .withPageSize(page.getSize())
                .withData(page.stream().map(assembler::assembleTO).collect(Collectors.toList()))
                .withLastPage(page.getNumber()+1 == page.getTotalPages() || page.getTotalPages() == 0)
                .build();


    }

    public static <T,D> PagedTO<T> from(Page<D> page, List<T> data){
        return newBuilder()
                .withNumberOfPages(page.getTotalPages())
                .withPage(page.getNumber())
                .withPageSize(page.getSize())
                .withData(data)
                .withLastPage(page.getNumber()+1 == page.getTotalPages() || page.getTotalPages() == 0)
                .build();


    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static class Builder<T> {
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

        public Builder withData(List<T> collect) {
            this.data = collect;
            return this;
        }

        public Builder withLastPage(Boolean val) {
            this.lastPage = val;
            return this;
        }

        public PagedTO build() {
            return new PagedTO(this);
        }

    }
}
