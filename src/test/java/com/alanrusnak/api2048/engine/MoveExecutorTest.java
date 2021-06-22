package com.alanrusnak.api2048.engine;

import com.alanrusnak.api2048.engine.model.MoveResult;
import com.alanrusnak.api2048.engine.model.Tile;
import org.junit.Assert;
import org.junit.Test;

public class MoveExecutorTest {

    MoveExecutor moveExecutor = new MoveExecutor();

    @Test
    public void testSlideTile0Spaces(){
        Tile[] row = row(0,0,0,2);
        moveExecutor.slideRight(row);
        assertRow(row, 0,0,0,2);
    }

    @Test
    public void testSlideTile1Spaces(){
        Tile[] row = row(0,0,2,0);
        moveExecutor.slideRight(row);
        assertRow(row, 0,0,0,2);
    }

    @Test
    public void testSlideTile2Spaces(){
        Tile[] row = row(0,2,0,0);
        moveExecutor.slideRight(row);
        assertRow(row, 0,0,0,2);
    }

    @Test
    public void testSlideTile3Spaces(){
        Tile[] row = row(2,0,0,0);
        moveExecutor.slideRight(row);
        assertRow(row, 0,0,0,2);
    }

    @Test
    public void testSlideTileWithSpaceTaken(){
        Tile[] row = row(2,0,0,4);
        moveExecutor.slideRight(row);
        assertRow(row, 0,0,2,4);
    }

    @Test
    public void testMergeTwoTiles(){
        Tile[] row = row(2,0,0,2);
        moveExecutor.slideRight(row);
        assertRow(row, 0,0,0,4);
    }

    @Test
    public void testMergeTwoTilesWithOneOnRight(){
        Tile[] row = row(2,0,2,4);
        moveExecutor.slideRight(row);
        assertRow(row, 0,0,4,4);
    }

    @Test
    public void testMergeTwoTilesWithBiggerOneOnRight(){
        Tile[] row = row(2,8,8,16);
        moveExecutor.slideRight(row);
        assertRow(row, 0,2,16,16);
    }

    @Test
    public void testMergeTwoTilesWithOneOnLeft(){
        Tile[] row = row(8,0,4,4);
        moveExecutor.slideRight(row);
        assertRow(row, 0,0,8,8);
    }

    @Test
    public void testMergeFourTiles(){
        Tile[] row = row(2,2,2,2);
        moveExecutor.slideRight(row);
        assertRow(row, 0,0,4,4);
    }

    @Test
    public void testWasTileMovedFalse(){
        Tile[] row = row(0,0,0,2);
        MoveResult result = moveExecutor.slideRight(row);
        Assert.assertFalse(result.wasTileMoved());
    }

    @Test
    public void testWasTileMovedFalseWithTwoTiles(){
        Tile[] row = row(0,0,4,2);
        MoveResult result = moveExecutor.slideRight(row);
        Assert.assertFalse(result.wasTileMoved());
    }

    @Test
    public void testWasTileMovedTrue(){
        Tile[] row = row(0,2,0,0);
        MoveResult result = moveExecutor.slideRight(row);
        Assert.assertTrue(result.wasTileMoved());
    }

    @Test
    public void testWasTileMovedTrueOnMerge(){
        Tile[] row = row(0,0,2,2);
        MoveResult result = moveExecutor.slideRight(row);
        Assert.assertTrue(result.wasTileMoved());
    }

    @Test
    public void testZeroScoreOnNoMerges(){
        Tile[] row = row(2,0,0,0);
        MoveResult result = moveExecutor.slideRight(row);
        Assert.assertEquals(0, result.getScore());
    }

    @Test
    public void testScoreOnMerge(){
        Tile[] row = row(2,2,4,4);
        MoveResult result = moveExecutor.slideRight(row);
        Assert.assertEquals(12, result.getScore());
    }

    private Tile[] row(int t0, int t1, int t2, int t3){
        return new Tile[]{new Tile(t0), new Tile(t1), new Tile(t2), new Tile(t3)};
    }

    private void assertRow(Tile[] row, int t0, int t1, int t2, int t3){
        Assert.assertEquals(t0, row[0].getValue());
        Assert.assertEquals(t1, row[1].getValue());
        Assert.assertEquals(t2, row[2].getValue());
        Assert.assertEquals(t3, row[3].getValue());
    }
}
