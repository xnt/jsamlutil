package github.xnt.jsamlutil.builders;

import github.xnt.jsamlutil.builders.SamlResponseBuilder;

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
	
	public void testBuildResponseWithCurrentNotBefore(){
		DateTime yesterday = new DateTime().minusDays(1);
		Response response = samlResponseBuilder.setIssuer("foo").setNotBefore(yesterday).buildSamlObject();
		Conditions conditions = response.getAssertions().get(0).getConditions();
		assertTrue(yesterday.isEqual(conditions.getNotBefore()));
		assertTrue(yesterday.plusMinutes(10).isEqual(conditions.getNotOnOrAfter()));
	}
	
}
