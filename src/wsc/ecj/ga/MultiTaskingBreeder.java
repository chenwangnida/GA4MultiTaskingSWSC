package wsc.ecj.ga;

import com.google.common.collect.ObjectArrays;

import ec.BreedingPipeline;
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
		Population newPop = (Population) state.population.emptyClone();

		Individual[] oldInds = oldPop.subpops[0].individuals;
		Individual[] newInds = new Individual[oldPop.subpops[0].individuals.length];

		// do regular breeding of this subpopulation
		BreedingPipeline bp = (BreedingPipeline) newPop.subpops[0].species.pipe_prototype;

		for (int start = 0; start < newInds.length; start += 2) {
			bp.produce(1, 1, start, 0, newInds, state, 0);
		}

		Individual[] combinedInds = ObjectArrays.concat(oldInds, newInds, Individual.class);

		newPop.subpops[0].individuals = combinedInds;
		return newPop;

	}

}
