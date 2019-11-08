package springTest;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebMvc // Spring-MVC config
@EnableTransactionManagement // Spring-Data config
@EnableJpaRepositories("springTest") // Spring-Data config
@ComponentScan(basePackages = "springTest")
public class AppConfig {

    // For Spring-MVC
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    // For Spring-Data (data-source elements of `hibernate.properties`)
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUsername("public_test");
        dataSource.setPassword("PUBLIC_PSWD");
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");

        return dataSource;
    }

    // For Spring-Data
    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("springTest");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        em.afterPropertiesSet();

        return em.getObject();
    }

    // For Spring-Data
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    // For Spring-Data
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    // For Spring-Data (most of `hibernate.properties` elements)
    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
//        properties.put("logging.level.org.hibernate.SQL", "debug");
//        properties.put("logging.level.org.hibernate.type.descriptor.sql", "trace");
        properties.put("hibernate.show-sql", "true"); // todo: currently doesn't work
        properties.put("hibernate.format-sql", "true");
        properties.put("hibernate.hbm2ddl.import_files_sql_extractor",
                "org.hibernate.tool.hbm2ddl.MultipleLinesSqlCommandExtractor");
        properties.put("hibernate.hbm2ddl.import_files", "data.sql");
        properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");

        return properties;
    }
}
