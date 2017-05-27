package edu.smcm.ai.search;

/**
 * A search engine that implements the Alpha Beta Pruning algorithm.
 * 
 * This class is implemented from the pseudocode that appears in "Artificial
 * Intelligence: A Modern Approach" (Third Edition) by Russell and Norvig on p.
 * 166.
 * 
 */
public class MiniMax extends AdversarialSearch {
	
	/**
	 * Find the value of this state for the Max player.
	 * 
	 * @param state state of game
	 * @return worst outcome for Max player
	 */
	private double maxValue(AdversarialState state) {
		if (state.terminalTest()) return state.utility();
		double v = Double.NEGATIVE_INFINITY;
		for (Action a : state.actions()){
			
			states_evaluated++;
			
			double min = minValue(state.result(a));
			v = Math.max(v, minValue(state.result(a)));
		}
		return v;
	}
	
	/**
	 * Find the value of this state for the Min player.
	 * 
	 * @param state state of game
	 * @return worst outcome for Min player
	 */
	private double minValue(AdversarialState state) {
		if (state.terminalTest()) return state.utility();
		double v = Double.POSITIVE_INFINITY;
		for (Action a : state.actions()){
			
			states_evaluated++;
			
			v = (Math.min(v, maxValue(state.result(a))));
		}
		return v;
	}
	
	/* (non-Javadoc)
	 * @see edu.smcm.ai.search.AdversarialSearch#search(edu.smcm.ai.search.AdversarialState)
	 */
	@Override
	public Action search(AdversarialState state) {
		Action argmax = null;
		double maxMinSoFar = Double.NEGATIVE_INFINITY;
		double thisMinValue;
		for (Action a : state.actions()){
			
			states_evaluated++;
			
			thisMinValue = minValue(state.result(a));
			argmax = (thisMinValue > maxMinSoFar ? a : argmax);
			maxMinSoFar = (thisMinValue > maxMinSoFar ? thisMinValue : maxMinSoFar);
		}
		return argmax;
	}
}