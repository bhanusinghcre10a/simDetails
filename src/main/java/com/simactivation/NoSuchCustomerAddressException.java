package com.simactivation;

public class NoSuchCustomerAddressException  extends Exception {
	private static final long serialVersionUID = 1L;
	public NoSuchCustomerAddressException() {
		super();
	}
	public NoSuchCustomerAddressException(String errors) {
		super(errors);
	}
}
