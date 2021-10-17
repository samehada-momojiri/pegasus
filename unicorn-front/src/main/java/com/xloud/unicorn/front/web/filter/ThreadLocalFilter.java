package com.xloud.unicorn.front.web.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

import com.xloud.pegasus.common.utils.DateUtils;

@Component
public class ThreadLocalFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		DateUtils.setThreadDateTime(new Date());
		chain.doFilter(request, response);
	}
}
