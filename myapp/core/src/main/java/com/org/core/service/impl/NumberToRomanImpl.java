package com.org.core.service.impl;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.org.core.exception.MyEndpointException;
import com.org.core.service.NumberToRoman;

/**
 * Service to generate roman numeral equivalent of input number. Reference:
 * https://en.wikipedia.org/wiki/Roman_numerals
 * 
 * @author gbehl
 *
 */
@Component(service = NumberToRoman.class, immediate = true)
public class NumberToRomanImpl implements NumberToRoman {

	private static final Logger LOG = LoggerFactory.getLogger(NumberToRomanImpl.class);

	/**
	 * method to convert input number to roman numeral
	 * 
	 * @param number input number
	 * @exception throws MyEndpointException
	 * @return roman numeral
	 */
	@Override
	public String getRomanNumeral(int number) throws MyEndpointException {
		// FIXME: Update the implementation to a better one if requirements change
		// in future
		String Thousands[] = { "", "M", "MM", "MMM" };
		String Hundreds[] = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
		String Tens[] = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
		String Units[] = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };

		String str = "";
		try {
			str = Thousands[number / 1000] + Hundreds[(number % 1000) / 100] + Tens[(number % 100) / 10]
					+ Units[number % 10];
			LOG.debug("Roman numeral for " + number + " is: " + str);
		} catch (Exception e) {
			LOG.error("Exception occurred while converting number to roman numeral", e);
			throw new MyEndpointException("", e.getMessage());
		}
		return str;
	}

}