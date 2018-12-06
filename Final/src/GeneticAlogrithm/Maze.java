package GeneticAlogrithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Maze {
	public int[][] map;
	public int map_height;
	public int map_width;
	public int Xstart;
	public int Ystart;
	public int Xend;
	public int Yend;
	
	// Use bufferedReader to read the maze file
	// Save the maze into map
	public void readDataFile() {
		String filePath = "map.txt";
		int MAZE_ENTRANCE_POS = 5;
		int MAZE_EXIT_POS = 8;
		  File file = new File(filePath);
		  ArrayList<String[]> dataArray = new ArrayList<String[]>();
		 
		  try {
		   BufferedReader in = new BufferedReader(new FileReader(file));
		   String str;
		   String[] tempArray;
		   while ((str = in.readLine()) != null) {
		    tempArray = str.split(",");
		    dataArray.add(tempArray);
		   }
		   in.close();
		  } catch (IOException e) {
		   e.getStackTrace();
		  }
		 
		  int rowNum = dataArray.size();
		  map = new int[rowNum][rowNum];
		  this.map_height=rowNum;
		  this.map_width=rowNum;
		  
		  for (int i = 0; i < rowNum; i++) {
		   String[] data = dataArray.get(i);
		   for (int j = 0; j < data.length; j++) {
			   map[i][j] = Integer.parseInt(data[j]);
		 
		    // position of entrance and exit
		    if (map[i][j] == MAZE_ENTRANCE_POS) {
		    	Xstart = i;
		    	Ystart = j;
		    } else if (map[i][j] == MAZE_EXIT_POS) {
		    	Xend = i;
		    	Yend = j;
		    }
		   }
		  }
		 }

}
