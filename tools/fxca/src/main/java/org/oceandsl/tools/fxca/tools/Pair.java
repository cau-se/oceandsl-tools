package org.oceandsl.tools.fxca.tools;

import java.util.Comparator;

public class Pair <T1, T2>{
	
	public T1 first;
	public T2 second;
	
	public Pair(T1 first, T2 second) {
		this.first = first;
		this.second = second;
	}
	
	public T1 getFirst() {
		return first;
	}
	
	public T2 getSecond() {
		return second;
	}
	
	@Override
	public int hashCode() {
		return getFirst().hashCode() + getSecond().hashCode();
	}
	
	@Override
	public boolean equals(Object compareto) {
		if (this == compareto) return true;
		if (compareto == null) return false;
		if (!(compareto instanceof Pair<?,?>)) return false;
		
		var comparePair = (Pair<?,?>) compareto;
		
		return     (comparePair.first.getClass().equals(this.first.getClass()))
				&& (comparePair.second.getClass().equals(this.second.getClass()))
				&& (comparePair.first.equals(this.first)) 
				&& (comparePair.second.equals(this.second));
	}

	public static <T extends Comparable<T>, S extends Comparable<S>> Comparator<Pair<S,T>> getComparatorFirstSecond() {
		return (pair1, pair2) -> {
			int result = pair1.first.compareTo(pair2.first);
			if (result != 0) {
				return result;
			}
			return pair1.second.compareTo(pair2.second);
		};
	}
	
	@Override
	public String toString() {
		return getFirst() + " -> " + getSecond();
	}
}
