package com.volumetricpixels.utils.array;

import java.util.Iterator;

import com.volumetricpixels.utils.Utilities;

public class ArrayIterator<E> implements Iterator<E> {
    private Object[] am;
    private int index = 0;

    private int[] removed;
    private int removedIndex = 0;

    public ArrayIterator(Object[] array) {
        this.am = array;
        this.removed = new int[array.length];
    }

    public boolean hasNext() {
        try {
            return am[index + 1] != null;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public E next() {
        do {
            index++;
        } while (Utilities.intArrayContains(removed, index));

        return (E) am[index];
    }

    public void remove() {
        removed[removedIndex] = index;
        removedIndex++;
    }

    @SuppressWarnings("unchecked")
    public E prev() {
        do {
            index--;
        } while (Utilities.intArrayContains(removed, index));

        return (E) am[index];
    }

    public int nextIndex() {
        return index + 1;
    }

    public int prevIndex() {
        return index - 1;
    }
}
