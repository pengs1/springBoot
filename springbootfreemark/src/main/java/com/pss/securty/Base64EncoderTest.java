package com.pss.securty;

import com.thoughtworks.xstream.core.util.Base64Encoder;

public class Base64EncoderTest {
	
	private static final String testResource = "pengs1 test base64";
	
	public static void main(String[] args) {
		Base64Encoder encoder = new Base64Encoder();
		String enCode = encoder.encode(testResource.getBytes());
		System.out.println("encode:"+enCode);
		
		byte[] deCode = encoder.decode(enCode);
		System.out.println("decode:"+new String(deCode));
	}
}
