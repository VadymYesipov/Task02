package ua.khpi.yesipov.task02;

/**
 * The ListIterable interface provides just one method
 * for retrieving an iterator.
 *
 * @version     1.0 06 November 2017
 * @author      Vadym Yesipov
 */
public interface ListIterable {

    /**
     *
     * @return an object of the ListIterator that can get round a list and
     *         use specified methods.
     */
	ListIterator listIterator();
}
