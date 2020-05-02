package be.artisjaap.crossword.crosswordengine;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CrosswordFieldTest {

    @Test
    public void testGrid(){
        CrosswordField grid = CrosswordField.init();
        grid.putWord("HELLO", StartPosition.create(0, 0, Direction.HORIZONTAL));
        grid.putWord("COOL", StartPosition.create(4, -2, Direction.HORIZONTAL));
        grid.putWord("HELLO", StartPosition.create(0, 0, Direction.VERTICAL));
        grid.putWord("INVALID", StartPosition.create(-2, 4, Direction.VERTICAL));

        grid.printGrid();
    }
}