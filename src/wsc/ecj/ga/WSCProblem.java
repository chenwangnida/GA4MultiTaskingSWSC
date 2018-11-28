package wsc.ecj.ga;

import java.util.ArrayList;

import ec.*;
import ec.simple.*;
import wsc.tasks.*;

public class WSCProblem extends Problem implements SimpleProblemForm {
	private static final long serialVersionUID = 1L;

	public void evaluate(final EvolutionState state, final Individual ind, final int subpopulation,
			final int threadnum) {
		if (ind.evaluated)
			return;

		if (!(ind instanceof SequenceVectorIndividual))
			state.output.fatal("Whoa!  It's not a SequenceVectorIndividual!!!", null);

		SequenceVectorIndividual ind2 = (SequenceVectorIndividual) ind;
		WSCInitializer init = (WSCInitializer) state.initializer;

		if (!(ind2.fitness instanceof SimpleFitness))
			state.output.fatal("Whoa!  It's not a SimpleFitness!!!", null);

		ArrayList<Double> fitnessTa = new ArrayList<>();
		for (Task task : init.tasks) {
			fitnessTa.add(task.calculateSequenceFitness(ind2.genome, init, state, ind2));
		}

		ind2.setFitnessTask(fitnessTa);

	}

}