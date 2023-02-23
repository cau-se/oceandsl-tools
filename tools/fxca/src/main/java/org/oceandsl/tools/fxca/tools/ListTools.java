package org.oceandsl.tools.fxca.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ListTools {

	// mutable versions of List.of
	public static <T> List<T> ofM() {
		return new ArrayList<T>();
	}

	public static <T> List<T> ofM(final T element) {
		final List<T> result = ofM();
		result.add(element);
		return result;
	}
	
	public static <T> List<T> ofM(final List<T> list1, final List<T> list2, Comparator<T> comparator) {
		final List<T> newList = (list1 == null)? ofM() : ofM(list1);
		if (list2 != null) { 
			newList.addAll(list2);
		}
		Collections.sort(newList, comparator);
		return newList;
	}

	public static<T> List<T> ofM(Iterable<T> elements) {
		return ofM(elements.iterator());
	}

	public static<T> List<T> ofM(Iterable<T> elements, Comparator<T> comparator) {
		List<T> result = ofM(elements.iterator());
		Collections.sort(result, comparator);
		return result;
	}

	public static<T> List<T> ofM(Iterator<T> iterator) {
		final List<T> newList = ofM();
		iterator.forEachRemaining(newList::add); 
		return newList;
	}
	
	public static <T> T getUniqueElement (Collection<T> collection) {
		if (collection.size() != 1) {
			HashSet<T> checkForDoubles = new HashSet<T>(collection);
			checkForDoubles.forEach(System.out::println);
			if (checkForDoubles.size() == 1) {
				return getUniqueElement(checkForDoubles);
			}
			throw new IllegalArgumentException("Unique element exists only for singletons.");
			
		}
		
		return collection.iterator().next();
	}
	
	public static <T> T getUniqueElementIfNonEmpty (Collection<T> collection, T alternative) {
		return collection.isEmpty()? alternative : getUniqueElement(collection);
	}

}
