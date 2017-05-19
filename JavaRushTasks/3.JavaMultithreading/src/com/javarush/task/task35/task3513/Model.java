package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NazarenkoDS on 19.05.2017.
 */
public class Model {

    private final static int FIELD_WIDTH = 4;
    private Tile[][] gameTiles= new Tile[FIELD_WIDTH][FIELD_WIDTH];

    public Model() {
        resetGameTiles();
    }

    public void resetGameTiles(){
        for(int i=0;i<gameTiles.length;i++)
            for(int j=0;j<gameTiles[i].length;j++)
                gameTiles[i][j] = new Tile();
        addTile();
        addTile();
    }

    private void addTile(){
        ArrayList<Tile> emptyTiles = getEmptyTiles();
        if (!emptyTiles.isEmpty()) {
            int randomTileIndex = (int) (Math.random() * emptyTiles.size());
            emptyTiles.get(randomTileIndex).value = (Math.random() < 0.9) ? 2 : 4;
        }
    }

    public ArrayList<Tile> getEmptyTiles(){
        ArrayList<Tile>result = new ArrayList<>();
        for(int i=0;i<gameTiles.length;i++)
            for(int j=0;j<gameTiles[i].length;j++)
                if(gameTiles[i][j].value == 0) result.add(gameTiles[i][j]);
        return result;
    }
}
