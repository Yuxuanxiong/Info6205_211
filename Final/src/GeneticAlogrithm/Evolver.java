package GeneticAlogrithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Evolver {
	public List<Individual> individualGroup = new ArrayList<>();
	public int individual_size =1000;
	public int chromosomeLength=1000;
	double crossover_rate=0.9;
	double mutation_rate=0.2;
	public int generation=3000;
  
	public Individual fittest =new Individual(chromosomeLength);
	Maze maze = new Maze();
	
	//Read maze
	//Create new individuals of size 1000
	
	public void init() {
		maze.readDataFile();
		for(int i=0;i<individual_size;i++)
		{
			Individual chromosome =new Individual(chromosomeLength);	
			double distance=distance(chromosome.getChromosome());
			if(distance > fittest.getscore()){
			    fittest = chromosome;
			   }
			chromosome.setscore(distance);
			individualGroup.add(chromosome);
			
		}		
	}
	
	// Calculate individuals distance to maze exit
	public double distance(int[] chromosome) {
		double score=0;
		int x= maze.Xstart;
		int y= maze.Ystart;
		for(int i=0;i<chromosome.length;i+=2) {
			if(chromosome[i]==0 && chromosome[i+1]==0) {
				if(x>0&&maze.map[x-1][y]==0) {
					x--;
				}
			}//move up
			else if(chromosome[i]==0 && chromosome[i+1]==1) {
				if(x<maze.map_height && maze.map[x+1][y]==0) {
					x++;
				}
			}//move down
			else if(chromosome[i]==1&& chromosome[i+1]==0) {
				if(y>0 && maze.map[x][y-1]==0) {
					y--;
				}
			}//move right
			else {
				if(y<maze.map_width && maze.map[x][y+1]==0) {
					y++;
				}
			}
			//move left
		}
		double absx=Math.abs(x-maze.Xend);
		double absy=Math.abs(y-maze.Yend);
		score =1/(absx+absy+1);	
		return score;
	}
	
	//Rule the individual movement
	public void route(int[] chromosome) {
		int x= maze.Xstart;
		int y= maze.Ystart;
		for(int i=0;i<chromosome.length;i+=2) {
			if(chromosome[i]==0 && chromosome[i+1]==0) {
				if(x>0&&maze.map[x-1][y]==0) {
					x--;
				}
			}//move down
			else if(chromosome[i]==0 && chromosome[i+1]==1) {
				if(x<maze.map_height && maze.map[x+1][y]==0) {
					x++;
				}
			}//move up
			else if(chromosome[i]==1&& chromosome[i+1]==0) {
				if(y>0 && maze.map[x][y-1]==0) {
					y--;
				}
			}//move left
			else {
				if(y<maze.map_width && maze.map[x][y+1]==0) {
					y++;
				}
			}//move right
		}
	}
	
	//Select function
	//According to the fitness of individuals, better fitness will get more chance to be selected
	//Use roulette method
	public int selectOperation(List<Individual> list) {
		double randomNum = 0;
		double sum=0;
		int result = 0;
		double[] adaptiveValue = new double[individual_size];
		for(int i=0;i<individual_size;i++){
			Individual gene =list.get(i);
			double x=gene.getscore();
			adaptiveValue[i]=(x*x);
			sum+=adaptiveValue[i];
		}
		for (int i = 0; i < individual_size; i++) {
			   adaptiveValue[i] = adaptiveValue[i] / sum;
	    }
		
		for (int i = 0; i < individual_size; i++) {
			   randomNum = Math.random();
		}
		sum=0;
		for (int j = 0; j < individual_size; j++) {
		    if (randomNum > sum&& randomNum <= sum + adaptiveValue[j]) {
		        for(int i=0;i<individual_size/2;i++) {
			    	result=j;
		        }
		     break;
		    } 
		    else {
		     sum += adaptiveValue[j];
		    }
		}
		return result;	
	}
	
	// Proportion of organisms that survive and breed is 0.5
	// So select half the the population 
	public int[] survival() {
		int[] survival =new int[individual_size/2];
		for(int i=0;i<survival.length;i++) {
			survival[i]=-1;
		}
		int count=0;
		while(count<individual_size/2) {
			Evolver main = new Evolver();
			int x=main.selectOperation(individualGroup);
			for(int i=0;i<survival.length;i++) {			
				if(x==survival[i]) {
					break;
				}
				else if(x!=survival[i]&&i==survival.length-1) {
					survival[count]=x;
					count++;
				}
			}
		}
		return survival;
	}
	
	// The crossover and mutation
	public List<Individual> evolution(int mother,int father) {
		  List<Individual> partgenegroup = new ArrayList<>();
		  Individual p1 =  individualGroup.get(mother);
		  Individual p2 = individualGroup.get(father);
		  Individual c1 = new Individual(chromosomeLength);
		  Individual c2 = new Individual(chromosomeLength);
		  int[] chromosome1 = new int[chromosomeLength];
		  int[] chromosome2 = new int[chromosomeLength];
		  for(int i=0;i<chromosomeLength;i++){
			   chromosome1[i] = p1.getChromosome()[i];
			   chromosome2[i] = p2.getChromosome()[i];
		  }
		  Random random=new Random();
		  double r = random.nextDouble();
		  if(r <= crossover_rate){
		   int n = random.nextInt(chromosomeLength);
		   for(int i=n;i<chromosomeLength;i++){
		    int tmp = chromosome1[i];
		    chromosome1[i] = chromosome2[i];
		    chromosome2[i] = tmp;
		   }
		  }
		  r = random.nextDouble();
		  if(r <= mutation_rate){
			   int n = random.nextInt(chromosomeLength);
			   if(chromosome1[n] == 0){
			    chromosome1[n] = 1;
			   }
			   else{
			    chromosome1[n] = 0;
			   }
			   if(chromosome2[n] == 0){
			    chromosome2[n] = 1;
			   }
			   else{
			    chromosome2[n] = 0;
			   }
			  }
		  c1.setChromosome(chromosome1);
		  c2.setChromosome(chromosome2);
		  double score1 = distance(c1.getChromosome());
		  double score2 = distance(c2.getChromosome());
		  if(score1 >fittest.getscore()){
			  fittest = c1;
		  }
		  if(score2 >fittest.getscore()){
			  fittest = c2;
		  }
		  c1.setscore(score1);
		  c2.setscore(score2);
		  partgenegroup.add(c1);
		  partgenegroup.add(c2);
		  partgenegroup.add(p1);
		  partgenegroup.add(p2);
 
		  return partgenegroup;
	}
	
	// Print the route of fittest individual
	public static void print(Evolver main) {
		for(int i=0;i<main.chromosomeLength;i+=2){
			   if(main.fittest.getChromosome()[i] == 0 && main.fittest.getChromosome()[i+1] == 1){
				   System.out.println("up");
			   }
			   else if(main.fittest.getChromosome()[i] == 0 && main.fittest.getChromosome()[i+1] == 0){
				   System.out.println("down");
			   }
			   else if(main.fittest.getChromosome()[i] == 1 && main.fittest.getChromosome()[i+1] == 0){
				   System.out.println("left");
			   } 
			   else if(main.fittest.getChromosome()[i] == 1 && main.fittest.getChromosome()[i+1] == 1){
				   System.out.println("right");
			   }
	     }
	}
}

