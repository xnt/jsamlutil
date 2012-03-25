package github.xnt.jsamlutil.configs;

import org.opensaml.common.SAMLObject;

/**
 * Represents a SAML Object configuration.
 * @author xnt
 *
 */
public interface ISamlConfig<T extends SAMLObject> {
	
	/**
	 * What's this config's target? Eg Response, AuthnRequest...
	 * @return
	 */
	public Class<? extends T> getTarget();
	public T buildSamlObject();
}
