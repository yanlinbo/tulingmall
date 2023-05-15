package com.ylb.mybatise.transaction.jdbc;

import com.ylb.mybatise.session.TransactionIsolationLevel;
import com.ylb.mybatise.transaction.Transaction;
import com.ylb.mybatise.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

public class JdbcTransactionFactory implements TransactionFactory {
    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource, level, autoCommit);
    }
}
