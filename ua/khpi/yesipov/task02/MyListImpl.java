package ua.khpi.yesipov.task02;

import java.util.Iterator;

/**
 * The MyListImpl class provides the all necessary methods for operating
 * the list that was made in the form of linked list.
 *
 * @version     1.0 06 November 2017
 * @author      Vadym Yesipov
 */
public class MyListImpl implements MyList, ListIterable {

    /**
     * The last and the first fields mean the beginning and the end
     * of the list, don't have any value in their inner fields.
     * They are just auxiliary element of the list.
     */
    private Node last;
    private Node first;

    /**
     * Means the size of the list, except last and first
     * (they don't have any values).
     */
    private int size;

    /**
     * Keeps a reference on such type of iterators as:
     * IteratorImpl, ListIteratorImpl.
     * Is used for getting round the list.
     */
    private Iterator<Object> iterator;

    /**
     * The Node class is used for the MyListImpl class to be operated
     * and keep value and links on previous and next nodes in the list.
     */
    private class Node {

        /**
         * Keeps the link on the previous node of the list
         */
        private Node previous;
        /**
         * Keeps the link on the next node of the list
         */
        private Node next;

        /**
         * Keeps the value of the current node
         */
        private Object value;

        /**
         * Initializes fields by default. Should be used just for
         * auxiliary fields of the outer class like first and last.
         */
        public Node() {
            previous = null;
            next = null;
            value = null;
        }

        /**
         * Initializes fields for nodes that have a value. Is used for
         * the method add in the outer class. The previous field is
         * initialized with the previous node of the last node, the next field
         * is initialized with the last field of the outer class,
         * the previous of the last one and the next of the previous one are
         * initialized with the current one.
         *
         * @param value initializes the field of the current node.
         */
        public Node(Object value) {
            this.value = value;
            previous = last.previous;
            next = last;
            last.previous = this;
            previous.next = this;
        }
    }

    /**
     * Creates the list with the size, the first and the last nodes.
     * The last and the first nodes tie via links between themselves.
     * The nodes mustn't include any value and should be used for the iterator
     * and several methods besides this constructor.
     */
    public MyListImpl() {
        first = new Node();
        last = new Node();
        first.next = last;
        last.previous = first;
        size = 0;
    }

    /**
     * Creates the new node. Initializes its value by parameter e.
     *
     * @param e initializes the value of the current node.
     */
    @Override
    public void add(Object e) {
        new Node(e);
        size++;
    }

    /**
     * Clears the whole list of each node, except the first and the last nodes.
     * Uses the iterator and its methods:
     * hasNext() - to check existence of the next node,
     * returns <code>true</code> if exists;
     * next() - to shift the pointer on the next node;
     * remove() - to remove the current node.
     */
    @Override
    public void clear() {
        iterator = iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }

    /**
     * Removes the inner parameter if it exists in the list
     * and returns <code>true</code>. Uses contains method to find out
     * whether the parameter is included in the list or not
     * and remove method to remove the parameter in it.
     *
     * @param o must be found and deleted.
     * @return <code>true</code> if the inner parameter was found and deleted.
     */
    @Override
    public boolean remove(Object o) {
        if (contains(o)) {
            iterator.remove();
            return true;
        }
        return false;
    }

    /**
     * Converts and returns the array of objects if the size doesn't equal 0,
     * otherwise it returns <code>null</code>. Uses the iterator to
     * get round the list and retrieve the value of the current node
     * in the specific position in the created array of objects.
     *
     * @return <code>null</code> if the size == 0, otherwise it returns
     * the array of objects.
     */
    @Override
    public Object[] toArray() {
        if (size == 0) {
            return null;
        } else {
            iterator = iterator();
            Object[] objects = new Object[size];
            for (int i = 0; iterator.hasNext(); i++) {
                objects[i] = iterator.next();
            }
            return objects;
        }
    }

