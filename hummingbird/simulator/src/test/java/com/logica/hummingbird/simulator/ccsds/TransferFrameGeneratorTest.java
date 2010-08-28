package com.logica.hummingbird.simulator.ccsds;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;


public class TransferFrameGeneratorTest {
	
	private TransferFrameGenerator generator;
	
	@Test
	public void testGenerator() {
		generator = new TransferFrameGenerator();
		byte[] payload = new byte[65542];
		
		List<byte[]> frames = generator.generateTransferFrames(0, payload);
		assertEquals(33, frames.size());
		
	}

}
