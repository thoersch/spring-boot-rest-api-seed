package com.thoersch.seeds.init;


import com.thoersch.seeds.ApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.thoersch.seeds.persistence", repositoryFactoryBeanClass = JpaRepositoryFactoryBean.class)
public class SqlInitialization {
    private org.apache.tomcat.jdbc.pool.DataSource pool;

    @Inject
    private ApplicationConfiguration config;

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    public TransactionTemplate transactionTemplate() {
        return new TransactionTemplate(transactionManager());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        //Allow JodaTime
        entityManagerFactoryBean.getJpaPropertyMap().put("jadira.usertype.autoRegisterUserTypes", "true");

        entityManagerFactoryBean.getJpaPropertyMap().put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        entityManagerFactoryBean.getJpaPropertyMap().put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        entityManagerFactoryBean.getJpaPropertyMap().put("hibernate.temp.use_jdbc_metadata_defaults", "false");

        entityManagerFactoryBean.getJpaPropertyMap().put("hibernate.jadira.usertype.autoRegisterUserTypes", "true");
        entityManagerFactoryBean.getJpaPropertyMap().put("hibernate.jadira.usertype.javaZone", "UTC");
        entityManagerFactoryBean.getJpaPropertyMap().put("hibernate.jadira.usertype.databaseZone", "UTC");

        HibernateJpaVendorAdapter hibernateAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(hibernateAdapter);
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("com.thoersch.seeds.representations");



        return entityManagerFactoryBean;
    }

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        this.pool = new org.apache.tomcat.jdbc.pool.DataSource();
        this.pool.setDriverClassName(config.getDriverClass());
        this.pool.setUrl(config.getUrl());
        this.pool.setUsername(config.getUser());
        this.pool.setPassword(config.getPassword());
        this.pool.setTestOnBorrow(true);
        this.pool.setTestOnReturn(false);
        this.pool.setValidationQuery(config.getValidationQuery());
        return this.pool;
    }


    @PreDestroy
    public void close() {
        if (this.pool != null) {
            this.pool.close();
        }
    }
}
