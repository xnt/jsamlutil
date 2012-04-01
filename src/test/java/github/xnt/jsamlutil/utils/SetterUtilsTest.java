package github.xnt.jsamlutil.utils;

import java.lang.reflect.Method;


import junit.framework.TestCase;

public class SetterUtilsTest extends TestCase {

	private SetterUtils setterUtils;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setterUtils = new SetterUtils();
	}

	public void testInvokeKnownMethod() throws Exception {
		TestClass testObject = new TestClass();
		assertNull(testObject.getFoo());
		Method method = TestClass.class.getMethod("setFoo", String.class);
		setterUtils.setIfNotEmpty(testObject, method, null);
		assertNull(testObject.getFoo());
		setterUtils.setIfNotEmpty(testObject, method, "abc");
		assertEquals("abc", testObject.getFoo());
		setterUtils.setIfNotEmpty(testObject, method, null);
		assertEquals("abc", testObject.getFoo());
		setterUtils.setIfNotEmpty(testObject, method, "xyz");
		assertEquals("xyz", testObject.getFoo());
	}

	public void testSetIfNotEmpty() throws Exception {
		TestClass testObject = new TestClass();
		assertNull(testObject.getFoo());
		setterUtils.setIfNotEmpty(testObject, "setFoo", null);
		assertNull(testObject.getFoo());
		setterUtils.setIfNotEmpty(testObject, "setFoo", "abc");
		assertEquals("abc", testObject.getFoo());
	}

}

class TestClass {
	private String foo;

	public void setFoo(String bar) {
		foo = bar;
	}

	public String getFoo() {
		return foo;
	}
}
