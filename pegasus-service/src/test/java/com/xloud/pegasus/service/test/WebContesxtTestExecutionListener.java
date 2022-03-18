package com.xloud.pegasus.service.test;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.web.context.WebApplicationContext;

public class WebContesxtTestExecutionListener extends AbstractTestExecutionListener {

	@Override
	public void prepareTestInstance(TestContext testContext) {
		if (testContext.getApplicationContext() instanceof GenericApplicationContext) {
			GenericApplicationContext context = (GenericApplicationContext) testContext.getApplicationContext();
			ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

			Scope sessionScope = new SimpleThreadScope();
			beanFactory.registerScope(WebApplicationContext.SCOPE_SESSION, sessionScope);
			Scope requestScope = new SimpleThreadScope();
			beanFactory.registerScope(WebApplicationContext.SCOPE_REQUEST, requestScope);
			Scope threadScope = new SimpleThreadScope();
			beanFactory.registerScope("thread", threadScope);
		}
	}
}
