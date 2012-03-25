package github.xnt.jsamlutil.builders;

import java.security.PrivateKey;

import org.apache.log4j.Logger;
import org.opensaml.DefaultBootstrap;
import org.opensaml.common.SignableSAMLObject;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.signature.X509Certificate;

public abstract class SignableSamlBuilder<T extends SignableSamlBuilder<T>> implements ISamlBuilder<SignableSAMLObject> {
	
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
	
	public SignableSamlBuilder(){
		try {
			DefaultBootstrap.bootstrap();
		} catch (ConfigurationException e) {
			Logger.getLogger(SignableSamlBuilder.class).error("Couldn't load bootstrap", e);
		}
	}
	
	public boolean isSign() {
		return sign;
	}
	public SignableSamlBuilder<T> setSign(boolean sign) {
		this.sign = sign;
		return getThis();
	}
	public boolean isVerifySignature() {
		return verifySignature;
	}
	public SignableSamlBuilder<T> setVerifySignature(boolean verifySignature) {
		this.verifySignature = verifySignature;
		return getThis();
	}
	public X509Certificate getCertificate() {
		return certificate;
	}
	public SignableSamlBuilder<T> setCertificate(X509Certificate certificate) {
		this.certificate = certificate;
		return getThis();
	}
	public PrivateKey getSigningKey() {
		return signingKey;
	}
	public SignableSamlBuilder<T> setSigningKey(PrivateKey signingKey) {
		this.signingKey = signingKey;
		return getThis();
	}

}
