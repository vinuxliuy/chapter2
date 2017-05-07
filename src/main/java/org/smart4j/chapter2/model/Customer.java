package org.smart4j.chapter2.model;

import java.sql.Timestamp;

/**
 * 客户类
 * Created by admin on 2017/5/6.
 */
public class Customer {
    /**
     * ID
     */
    private long id;
    /**
     * 客户名称
     */
    private String name;
    /**
     * 联系人
     */
    private String contect;
    /**
     * 电话号码
     */
    private String telephone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Timestamp cdate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContect() {
        return contect;
    }

    public void setContect(String contect) {
        this.contect = contect;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getCdate() {
        return cdate;
    }

    public void setCdate(Timestamp cdate) {
        this.cdate = cdate;
    }
}
