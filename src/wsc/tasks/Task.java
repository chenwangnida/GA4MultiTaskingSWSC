package wsc.tasks;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;

import ec.EvolutionState;
import wsc.data.pool.Service;
import wsc.ecj.ga.SequenceVectorIndividual;
import wsc.ecj.ga.WSCInitializer;
import wsc.graph.ServiceEdge;

abstract public class Task {
	public abstract double calculateSequenceFitness(Service[] sequence, WSCInitializer init, EvolutionState state, SequenceVectorIndividual ind2);

	public abstract void aggregationAttribute(SequenceVectorIndividual individual,
			DefaultDirectedWeightedGraph<String, ServiceEdge> directedGraph);

	public abstract double calculateFitness(SequenceVectorIndividual individual);

}
