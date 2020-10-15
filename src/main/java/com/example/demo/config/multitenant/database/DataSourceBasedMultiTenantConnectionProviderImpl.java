package com.example.demo.config.multitenant.database;
import  org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.context.ApplicationContext;
import  javax.sql.DataSource;
import  java.util.HashMap;
import  java.util.Map;
import static  com.example.demo.constant.MultiTenantConstants.DEFAULT_TENANT_ID;
//public class DataSourceBasedMultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
//    @Autowired
//    private DataSource defaultDS;
//
//    @Autowired
//    private ApplicationContext context;
//
//    private Map<String,DataSource> map = new HashMap<>();
//    boolean init = false;
//
//    public  void load(){
//        map.put(DEFAULT_TENANT_ID,defaultDS);
//    }
//
//    @Override
//    protected DataSource selectAnyDataSource(){
//        return map.get(DEFAULT_TENANT_ID);
//    }
//
//    @Override
//    protected DataSource selectDataSource(String tenantId){
//        if (!init){
//            init = true;
//            TenantDataSource tenantDataSource = context.getBean(TenantDataSource.class);
//            map.putAll(tenantDataSource.getAll());
//        }
//        return map.get(tenantId) != null ? map.get(tenantId) :map.get(DEFAULT_TENANT_ID);
//    }
//}
