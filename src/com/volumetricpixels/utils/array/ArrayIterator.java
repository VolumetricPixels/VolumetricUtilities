package com.volumetricpixels.utils.array;

import java.util.Iterator;

public interface ArrayIterator<E> extends Iterator<E> {
    public E prev();

    public int nextIndex();

    public int prevIndex();
}
