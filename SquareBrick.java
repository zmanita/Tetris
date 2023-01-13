/*
CSCI 282 | Semister Project "Tetris"
Author : Zarin Manita
Date: Jan 09 2021

 */
public class SquareBrick extends TetrisBrick{
    
    
    
    public SquareBrick(){
        
        super();
        colorNum = 4;
        initPosition();
        orientation = 0;
    }
    
    @Override
    public void initPosition() {
        
        
        position = new int[numSegment][2];
        
        position[0][0] = 5;
        position[0][1] = 0;
        
        position[1][0] = 6;
        position[1][1] = 0;
        
        position[2][0] = 5;
        position[2][1] = 1;
        
        position[3][0] = 6;
        position[3][1] = 1;
        
    }
    
    @Override
    public void rotate() {
        
    }
    @Override
    public void unrotate() {
       
    }
}
