package com.niit.config;

import java.nio.charset.StandardCharsets;

import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.fasterxml.classmate.Filter;

public class WebIntializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		System.out.println("customizeRegistration");
		registration.setInitParameter("dispatchOptionRequest", "true");
		registration.setAsyncSupported(true);
	}
	
	
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return new Class[] { WebResolver.class, HibernateConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		
		return  new String[] { "/" };
	}

	
	@Override
	protected javax.servlet.Filter[] getServletFilters()
	{
		CharacterEncodingFilter encodingFilter=new CharacterEncodingFilter();
		encodingFilter.setEncoding(StandardCharsets.UTF_8.name());
		return new javax.servlet.Filter[] {encodingFilter};
	}
}
