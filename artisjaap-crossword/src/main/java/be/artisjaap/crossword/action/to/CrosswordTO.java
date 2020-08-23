package be.artisjaap.crossword.action.to;

import lombok.Builder;
import lombok.Value;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Value
@Builder
public class CrosswordTO {

    @Builder.Default
    private List<List<Character>> crossword = new ArrayList<>();
    
    public void prettyPrint() {
        System.out.println("GENERATED:");
        
        crossword.forEach(row -> {
            row.forEach(System.out::print);
            System.out.println();
        });
    }
}
