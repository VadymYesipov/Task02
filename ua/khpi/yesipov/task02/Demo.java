package ua.khpi.yesipov.task02;

import java.util.Iterator;

/**
 * The Demo class shows the all necessary methods for operating the list
 * that has type MyListImpl.
 *
 * @version     1.0 06 November 2017
 * @author      Vadym Yesipov
 */
public class Demo {

    /**
     * Shows the work of the all methods that is realized in MyListImpl class
     * and the package interfaces.
     *
     * @param args inner parameters that was deployed with the program.
     */
	public static void main(String[] args) {
		System.out.println("==== Part1");
		MyList list = new MyListImpl();
		// [A, A2]
		list.add("A");
		list.add("A2");
		System.out.println(list);
		// []
		list.clear();
		System.out.println(list);
		// [A, A3]
		list.add("A");
		list.add("A2");
		list.add("A3");
		list.remove("A2");
		System.out.println(list);
		// AA3
		for (Object el : list.toArray()) {
			System.out.print(el);
		}
		System.out.println();
		// 2
		System.out.println(list.size());
		// false
		System.out.println(list.contains("B"));
		// true
		System.out.println(list.contains("A3"));
		// true
		list.add("A2");
		MyList anotherList = new MyListImpl();
		anotherList.add("A");
		anotherList.add("A2");
		System.out.println(list.containsAll(anotherList));
		// false
		anotherList.add("B");
		System.out.println(list.containsAll(anotherList));
		// true
		list.add("B");
		System.out.println(list.containsAll(anotherList));
		System.out.println("==== Part2");
		list = new MyListImpl();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		// 1 2 3 4
		Iterator<Object> it = list.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();
		// [1, 3, 4]
		it = list.iterator();
		it.next();
		it.next();
		it.remove();
		System.out.println(list);
		// 3
		System.out.println(it.next());
		// [1, 4]
		it.remove();
		System.out.println(list);
		// class java.lang.IllegalStateException
		try {
			it.remove();
		} catch (IllegalStateException ex) {
			System.out.println(ex.getClass());
		}
		System.out.println("==== Part3");
		list = new MyListImpl();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		// 1 2 3 4
		ListIterator lit = ((ListIterable) list).listIterator();
		while (lit.hasNext()) {
			System.out.print(lit.next() + " ");
		}
		System.out.println();
		// 4 3 2 1
		while (lit.hasPrevious()) {
			System.out.print(lit.previous() + " ");
		}
		System.out.println();
		list = new MyListImpl();
		lit = ((ListIterable) list).listIterator();
		// false
		System.out.println(lit.hasNext());
		// false
		System.out.println(lit.hasPrevious());
		// Elemenet
		list.add("Element");
		System.out.println(lit.next());
		// false
		System.out.println(lit.hasNext());
		// true
		System.out.println(lit.hasPrevious());
	}
}
