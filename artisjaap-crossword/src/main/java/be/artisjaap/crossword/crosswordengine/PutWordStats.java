package be.artisjaap.crossword.crosswordengine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(staticName = "create")
@Getter
public class PutWordStats {
    private boolean canBePut = false;
    private List<PositionalCrossTile> tilesFiled = new ArrayList<>();


}
