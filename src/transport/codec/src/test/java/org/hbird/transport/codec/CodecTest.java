package org.hbird.transport.codec;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class CodecTest {
	
	private long performanceTestRuns = Integer.MAX_VALUE/10;
	
	@Test
	public void intFromBytes() {
		byte[] anArray = {(byte) 0x2A, (byte) 0xAA, (byte) 0xAA, (byte) 0xAA};
		int expected = 715827882;
		
		int result = Codec.bigEndianIntFromBytes(anArray);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void stringFromBytes() {
		byte[] anArray = {(byte) '\u0048', (byte) '\u0062', (byte) '\u0069', (byte) '\u0072', (byte) '\u0064' };
		String expected = "Hbird";
		
		String result = Codec.stringFromBytes(anArray);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void anythingFromIntAsBytes() throws Exception {
		byte[] anArray = {(byte) 0x2A, (byte) 0xAA, (byte) 0xAA, (byte) 0xAA};
		int expected = 715827882;
		
		int result = (Integer) Codec.anythingFromBytes(anArray, Encoding.INT);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void anythingFromStringAsBytes() throws Exception {
		
		byte[] anArray = {(byte) '\u0048', (byte) '\u0062', (byte) '\u0069', (byte) '\u0072', (byte) '\u0064' };
		String expected = "Hbird";
		
		String result = (String) Codec.anythingFromBytes(anArray, Encoding.STRING);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void performanceInt() {
		byte[] anArray = {(byte) 0x2A, (byte) 0xAA, (byte) 0xAA, (byte) 0xAA};
		int expected = 715827882;
		
		int result = 0;
		long startTime = System.currentTimeMillis();
		for (long i=0; i<performanceTestRuns; i++) {
			result = Codec.bigEndianIntFromBytes(anArray);
		}
		long time = System.currentTimeMillis() - startTime;
		System.out.println("Specific function call decoded " + performanceTestRuns/time/1000 + " million ints per second");
		
		assertEquals(expected, result);
	}
	
	@Test
	public void performanceAnythingInt() throws Exception {
		byte[] anArray = {(byte) 0x2A, (byte) 0xAA, (byte) 0xAA, (byte) 0xAA};
		int expected = 715827882;
		
		int result = 0;
		long startTime = System.currentTimeMillis();
		for (long i=0; i<performanceTestRuns; i++) {
			result = (Integer) Codec.anythingFromBytes(anArray, Encoding.INT);
		}
		long time = System.currentTimeMillis() - startTime;
		System.out.println("Anything function call decoded " + performanceTestRuns/time/1000 + " million ints per second");
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testMany() throws Exception {
		
		byte[] anArray = {(byte) 0x2A, (byte) 0xAA, (byte) 0xAA, (byte) 0xAA,
				(byte) '\u0048', (byte) '\u0062', (byte) '\u0069', (byte) '\u0072', (byte) '\u0064'
				};
		
		ParameterEncoding p0 = new ParameterEncoding("Number", Encoding.INT, 0, 4);
		ParameterEncoding p1 = new ParameterEncoding("Name", Encoding.STRING, 4, 5);
		
		List<ParameterEncoding> parameters = new ArrayList<ParameterEncoding>();
		parameters.add(p0);
		parameters.add(p1);
		
		Map<String,Object> results = Codec.decodeMany(anArray, parameters);
		
		int expectedNumber = 715827882;
		String expectedString = "Hbird";
		
		assertEquals(expectedNumber, results.get("Number"));
		assertEquals(expectedString, results.get("Name"));
		
	}
	

}
