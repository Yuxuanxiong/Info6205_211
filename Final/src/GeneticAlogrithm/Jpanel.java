package GeneticAlogrithm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
class Jpanel extends JFrame{
	private List<JLabel> labels = new ArrayList<>();
	public int[] pg;
	Maze maze = new Maze();
	
	// Read maze file 
	// Paint the fittest route in maze on JPanel
	public Jpanel() {		 
		  maze.readDataFile();
		  setSize(700, 700);
		  setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		  setResizable(false);
		  getContentPane().setLayout(null);
		  JPanel panel = new JPanel();
		  panel.setLayout(new GridLayout(maze.map_height,maze.map_width));
		  panel.setBounds(5, 5, maze.map_width*20, maze.map_height*20);
		  getContentPane().add(panel);
		  for(int i=0;i<maze.map_height;i++){
		   for(int j=0;j<maze.map_width;j++){
		    JLabel label = new JLabel();
		    Color color = null;
		    if(maze.map[i][j] == 1){
		     color = Color.black;
		    }
		    if(maze.map[i][j] == 0){
		     color = Color.GRAY;
		    }
		    if(maze.map[i][j] == 5){
		     color = Color.green;
		    }if(maze.map[i][j]==8) {
		    	color = Color.red;
		    }
		    label.setBackground(color);
		    label.setOpaque(true);
		    panel.add(label);
		    labels.add(label);
		   }
		  }
	 }
	//Paint the route
	public void paint(Graphics g) {
		  super.paint(g);
		  int[] gene = pg;
		  int x = maze.Xstart;
		  int y= maze.Ystart;

		  for(int i=0;i<gene.length;i+=2){
		   // move up
		   if(gene[i] == 0 && gene[i+1] == 0){
		    if(x>=1 && maze.map[x-1][y] == 0){
		     x --;
		     labels.get(x*maze.map_width+y).setBackground(Color.BLUE);
		    }
		   }
		   // move down
		   else if(gene[i] == 0 && gene[i+1] == 1){
		    if(x<=maze.map_height-1 && maze.map[x+1][y] == 0){
		     x ++;
		     labels.get(x*maze.map_width+y).setBackground(Color.BLUE);
		    }
		   }
		   // move left
		   else if(gene[i] == 1 && gene[i+1] == 0){
		    if(y>=1 && maze.map[x][y-1] == 0){
		     y --;
		     labels.get(x*maze.map_width+y).setBackground(Color.BLUE);
		    }
		   } 
		   else{
		    if(y <= maze.map_width-1 && maze.map[x][y+1] == 0){
		     y ++;
		     labels.get(x*maze.map_width+y).setBackground(Color.BLUE);
		    }
		   }

		  }
		 }
}
