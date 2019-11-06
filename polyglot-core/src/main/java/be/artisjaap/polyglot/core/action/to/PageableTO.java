package be.artisjaap.polyglot.core.action.to;

import java.util.ArrayList;
import java.util.List;

public class PageableTO {
    private Integer page;
    private Integer pageSize;
    private List<SortTO> orderByFields;


    public Integer page() {
        return page;
    }

    public Integer pageSize() {
        return pageSize;
    }

    public List<SortTO> getOrderByFields(){
        return orderByFields;
    }

    public boolean isOrderDefined() {
        return !orderByFields.isEmpty();
    }

    protected void buildCommon(AbstractBuilder builder) {
        page = builder.page;
        pageSize = builder.pageSize;
        orderByFields = builder.orderByFields;
    }

    public static class AbstractBuilder<T> {
        private Integer page = 0;
        private Integer pageSize = 10;
        private List<SortTO> orderByFields = new ArrayList<>();

        public T withPage(Integer val) {
            page = val;
            return (T)this;
        }

        public T withPageSize(Integer val) {
            pageSize = val;
            return (T)this;
        }

        public T withOrderByField(SortTO field){
            this.orderByFields.add(field);
            return (T) this;
        }

        public T withOrderByFields(List<SortTO> fields){
            this.orderByFields.addAll(fields);
            return (T) this;
        }

    }
}
