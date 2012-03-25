package github.xnt.jsamlutil.configs;

import java.security.PrivateKey;

import org.apache.log4j.Logger;
import org.opensaml.DefaultBootstrap;
import org.opensaml.common.SignableSAMLObject;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.signature.X509Certificate;

public abstract class SignableSamlConfig<T extends SignableSamlConfig<T>> implements ISamlConfig<SignableSAMLObject> {
	
	protected abstract T getThis();
	
	/**
	 * Which X509 Certificate should I use if both are set? The one within the
	 * Signable Object? Or the one passed through this object?
	 * 
	 * @author xnt
	 */
	public enum CERTIFICATE_PREFERENCE {
		EMBEDDED, EXTERNAL;
	}
	
	private boolean sign;
	private boolean verifySignature;
	private X509Certificate certificate;
	private PrivateKey signingKey;
	
	public SignableSamlConfig(){
		try {
			DefaultBootstrap.bootstrap();
		} catch (ConfigurationException e) {
			Logger.getLogger(SignableSamlConfig.class).error("Couldn't load bootstrap", e);
		}
	}
	
	public boolean isSign() {
		return sign;
	}
	public SignableSamlConfig<T> setSign(boolean sign) {
		this.sign = sign;
		return getThis();
	}
	public boolean isVerifySignature() {
		return verifySignature;
	}
	public SignableSamlConfig<T> setVerifySignature(boolean verifySignature) {
		this.verifySignature = verifySignature;
		return getThis();
	}
	public X509Certificate getCertificate() {
		return certificate;
	}
	public SignableSamlConfig<T> setCertificate(X509Certificate certificate) {
		this.certificate = certificate;
		return getThis();
	}
	public PrivateKey getSigningKey() {
		return signingKey;
	}
	public SignableSamlConfig<T> setSigningKey(PrivateKey signingKey) {
		this.signingKey = signingKey;
		return getThis();
	}

}
