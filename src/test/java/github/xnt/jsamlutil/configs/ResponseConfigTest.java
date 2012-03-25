package github.xnt.jsamlutil.configs;

import org.joda.time.DateTime;
import org.opensaml.saml2.core.Conditions;
import org.opensaml.saml2.core.Response;

import junit.framework.TestCase;

public class ResponseConfigTest extends TestCase{

	ResponseConfig responseConfig;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		responseConfig = new ResponseConfig();
	}
	
	public void testBuildResponseWithDefaultValues() throws Exception{
		Response response = responseConfig.setIssuer("myIssuer").buildSamlObject();
		assertEquals(response.getIssuer().getValue(), responseConfig.getIssuer());
		assertEquals(responseConfig.getNotBefore().plusMinutes(10), responseConfig.getNotOnOrAfter());
	}
	
	public void testBuildResponseWithCurrentNotBefore(){
		DateTime yesterday = new DateTime().minusDays(1);
		Response response = responseConfig.setIssuer("foo").setNotBefore(yesterday).buildSamlObject();
		Conditions conditions = response.getAssertions().get(0).getConditions();
		assertTrue(yesterday.isEqual(conditions.getNotBefore()));
		assertTrue(yesterday.plusMinutes(10).isEqual(conditions.getNotOnOrAfter()));
	}
	
}
