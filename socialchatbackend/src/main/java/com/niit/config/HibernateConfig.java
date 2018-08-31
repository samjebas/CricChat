package com.niit.config;
import java.util.Properties;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.model.Blog;
import com.niit.model.BlogComment;
import com.niit.model.UserDetail;



@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement
public class HibernateConfig {
	
	
	//1.Data Source Object
	public DataSource getDataSource()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/XE");
		dataSource.setUsername("hr");
		dataSource.setPassword("hr");
		return dataSource;
	}
	
	//2.Create SessionFactory Bean
	@Bean(name="sessionfactory")
	public SessionFactory getSessionFactory()
	{
		
		Properties hibernateProp=new Properties();
		hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		hibernateProp.put("hibernate.hbm2ddl.auto","update");
		hibernateProp.put("hibernate.show_sql", "true");
		
		
		LocalSessionFactoryBuilder sessionFactoryBuilder=new LocalSessionFactoryBuilder(getDataSource());
		sessionFactoryBuilder.addProperties(hibernateProp);
		
		sessionFactoryBuilder.scanPackages("com.niit");
	
		
		SessionFactory sessionFactory=sessionFactoryBuilder.buildSessionFactory();
		System.out.println("----SessionFactory Object----------");
		return sessionFactory;
	}
	
	@Bean
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory)
	{
		System.out.println("----Hibernate Object----------");
		return new HibernateTransactionManager(sessionFactory);
	}

}

	