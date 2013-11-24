package assignment4.processors;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class for easily constructing commonly used iterators.
 * 
 * @author fmma
 *
 */
public class Iterators {

	/**
	 * Construct a one element iterator.
	 * 
	 * @param x The element of the iterator.
	 * 
	 * @return A one element iterator of x.
	 */
	public static <T> Iterator<T> singleton(final T x) {
		return new Iterator<T>() {

			boolean b = true;

			@Override
			public boolean hasNext() {
				return b;
			}

			@Override
			public T next() {
				if (b) {
					b = false;
					return x;
				} else
					throw new NoSuchElementException();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	/**
	 * Constructs the empty iterator.
	 * 
	 * @return An iterator containing no elements.
	 */
	public static <T> Iterator<T> empty() {
		return new Iterator<T>() {

			@Override
			public boolean hasNext() {
				return false;
			}

			@Override
			public T next() {
				throw new NoSuchElementException();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

}
