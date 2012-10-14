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

	/** Returns the backing set of this object. */
	public E[] getBackingSet() {
		return backingSet;
	}
	
	
	/** Returns the size k of the subsets. */
	public int getK() {
		return k;
	}
	
	
	/*
	 * This method calculates the total possible amounts of subsets that
	 * can be produced. This method is basically n choose k, using properties
	 * of binomial coefficients to make the math a little nicer.
	 * 
	 * The equation for binomial coefficient calculation can be written:
	 * 
	 *                   n!        n   n - 1   n - 2         n - (k - 1)
 	 * n choose k == ---------- == - * ----- * ----- * ... * -----------
 	 *               k!(n - k)!    k   k - 1   k - 2         k - (k - 1)
 	 *               
 	 * where in the last term, k - (k - 1) == 1, and simplifies to n - (k - 1).
 	 * In the next term, k - (k - 2) == 2, k - (k - 3) == 3, and so forth.
 	 * 
 	 * Each term in the equation is itself a coefficient, and so they each
 	 * have an integer result, and can be calculated right-to-left without
 	 * dealing with real numbers are extremely large factorials.
 	 * 
 	 * Furthermore,
 	 * 
 	 * n choose k == n choose (n - k), so the equation can be minimized such
 	 * that there will never be more than n/2 factors to calculate.
	 */
	/** Calculates the number of possible subsets. */
	@Override
	public int size() {
		
		int n = backingSet.length;
		int product = 1;
		
		// Reduce the problem to n choose (n - k) *if* it is smaller
		int k = this.k > n ? this.k : n - this.k;
		
		for (int i = 1; i <= k; i++) {
			// Doing the multiplication first explicitly will ensure only
			// integer results are produced.
			product *= n - (k - i);
			product /= i;
		}
		
		return product;
	}
	
	
	public static void main (String[] args) {
		SizeKSubset<Integer> s = new SizeKSubset<Integer>(
				new TreeSet<Integer>(Arrays.asList(
						new Integer[] {0,1,2,3,4,5,6,7,8,9})), 3);
		
		System.out.println("Size: " + s.size() + " sets.");
		
		for (Set<Integer> subset : s) {
			System.out.println(subset);
		}
	}
}
