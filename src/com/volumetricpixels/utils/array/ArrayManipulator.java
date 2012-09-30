package com.volumetricpixels.utils.array;

/**
 * Simple utility for easily controlling an Array when after-compilation array
 * type doesn't matter too much.
 * 
 * @param <E>
 *            The type of Array to store
 */
@SuppressWarnings("unchecked")
public class ArrayManipulator<E> {
    private Object[] array;

    public ArrayManipulator(E[] array) {
        this.array = array;
    }

    public E get(int index) {
        return (E) array[index];
    }

    public E set(int index, E value) {
        return (E) (array[index] = value);
    }

    public boolean contains(E object) {
        for (Object e : array) {
            if (e.equals(object)) {
                return true;
            }
        }

        return false;
    }

    public void remove(int index) {
        Object[] localArrayOfObject = array;
        array = new Object[array.length - 1];

        boolean pastIndex = false;
        for (int i = 0; i < array.length; i++) {
            if (i != index) {
                array[pastIndex ? i - 1 : i] = localArrayOfObject[pastIndex ? i - 1 : i];
            } else {
                pastIndex = true;
            }
        }
    }

    public ArrayIterator<E> iterator() {
        return new ArrayIterator<E>(this.array);
    }
}
