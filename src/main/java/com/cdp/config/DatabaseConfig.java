package com.cdp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class DatabaseConfig {

    private final FeatureConfig featureConfig;
    private final Environment env; // Autowired Spring Environment

    @Bean
    @ConditionalOnProperty(name = "features.database.maria-db.enabled", havingValue = "true")
    public DataSource dataSource() {
        String dbUrl = buildDatabaseUrl();
        String username = env.getProperty("spring.datasource.username", "root");
        String password = env.getProperty("spring.datasource.password", "Dreamer@1");

        return DataSourceBuilder.create()
                .url(dbUrl)
                .username(username)
                .password(password)
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }

    private String buildDatabaseUrl() {
        String host = env.getProperty("spring.datasource.host", "localhost");
        String port = env.getProperty("spring.datasource.port", "3306");
        String database = env.getProperty("spring.datasource.database", "adp");

        return String.format("jdbc:mariadb://host.docker.internal:3306/adp?useSSL=false&serverTimezone=UTC",
                host, port, database);
    }

    @Bean
    @ConditionalOnProperty(name = "features.database.maria-db.enabled", havingValue = "true")
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @ConditionalOnProperty(name = "features.database.maria-db.enabled", havingValue = "true")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource()); // Use the bean we defined
        em.setPackagesToScan("com.cdp.domain.model");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.use_sql_comments", "true");
        properties.setProperty("hibernate.format_sql_comments", "true");

        em.setJpaProperties(properties);
        return em;
    }

    @Bean
    @ConditionalOnProperty(name = "features.database.maria-db.enabled", havingValue = "true")
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }
}