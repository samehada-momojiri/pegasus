package com.xloud.pegasus.front.web.view;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

public class SampleDialect implements IExpressionObjectDialect {

	// Thymeleafで使用する名称
	private static final String EXPRESSION_NAME = "sample";

	private static final Set<String> ALL_EXPRESSION_NAMES = new HashSet<String>() {
		private static final long serialVersionUID = 1L;

		{
			add(EXPRESSION_NAME);
		}
	};

	@Override
	public IExpressionObjectFactory getExpressionObjectFactory() {
		return new IExpressionObjectFactory() {
			@Override
			public Set<String> getAllExpressionObjectNames() {
				return ALL_EXPRESSION_NAMES;
			}

			@Override
			public Object buildObject(IExpressionContext context, String expressionObjectName) {
				// 独自Utilityのインスタンスと名前を紐付け
				if (expressionObjectName.equals(EXPRESSION_NAME)) {
					return new SampleUtility();
				}
				return null;
			}

			@Override
			public boolean isCacheable(String expressionObjectName) {
				// 必要に応じて実装
				return true;
			}
		};
	}

	@Override
	public String getName() {
		return "Sample";
	}
}
