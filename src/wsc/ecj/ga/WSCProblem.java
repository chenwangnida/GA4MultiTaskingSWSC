package wsc.ecj.ga;

import java.util.ArrayList;
import java.util.List;

import ec.*;
import ec.simple.*;

import wsc.tasks.*;

public class WSCProblem extends Problem implements SimpleProblemForm {
	private static final long serialVersionUID = 1L;

	public void evaluate(final EvolutionState state, final Individual ind, final int subpopulation,
			final int threadnum) {

		if (!(ind instanceof SequenceVectorIndividual))
			state.output.fatal("Whoa!  It's not a SequenceVectorIndividual!!!", null);

		SequenceVectorIndividual ind2 = (SequenceVectorIndividual) ind;
		WSCInitializer init = (WSCInitializer) state.initializer;

		if (!ind2.evaluated) {
			ind2.calculateSequenceFitness(ind2.genome, init, state);
		}

//		if (!(ind2.fitness instanceof SimpleFitness))
//			state.output.fatal("Whoa!  It's not a SimpleFitness!!!", null);

		// compute the fitness for the first generation
		if (state.generation == 0) {
			ArrayList<Double> fitnessTa = new ArrayList<>();

			for (Task task : init.tasks) {
				fitnessTa.add(task.calculateFitness4Tasks(ind2, init));
			}
			ind2.setFitnessTask(fitnessTa);
		}

		// re-compute the fitness
		if (state.generation > 0) {
			List<Double> ft = ind2.getFitnessTask();
			for (int j = 0; j < init.TaskNum; j++) {
				if (ft.get(subpopulation) == init.LIMIT) {
					Task t = init.tasks.get(j);
					ft.set(j, t.calculateFitness4Tasks(ind2, init));
				}

			}
		}

	}

}