package github.xnt.jsamlutil.builders;

import github.xnt.jsamlutil.utils.SetterUtils;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.opensaml.common.SignableSAMLObject;
import org.opensaml.common.impl.RandomIdentifierGenerator;
import org.opensaml.saml2.core.impl.AssertionBuilder;
import org.opensaml.saml2.core.impl.ConditionsBuilder;
import org.opensaml.saml2.core.impl.IssuerBuilder;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Conditions;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.core.impl.ResponseBuilder;

public class SamlResponseBuilder extends SignableSamlBuilder<SamlResponseBuilder> {

	private DateTime notOnOrAfter;
	private DateTime notBefore;
	private String issuer;
	private String destination;
	private String assertionId;
	private final SetterUtils setterUtils = new SetterUtils();
	
	@Override
	public Class<? extends SignableSAMLObject> getTarget() {
		return Response.class;
	}

	@Override
	protected SamlResponseBuilder getThis() {
		return this;
	}

	@Override
	public Response buildSamlObject() {
		ResponseBuilder builder = new ResponseBuilder();
		Response response = builder.buildObject();
		Assertion assertion = new AssertionBuilder().buildObject();
		Conditions conditions = new ConditionsBuilder().buildObject();
		Issuer samlIssuer = new IssuerBuilder().buildObject();
		setterUtils.setIfNotEmpty(samlIssuer, "setValue", issuer);
		if(notBefore == null){
			notBefore = new DateTime();
		}
		if(notOnOrAfter == null){
			notOnOrAfter = notBefore.plusMinutes(10);
		}
		conditions.setNotBefore(notBefore);
		conditions.setNotOnOrAfter(notOnOrAfter);
		response.setIssueInstant(new DateTime());
		response.setIssuer(samlIssuer);
		// TOOD use potentially different variables, keep this as a default
		samlIssuer = new IssuerBuilder().buildObject();
		setterUtils.setIfNotEmpty(samlIssuer, "setValue", issuer);
		assertion.setIssuer(samlIssuer);
		assertion.setConditions(conditions);
		assertion.setID(getOrGenerateIdentifier());
		setterUtils.setIfNotEmpty(response, "setDestination", destination);
		
		response.getAssertions().add(assertion);
		return response;
	}
	
	String getOrGenerateIdentifier(){ // TODO I think this will go to a general util or something...
		if(StringUtils.isBlank(assertionId)){
			RandomIdentifierGenerator idGenerator = new RandomIdentifierGenerator();
			return idGenerator.generateIdentifier();
		}
		return assertionId;
	}
	

	public DateTime getNotOnOrAfter() {
		return notOnOrAfter;
	}

	public SamlResponseBuilder setNotOnOrAfter(DateTime notOnOrAfter) {
		this.notOnOrAfter = notOnOrAfter;
		return this;
	}

	public DateTime getNotBefore() {
		return notBefore;
	}

	public SamlResponseBuilder setNotBefore(DateTime notBefore) {
		this.notBefore = notBefore;
		return this;
	}
	
	public String getIssuer() {
		return issuer;
	}

	public SamlResponseBuilder setIssuer(String issuer) {
		this.issuer = issuer;
		return this;
	}

	public String getDestination() {
		return destination;
	}

	public SamlResponseBuilder setDestination(String destination) {
		this.destination = destination;
		return this;
	}

	public String getAssertionId() {
		return assertionId;
	}

	public SamlResponseBuilder setAssertionId(String assertionId) {
		this.assertionId = assertionId;
		return this;
	}
	
}
