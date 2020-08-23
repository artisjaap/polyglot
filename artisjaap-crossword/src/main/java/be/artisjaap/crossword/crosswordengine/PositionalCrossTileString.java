package be.artisjaap.crossword.crosswordengine;

import lombok.Getter;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PositionalCrossTileString {
    private final List<PositionalCrossTile> tiles;
    private final StartPosition startPosition;
    
    public String stringToMatch() {
        return tiles.stream().map(PositionalCrossTile::crossTile).map(CrossTile::containgCharacter).collect(Collectors.joining());
    }
    
    public StartPosition startPositionWithOffset(int offset){
        if(startPosition.direction() == Direction.HORIZONTAL){
            return StartPosition.create(startPosition.row(), startPosition.column()-offset, startPosition.direction());
        }else {
            return StartPosition.create(startPosition.row()-offset, startPosition.column(), startPosition.direction());
        }
    }
    
    private PositionalCrossTileString( List<PositionalCrossTile> tiles, Direction direction){
        this.tiles = tiles;
        this.startPosition = calcucateStartPosition(tiles, direction);
    }

    private StartPosition calcucateStartPosition(List<PositionalCrossTile> tiles, Direction direction) {
        PositionalCrossTile firstTile = tiles.get(0);
        return StartPosition.create(firstTile.row(), firstTile.column(), direction);
       
    }
    
    public final static PositionalCrossTileStringBuilder createEmpty() {
        return new  PositionalCrossTileStringBuilder();
    }

    public final static PositionalCrossTileStringBuilder createNew(PositionalCrossTile firstTile, Direction direction){
        return new PositionalCrossTileStringBuilder(firstTile, direction);
    }
    
    static class PositionalCrossTileStringBuilder {
        private List<PositionalCrossTile> tiles = new ArrayList<>();
        private Direction direction;

        public boolean isComplete(){
            return !tiles.isEmpty() && direction != null;
        }

        public PositionalCrossTileStringBuilder() {
        }
        
        public PositionalCrossTileStringBuilder setHorizontal(){
            this.direction = Direction.HORIZONTAL;
            return this;
        }

        public PositionalCrossTileStringBuilder setVertical(){
            this.direction = Direction.VERTICAL;
            return this;
        }

        public PositionalCrossTileStringBuilder(PositionalCrossTile firstTile, Direction direction){
            this.tiles.add(firstTile);
            this.direction = direction;
                    
        }
        
        public PositionalCrossTileStringBuilder  addTile(PositionalCrossTile tile){
            tiles.add(tile);
            return this;
        }
        
        public PositionalCrossTileString create() {
            return new PositionalCrossTileString(tiles, direction);
        }
        

    }
}
