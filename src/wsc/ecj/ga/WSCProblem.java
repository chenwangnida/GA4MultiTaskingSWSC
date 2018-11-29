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
		
		
		// remove it ?
//		if (ind.evaluated)
//			return;

		if (!(ind instanceof SequenceVectorIndividual))
			state.output.fatal("Whoa!  It's not a SequenceVectorIndividual!!!", null);

		SequenceVectorIndividual ind2 = (SequenceVectorIndividual) ind;
		WSCInitializer init = (WSCInitializer) state.initializer;

		if (!(ind2.fitness instanceof SimpleFitness))
			state.output.fatal("Whoa!  It's not a SimpleFitness!!!", null);

		ArrayList<Double> fitnessTa = new ArrayList<>();

		if (state.generation == 0) {
			for (Task task : init.tasks) {
				fitnessTa.add(task.calculateSequenceFitness(ind2.genome, init, state, ind2));
			}
		}

//		if (state.generation > 0) {
//			for (int i = 0; i < init.TaskNum; i++)
//				if (i != ind2.getSkillFactor())
//					fitnessTa.add(init.LIMIT);
//				else
//					fitnessTa.add(init.tasks.get(i).calculateSequenceFitness(ind2.genome, init, state, ind2));
//		}

		ind2.setFitnessTask(fitnessTa);

	}
	
	
	private void reComputeFitnessTaskForChild(List<Individual> children) {
		for (Individual child : children) {
			List<Double> fT = child.getFitnessTask();
			for (int j = 0; j < tasks.size(); j++)
				if (fT.get(j) == LIMIT) {
					Task t = tasks.get(j);
					fT.set(j, t.computeFitness(child.gen));
				}
		}
	}

}