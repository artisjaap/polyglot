package be.artisjaap.crossword.crosswordengine;

public class StartPosition {
    private int row;
    private int column;
    private Direction direction;
    
    private StartPosition(int row, int column, Direction direction){
        this.row = row;
        this.column = column;
        this.direction = direction;
                
    }
    
    public static StartPosition create(int row, int column, Direction direction){
        return new StartPosition(row, column, direction);
    }

    public static StartPosition center() {
        return create(0, 0, Direction.HORIZONTAL);
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

    public Direction direction() {
        return direction;
    }

    @Override
    public String toString() {
        return "["+ direction + ":" + row + "," + column + "]";
    }
}

