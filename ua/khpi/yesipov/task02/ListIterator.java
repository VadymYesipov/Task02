package ua.khpi.yesipov.task02;

import java.util.Iterator;

/**
 * The ListIterator interface provides the simple methods for
 * implementation the iterator that can move forwards(due to extending)
 * and backwards.
 *
 * @version     1.0 06 November 2017
 * @author      Vadym Yesipov
 */
public interface ListIterator extends Iterator<Object> {

    /**
     * Checks if the previous element exists.
     *
     * @return true if this list iterator has more elements when traversing
     *         the list in the reverse direction.
     */
	boolean hasPrevious();

    /**
     * Returns the previous element in the list and
     * moves the cursor position backwards.
     *
     * @return the previous element in the list
     */
	Object previous();

    /**
     *
     * @param e replaces the last element returned by next or
     *          previous with the specified element.
     */
	void set(Object e);

    /**
     * Removes the last element returned by next or
     * previous with the specified element.
     */
	void remove();
}
