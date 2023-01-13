/*
CSCI 282 | Semister Project "Tetris"
Author : Zarin Manita
Date: Jan 09 2021

 */

import java.util.*;
public abstract class TetrisBrick {
    
    protected int[][] position;
    protected int orientation;
    protected int numSegment = 4;
    protected int colorNum;
    
    public void TetrisBrick() {
        
    }
    
    public void moveDown() {
        for (int seg = 0; seg<numSegment ; seg++)
        {
        
            position[seg][1]++;
        }
    }
    
    public void moveUp() {
        
        for (int seg = 0; seg<numSegment ; seg++)
        {
            position[seg][1]--;
        }
    }
    
    public int getPositionCol(int seg) {
       
        return position[seg][0];
        
    }
    
    public int getPositionRow(int seg) {
       
        return position[seg][1];
    }
    
    
    public int getColorNum() {
        
        return colorNum;
    }

    public int getNumSegment() {
        return numSegment;
    }
    
    public void moveLeft(){
        for (int seg = 0; seg<numSegment ; seg++)
        {
        position[seg][0]--;
        }
    }
    
    public void moveRight(){
        for (int seg = 0; seg<numSegment ; seg++)
        {
        position[seg][0]++;
        }
    }
    
    public int getMaxRow(){
        int seg_row0 = getPositionRow(0);
        int seg_row1 = getPositionRow(1);
        int seg_row2 = getPositionRow(2);
        int seg_row3 = getPositionRow(3);
        
        int[] seg_rows = {seg_row0, seg_row1, seg_row2, seg_row3};
        
        int maxRow = seg_rows[0];
        for(int i=1;i < seg_rows.length;i++){
            if(seg_rows[i] > maxRow){
                maxRow = seg_rows[i];
          }
    }
        return maxRow;
    }
    
    public int getMinRow(){
        int seg_row0 = getPositionRow(0);
        int seg_row1 = getPositionRow(1);
        int seg_row2 = getPositionRow(2);
        int seg_row3 = getPositionRow(3);
        
        int[] seg_rows = {seg_row0, seg_row1, seg_row2, seg_row3};
        
        int minRow = seg_rows[0];
        for(int i=1;i < seg_rows.length;i++){
            if(seg_rows[i] < minRow){
                minRow = seg_rows[i];
            }
        }
        return minRow;
        
    }
    public abstract void initPosition();
    public abstract void rotate();
    public abstract void unrotate();
    
}
