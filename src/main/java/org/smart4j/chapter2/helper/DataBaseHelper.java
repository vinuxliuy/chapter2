package org.smart4j.chapter2.helper;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import org.smart4j.chapter2.util.CollectionUtil;
import org.smart4j.chapter2.util.PropertiesUtil;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库操作助手类
 * Created by admin on 2017/5/7.
 */
public final class DataBaseHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseHelper.class);

    private static final ThreadLocal<Connection> CONNECTION_HOLDER ;
    private static final QueryRunner QUERY_RUNNER ;
    private static final BasicDataSource DATA_SOURCE;

    static {
         Properties props = PropertiesUtil.loadProps("database.properties");
        CONNECTION_HOLDER = new ThreadLocal<Connection>();  //线程隔离器，确保一个线程只有一个独立的Connection
        QUERY_RUNNER = new QueryRunner(); //QueryRunner可以对象面向实体查询
        DATA_SOURCE = new BasicDataSource();//数据库连接池
        DATA_SOURCE.setDriverClassName(props.getProperty("jdbc.driver"));
        DATA_SOURCE.setUrl(props.getProperty("jdbc.url"));
        DATA_SOURCE.setUsername(props.getProperty("jdbc.username"));
        DATA_SOURCE.setPassword(props.getProperty("jdbc.password"));
    }



    /**
     *  获取数据库连接
     */
    private  static Connection getConnection(){
        Connection connection = CONNECTION_HOLDER.get();//先在线程中查找,否则的创建新的connection
        if(connection == null){
            try {
                connection = DATA_SOURCE.getConnection();
            }catch (SQLException e){
                LOGGER.error("get connection failure",e);
            }finally {
                CONNECTION_HOLDER.set(connection);//新的connection放到线程中
            }
        }
        return connection;
    }

    /**
     *  查询实体列表
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass,String sql,Object... params){
        List<T> entityList;
            try {
                Connection conn = getConnection();
                entityList = QUERY_RUNNER.query(conn,sql,new BeanListHandler<T>(entityClass),params);
            } catch (SQLException e) {
                LOGGER.error("query entity list failure",e);
                throw new RuntimeException(e);
            }
        return entityList;
    }

    /**
     * 查询实体
     */
    public static <T> T queryEntity(Class<T> entityClass,String sql,Object... params){
        T entity ;
        try {
            Connection conn = getConnection();
            entity =  QUERY_RUNNER.query(conn,sql,new BeanHandler<T>(entityClass),params);
        } catch (SQLException e) {
            LOGGER.error("query entity failure",e);
            throw new RuntimeException(e);
        }
        return entity;
    }

    /**
     *  查询语句
     */
    public static List<Map<String,Object>> executeQuery(String sql, Object... params){
        List<Map<String,Object>> result = null;
        try {
            Connection conn = getConnection();
            result = QUERY_RUNNER.query(conn, sql, new MapListHandler(), params);
        }catch (Exception e){
            LOGGER.error("execute query failure",e);
            throw new RuntimeException(e);
        }
        return  result;
    }

    /**
     *  执行Update语句
     */
    public static int executeUpdate(String sql, Object... params){
        int rows = 0;
        try {
            Connection conn = getConnection();
            rows = QUERY_RUNNER.update(conn, sql,params);
        }catch (Exception e){
            LOGGER.error("execute query failure",e);
            throw new RuntimeException(e);
        }
        return  rows;
    }

    /**
     *  插入实体
     */
    public static <T> boolean insertEntity(Class<T> entityClass,Map<String,Object> fieldMap){
        if(CollectionUtil.isEmpty(fieldMap)){
            LOGGER.error("can not insert entity:fieldMap is empty");
            return false;
        }
        String sql = "INSERT INTO "+getTableName(entityClass);
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for (String fieldName : fieldMap.keySet()){
            columns.append(fieldName).append(",");
            values.append("?,");
        }
        columns.replace(columns.lastIndexOf(","),columns.length(),")");
        values.replace(values.lastIndexOf(","),values.length(),")");
        sql+=columns+" values "+values;

        Object[] params = fieldMap.values().toArray();
        return executeUpdate(sql,params) == 1;
    }

    /**
     *  修改实体
     */
    public static <T> boolean updateEntity(Class<T> entityClass,long id ,Map<String,Object> fieldMap){
        if(CollectionUtil.isEmpty(fieldMap)){
            LOGGER.error("can not insert entity:fieldMap is empty");
            return false;
        }
        String sql = "UPDATE "+getTableName(entityClass)+" SET ";
        StringBuilder columns = new StringBuilder();
        for (String fieldName : fieldMap.keySet()){
            columns.append(fieldName).append("=?,");
        }
        sql+=columns.substring(0,columns.lastIndexOf(","))+" WHERE id = ?";
        List<Object> paramList = new ArrayList<Object>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);
        Object[] params = paramList.toArray();
        return executeUpdate(sql,params) == 1;
    }

    /**
     *  删除实体
     * @return
     */
    public static <T> boolean deleteEntity(Class<T> entityClass,long id ){
        String sql = "DELETE FROM "+ getTableName(entityClass) + " WHERE id=?";
        return executeUpdate(sql,id) == 1;
    }

    private static <T> String getTableName(Class<T> entityClass) {
        return entityClass.getSimpleName();
    }




}