    /**
     * @return the size of the list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Searches in the list the parameter. Uses iterator that and its methods:
     * hasNext() - to check existence of the next node,
     * returns <code>true</code> if exists;
     * next() - to shift the pointer on the next node(after this action
     * the pointer compares to the parameter: if they are equal,
     * <code>true</code> will be returned).
     *
     * @param o the value that should be found in the list.
     * @return <code>true</code> if the parameter exists in the list.
     */
    @Override
    public boolean contains(Object o) {
        iterator = iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == o) {
                return true;
            }
        }
        return false;
    }

    /**
     * Searches in the list the parameter. Uses the method contains to find out
     * existence of the inner list. If the list doesn't include
     * just a single element, the contains method returns <code>false</code>.
     * Otherwise searching goes on and doesn't break till the method returns
     * <code>true</code>.
     *
     * @param c represents the list of parent's type MyList
     *          and is used for giving its values to contains method
     *          to find out value's existence.
     * @return <code>true</code> if the list includes the parameter
     */
    @Override
    public boolean containsAll(MyList c) {
        for (Object value : c) {
            if (!contains(value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Creates the array of objects to get round the list of the values
     * (not nodes) and make the string result. Uses ternary operator to
     * improve the result.
     *
     * @return the string result that includes square brackets
     * with values between themselves if they exist in the list,
     * otherwise the string is just empty square brackets.
     */
    @Override
    public String toString() {
        Object[] objects = toArray();
        if (objects == null) {
            return "[]";
        }
        String result = "[";
        for (int i = 0; i < objects.length; i++) {
            String string = objects[i].toString();
            result += i == objects.length - 1 ? string + "]" : string + ", ";
        }
        return result;
    }

    /**
     * @return the object of IteratorImpl to get round the list
     * from the beginning to the end.
     */
    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    /**
     * The IteratorImpl class provides the all necessary methods
     * to get round the list from the beginning to the end and remove elements.
     */
    private class IteratorImpl implements Iterator<Object> {

        /**
         * This field is the pointer of the current place of the list.
         * Initially, it is initialized by the first node in the list,
         * that doesn't have a value in it.
         */
        protected Node switcher = first;

        /**
         * The flag is used for methods that shift the switcher
         * or change the list. Initially, it is false,
         * but it changes its value after the methods next and remove.
         */
        private boolean flag = false;

        /**
         * This is the auxiliary field for the extending inner class.
         */
        private int i;

        /**
         * If the next node exists and it's not the last field,
         * that doesn't have a value, then the method returns true.
         * Otherwise the pointer is placed on the last node(that has a value),
         * therefore it's initialized by the last node in the list
         * (doesn't has a value). This node s used for extending class
         * to get round back to the beginning.
         *
         * @return true if the iteration has more elements
         */
        public boolean hasNext() {
            if (switcher.next != last) {
                return true;
            }
            switcher = last;
            return false;
        }

        /**
         * Changes the flag if the next node isn't the last node(without value)
         * or null, shifts the switcher on the next position in the list and
         * initializes the field i with 0. Then returns the value of
         * the recent placed node, otherwise null if the condition in
         * the very beginning didn't come true.
         *
         * @return the next element in the iteration
         */
        public Object next() {
            if (hasNext()) {
                flag = true;
                switcher = switcher.next;
                i = 0;
                return switcher.value;
            }
            return null;
        }

        /**
         * Removes from the underlying collection the last element
         * returned by this iterator and reinitialize the links between
         * the previous and the next nodes, if the flag is true.
         * Otherwise it throws exception.
         *
         * @throws IllegalStateException If the flag isn't true that means
         *                               repeated method call.
         */
        public void remove() {
            if (!flag) {
                throw new IllegalStateException();
            }
            if (switcher.next == null) {
                Node previous = switcher.previous;
                previous.next = null;
                switcher = previous;
            } else {
                Node next = switcher.next;
                Node previous = switcher.previous;
                next.previous = previous;
                previous.next = next;
                switcher = previous;
            }
            flag = false;
            size--;
        }
    }

    /**
     * @return the object of ListIteratorImpl to get round the list
     * from the end to the beginning.
     */
    @Override
    public ListIterator listIterator() {
        return new ListIteratorImpl();
    }

    /**
     * The ListIteratorImpl class provides the all necessary methods
     * to get round the list from the end to the beginning, remove
     * and set elements.
     */
    private class ListIteratorImpl extends IteratorImpl implements ListIterator {

        /**
         * Returns false if the previous node in the list is null or the first
         * node in the list(without value). Otherwise returns true.
         *
         * @return true if this list iterator has more elements when
         * traversing the list in the reverse direction.
         */
        @Override
        public boolean hasPrevious() {
            if (switcher.previous != first) {
                return true;
            }
            switcher = first;
            return false;
        }

        /**
         * Changes the flag if the previous node isn't the first node(without
         * value) or null, shifts the switcher on the previous position in
         * the list and initializes the field i from the parent's class with 1.
         * Then returns the value of the recent placed node, otherwise null
         * if the condition in the very beginning didn't come true.
         *
         * @return returns the previous element in the list and
         * moves the cursor position backwards.
         */
        @Override
        public Object previous() {
            if (hasPrevious()) {
                super.flag = true;
                switcher = switcher.previous;
                super.i = 1;
                return switcher.value;
            }
            return null;
        }

        /**
         * Changes the current node's value if the flag is true and then
         * reverses the value of it.
         *
         * @param e changes the current node's value.
         */
        @Override
        public void set(Object e) {
            if (super.flag) {
                switcher.value = e;
                super.flag = false;
            }
        }

        /**
         * Removes from the underlying collection the last element
         * returned by this iterator and reinitialize the links between
         * the previous and the next nodes, if the flag is true.
         * Otherwise it throws exception. If before removing the iterator
         * had moved forwards, switcher would have remained. Otherwise it would
         * have been initialized by the next node of the list.
         *
         * @throws IllegalStateException If the flag is true, but that means
         *                               repeated method call.
         */
        @Override
        public void remove() {
            super.remove();
            switcher = super.i == 0 ? switcher : switcher.next;
        }
    }
}