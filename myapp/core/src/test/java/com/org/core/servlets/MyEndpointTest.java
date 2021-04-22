/*
 *  Copyright 2018 Adobe Systems Incorporated
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
package com.org.core.servlets;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class MyEndpointTest {

	private final AemContext ctx = new AemContext();

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void doGet(AemContext context) throws ServletException, IOException {
//		context.build().resource("/content/test", "jcr:title", "resource title").commit();
//		context.currentResource("/content/test");
//
//		MockSlingHttpServletRequest request = context.request();
//		MockSlingHttpServletResponse response = context.response();
//
//		fixture.doGet(request, response);
//
//		assertEquals("Title = resource title", response.getOutputAsString());
	}
}
