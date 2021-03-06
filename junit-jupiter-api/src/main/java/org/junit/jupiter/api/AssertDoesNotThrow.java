/*
 * Copyright 2015-2018 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.jupiter.api;

import static org.junit.jupiter.api.AssertionUtils.buildPrefix;
import static org.junit.jupiter.api.AssertionUtils.nullSafeGet;

import java.util.function.Supplier;

import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;
import org.opentest4j.AssertionFailedError;

/**
 * {@code AssertDoesNotThrow} is a collection of utility methods that support
 * explicitly asserting that a given code block does not throw an exception.
 *
 * @since 5.2
 */
class AssertDoesNotThrow {

	///CLOVER:OFF
	private AssertDoesNotThrow() {
		/* no-op */
	}
	///CLOVER:ON

	static void assertDoesNotThrow(Executable executable) {
		assertDoesNotThrow(executable, (Object) null);
	}

	static void assertDoesNotThrow(Executable executable, String message) {
		assertDoesNotThrow(executable, (Object) message);
	}

	static void assertDoesNotThrow(Executable executable, Supplier<String> messageSupplier) {
		assertDoesNotThrow(executable, (Object) messageSupplier);
	}

	private static void assertDoesNotThrow(Executable executable, Object messageOrSupplier) {
		try {
			executable.execute();
		}
		catch (Throwable t) {
			String message = buildPrefix(nullSafeGet(messageOrSupplier)) + "Unexpected exception thrown: "
					+ t.getClass().getName();
			throw new AssertionFailedError(message, t);
		}
	}

	static <T> T assertDoesNotThrow(ThrowingSupplier<T> supplier) {
		return assertDoesNotThrow(supplier, (Object) null);
	}

	static <T> T assertDoesNotThrow(ThrowingSupplier<T> supplier, String message) {
		return assertDoesNotThrow(supplier, (Object) message);
	}

	static <T> T assertDoesNotThrow(ThrowingSupplier<T> supplier, Supplier<String> messageSupplier) {
		return assertDoesNotThrow(supplier, (Object) messageSupplier);
	}

	private static <T> T assertDoesNotThrow(ThrowingSupplier<T> supplier, Object messageOrSupplier) {
		try {
			return supplier.get();
		}
		catch (Throwable t) {
			String message = buildPrefix(nullSafeGet(messageOrSupplier)) + "Unexpected exception thrown: "
					+ t.getClass().getName();
			throw new AssertionFailedError(message, t);
		}
	}

}
