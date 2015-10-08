package parser;

/**
 * Represents a connection between 2 ClassObjects.
 * 
 */
public class Connection {

	public enum Type {
		call, create, ref, use, inh, has, wildcard
	};

	protected ClassObject From;
	protected ClassObject To;
	protected Type type;

	public Connection(ClassObject A, ClassObject B, Type type) {
		From = A;
		To = B;
		this.type = type;
	}

	/**
	 * Returns a ClassObject representing the first part of this Connection.
	 * 
	 */
	public ClassObject get_From() {
		return From;
	}

	/**
	 * Returns a ClassObject representing the second part of this Connection.
	 * 
	 */
	public ClassObject get_To() {
		return To;
	}

	/**
	 * Returns an enum type representing the type of this Connection.
	 * 
	 */
	public Type get_type() {
		return type;
	}

	/**
	 * Overriding toString() to print the appropriate results.
	 */
	@Override
	public String toString() {
		return From + " " + To + " " + type;
	}

	/**
	 * Returns a String representing the type of this Connection.
	 * 
	 * @return s String equivalent of this object's Connection.Type
	 */
	public String getType() {
		String s;
		switch (this.get_type()) {
		case use:
			s = "uses";
			break;
		case inh:
			s = "inherits";
			break;
		case create:
			s = "creates";
			break;
		// Either interface or abstract
		case call:
			s = "calls";
			break;
		case ref:
			s = "references";
			break;
		case has:
			s = "has";
			break;
		case wildcard:
			s = "*";
		default:
			s = null;
		}
		return s;
	}
}