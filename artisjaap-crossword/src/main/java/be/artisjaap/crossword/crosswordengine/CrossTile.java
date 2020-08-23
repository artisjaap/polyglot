package be.artisjaap.crossword.crosswordengine;

public class CrossTile {
    
    private CrossTileType crossTileType;
    private String containgCharacter;
    
    
    private CrossTile(){
        this.crossTileType = CrossTileType.WHITE;
        this.containgCharacter = "";
    }

    public String containgCharacter() {
        return containgCharacter;
    }

    public static CrossTile createEmpty(){
        return new CrossTile();
    }

    public static CrossTile createBlack(){
        return new CrossTile().markAsBlack();
    }
    public static CrossTile createWithCharacter(String s) {
        return new CrossTile().updateWithCharacter(s);
    }
    
    public CrossTile markAsBlack(){
        this.crossTileType = CrossTileType.BLACK;
        return this;
    }

    public CrossTileType crossTileType() {
        return crossTileType;
    }

    public CrossTile updateWithCharacter(String containgCharacter){
        if(crossTileType != CrossTileType.WHITE){
            throw new IllegalStateException("cant put letter on block: " + this);
        }
        this.containgCharacter = containgCharacter;
        this.crossTileType = CrossTileType.FILLED;
        return this;
    }

    @Override
    public String toString() {
        return "CrossTile{" +
                "crossTileType=" + crossTileType +
                ", containgCharacter='" + containgCharacter + '\'' +
                '}';
    }

    public String print() {
        switch (crossTileType){
            case BLACK: return "#";
            case WHITE: return ".";
            case FILLED:
                return containgCharacter;
        }
        throw new IllegalStateException("cant print Tyle of type " + crossTileType);
    }

    public void update(CrossTile tile) {
        this.crossTileType = tile.crossTileType;
        this.containgCharacter = tile.containgCharacter;
    }
}
