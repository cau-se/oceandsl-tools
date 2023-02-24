package org.oceandsl.tools.fxca.tools;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Supplier;

public class IndexIterator<T> implements Iterator<T>{
	
	private Supplier<Integer> getLength;
	private Function<Integer, T> getElement;
	private int index;
	
	public IndexIterator(Supplier<Integer> getLength, Function<Integer, T> getElement) {
		this.getLength = getLength;
		this.getElement = getElement;
		this.index = 0;
	}

	@Override
	public boolean hasNext() {
		return index < getLength.get();
	}

	@Override
	public T next() {
		return getElement.apply(index++);
	}
}
