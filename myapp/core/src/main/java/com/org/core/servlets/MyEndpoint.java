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
package com.org.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.org.core.exception.MyEndpointException;
import com.org.core.service.NumberToRoman;

/**
 * Servlet to expose endpoint for converting number to roman.
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent.
 * 
 */
@Component(service = Servlet.class, property = { "sling.servlet.paths=/bin/romannumeral",
		Constants.SERVICE_DESCRIPTION + "=Endpoint for Number to Roman" })
public class MyEndpoint extends SlingSafeMethodsServlet {

	// FIXME: Assumption: Endpoint pattern is flexible otherwise setup dispatcher
	// for url shortener
	private static final Logger LOG = LoggerFactory.getLogger(MyEndpoint.class);

	private static final long serialVersionUID = 1L;

	@Reference
	private transient NumberToRoman numberToRoman;

//	private static final Pattern REGEX_INPUT_PATTERN = Pattern.compile("(\\d)+"); // like {number}

	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServletException, IOException {

		JSONObject json = new JSONObject();
		// initialize the number to any negative value
		int number = -1;
		String errorMsg = null, numStr = null;
		RequestParameter param = req.getRequestParameter("query");
		// FIXME: NPE not handled if the query param is missing. Get exact requirements
		// to fix it
		if (null == param) {
			errorMsg = "Invalid input, the querystring param should be in '?query={number}' format";
		} else {
			try {
				// FIXME: PatternMatcher might be an alternative based on requirements
				numStr = param.toString().substring(param.toString().indexOf("{") + 1, param.toString().indexOf("}"));

				if (StringUtils.isNumeric(numStr)) {
					number = Integer.parseInt(numStr);
					// Extension1: number check of 1-3999
					if (number > 0 && number < 4000) {
						// FIXME: If ordering is required in response json then use array, TreeMap or
						// other sort collection
						try {
							json.put("input", number);
							json.put("output", numberToRoman.getRomanNumeral(number));
						} catch (MyEndpointException e) {
							LOG.error("Exception occured while parsing number " + number + " to json: ", e);
							errorMsg = "Error occured while converting number to roman numeral";
						} catch (JSONException e) {
							LOG.error("Exception occured while creating json for " + number + " to json: ", e);
							errorMsg = "Error occured while creating json for " + number + e.getMessage();
						}
					} else {
						errorMsg = "Number must be in the range 1 - 3999 within curly braces like {34}";
					}
				} else {
					errorMsg = "Invalid input, The input must be a number within 1 - 3999 wrapped within curly braces like {34}";
				}
			} catch (IndexOutOfBoundsException e) {
				LOG.error("Exception occured while parsing input: ", e);
				errorMsg = "Invalid input, The input must be a number within 1 - 3999 wrapped within curly braces like {34}";
			}
		}
		resp.setContentType("text/plain");
		resp.getWriter().write(errorMsg != null ? errorMsg : json.toString());
	}
}
