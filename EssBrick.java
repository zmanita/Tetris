/*
CSCI 282 | Semister Project "Tetris"
Author : Zarin Manita
Date: Jan 09 2021

 */
public class EssBrick extends TetrisBrick{
    
    
    
    public EssBrick(){
        
        super();
        colorNum = 1;
        initPosition();
        orientation = 0;
        
    }
    
    @Override
    public void initPosition() {
        
        
        position = new int[numSegment][2];
        
        position[0][0] = 6;
        position[0][1] = 0;
        
        position[1][0] = 7;
        position[1][1] = 0;
        
        position[2][0] = 6;
        position[2][1] = 1;
        
        position[3][0] = 5;
        position[3][1] = 1;
        
    }
    
    @Override
    public void rotate() {
        
        int cent_row = position[2][1];
        int cent_col = position[2][0];
        
        if (orientation == 0)
        {
            position[0][0] = cent_col-1;
            position[0][1] = cent_row;

            position[1][0] = cent_col;
            position[1][1] = cent_row+1;

            position[2][0] = cent_col;
            position[2][1] = cent_row;

            position[3][0] = cent_col-1;
            position[3][1] = cent_row-1;
        }
        
        else if (orientation == 1)
        {
            position[0][0] = cent_col;
            position[0][1] = cent_row -1;

            position[1][0] = cent_col -1;
            position[1][1] = cent_row;

            position[2][0] = cent_col;
            position[2][1] = cent_row;

            position[3][0] = cent_col+1;
            position[3][1] = cent_row - 1;
        }
        
        else if (orientation == 2)
        {
            position[0][0] = cent_col -1;
            position[0][1] = cent_row;

            position[1][0] = cent_col;
            position[1][1] = cent_row-1;

            position[2][0] = cent_col;
            position[2][1] = cent_row ;

            position[3][0] = cent_col - 1;
            position[3][1] = cent_row +1;
        }
        
        else if (orientation == 3)
        {
            position[0][0] = cent_col;
            position[0][1] = cent_row- 1;

            position[1][0] = cent_col+1;
            position[1][1] = cent_row -1;

            position[2][0] = cent_col;
            position[2][1] = cent_row;

            position[3][0] = cent_col-1;
            position[3][1] = cent_row;
        }
        if (orientation < 3){
            
            orientation = orientation+1;
        }
        else if (orientation == 3){
            
            orientation = 0;
        }
        
    }
    
    @Override
    public void unrotate() {
       orientation = orientation-1; 
    }
    
}
