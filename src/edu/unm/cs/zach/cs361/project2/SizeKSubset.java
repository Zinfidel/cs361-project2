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

	/** Non-mutable set that Subset object will reference. */
	private Set<E> backingSet;  

	/** Size of subsets to produce. */
	private int k;

	
	/**
	 * Constructor
	 * @param backingSet Non-mutable set to reference.
	 * @param k Size of subsets.
	 */
	public SizeKSubset (Set<E> backingSet, int k) {
		this.backingSet = backingSet;
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
	public Set<E> getBackingSet() {
		return backingSet;
	}
	
	
	/** Returns the size k of the subsets. */
	public int getK() {
		return k;
	}
	
	
	public static void main (String[] args) {
		// TODO: add your own tests.
		SizeKSubset<String> s = new SizeKSubset<String>(new TreeSet<String>(Arrays.asList( args )), 3 );
		for (Set<String> x : s) {
			System.out.println(x);
		}
	}
}
