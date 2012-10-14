package edu.unm.cs.zach.cs361.project2;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * An iterator object for SizeKSubset collections.
 * 
 * This object will sequentially move through all of the possible subsets of
 * size k in the SizeKSubset. Note that this iterator actually calculates these
 * subsets as it produces them.
 * 
 * @author zach@cs.unm.edu
 * @author hayes@cs.unm.edu
 * 
 * @param <E> The type of objects in the set.
 */
public class SizeKSubsetIterator<E> implements Iterator<Set<E>> {

	/** The backing set (array) to be iterated over. */
	private E[] backingSet;

	/** The size of the subsets to produce. */
	private int k;

	/** The number of elements in the backing set. */
	private int n;

	/** Array of indexes representing the current size-k combination. */
	private int[] combination;

	/** Tracks the index being currently examined in the combination array. */
	private int curIndex;
	
	/** Flag is true while there are still more combinations to generate. */
	private boolean hasNextCombination = true;
	
	/** Iterator variable. Declared as field to keep instantiation out of
	 *  tight loops that might arise from rapidly calling the next() method. */
	private int i;

	/**
	 * Construct a new iterator for a SizeKSubset
	 * 
	 * @param subset The SizeKSubset this iterator is associated with.
	 */
	public SizeKSubsetIterator(SizeKSubset<E> subset) {
		backingSet = subset.getBackingSet();
		k = subset.getK();
		n = backingSet.length;
		combination = new int[k];

		/*
		 * Generate combination array initially such that last index is
		 * one less than it should be (combination[k-1] == combination[k-2])
		 * so that next() correctly produces the first combination the
		 * first time it is run.
		 */
		for (i = 0; i < k; i++) { combination[i] = i; }
		combination[k-1]--;
	}

	/** {@inheritDoc} */
	@Override
	public boolean hasNext() {
		return hasNextCombination;
	}

	/** {@inheritDoc} */
	@Override
	public Set<E> next() {
		
		curIndex = k - 1; // Current item index being evaluated for advancement
		
		// If the last index is less than n-1, increment it.
		if (combination[k-1] < n-1) {
			combination[k-1]++;
		}
		
		// Otherwise move backward to find an index to increase
		else {
			
			/*
			 * If the current index is the index of the last item in the set,
			 * move curIndex 'back' until we get to an index that is not in
			 * descending order (is less than what it would be in order). This
			 * will move curIndex to the index that needs to be incremented.
			 */
			i = 1;
			while (combination[curIndex] == n - i++) { curIndex--; }
			
			/*
			 * Generate an increasing range at the current index starting with
			 * the value of the current index + 1 and increasing until the end
			 * of the index array is reached.
			 */
			for (i = combination[curIndex] + 1; curIndex < k; i++) {
				combination[curIndex++] = i;
			}
			
			// If this is the last combination, set the flag
			if (combination[0] == n - k) { hasNextCombination = false; }
		}
		
		// Convert the combination array to a set and return it
		TreeSet<E> subset = new TreeSet<E>();
		for (i = 0; i < k; i++) {
			subset.add(backingSet[combination[i]]);
		}
		
		return subset;
	}

	/** {@inheritDoc} */
	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

}
