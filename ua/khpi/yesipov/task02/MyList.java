package ua.khpi.yesipov.task02;

/**
 * The MyList interface provides the simple methods for operating the list.
 *
 * @version     1.0 06 November 2017
 * @author      Vadym Yesipov
 */
public interface MyList extends Iterable<Object>{

	/**
	 * Appends the specified element to the end of this list.
	 *
	 * @param e initializes the value of the current node.
	 */
	void add(Object e);

    /**
     * Removes all of the elements from this list.
     */
	void clear();

    /**
     * Removes the first occurrence of the specified element from this list.
     *
     * @param o must be found and deleted.
     * @return  <code>true</code> if the inner parameter was found and deleted.
     */
	boolean remove(Object o);

    /**
     *
     * @return an array containing all of the elements in this list
     *         in proper sequence.
     */
	Object[] toArray();

    /**
     *
     * @return the number of elements in this list.
     */
	int size();

    /**
     * Returns true if this list contains the specified element.
     *
     * @param o the value that should be found in the list.
     * @return  <code>true</code> if the parameter exists in the list.
     */
	boolean contains(Object o);

    /**
     * Returns true if this list contains all of the elements
     * of the specified list.
     *
     * @param c represents the list of parent's type MyList.
     * @return  <code>true</code> if the list includes the parameter.
     */
	boolean containsAll(MyList c);
}
