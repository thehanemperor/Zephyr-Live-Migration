package com.example.demo.config.multitenant.schema;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class TenantConnectionProvider implements  MultiTenantConnectionProvider{
    private  static  Logger logger = LoggerFactory.getLogger(TenantConnectionProvider.class);
    private  String DEFAULT_TENANT = "INFORMATION_SCHEMA";
    private  DataSource dataSource;
    public TenantConnectionProvider(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
            return dataSource.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException{
        connection.close();
    }

    @Override
    public  Connection getConnection(String tenantId) throws SQLException{
        logger.info("Get connection for tenant {}",tenantId);
        final Connection connection = getAnyConnection();
        connection.setSchema(tenantId);
        return connection;
    }

    @Override
    public void releaseConnection(String tenantId, Connection connection) throws SQLException{
        logger.info("Release connection for tenant {}", tenantId);
        connection.setSchema(DEFAULT_TENANT);
        releaseAnyConnection(connection);
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean isUnwrappableAs(Class aClass) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        return null;
    }

}
