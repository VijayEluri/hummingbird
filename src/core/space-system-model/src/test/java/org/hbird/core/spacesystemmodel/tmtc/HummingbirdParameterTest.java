package org.hbird.core.spacesystemmodel.tmtc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hbird.core.spacesystemmodel.tmtc.provided.HummingbirdParameter;
import org.junit.Before;
import org.junit.Test;

/**
 * @author kimmell
 * 
 */
public class HummingbirdParameterTest {

	private static final String QN = "QN";
	private static final String NAME = "name";
	private static final String DESCRIPTION_SHORT = "short";
	private static final String DESCRIPTION_LONG = "long";

	private HummingbirdParameter<Object> parameter;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		parameter = new HummingbirdParameter<Object>(QN, NAME, DESCRIPTION_SHORT, DESCRIPTION_LONG);
	}

	/**
	 * Test method for
	 * {@link org.hbird.core.commons.tmtcgroups.HummingbirdParameter#HummingbirdParameter(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testHummingbirdParameter() {
		testGetName();
		testGetQualifiedName();
		testGetShortDescription();
		testGetLongDescription();
	}

	/**
	 * Test method for {@link org.hbird.core.commons.tmtcgroups.HummingbirdParameter#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals(NAME, parameter.getName());
	}

	/**
	 * Test method for {@link org.hbird.core.commons.tmtcgroups.HummingbirdParameter#getShortDescription()}.
	 */
	@Test
	public void testGetShortDescription() {
		assertEquals(DESCRIPTION_SHORT, parameter.getShortDescription());
	}

	/**
	 * Test method for {@link org.hbird.core.commons.tmtcgroups.HummingbirdParameter#getLongDescription()}.
	 */
	@Test
	public void testGetLongDescription() {
		assertEquals(DESCRIPTION_LONG, parameter.getLongDescription());
	}

	/**
	 * Test method for {@link org.hbird.core.commons.tmtcgroups.HummingbirdParameter#getValue()}.
	 */
	@Test
	public void testGetValue() {
		testSetValue();
	}

	/**
	 * Test method for {@link org.hbird.core.commons.tmtcgroups.HummingbirdParameter#setValue(java.lang.Object)}.
	 */
	@Test
	public void testSetValue() {
		assertNull(parameter.getValue());
		Object value = new Object();
		parameter.setValue(value);
		assertEquals(value, parameter.getValue());
	}

	/**
	 * Test method for {@link org.hbird.core.commons.tmtcgroups.HummingbirdParameter#toString()}.
	 */
	@Test
	public void testToString() {
		assertNotNull(parameter.toString());
	}

	/**
	 * Test method for {@link org.hbird.core.commons.tmtcgroups.HummingbirdParameter#getQualifiedName()}.
	 */
	@Test
	public void testGetQualifiedName() {
		assertEquals(QN, parameter.getQualifiedName());
	}
	
	@Test
	public void testParameterToJson() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		parameter.setValue(new Integer(1337));
		System.out.println(mapper.writeValueAsString(parameter));
	}
}
