package com.kamkry.app.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan({"com.kamkry.app"})
@EnableAutoConfiguration
@EnableTransactionManagement
public class AppConfig implements TransactionManagementConfigurer, WebMvcConfigurer {


    @Bean
    SessionFactory SessionFactory() {
        LocalSessionFactoryBuilder factoryBuilder = new LocalSessionFactoryBuilder(dataSource());
        return factoryBuilder.scanPackages("com.kamkry.app")
                .addProperties(properties())
                .buildSessionFactory();
    }

    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://sql.kamkry.eu/00381858_mysql?verifyServerCertificate=false&useSSL=false&requireSSL=false&serverTimezone=UTC");
        dataSource.setUsername("00381858_mysql");
        dataSource.setPassword("Db2019!?");
        return dataSource;
    }

    private Properties properties() {
        Properties properties = new Properties();
        properties.put("hibernate.format_sql", true);
        //properties.put("hibernate.show_sql", "true");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.put("hibernate.connection.datasource", dataSource());
        return properties;
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new HibernateTransactionManager(SessionFactory());
    }
}
