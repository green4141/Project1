package com.tjoeun.config;

import javax.annotation.Resource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tjoeun.dto.UserDTO;

import com.tjoeun.interceptor.CheckLoginInterceptor;
import com.tjoeun.interceptor.CheckWriterInterceptor;
import com.tjoeun.interceptor.AdminInterceptor;
import com.tjoeun.interceptor.CheckBoardInterceptor;
import com.tjoeun.interceptor.TopMenuInterceptor;
import com.tjoeun.mapper.BoardMapper;
import com.tjoeun.mapper.FileMapper;
import com.tjoeun.mapper.ReplyMapper;
import com.tjoeun.mapper.TopMenuMapper;
import com.tjoeun.mapper.UserMapper;
import com.tjoeun.service.BoardService;
import com.tjoeun.service.TopMenuService;

@Configuration
@EnableWebMvc
@ComponentScan("com.tjoeun.controller")
@ComponentScan("com.tjoeun.dao")
@ComponentScan("com.tjoeun.service")
@ComponentScan("com.tjoeun.validator")
@PropertySource("/WEB-INF/properties/db.properties")
@PropertySource("/WEB-INF/properties/option.properties")
public class ServletAppContext implements WebMvcConfigurer{
	@Value("${db.classname}")
	private String db_classname;
	
	@Value("${db.url}")
	private String db_url;
	
	@Value("${db.username}")
	private String db_username;
	
	@Value("${db.password}")
	private String db_password;
	
	@Autowired
	private TopMenuService topMenuService;
	
	@Autowired
	private BoardService boardService;

	@Resource(name="loginUserDTO")
	private UserDTO loginUserDTO;

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName(db_classname);
		source.setUrl(db_url);
		source.setUsername(db_username);
		source.setPassword(db_password);
		return source;
	}
	
	@Bean
	public SqlSessionFactory factory(BasicDataSource source) throws Exception{
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(source);
		SqlSessionFactory factory = factoryBean.getObject();
		return factory;
	}
	
	@Bean
	public MapperFactoryBean<BoardMapper> getBoardMapper(SqlSessionFactory factory) throws Exception{
		MapperFactoryBean<BoardMapper> fatoryBean = new MapperFactoryBean<BoardMapper>(BoardMapper.class);
		fatoryBean.setSqlSessionFactory(factory);
		return fatoryBean;
	}
	
	@Bean
	public MapperFactoryBean<TopMenuMapper> getTopMenuMapper(SqlSessionFactory factory) throws Exception{
		MapperFactoryBean<TopMenuMapper> factoryBean = new MapperFactoryBean<TopMenuMapper>(TopMenuMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}
	
	@Bean
	public MapperFactoryBean<UserMapper> getUserMapper(SqlSessionFactory factory) throws Exception{
		MapperFactoryBean<UserMapper> fatoryBean = new MapperFactoryBean<UserMapper>(UserMapper.class);
		fatoryBean.setSqlSessionFactory(factory);
		return fatoryBean;
	}

	@Bean
	public MapperFactoryBean<ReplyMapper> getReplyMapper(SqlSessionFactory factory) throws Exception{
		MapperFactoryBean<ReplyMapper> fatoryBean = new MapperFactoryBean<ReplyMapper>(ReplyMapper.class);
		fatoryBean.setSqlSessionFactory(factory);
		return fatoryBean;
	}
	
	@Bean
	public MapperFactoryBean<FileMapper> getFileMapper(SqlSessionFactory factory) throws Exception{
		MapperFactoryBean<FileMapper> fatoryBean = new MapperFactoryBean<FileMapper>(FileMapper.class);
		fatoryBean.setSqlSessionFactory(factory);
		return fatoryBean;
	}
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		WebMvcConfigurer.super.addInterceptors(registry);
		TopMenuInterceptor topMenuInterceptor = new TopMenuInterceptor(topMenuService, loginUserDTO);
		InterceptorRegistration reg1 = registry.addInterceptor(topMenuInterceptor);
		reg1.addPathPatterns("/**");

		CheckLoginInterceptor checkLoginInterceptor = new CheckLoginInterceptor(loginUserDTO);
		InterceptorRegistration reg2 = registry.addInterceptor(checkLoginInterceptor);
		reg2.addPathPatterns("/user/modify", "/user/logout", "/board/**");
		
		CheckBoardInterceptor checkBoardInterceptor = new CheckBoardInterceptor(loginUserDTO);
		InterceptorRegistration reg3 = registry.addInterceptor(checkBoardInterceptor);
		reg3.addPathPatterns("/board/**");
		
		CheckWriterInterceptor checkWriterInterceptor = 
				new CheckWriterInterceptor(loginUserDTO, boardService);
		InterceptorRegistration reg4 = registry.addInterceptor(checkWriterInterceptor);
		reg4.addPathPatterns("/board/modify", "/board/delete");
		
		AdminInterceptor adminInterceptor = new AdminInterceptor(loginUserDTO);
		registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/**");

	}
	
	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource res = 
				new ReloadableResourceBundleMessageSource();
		res.setBasename("/WEB-INF/properties/error");
		return res;
	}
	
	
}
