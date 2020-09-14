package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

//@SpringBootApplication
//@Import(MvcConfig.class)
//@Import(JpaConfig.class)
//@Import(HbConfig.class)
//@Import(SecurityConfig.class)
//@EnableJpaRepositories(basePackages = { "com.springboot.repository" })
//OR
//for .jar packaging
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
//@ImportResource("classpth:app-root-config.xml") //when we are using xml based configuration with springboot.
// @EnableJpaRepositories(basePackages = { "com.springboot.repository" })
public class SpringBootSpringRestAppWithHB {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSpringRestAppWithHB.class, args);
	}
	
	//To see all loaded spring beans
	/*@Autowired
	private ApplicationContext appContext;

	// @Override
	public void run(String... args) throws Exception {
		String[] beans = appContext.getBeanDefinitionNames();
		Arrays.sort(beans);
		for (String bean : beans) {
			System.out.println(bean + " of Type :: " + appContext.getBean(bean).getClass());
		}
	}*/
}

// OR
//for .war packaging
/*
 * @SpringBootApplication
 * 
 * @EnableJpaRepositories(basePackages = { "com.springboot.repository" }) public
 * class SpringBootWebApp extends SpringBootServletInitializer {
 * 
 * @Override protected SpringApplicationBuilder
 * configure(SpringApplicationBuilder application) { return
 * application.sources(SpringBootWebApp.class); }
 * 
 * public static void main(String[] args) {
 * SpringApplication.run(SpringBootWebApp.class, args); } }
 */
