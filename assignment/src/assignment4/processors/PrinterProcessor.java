package assignment4.processors;

import java.util.Iterator;

/**
 * A processor that simply consumes input by printing the input and producing no
 * output.
 * 
 * @author fmma
 * 
 * @param <T> The type of the objects to print.
 */
public class PrinterProcessor<T> implements Processor<T, Object> {

	private String id;

	public PrinterProcessor(String id) {
		this.id = id;
	}
	
	@Override
	public Iterator<Object> process(T in) {
		System.out.println("[" + id +"] " + in);
		return Iterators.empty();
	}

}
