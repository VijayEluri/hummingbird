package org.hbird.transport.spacesystemmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hbird.transport.spacesystemmodel.encoding.Encoding;
import org.hbird.transport.spacesystemmodel.exceptions.ParameterNotInModelException;
import org.hbird.transport.spacesystemmodel.exceptions.UnknownParameterException;
import org.hbird.transport.spacesystemmodel.exceptions.UnknownParameterGroupException;
import org.hbird.transport.spacesystemmodel.parameters.Parameter;
import org.hbird.transport.spacesystemmodel.tmtcgroups.ParameterGroup;


/**
 * Interface to create Space System Model factories.
 *
 * The models are created based on an underlying space system model, defining the structure of the space system,
 * including the telemetry and command structures. The space system can be expressed in different ways, such as through
 * the OMG/CCSDS XTCE, ESA MIB/PUS model, or any other DSL. The model factory implementation will depend on a data
 * structure in a specific format, but hides this implementation to the monitoring component.
 */
public interface SpaceSystemModel extends Serializable {

	String HUMMINGBIRD_PROCESSED_HEADER = "HEADER";

	String getName();

	// Parameter Group related
	ParameterGroup getParameterGroup(String qualifiedName) throws UnknownParameterGroupException;
	Map<String, ParameterGroup> getParameterGroups();
	Collection<ParameterGroup> getParameterGroupsCollection();

	// Parameter related
	Parameter<?> getParameter(String qualifiedName) throws UnknownParameterException;
	Parameter<Integer> getIntParameter(String qualifiedName) throws UnknownParameterException;
	Parameter<Long> getLongParameter(String qualifiedName) throws UnknownParameterException;
	Parameter<BigDecimal> getBigDecimalParameter(String qualifiedName) throws UnknownParameterException;
	Parameter<String> getStringParameter(String qualifiedName) throws UnknownParameterException;
	Parameter<Float> getFloatParameter(String qualifiedName) throws UnknownParameterException;
	Parameter<Double> getDoubleParameter(String qualifiedName) throws UnknownParameterException;
	Parameter<Byte[]> getRawParameter(String qualifiedName) throws UnknownParameterException;

	/** Returns all parameters (unbound) from all payload groups in the model */
	Map<String, Parameter<?>> getAllPayloadParameters();
	/** Returns all Integer parameters from all payload groups in the model */
	Map<String, Parameter<Integer>> getAllIntegerParameters();
	/** Returns all Long parameters from all payload groups in the model */
	Map<String, Parameter<Long>> getAllLongParameters();
	/** Returns all BigDecimal parameters from all payload groups in the model */
	Map<String, Parameter<BigDecimal>> getAllBigDecimalParameters();
	/** Returns all Float parameters from all payload groups in the model */
	Map<String, Parameter<Float>> getAllFloatParameters();
	/** Returns all Double parameters from all payload groups in the model */
	Map<String, Parameter<Double>> getAllDoubleParameters();
	/** Returns all String parameters from all payload groups in the model */
	Map<String, Parameter<String>> getAllStringParameters();
	/** Returns all Raw parameters from all payload groups in the model */
	Map<String, Parameter<Byte[]>> getAllRawParameters();

	/** Finds and replaces a parameter keyed by qualified name in the the model 
	 * @throws ParameterNotInModelException */
	void replaceParameterInModel(String qualifiedName, final Parameter<?> newParameter) throws ParameterNotInModelException;

	Map<String, List<Object>> getAllPayloadRestrictions();
	Map<String, Encoding> getEncodings();
}