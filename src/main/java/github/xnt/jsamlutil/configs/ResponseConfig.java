package github.xnt.jsamlutil.configs;

import org.joda.time.DateTime;
import org.opensaml.common.SignableSAMLObject;
import org.opensaml.saml2.core.impl.AssertionBuilder;
import org.opensaml.saml2.core.impl.ConditionsBuilder;
import org.opensaml.saml2.core.impl.IssuerBuilder;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Conditions;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.core.impl.ResponseBuilder;

public class ResponseConfig extends SignableSamlConfig<ResponseConfig> {

	private DateTime notOnOrAfter;
	private DateTime notBefore;
	private String issuer;

	@Override
	public Class<? extends SignableSAMLObject> getTarget() {
		return Response.class;
	}

	@Override
	protected ResponseConfig getThis() {
		return this;
	}

	@Override
	public Response buildSamlObject() {
		ResponseBuilder builder = new ResponseBuilder();
		Response response = builder.buildObject();
		Assertion assertion = new AssertionBuilder().buildObject();
		Conditions conditions = new ConditionsBuilder().buildObject();
		Issuer samlissuer = new IssuerBuilder().buildObject();
		samlissuer.setValue(issuer);
		if(notBefore == null){
			notBefore = new DateTime();
		}
		if(notOnOrAfter == null){
			notOnOrAfter = notBefore.plusMinutes(10);
		}
		conditions.setNotBefore(notBefore);
		conditions.setNotOnOrAfter(notOnOrAfter);
		response.setIssueInstant(new DateTime());
		response.setIssuer(samlissuer);
		// TOOD use potentially different variables, keep this as a default
		samlissuer = new IssuerBuilder().buildObject();
		samlissuer.setValue(issuer);
		assertion.setIssuer(samlissuer);
		assertion.setConditions(conditions);
		response.getAssertions().add(assertion);
		return response;
	}

	public DateTime getNotOnOrAfter() {
		return notOnOrAfter;
	}

	public ResponseConfig setNotOnOrAfter(DateTime notOnOrAfter) {
		this.notOnOrAfter = notOnOrAfter;
		return this;
	}

	public DateTime getNotBefore() {
		return notBefore;
	}

	public ResponseConfig setNotBefore(DateTime notBefore) {
		this.notBefore = notBefore;
		return this;
	}
	
	public String getIssuer() {
		return issuer;
	}

	public ResponseConfig setIssuer(String issuer) {
		this.issuer = issuer;
		return this;
	}
	
}
