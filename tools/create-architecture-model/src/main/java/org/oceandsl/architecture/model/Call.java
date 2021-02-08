/**
 * 
 */
package org.oceandsl.architecture.model;

/**
 * @author reiner
 *
 */
public class Call {

	private final String fromClassSignature;
	private final String fromOperationSignature;
	private final String toClassSignature;
	private final String toOperationSignature;

	public Call(ECallType before, String fromClassSignature, String fromOperationSignature, String toClassSignature,
			String toOperationSignature) {
		this.fromClassSignature = fromClassSignature;
		this.fromOperationSignature = fromOperationSignature;
		this.toClassSignature = toClassSignature;
		this.toOperationSignature = toOperationSignature;
	}

	public final String getFromClassSignature() {
		return fromClassSignature;
	}

	public final String getFromOperationSignature() {
		return fromOperationSignature;
	}

	public final String getToClassSignature() {
		return toClassSignature;
	}

	public final String getToOperationSignature() {
		return toOperationSignature;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof Call) {
			Call otherCall = (Call)object;
			return compareValues(this.fromClassSignature, otherCall.getFromClassSignature()) &&
					compareValues(this.fromOperationSignature, otherCall.getFromOperationSignature()) &&
					compareValues(this.toClassSignature, otherCall.getToClassSignature()) &&
					compareValues(this.toOperationSignature, otherCall.getToOperationSignature());
		} else
			return false;
	}
	
	private boolean compareValues(String from, String to) {
		if (from == null && to == null)
			return true;
		return from != null && to != null && from.equals(to);
	}

}
