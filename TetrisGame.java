/*
CSCI 282 | Semister Project "Tetris"
Author : Zarin Manita
Date: Jan 09 2021

 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.io.*;
import java.util.*;

public class TetrisGame {
    
    int[][] background;
    private int[][] board;
    Random randGen;
    
    int state;
    
    private int rows;
    private int cols;
    
    private TetrisBrick fallingBrick;
    int brickColorTest;
    private int brickType;
    
    private int game_continuing = 0;     //0 means game is going on, not paused; 
                                         //1 means game is paused
    int fullRowsNum;
    private int score;
    List<Integer> scores = new ArrayList<>();
    
    public TetrisGame(int rs, int cs)
    {
        rows = rs;
        cols = cs;
        board = new int [rows][cols];
        
        initBoard();
        spawnBrick();
    }
    
    public void initBoard(){
        
        for (int row = 0; row<board.length ; row++){
           for (int col = 0; col<board[0].length ; col++){
               board[row][col] = -1;
            }
        }
    
    }

    public void spawnBrick(){
         
        int toalBrickType = 7;
        
        randGen = new Random();
        brickType = randGen.nextInt(toalBrickType);
         
        switch(getBrickType()){
                        
                        case 0:
                            fallingBrick = new ElBrick();
                            break;
                        case 1:
                            fallingBrick = new EssBrick();
                            break;
                        case 2:
                            fallingBrick = new JayBrick();
                            break;
                        case 3:
                             fallingBrick = new LongBrick();
                             break;
                        case 4:
                            fallingBrick = new SquareBrick();
                            break;
                        case 5:
                            fallingBrick = new StackBrick();
                            break;
                        case 6: 
                            fallingBrick = new ZeeBrick();
                            break;
                } 
    }
    
    public void makeMove(String code){
        
        switch(code)
        {
            case "D":{
                fallingBrick.moveDown();
                
                if (validateMove() == false){
                
                getFallingBrick().moveUp();
                transferColor();
                fullRowDetect();
                if (gameOverDetection() == false){
                    spawnBrick();
                }
                }
                break;
                
            }
            case "Rotate":{
                fallingBrick.rotate();
                if (validateMove() == false){
                    getFallingBrick().unrotate();
                }
                break;
            }
            case "L":{
                fallingBrick.moveLeft();
                
                if (validateMove() == false){
                    getFallingBrick().moveRight();
                }
                break;
            }
            case "R":{
                fallingBrick.moveRight();
                
                if (validateMove() == false){
                    getFallingBrick().moveLeft();
                }
                break;
            }
            case "N":{
                newGame();
                break;
            }
            case "P":{
                pauseAndUnpauseGame();
                break;
            }
        }
    }
    
    public boolean validateMove(){
        
        
        for (int seg = 0; seg<fallingBrick.numSegment ; seg++)
        {
            int row = getSegmentRow(seg);
            int col = getSegmentCol(seg);
            
            int rows = getRows();
            int cols = getCols();
            
            if (row >= rows){
                
                return false;     //brick has not reached bottom yet
            }
            
            else if (col >= cols){
                
                return false;     //brick has reached left boundary
            }
            else if (col < 0){
                
                return false;     //brick has reached right boundary
            }
            
            else if (board[row][col] != -1){
                
                return false;     //board already has color
            }
            
        } 
        return true; 
    }
    
    public void transferColor(){
        
        for (int seg = 0; seg < getFallingBrick().getNumSegment(); seg++){

            int row = getSegmentRow(seg);
            int col = getSegmentCol(seg);

            board[row][col] = fallingBrick.getColorNum();
            }
    }
    
    public void pauseAndUnpauseGame(){
        if (getGame_continuing() == 0){
                    
                    transferColor();
                    game_continuing = 1;
                }
                else if (getGame_continuing() == 1){
                    
                    game_continuing = 0;
                    
                    for (int seg = 0; seg<4 ; seg++)
                    {
                        int row = getSegmentRow(seg);
                        int col = getSegmentCol(seg);
                        
                        board[row][col] = -1;
                    }
                    fallingBrick.moveDown();
                }
        
    }
    
    public void newGame(){
        initBoard();
        spawnBrick();
        score = 0;
    
    }
    
    public boolean gameOverDetection(){
        for (int col = 0; col < cols; col++){
            
            if(board[0][col] != -1){
               scores.add(score);
               scoreToFile();
               return true;      //game over
               
            }
        }
        return false;      //game not over
    }
    
    public String scoreToFile(){
        
        String text = "";
        Integer[] array = scores.toArray(new Integer[0]);
        for (int i = 0; i< 10; i++){
            int val = array[0];
            text += Integer.toString(val) + "\n";
        }
        String filename = "leader_board";
        File outfile = new File(filename);
        if (outfile.exists() && !outfile.canWrite()){
            String err_message = "!!!!!!!!!!!!!!!!!!!!!!!!!!!"+
                                 "Trouble opening data to write "+filename+
                                "\nThis program is ending.";
            System.err.print(err_message);
        }
        try{
            FileWriter outWriter = new FileWriter(outfile);
            outWriter.write(text);
            outWriter.close();
        }
        catch(IOException ioe){
            String err_message = "!!!!!!!!!!!!!!!!!!!!!!!!!!!"+
                                 "Trouble opening data to write "+filename+
                                "\nThis program is ending.";
            System.err.print(err_message);
        }
        
        return text;
    }
    public boolean rowHasSpace(int row){
        
        for (int col = 0; col<getCols() ; col++){
            
            if (board[row][col] == -1){
                return true;
            }
            
        }
        return false;
    }
    
    public void copyRow(int row){
        int board_color;
        for (int col = 0; col<getCols() ; col++){
            board[row][col] = board[row-1][col];
        } 
    }
    
    public void copyAllRows(int row){ 
        while (row > 0 ){
            copyRow(row);
            row--;
        }
    }
    
    public int score (){
        
        if (fullRowsNum == 0){
            score = 0;
        }
        else if (fullRowsNum == 1){
            score = score+100;
        }
        else if (fullRowsNum == 2){
            score = score+300;
        }
        else if (fullRowsNum == 3){
            score = score+600;
        }
        else if (fullRowsNum == 4){
            score = score+1200;
        }
        
        return score;
    }
    
    public void fullRowDetect (){
        
        int maxRow = getFallingBrick().getMaxRow();
        int minRow = getFallingBrick().getMinRow();
        
        for (int currentRow = maxRow; currentRow > minRow; currentRow--){
            
            if ( rowHasSpace(currentRow) == false){
                
                fullRowsNum = fullRowsNum + 1;
                
                int score = score();
                
                copyAllRows(currentRow);
                currentRow++;
                minRow--;
            }
        }
        fullRowsNum = 0;
    }
    
    public String toString()
    {
         String stuff = ""+board.length+" "+board[0].length+"\n";
         for(int row = 0; row < board.length;row++)
         {
            for(int col = 0; col < board[0].length;col++)
            {
                stuff += board[row][col]+ " ";
            }
            stuff += "\n";
         }
         stuff = stuff.substring(0,stuff.length()-1 );
         return stuff;
     }
    
    public void saveToFile(String fName)
        {
        File fileConnection = new File (fName);
        if (fileConnection.exists() && !fileConnection.canWrite())
        {
            System.err.print("Trouble opening to file: "+ fName);
            return;
        }
        try
        {
            FileWriter outWriter =
            new FileWriter(fileConnection);
            outWriter.write(this.toString());
            outWriter.close();
        }
        catch(IOException ioe)
        {
            System.err.print(" error save to file ");
        }
        }
     
    public void initFromFile(String fName)
    {
        File fileConnection = new File(fName);
        try
        {
            Scanner inScann = new Scanner(fileConnection);
            int rows = inScann.nextInt();
            int cols = inScann.nextInt();
            board = new int[rows][cols];
            for(int row = 0; row < rows;row++)
             {
                for(int col = 0; col < cols;col++)
                {
                board[row][col] = inScann.nextInt();
                }
            }
        }catch(Exception e)
            {
            System.err.print("Error occurred during retrieve from file ");
            }
    }
    public int getPosition(int row, int col){
        
        return getFallingBrick().position[row][col];
    }
    
    public int getSegmentRow(int seg) {
        
        return getFallingBrick().getPositionRow(seg);
        
    }
    
    public int getSegmentCol(int seg) {
        
        return getFallingBrick().getPositionCol(seg);
        
    }
    
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
    
    public TetrisBrick getFallingBrick() {
        return fallingBrick;
    }
    
    
    public int getGame_continuing(){
        return game_continuing;
    
    }
    
    public int getBrickType() {
        return brickType;
    }
    
    public int getBrickColor() {
        return getFallingBrick().colorNum;
    }
    
    public int fetchBoardPosition(int row, int col){
        return board[row][col];
    
    }
    
    public int getScore() {
        return score;
    }
    
}
