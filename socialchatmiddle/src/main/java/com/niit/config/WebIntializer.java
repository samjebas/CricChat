package com.niit.config;

import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



public class WebIntializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	protected void Customizeregistration(ServletRegistration.Dynamic registration)
	{
		registration.setInitParameter("dispatchOptionsRequest","true");
		registration.setAsyncSupported(true);
	}
	@Override
	protected Class<?>[] getRootConfigClasses() 
	{
		
		return new Class[] {WebResolver.class,HibernateConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses()
	{
		
		return null;
	}

	@Override
	protected String[] getServletMappings()
	{
		
		return new String[] {"/"};
	}
	
	protected Filter[] getServletFilter()
	{
		CharacterEncodingFilter encodingFilter=new CharacterEncodingFilter();
		encodingFilter.setEncoding(StandardCharsets.UTF_8.name());
		return new Filter[] {encodingFilter};
	}
}
