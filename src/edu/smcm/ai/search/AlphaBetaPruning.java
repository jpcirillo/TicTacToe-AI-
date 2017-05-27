package edu.smcm.ai.search;

/**
 * A search engine that implements the Alpha Beta Pruning algorithm.
 * 
 * This class is implemented from the pseudocode that appears in "Artificial
 * Intelligence: A Modern Approach" (Third Edition) by Russell and Norvig on p.
 * 170. The search() method does not follow the Russell and Norvig pseudocode for
 * this algorithm because it is not clear how this should be implemented.
 * Instead, the pseudocode from the MiniMax algorithm (p. 166) is used.
 * 
 */
public class AlphaBetaPruning extends AdversarialSearch {

	/**
	 * Find the value of this state for the Max player.
	 * 
	 * @param state state of game
	 * @param alpha best value so far for Max player
	 * @param beta best value so far for Min player
	 * @return worst outcome for Max player
	 */
	private double maxValue(AdversarialState state, double alpha, double beta) {
		// TODO Implement AlphaBetaPruning.maxValue()
		
		Double v;
		
		if (state.terminalTest())
			return state.utility();
		
		v = Double.MIN_VALUE;
		
		for (Action a : state.actions()) {
			
			states_evaluated++;
			
			v = Math.max(v, minValue(state.result(a), alpha, beta));
			if (v >= beta)
				return v;
			
			alpha = Math.max(alpha, v);
			
		}
		
		return v;
	}

	/**
	 * Find the value of this state for the Min player.
	 * 
	 * @param state state of game
	 * @param alpha best value so far for Max player
	 * @param beta best value so far for Min player
	 * @return worst outcome for Min player
	 */
	private double minValue(AdversarialState state, double alpha, double beta) {
		// TODO Implement AlphaBetaPruning.minValue()
		
		Double v;
		
		if (state.terminalTest())
			return state.utility();
		
		v = Double.POSITIVE_INFINITY;
		
		for (Action a : state.actions()) {
			
			states_evaluated++;
			
			v = Math.min(v, maxValue(state.result(a), alpha, beta));
			if (v <= alpha)
				return v;
			
			beta = Math.min(beta, v);
			
		}
		
		return v;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.smcm.ai.search.AdversarialSearch#search(edu.smcm.ai.search.
	 * AdversarialState)
	 */
	@Override
	public Action search(AdversarialState state) {
		// TODO Implement AlphaBetaPruning.search()
		Action argmax = null;
		double thisMin;
		double maxMinSoFar = Double.NEGATIVE_INFINITY;
		for (Action a : state.actions()){
			
			states_evaluated++;
			
			thisMin = minValue(state.result(a), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
			argmax = (thisMin > maxMinSoFar ? a : argmax);
			maxMinSoFar = (thisMin > maxMinSoFar ? thisMin : maxMinSoFar);
			
			
		}
		
		return argmax;
	}
}
