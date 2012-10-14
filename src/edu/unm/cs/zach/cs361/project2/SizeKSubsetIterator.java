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
	private int[] combination = null;

	/** Tracks the index being currently examined in the combination array. */
	private int curIndex;
	
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
	}

	/** {@inheritDoc} */
	@Override
	public boolean hasNext() {
		// Returns true if the iteration has more elements.
		// TODO: implement this.
		return true;
	}

	/** {@inheritDoc} */
	// NOTE: Study: http://msdn.microsoft.com/en-us/library/aa289166.aspx
	@Override
	public Set<E> next() {
		
		i = 0; // Reset iterator
		curIndex = k - 1; // Current item index being evaluated for advancement
		
		// If it initial combination (0...k-1) doesn't exist, generate it.
		if (combination == null) {
			combination = new int[k];
			for (i = 0; i < k; i++) { combination[i] = i; }
		}
		
		// Otherwise produce next combination
		else {
			/*
			 * If the current index is the index of the last item in the set,
			 * move curIndex 'back' until we get to an index that is not in
			 * descending order (is less than what it would be in order). This
			 * will move curIndex to the index that needs to be incremented.
			 */
			while (combination[curIndex--] == n - ++i);
			
			// If curIndex is -1, then the last combination has been found.
			if (curIndex == -1) { }
			
			/*
			 * Otherwise, generate an increasing range at the current index
			 * starting with the value of the current index + 1 and increasing
			 * until the end of the index array is reached.
			 */
			else {
				for (i = 0; curIndex + i < k-1; i++) {
					combination[curIndex + i] = combination[curIndex] + i;
				}
			}
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
