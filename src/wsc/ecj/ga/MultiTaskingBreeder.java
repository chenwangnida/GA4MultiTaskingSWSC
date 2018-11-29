package wsc.ecj.ga;

import ec.EvolutionState;
import ec.Individual;
import ec.Population;
import ec.Subpopulation;
import ec.simple.SimpleBreeder;
import ec.util.Parameter;

public class MultiTaskingBreeder extends SimpleBreeder {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4304591219117968129L;
	
	
	// ovrride this method plz ????

	@Override
	public void setup(EvolutionState state, Parameter base) {
		// TODO Auto-generated method stub
		super.setup(state, base);
	}




	/**
	 * Override breedPopulation(). We take the result from the super method in
	 * SimpleBreeder and append it to the old population. Hence, after generation 0,
	 * every subsequent call to <code>NSGA2Evaluator.evaluatePopulation()</code>
	 * will be passed a population of 2x<code>originalPopSize</code> individuals.
	 */
	public Population breedPopulation(EvolutionState state) {
		Population oldPop = (Population) state.population;
		Population newPop = super.breedPopulation(state);
		Individual[] combinedInds;
		Subpopulation[] subpops = oldPop.subpops;
		Subpopulation oldSubpop;
		Subpopulation newSubpop;
		int subpopsLength = subpops.length;

		for (int i = 0; i < subpopsLength; i++) {
			oldSubpop = oldPop.subpops[i];
			newSubpop = newPop.subpops[i];
			combinedInds = new Individual[oldSubpop.individuals.length + newSubpop.individuals.length];
			System.arraycopy(newSubpop.individuals, 0, combinedInds, 0, newSubpop.individuals.length);
			System.arraycopy(oldSubpop.individuals, 0, combinedInds, newSubpop.individuals.length,
					oldSubpop.individuals.length);
			newSubpop.individuals = combinedInds;
		}
		return newPop;
	}

}
