package be.artisjaap.common.utils;

import java.util.List;

public class InfinitRandomDataStreamer<T> {

    private final List<T> dataset;
    private List<T> random;


    private InfinitRandomDataStreamer(List<T> data){
        this.dataset = data;
        this.random = ListUtils.shuffle(data);
    }

    public static <T> InfinitRandomDataStreamer fromDataList(List<T> data) {
        return new InfinitRandomDataStreamer(data);
    }

    public T next() {
        if( this.random.isEmpty()){
            if(dataset.isEmpty()){
                throw new IllegalStateException("No values found");
            }
            this.random = ListUtils.shuffle(dataset);
        }
        return random.remove(0);
    }
}
