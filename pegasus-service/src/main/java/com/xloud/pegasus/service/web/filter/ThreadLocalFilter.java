package com.xloud.pegasus.service.web.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.xloud.pegasus.common.utils.DateUtils;
import com.xloud.pegasus.service.common.context.ThreadContext;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ThreadLocalFilter implements Filter {

	private final ThreadContext threadContext;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		Date threadDate = new Date();
		DateUtils.setThreadDateTime(threadDate);
		threadContext.setThreadDate(threadDate);
		if (request instanceof HttpServletRequest) {
			threadContext.setSessionId(((HttpServletRequest) request).getSession().getId());
		}
		chain.doFilter(request, response);
	}
}
