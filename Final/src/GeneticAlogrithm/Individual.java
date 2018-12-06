package GeneticAlogrithm;
import java.util.Random;

public class Individual {
	public int length;
	public int[] chromosome;
	public double score;
	public int getlength() {
		return length;
	}
	public void setlength(int length) {
		this.length=length;
	}
	public int[] getChromosome() {
		return chromosome;
	}
	public void setChromosome(int gene[]) {
		this.chromosome=gene;
	}
	public double getscore() {
		return score;
	}
	public void setscore(double score) {
		this.score=score;
	}
	// Create individuals have same chromosome length of 1000
	// Every chromosome is created random
	public Individual(int length){
		  this.length = length;
		  chromosome = new int[length];
		  Random random = new Random();
		  for(int i=0;i<length;i++){
		   chromosome[i] = random.nextInt(2);
		  }
		  this.score=0;
	}
}
