package be.artisjaap.crossword.crosswordengine;

public class PositionalCrossTile {
    private CrossTile crossTile;
    private int row;
    private int column;

    public PositionalCrossTile(CrossTile crossTile, int row, int column) {
        this.crossTile = crossTile;
        this.row = row;
        this.column = column;
    }
    
    public final static PositionalCrossTile forData(CrossTile crossTile, int row, int column){
        return new PositionalCrossTile(crossTile, row, column);
    }

    public CrossTile crossTile() {
        return crossTile;
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }
}
