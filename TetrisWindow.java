/*
CSCI 282 | Semister Project "Tetris"
Author : Zarin Manita
Date: Jan 09 2021

 */

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.*;

public class TetrisWindow extends JFrame {
    
    private int winWid = 300;
    private int winHei = 400;
    
    public int rows = 20;
    public int cols = 12;
    
    
    private TetrisDisplay display;
    private TetrisGame game;
    

    public TetrisWindow()
    {
        
        this.setTitle("My Tetris Game");
        this.setSize(winWid,winHei);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        game = new TetrisGame(rows,cols);
        display = new TetrisDisplay(game);
         

        initMenus();
        this.add(display);
        this.setVisible(true);
    }
    
    public void initMenus(){
        
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        
        JMenu men1 = new JMenu("Menu1");
        menuBar.add(men1);
        
        JMenu men2 = new JMenu("Menu2");
        menuBar.add(men2);
        
        JMenuItem leaderBoard = new JMenuItem("Leader Board");
        men1.add(leaderBoard);
        men1.addSeparator();
        JMenuItem deleteAll = new JMenuItem("Delete All");
        men1.add(deleteAll);
        
        JMenuItem newGame = new JMenuItem("New Game");
        men2.add(newGame);
        men2.addSeparator();
        JMenuItem saveGame = new JMenuItem("Save Game");
        men2.add(saveGame);
        JMenuItem retrieveGame = new JMenuItem("Retrieve Game");
        men2.add(retrieveGame);
        
        leaderBoard.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ac)
            {JOptionPane.showMessageDialog(null,
            "leaderBoarDisplay",
            "Leader Board",1); }
            });
        
        deleteAll.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ac)
            { JOptionPane.showMessageDialog(null,
            "Menu Item 2 clicked",
            "Response to menu item 2",1); }
            });
        
        saveGame.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ac)
            {
            game.saveToFile("TrialGame.dat");
            }
            });
        
        retrieveGame.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ac)
            {
            game.initFromFile( "TrialGame.dat" );
            repaint();}
            });
        
        newGame.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ac)
            {game.newGame(); }
            });
    }
    public static void main(String[] args)
    {
        TetrisWindow myWin = new TetrisWindow();

    }
    
    
}