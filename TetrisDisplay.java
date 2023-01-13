
/*
CSCI 282 | Semister Project "Tetris"
Author : Zarin Manita
Date: Jan 09 2021

 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import java.util.Random;

public class TetrisDisplay extends JPanel{
    
    TetrisGame game;
    
    int start_x = 50;
    int start_y = 50;
    int cellSize = 10;
    int wallWid = 10;
    
    int speed;
    Timer timer;
    private int delay = 300;
    Color[] colors = {Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW,
                      Color.RED, Color.ORANGE, Color.CYAN};
    
    int brickReachBottom;
    Random randGen;
    int brickColor;
    int brickColorTest;      // -1 if no color is in board
    
    public TetrisDisplay(TetrisGame gam)
    {
        
        game = gam;
        
        timer = new Timer(delay, new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                cycleMove();
            }
        });   
        
        timer.start();
        
        this.addKeyListener( new KeyAdapter(){
        public void keyPressed(KeyEvent ke)
         {
            translateKey(ke);
         }
        });
        
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false); 
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(2));
        
        drawWalls(g);
        drawBoard(g);
        drawBrick(g);
        displayScore(g);
        
        boolean gameOver = game.gameOverDetection();
        if (gameOver == true){
        drawGameOver(g);
        }
    }
    
    public void drawBoard (Graphics g){
        
        for (int row = 0; row<game.getRows() ; row++){
           
            for (int col = 0; col<game.getCols() ; col++){
                
                if (game.fetchBoardPosition(row, col) == -1)
                {
                    g.setColor(Color.white);
                    g.fillRect(start_x+cellSize*col, start_y+cellSize*row, cellSize, cellSize);
                }
                
                else{
                        g.setColor(colors[game.fetchBoardPosition(row,col)]);
                        g.fillRect(start_x+cellSize*col, start_y+cellSize*row, cellSize, cellSize);
                        
                        g.setColor(Color.BLACK);
                        g.drawRect(start_x+cellSize*col, start_y+cellSize*row, cellSize, cellSize);
                     
                }
            }
        }
    }
    
    
    public void drawWalls(Graphics g){
        
        // left
        g.fillRect(start_x-wallWid, start_y, wallWid, cellSize*(game.getRows()+1));
        //right
        g.fillRect(start_x+cellSize*game.getCols(),
        start_y, wallWid, cellSize*(game.getRows()+1));
        //bottom
        g.fillRect(start_x-wallWid,
        start_y-wallWid+cellSize*(game.getRows()+1),cellSize*game.getCols()+2*wallWid,wallWid);
    }
    
    public void drawBrick(Graphics g){
        
        for (int seg = 0; seg < game.getFallingBrick().getNumSegment(); seg++){
            
            int row = game.getSegmentRow(seg);
            int col = game.getSegmentCol(seg);
            
            g.setColor(Color.black);
            g.drawRect(start_x+cellSize*col, start_y+cellSize*row, cellSize, cellSize);
            
            brickColor = game.getFallingBrick().getColorNum();
            g.setColor(colors[brickColor]);
            g.fillRect(start_x+cellSize*col, start_y+cellSize*row, cellSize, cellSize);
            
        }
    }
    
    public void cycleMove(){
        
        if (game.getGame_continuing() == 0)   //means game is not paused
        {
            game.makeMove("D");
        }
        repaint();
        
    }
    
    public void displayScore(Graphics g){
        
        int score = game.getScore();
        String string = " score "+ score + "\n";
        g.setColor(Color.GREEN);
        g.setFont(g.getFont().deriveFont(20f));
        g.drawString(string, 200, 50);
        repaint();
        
    }
    
    public void drawGameOver(Graphics g){
            String string = " GAME OVER "+ "\n";
            g.setColor(Color.RED);
            g.setFont(g.getFont().deriveFont(30f));
            g.drawString(string, start_x-wallWid*2, start_y+cellSize*(game.getRows()/2));
            
    }    
    
    public void translateKey(KeyEvent ke){
        
        int code = ke.getKeyCode();
        System.err.print("\n Key Pressed "+code);
        
        switch(code)
        {
            case KeyEvent.VK_UP:
            game.makeMove("Rotate");
            break;
            case KeyEvent.VK_RIGHT:
            game.makeMove("R");
            break;
            case KeyEvent.VK_LEFT:
            game.makeMove("L");
            break;
            case KeyEvent.VK_N:
            game.makeMove("N");
            break;
            case KeyEvent.VK_SPACE:
            game.makeMove("P");
            break;
            
        }
    }
    

}