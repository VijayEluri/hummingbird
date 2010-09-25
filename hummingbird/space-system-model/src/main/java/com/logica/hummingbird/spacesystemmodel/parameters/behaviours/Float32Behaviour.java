package com.logica.hummingbird.spacesystemmodel.parameters.behaviours;

import java.util.BitSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.logica.hummingbird.util.BitSetUtility;
import com.logica.hummingbird.util.exceptions.BitSetOperationException;

/**
 * Parameter behaviour for an IEEE 754 32-bit precision Float.
 * 
 * @author Mark Doyle
 * @author Johannes Klug
 * 
 */
public class Float32Behaviour extends AbstractFloatBehaviour {
	private final static Logger LOG = LoggerFactory.getLogger(Float32Behaviour.class);

	public Float32Behaviour() {
		super(32);
	}

	@Override
	public Float valueFromBitSet(BitSet packet) {
		int offset = 0;

		BitSet actualBitSet = packet.get(offset, offset + (int) getSizeInBits());

		LOG.debug("Float Parameter BitSet taken from bitset in = " + BitSetUtility.binDump(actualBitSet));

		return Float.intBitsToFloat(BitSetUtility.toInt(actualBitSet));
	}

	@Override
	public BitSet insertIntoBitSet(Number number, BitSet bitSetTarget, int offset) throws BitSetOperationException {
		float value = number.floatValue();

		// Convert the value to a bitset
		// Parse as IEEE-754 Single Precision (32-bit) (Java Integer)
		int intBits = Float.floatToIntBits(value);

		String binaryString = Integer.toBinaryString(intBits);
		LOG.debug("Float32 insertIntoBitSet - Binary string = " + binaryString);

		if (value >= 0) {
			// We have to add the Sign bit manually for positive Numbers
			binaryString = '0' + binaryString;
		}

		// Get the BitSet from the String.
		BitSet valueBitSet = this.bitSetFromString(binaryString);

		// If the floats has leading zeros the int conversion above will truncate them. This is expected as
		// the leading zeros are surplus for a Java big endian int. They are however vital to an IEEE-754 float
		// since they contain the sign, exponent and mantissa. We must repair the bitset in this case.
		if (valueBitSet.length() < 32) {
			// int truncation = 32 - valueBitSet.length();
			String bitsetString = BitSetUtility.bitSetToBinaryString(valueBitSet, true);
			LOG.debug("Truncated bitset = " + bitsetString);
			bitsetString = BitSetUtility.padStringFromTheFront(bitsetString, 32);
			LOG.debug("Repaired bitset = " + bitsetString);
			valueBitSet = BitSetUtility.stringToBitSet(bitsetString, true, true);
		}

		// Insert the value BitSet into the target BitSet and return
		for (int i = 0; i < getSizeInBits(); i++) {
			if (valueBitSet.get(i)) {
				bitSetTarget.set(i + offset);
			}
			else {
				bitSetTarget.clear(i + offset);
			}
		}

		LOG.debug("Returning BitSet = " + BitSetUtility.binDump(bitSetTarget));
		return bitSetTarget;
	}

	@Override
	public String getTypeName() {
		return "Float (IEEE754 Single precision 32-bit)";
	}

}
