/**
 * Licensed under the Apache License, Version 2.0. You may obtain a copy of 
 * the License at http://www.apache.org/licenses/LICENSE-2.0 or at this project's root.
 */

package org.hbird.transport.spacesystemmodel;

import java.util.BitSet;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hbird.transport.commons.util.exceptions.BitSetOperationException;
import org.hbird.transport.spacesystemmodel.parameters.Parameter;


/**
 * The standard interface of a marshaller container. The interface supports the
 * unmarshalling and marshalling of containers.
 */
public interface Container {

	/**
	 *  The method MUST remove the part of the packet that it represents, i.e.
	 *  truncate the length of the parameter 
	 * 
	 * @param packet A bitwise representation of the packet. The packet 
	 * has been truncated to the right position by the caller, i.e. the
	 * offset to this packet is always '0'.
	 */
	BitSet unmarshall(BitSet packet);
	
	/**
	 * Encodes this container into the bitset, from the provider offset.
	 *
	 * @param packet The data set to be inserted into.
	 * @param offset The current position to be inserted into. 
	 * @return int The offset of the last inserted data. 
	 * @throws BitSetOperationException 
	 */
	int marshall(BitSet packet, int offset) throws BitSetOperationException;
	
	
	/**
	 * Encodes the container (and sub containers) into a string. The string 
	 * is purely informative format, intended to help debugging and visualization. 
	 * The format is;
	 * 
	 *   Container = '[' + NAME + 0..n * Container | Parameter + ']'
	 *   Parameter = '{' + TYPE (LENGTH) NAME value = VALUE'}'
	 *   NAME      = N*ASCII 
	 *   TYPE      = float | int
	 *   LENGTH    = 0..64
	 *   VALUE     = integer or float to string.
	 * 
	 * @return String Encoded representation of the container.
	 *
	 */
	String toString();

	
	/**
	 * Returns the total length of the container, based on the length of
	 * the subcontainers (if any).
	 * 
	 * @return int The parsed length + the length of the container. 
	 *
	 */
	int getLength();

	
	/**
	 * Returns the raw value of the container.
	 *
	 * @return The container value as a bitset.  
	 *
	 */
	BitSet getRawValue();

	void addPacketObserver(PacketObserver observer);
	
	void addParameterUpdateObserver(ParameterObserver observer);
	
	List<Container> getSubContainers();
	
	String getName();
	
	Map<Parameter, String> getRestrictions();

	void addRestriction(Parameter param, String comparisonValue);

	void addContainer(Container container);
	
	void addContainer(Collection<? extends Container> containers);

	void addParent(Container parentContainer);

	List<Container> getParents();
}
