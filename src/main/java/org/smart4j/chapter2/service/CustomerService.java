package org.smart4j.chapter2.service;

import org.smart4j.chapter2.helper.DataBaseHelper;
import org.smart4j.chapter2.model.Customer;
import org.smart4j.chapter2.util.PropertiesUtil;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 提供客户数据服务
 * Created by admin on 2017/5/6.
 */
public class CustomerService {

    /**
     * 获取客户列表
     */
    public List<Customer> getCustomerList(String sql){
        return DataBaseHelper.queryEntityList(Customer.class, sql);
    }

    /**
     * 创建客户
     */
    public boolean addCustomer(Map<String,Object> fieldMap){
        return DataBaseHelper.insertEntity(Customer.class, fieldMap);
    }

    /**
     * 修改客户
     */
    public boolean updateCustomer(Map<String,Object> fieldMap,long id){
        return DataBaseHelper.updateEntity(Customer.class,id, fieldMap);
    }

    /**
     *  删除客户
     */
    public boolean deleteCustomer(long id){
        return DataBaseHelper.deleteEntity(Customer.class, id);
    }

}
