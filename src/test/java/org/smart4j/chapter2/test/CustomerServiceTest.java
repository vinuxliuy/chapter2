package org.smart4j.chapter2.test;

import org.junit.Before;
import org.junit.Test;
import org.smart4j.chapter2.model.Customer;
import org.smart4j.chapter2.service.CustomerService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CustomerService单元测试
 * Created by admin on 2017/5/6.
 */
public class CustomerServiceTest {
    private final CustomerService customerService;
    public CustomerServiceTest() {
        customerService = new CustomerService();
    }

    @Before
    public void init(){
        new CustomerServiceTest();
    }

    @Test
    public void customerTest(){
//        Map<String , Object> customerMap = new HashMap<String , Object>();
//        customerMap.put("name","JackWang");
//        customerMap.put("contect","JackWang");
//        customerMap.put("telephone","888888888");
//        customerMap.put("email","JackWang@mail.com");
//        customerMap.put("remark",null);
//        customerMap.put("cdate",new Timestamp(new Date().getTime()));
//        customerService.addCustomer(customerMap);

        System.out.print("这段话些什么呢?");

        String sql = "SELECT * FROM customer";
        List<Customer> customerList = customerService.getCustomerList(sql);
        for (int i =0; i<customerList.size();i++){
            Customer customer = customerList.get(i);
            System.out.print("customer:"+customer.getName()+","+customer.getTelephone());
        }
    }

}
