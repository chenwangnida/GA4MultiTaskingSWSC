package wsc.ecj.ga;

import ec.EvolutionState;
import ec.Individual;
import ec.select.TournamentSelection;

public class MultiTaskingSelection extends TournamentSelection {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2879964074147477753L;

	@Override
	public boolean betterThan(Individual first, Individual second, int subpopulation, EvolutionState state,
			int thread) {

		boolean better = false;
		SequenceVectorIndividual firtIndi = (SequenceVectorIndividual) first;
		SequenceVectorIndividual secondIndi = (SequenceVectorIndividual) second;

		if (firtIndi.getScalarFitness() > secondIndi.getScalarFitness()) {
			better = true;
		}

		return better;
	}

}
