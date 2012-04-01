package github.xnt.jsamlutil.utils;

import java.lang.reflect.Method;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class SetterUtils {

	private final Logger _logger = Logger.getLogger(SetterUtils.class);

	public void setIfNotEmpty(Object target, String setterName, String value) {
		try {
			Method method = target.getClass().getMethod(setterName,
					String.class);
			setIfNotEmpty(target, method, value);
		} catch (Exception e) {
			_logger.error("Can't load the setter method", e);
		}
	}

	void setIfNotEmpty(Object target, Method setter, String value) {
		if (StringUtils.isNotBlank(value)) {
			try {
				setter.invoke(target, value);
			} catch (Exception e) {
				_logger.error("Can't invoke the setter method", e);
			}
		}
	}
}
