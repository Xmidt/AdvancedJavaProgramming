package assignment4.djava;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * A box that encapsulates an object, providing access to fields and method
 * through reflection.
 * 
 * @author fmma
 * 
 */
public class Box {
	
	/**
	 * The boxed object.
	 */
	private Object boxedObject;
	static ClassLoader cl = ClassLoader.getSystemClassLoader();

	/**
	 * Creates a box of a given object.
	 * 
	 * @param obj
	 *            The object to box.
	 */
	public Box(Object obj) {
		this.boxedObject = obj;
	}

	/**
	 * Creates a new boxed object, by finding the constructor identified by
	 * className and the types of the input arguments boxedArgs, and calling it
	 * using reflection. If an exception is thrown, set the boxed object to the
	 * exception.
	 * 
	 * @param className
	 *            The class name of the constructor (e.g. "java.util.List").
	 * @param boxedArgs
	 *            The (boxed) arguments given to the constructor.
	 */
	public Box(String className, Box... boxedArgs) {
		
		Class<?> boxedOBject = null;
		
		try {
			
			boxedObject = cl.loadClass(className);
			boxedObject.
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * Lookup and return the content of a field identified by fieldName.
	 * 
	 * @param fieldName
	 *            The name of one of the fields of the boxed object.
	 * @return The (boxed) content of that field on success. A boxed exception
	 *         on failure.
	 */
	Box get(String fieldName) {
		
		Field[] fields = ((Class) boxedObject).getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			String[] temp = fields[i].toString().split(" ");
			if (temp[2].equalsIgnoreCase(fieldName)) {
				
				System.out.println(temp[2]);
			}
		}
		
		return null;
	}

	/**
	 * Set the content of a field identified by fieldName.
	 * 
	 * @param fieldName
	 *            The name of one of the fields of the boxed object.
	 * @param value
	 *            A value to set the field to.
	 * @return null on success, a boxed exception on failure.
	 */
	Box set(String fieldName, Box value) {
		
		// TODO
		return null;
	}

	/**
	 * Invoke a method on the boxed object using reflection.
	 * 
	 * @param methodName
	 *            The name of the method.
	 * @param boxedArgs
	 *            The input arguments to the method.
	 * @return The return type of the method on success (void returns null), or
	 *         a boxed exception on failure.
	 */
	Box call(String methodName, Box... boxedArgs) {
		// TODO
		return null;
	}

	/**
	 * Returns the simple name of the class of the boxed object, followed by a
	 * key-value print of contents of the fields of the boxed object. E.g.
	 * Employee[age=30, salary=4000, name="John doe"].
	 * 
	 * Bonus points: If a field does not declare a toString method, use toString
	 * of Box recursively on that field. Recursion should be limited to some
	 * depth, so that cyclic references wont cause toString to loop forever.
	 */
	@Override
	public String toString() {
		// TODO
		return null;
	}
}
