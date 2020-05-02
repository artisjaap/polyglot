package be.artisjaap.crossword.crosswordengine;


import org.junit.Test;

public class DynamicGridTest {
    
    @Test
    public void emptyDynamicGrid() {
        DynamicGrid.createGrid().prettyPrint();
    }

    @Test
    public void emptyWithWord_topLeft() {
        DynamicGrid grid = DynamicGrid.createGrid();
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("X"), 0, 0);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("T"), -5, -5);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("E"), -5, -4);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("S"), -5, -3);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("T"), -5, -2);

        grid.prettyPrint();
    }


    @Test
    public void emptyWithWord_topRight() {
        DynamicGrid grid = DynamicGrid.createGrid();
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("X"), 0, 0);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("T"), -5, 0);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("E"), -5, 1);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("S"), -5, 2);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("T"), -5, 3);

        grid.prettyPrint();
    }

    @Test
    public void emptyWithWord_bottomRight() {
        DynamicGrid grid = DynamicGrid.createGrid();
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("X"), 0, 0);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("T"), 5, 0);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("E"), 5, 1);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("S"), 5, 2);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("T"), 5, 3);

        grid.prettyPrint();
    }

    @Test
    public void emptyWithWord_bottomLeft() {
        DynamicGrid grid = DynamicGrid.createGrid();
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("X"), 0, 0);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("T"), 5, -5);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("E"), 5, -4);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("S"), 5, -3);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("T"), 5, -2);

        grid.prettyPrint();
    }

    @Test
    public void emptyWithWord() {
        DynamicGrid grid = DynamicGrid.createGrid();
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("E"), -1, 0);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("X"), 0, 0);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("T"), 1, 0);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("O"), 1, 1);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("R"), 1, 2);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("E"), 1, 3);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("N"), 1, 4);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("R"), 2, 0);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("A"), 3, 0);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("T"), 5, -5);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("E"), 5, -4);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("S"), 5, -3);
        grid.putOnPosition(CrossTile.createEmpty().updateWithCharacter("T"), 5, -2);

        grid.prettyPrint();
        grid.prettyPrintColumn(0);
        grid.prettyPrintRow(0);
    }
}