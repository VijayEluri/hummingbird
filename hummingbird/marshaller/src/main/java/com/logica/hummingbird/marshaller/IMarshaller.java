/* ----------------------------------------------------------------------------
 * (c) Copyright Logica 2010
 *
 * All rights reserved. This document is protected by international copyright 
 * law and may not be reprinted, reproduced, copied or utilised in whole or in 
 * part by any means including electronic, mechanical, or other means without 
 * the prior written consent of Logica. 
 * Whilst reasonable care has been taken by Logica to ensure the information 
 * contained herein is reasonably accurate, Logica shall not, under any 
 * circumstances be liable for any loss or damage (direct or consequential) 
 * suffered by any party as a result of the contents of this publication or the 
 * reliance of any party thereon or any inaccuracy or omission therein. The 
 * information in this document is therefore provided on an "as is" basis 
 * without warranty and is subject to change without further notice and cannot 
 * be construed as a commitment by Logica. 
 * The products mentioned in this document are identified by the names, 
 * trademarks, service marks and logos of their respective companies or 
 * organisations and may not be used in any advertising or publicity or in any 
 * other way whatsoever without the prior written consent of those companies 
 * or organisations and Logica.
 * ----------------------------------------------------------------------------
 * System       : Hummingbird
 * Author       : VillemosG
 * Created on   : 10.01.2010
 * ----------------------------------------------------------------------------
 */
package com.logica.hummingbird.marshaller;

import java.util.BitSet;

/**
 * The interface of the marshaller. 
 */
public interface IMarshaller {
	
	/**
	 * Unmarshalles the bitset using the container identified through the
	 * container parameter name. Following the unmarshalling the 'getContainerFactory'
	 * can be used to get a reference to a subcontainer.
	 *
	 * @param container The name of the container as registered within the container factory.
	 * @param data The data stream from which the data shall be extracted. 
	 *
	 */
	public void unmarshall(String container, BitSet data);
	
	/**
	 * Marshalles the container identified through the container parameter name into
	 * into the bitset using the container. 
	 *
	 * @param container The name of the container as registered within the container factory.
	 * @param data The data stream from which the data shall be extracted. 
	 *
	 */	
	public void marshall(String container, BitSet data);

	/**
	 * Marshalles the container identified through the container parameter name into
	 * into the String using the container. 
	 *
	 * @param container The name of the container as registered within the container factory.
	 * @param String The data stream from which the data shall be extracted. 
	 *
	 */	
	public void marshall(String container, String data);
		
	/**
	 * Returns a container reference.
	 *
	 * @param container Identifier of the container. 
	 * @throws Exception 
	 *
	 */
	public IContainer getContainer(String container) throws Exception;
}
