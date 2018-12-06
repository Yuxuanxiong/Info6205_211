package Test;

import GeneticAlogrithm.Individual;
import GeneticAlogrithm.Evolver;

import static org.junit.Assert.assertEquals;

import java.util.Random;

class Test {

	@org.junit.jupiter.api.Test
	void testGene1() {
		 int[] threestepgene= {0,0,1,1,0,1};
		 Individual testgene = new Individual(6);
		 testgene.setChromosome(threestepgene);
		 assertEquals(testgene.getChromosome()[0], 0);
		 assertEquals(testgene.getChromosome()[1], 0);
		 assertEquals(testgene.getChromosome()[2], 1);
		 assertEquals(testgene.getChromosome()[3], 1);
		 assertEquals(testgene.getChromosome()[4], 0);
		 assertEquals(testgene.getChromosome()[5], 1);
	}
	@org.junit.jupiter.api.Test
	void testGene2() {
		 int[] threestepgene= {0,0,1,1,0,1,1,0,1,0};
		 Individual testgene = new Individual(10);
		 testgene.setChromosome(threestepgene);
		 assertEquals(testgene.getChromosome()[0], 0);
		 assertEquals(testgene.getChromosome()[2], 1);
		 assertEquals(testgene.getChromosome()[4], 0);
		 assertEquals(testgene.getChromosome()[6], 1);
		 assertEquals(testgene.getChromosome()[8], 1);
	}
	
	@org.junit.jupiter.api.Test
	void testinitfuction() {
		Evolver testinit =new Evolver();
		testinit.init();
		 assertEquals(testinit.individualGroup.size(), testinit.individual_size);
		 assertEquals(testinit.individualGroup.get(0).length, testinit.chromosomeLength);
		 assertEquals(testinit.individualGroup.get(0).getscore(), 0,1);
	}
	
	@org.junit.jupiter.api.Test
	void testDistance() {
		Evolver testcalculatescore =new Evolver();
		testcalculatescore.init();
		assertEquals(testcalculatescore.distance(testcalculatescore.individualGroup.get(0).chromosome),testcalculatescore.distance(testcalculatescore.individualGroup.get(1).chromosome),0.4);
	}

	@org.junit.jupiter.api.Test
	void testSelectoperation() {
		Evolver testDistance =new Evolver();
		testDistance.init();	
		assertEquals(testDistance.selectOperation(testDistance.individualGroup),0, testDistance.individual_size);
	}
	
	@org.junit.jupiter.api.Test
	void testSurvival() {
		Evolver testluckdog =new Evolver();
		testluckdog.init();	
		assertEquals(testluckdog.survival().length,testluckdog.individual_size/2);
		Random random =new Random();
		assertEquals(testluckdog.survival()[random.nextInt(testluckdog.individual_size/2)],testluckdog.individual_size/2,testluckdog.individual_size/2);
	}
	
	@org.junit.jupiter.api.Test
	void testEvolution() {
		Evolver testmate =new Evolver();
		testmate.init();
		assertEquals(testmate.evolution(0, 1).size(),4);

	}
}
