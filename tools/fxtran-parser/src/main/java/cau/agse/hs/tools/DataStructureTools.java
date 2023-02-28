package cau.agse.hs.tools;

import java.util.Collection;

public class DataStructureTools {

    public static <T> T getUniqueElement(final Collection<T> collection) {

        if (collection.size() != 1) {
            throw new IllegalArgumentException("Unique element exists only for singletons.");
        }

        return collection.iterator().next();

    }

    public static <T> T getUniqueElementIfNonEmpty(final Collection<T> collection, final T alternative) {
        return collection.isEmpty() ? alternative : DataStructureTools.getUniqueElement(collection);
    }
}
