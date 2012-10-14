package edu.unm.cs.zach.cs361.project2;

import java.util.Arrays;
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
	
	/** Array of indexes representing the current size-k combination. */
	private int[] combination = null;

	
	/**
	 * Construct a new iterator for a SizeKSubset
	 * @param subset The SizeKSubset this iterator is associated with.
	 */
	public SizeKSubsetIterator(SizeKSubset<E> subset) {
		backingSet = subset.getBackingSet();
		k = subset.getK();
		
		
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
		
		// Points toward current item index being evaluated for advancement
		int curIndex = k - 1;
		
		// If it initial combination (0...k-1) doesn't exist, generate it.
		if (combination == null) {
			combination = new int[k];
			for (int i = 0; i < k; i++) { combination[i] = i; }
		}
		
		// Otherwise produce next combination
		else {
			// If current index is last item index, set to 0, move to prior index
			while (curIndex == 1) {}
				// TODO: Need to make sure we don't repeat indexes too!!
		}
		
		// Returns the next element in the iteration.
		// This will always be a size-k subset of the Set<E>
		// that is backing theSet.
		// TODO: implement this.
		return new TreeSet<E>();
	}

	
	/** {@inheritDoc} */
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
