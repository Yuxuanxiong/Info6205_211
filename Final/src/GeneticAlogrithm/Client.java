package GeneticAlogrithm;

import java.util.ArrayList;
import java.util.List;



public class Client {
	// main function
	// Create 200 individuals
	// Calculate the fitness then find the best one
	// If individuals reached the max generations but still don't find the exit
	// All of the actions will be stoped
	// Then print the fittest route


	public static void main(String[] args) {
		Evolver main=new Evolver();
		int count=0;
        List<Individual> newgenegroup = new ArrayList<>();	
		main.init();
		while(main.fittest.getscore()<0.5 && count<main.generation) {
			for(int i=0;i<main.survival().length;i=i+2) {
				newgenegroup.addAll(main.evolution(main.survival()[i], main.survival()[i+1]));				
			}
			main.individualGroup=newgenegroup;
			System.out.println("Generation " + (count+1) + " : " +"The fitness of best individual: " + main.fittest.getscore());
			count++;
		}
		if(count==main.generation) {
			System.out.println("Can't find the exit within "+ count+" generations");
			System.out.println("The gene of individual with the best fitness is ");
			for(int z=0;z<main.chromosomeLength;z++) {
				System.out.print(main.fittest.getChromosome()[z]);
			}

		}
		else {
			System.out.println("Find the exit with "+count+" generations");
			System.out.println("The gene of individual with the best fitness is ");
			for(int z=0;z<main.chromosomeLength;z++) {
				System.out.print(main.fittest.getChromosome()[z]);
			}
		}
		Jpanel panel = new Jpanel();
		panel.pg=main.fittest.getChromosome();
		panel.setVisible(true);
	}

		
}

