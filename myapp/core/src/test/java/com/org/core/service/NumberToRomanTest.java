/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.org.core.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.org.core.exception.MyEndpointException;
import com.org.core.service.impl.NumberToRomanImpl;

import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * Simple test verifying the NumberToRomanTest
 */
@ExtendWith({ AemContextExtension.class })
class NumberToRomanTest {

	private NumberToRoman numberToRoman;

	@BeforeEach
	void setUp() throws Exception {
		numberToRoman = new NumberToRomanImpl();
	}

	@Test
	void testPositive() throws Exception {
		final String expected = "III";
		String roman = numberToRoman.getRomanNumeral(3);
		assertEquals(expected, roman);
	}

	@Test
	void testNegative() throws Exception {
		final String expected = "IIII";
		String roman = numberToRoman.getRomanNumeral(3);
		assertNotEquals(expected, roman);
	}

	@Test
	void testException() throws Exception {
		Exception exception = assertThrows(MyEndpointException.class, () -> {
			numberToRoman.getRomanNumeral(5000);
		});

		String expectedMessage = "5";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

}
