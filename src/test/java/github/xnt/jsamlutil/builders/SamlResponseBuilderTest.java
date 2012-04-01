package github.xnt.jsamlutil.builders;

import github.xnt.jsamlutil.builders.SamlResponseBuilder;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.opensaml.saml2.core.Conditions;
import org.opensaml.saml2.core.Response;

import junit.framework.TestCase;

public class SamlResponseBuilderTest extends TestCase{

	SamlResponseBuilder samlResponseBuilder;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		samlResponseBuilder = new SamlResponseBuilder();
	}
	
	public void testBuildResponseWithDefaultValues() throws Exception{
		Response response = samlResponseBuilder.setIssuer("myIssuer").buildSamlObject();
		assertEquals(response.getIssuer().getValue(), samlResponseBuilder.getIssuer());
		assertEquals(samlResponseBuilder.getNotBefore().plusMinutes(10), samlResponseBuilder.getNotOnOrAfter());
	}
	
	public void testBuildResponseWithDestination(){
		Response response = samlResponseBuilder.setDestination("destination").buildSamlObject();
		assertEquals("destination", response.getDestination());
	}
	
	public void testBuildResponseWithAssertionId(){
		Response response = samlResponseBuilder.setAssertionId("abc123").buildSamlObject();
		assertEquals("abc123", response.getAssertions().get(0).getID());
	}
	
	public void testBuildResponseWithCurrentNotBefore(){
		DateTime yesterday = new DateTime().minusDays(1);
		Response response = samlResponseBuilder.setIssuer("foo").setNotBefore(yesterday).buildSamlObject();
		Conditions conditions = response.getAssertions().get(0).getConditions();
		assertTrue(yesterday.isEqual(conditions.getNotBefore()));
		assertTrue(yesterday.plusMinutes(10).isEqual(conditions.getNotOnOrAfter()));
	}
	
	public void testBuildResponseClass(){
		assertEquals(Response.class, samlResponseBuilder.getTarget());
		assertTrue(samlResponseBuilder.getTarget().isAssignableFrom(samlResponseBuilder.buildSamlObject().getClass()));
	}
	
	public void testGetOrGenerateId(){
		assertTrue(StringUtils.isNotBlank(samlResponseBuilder.getOrGenerateIdentifier()));
		Response response = samlResponseBuilder.setAssertionId("abc123").buildSamlObject();
		assertEquals("abc123", response.getAssertions().get(0).getID());
	}
	
}
