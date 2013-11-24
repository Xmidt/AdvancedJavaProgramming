package assignment4.processors;

import java.util.Iterator;

import assignment4.dataflow.Node;

/**
 * A processor defines the internal processing logic within a {@link Node}. It
 * functionally defines input-output relationship for the node. Each input may
 * generate zero or more outputs. This is modeled by using {@link Iterator}.
 * 
 * @author fmma
 * 
 * @param <I>
 *            The input element type.
 * @param <O>
 *            The output element type.
 */
public interface Processor<I, O> {

	/**
	 * Consumes and input and produces zero or more outputs.
	 * 
	 * @param in
	 *            The input element to be consumed.
	 * @return An iterator over the output elements produces by processing the
	 *         input element. The iterator may be empty or it may contain one or
	 *         more elements. It may even contain an infinite number of
	 *         elements.
	 */
	public Iterator<O> process(I in);

}
