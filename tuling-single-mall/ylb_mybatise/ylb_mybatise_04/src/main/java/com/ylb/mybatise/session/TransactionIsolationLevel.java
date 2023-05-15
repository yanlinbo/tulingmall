package com.ylb.mybatise.session;

import java.sql.Connection;

/**
 * TransactionIsolationLevel是枚举类
 */
public enum TransactionIsolationLevel {

    //包括JDBC支持的5个级别  表示TransactionIsolationLevel的5个实例
    NONE(Connection.TRANSACTION_NONE), //表示通过该构造方法创建了5个实例
    READ_COMMITTED(Connection.TRANSACTION_READ_COMMITTED),
    READ_UNCOMMITTED(Connection.TRANSACTION_READ_UNCOMMITTED),
    REPEATABLE_READ(Connection.TRANSACTION_REPEATABLE_READ),
    SERIALIZABLE(Connection.TRANSACTION_SERIALIZABLE);

    private final int level;  //枚举类的类属性

    TransactionIsolationLevel(int level) {
        this.level = level;
    }

    /**
     * 获取枚举类的类属性
     * @return
     */
    public int getLevel() {
        return level;
    }
}
