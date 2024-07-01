package thuctap.task4.config;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;


public class DBCP2Source {
    private static Logger logger = LogManager.getLogger(DBCP2Source.class);

    private static BasicDataSource dataSource = new BasicDataSource();
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${spring.datasource.driver-class-name}")
    private String dbDriverClassName;

    @Value("${spring.datasource.hikari.minimum-idle}")
    private int minIdle;

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private int maxPoolSize;

    @Value("${spring.datasource.hikari.idle-timeout}")
    private long idleTimeout;

    @Value("${spring.datasource.hikari.connection-timeout}")
    private long connectionTimeout;

    @Value("${spring.datasource.hikari.max-lifetime}")
    private long maxLifetime;

    @Value("${spring.datasource.hikari.leak-detection-threshold}")
    private long leakDetectionThreshold;

    @Value("${spring.datasource.hikari.validation-timeout}")
    private long validationTimeout;

    static {
        dataSource.setUrl(dataSource.getUrl());
        dataSource.setUsername(dataSource.getUsername());
        dataSource.setPassword(dataSource.getPassword());

        dataSource.setMinIdle(dataSource.getMinIdle());
        dataSource.setMaxIdle(dataSource.getMaxIdle());
        dataSource.setMaxTotal(dataSource.getMaxTotal());
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();

        logger.info("-----------------------");
        logger.info("+ Num of Idle Connections:: " + dataSource.getNumIdle());
        logger.info("+ Num of Busy Connections: " + dataSource.getNumActive());
        return connection;
    }
}
