package be.artisjaap.crossword.crosswordengine;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor(staticName = "create")
@Getter
public class NewWordPositionInfo {
    private String word;
    private List<PositionalCrossTile> positionsToBlank;
    private StartPosition newStartPosition;
    private List<String> wordsToPutBack;
}
