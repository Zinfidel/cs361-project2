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

	/** Array of indices representing the current size-k combination. */
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

		// Edge case of k = 0, n = 0, or k > n
		if (k == 0 || n == 0 || k > n) {
			hasNextCombination = false;
		} else {
			/*
			 * Generate combination array initially such that last index is
			 * one less than it should be (combination[k-1] == combination[k-2])
			 * so that next() correctly produces the first combination the
			 * first time it is run.
			 */
			for (i = 0; i < k; i++) { combination[i] = i; }
			combination[k-1]--;
		}
	}
	

	/*
	 * The basic idea behind this algorithm is shown via a bad ASCII table
	 * below for size n = 8 and subset size k = 4:
	 * 
	 *                  Backing Set                            Combination
	 * ------------------------------------------------------------------------
	 * Starting:
	 *           [ A, B, C, D, E, F, G, H ]                   [ 0, 1, 2, 3 ]
	 *             ^  ^  ^  ^
	 *
	 * Step:
	 *           [ A, B, C, D, E, F, G, H ]                   [ 0, 1, 2, 4 ]
	 *             ^  ^  ^     ^                                         +
	 *               
	 * Last index is last item after several steps:
	 *           [ A, B, C, D, E, F, G, H ]                   [ 0, 1, 2, 7 ]
	 *             ^  ^  ^              ^                             ^--<
	 *               
	 * Produce a range in combination to find the next subset
	 *           [ A, B, C, D, E, F, G, H ]                   [ 0, 1, 3, 4 ]
	 *             ^  ^     ^  ^                 range(2+1...)--------^-->
	 *
	 * Last index reached at a much later combination:
	 *           [ A, B, C, D, E, F, G, H ]                   [ 0, 5, 6, 7 ]
	 *             ^              ^  ^  ^                       ^--<--<--<
	 *             
	 * Subsequent range produced:
	 *           [ A, B, C, D, E, F, G, H ]                   [ 1, 2, 3, 4 ]
	 *                ^  ^  ^  ^                 range(0+1...)--^-->-->-->          
	 * ------------------------------------------------------------------------
	 * 
	 * Note that the algorithm determines how far back in the combination array
	 * to start the range by counting down from the right-most (last item)
	 * index to the left. For instance, if 7 is produced in the last index, and
	 * the algorithm moves left and finds 6, it will continue to move left as
	 * 6 is the next item in the descending range. It will do this until it
	 * finds an index that is not in order (more than 1 less than to the right)
	 * and then produce the increasing range from there.
	 */
	/** {@inheritDoc} */
	@Override
	public Set<E> next() {
		
		// Start with the last index
		curIndex = k - 1;
		
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
			 * of the index array is reached. This produces indices representing
			 * the next non-duplicate set.
			 */
			for (i = combination[curIndex] + 1; curIndex < k; i++) {
				combination[curIndex++] = i;
			}
		}
		
		// If this is the last combination, set the flag
		if (combination[0] == n - k) { hasNextCombination = false; }
		
		// Convert the combination array to a set and return it
		TreeSet<E> subset = new TreeSet<E>();
		for (i = 0; i < k; i++) {
			subset.add(backingSet[combination[i]]);
		}
		
		return subset;
	}
	
	
	/** {@inheritDoc} */
	@Override
	public boolean hasNext() {
		return hasNextCombination;
	}
	

	/** {@inheritDoc} */
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
