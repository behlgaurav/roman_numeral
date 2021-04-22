package com.org.core.service;

import com.org.core.exception.MyEndpointException;

public interface NumberToRoman {
	String getRomanNumeral(int number) throws MyEndpointException;
}