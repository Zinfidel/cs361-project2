package edu.unm.cs.zach.cs361.project2;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * A non-mutable set class.
 * Each instance is defined by a backing set, and a size, k.
 * It contains all k-element subsets of the backing set.
 * 
 * @author zach@cs.unm.edu
 * @author hayes@cs.unm.edu
 * 
 * @param <E> The type of objects in the set.
 */
public class SizeKSubset<E> extends AbstractSet<Set<E>> {

	/**
	 * Backing set as an array. Converted into array form so that objects in
	 * the set are indexable, as the set interface does not guarantee order.
	 */
	private E[] backingSet;  

	/** Size of subsets to produce. */
	private int k;

	
	/**
	 * Constructor
	 * @param backingSet Array version of supplied backing set.
	 * @param k Size of subsets.
	 */
	@SuppressWarnings("unchecked")
	public SizeKSubset (Set<E> backingSet, int k) {
		this.backingSet = (E[])backingSet.toArray(); // unchecked type
		this.k = k;
	}
	
	
	/** Returns an iterator over the subsets of the backing set. */
	@Override
	public Iterator<Set<E>> iterator() {
		return new SizeKSubsetIterator<E>(this);
	}

	
	/** Return the cardinality of the set of subsets of size k. */
	@Override
	public int size() {
		// TODO: return the correct size.
		return 0;
	}

	
	/** Returns the backing set of this object. */
	public E[] getBackingSet() {
		return backingSet;
	}
	
	
	/** Returns the size k of the subsets. */
	public int getK() {
		return k;
	}
	
	
	public static void main (String[] args) {
		
		// Generate iterator
		SizeKSubset<Integer> s = new SizeKSubset<Integer>(new TreeSet<Integer>(Arrays.asList(new Integer[] {1,2,3,4,5})), 3);
		Iterator<Set<Integer>> sit = s.iterator();
		
		for (int j = 0; j < 5; j++) {
			Set<Integer> out = sit.next();
			System.out.println(out);
		}
		
		/* Toms test
		SizeKSubset<String> s = new SizeKSubset<String>(new TreeSet<String>(Arrays.asList( args )), 3 );
		for (Set<String> x : s) {
			System.out.println(x);
		}
		*/
	}
}
