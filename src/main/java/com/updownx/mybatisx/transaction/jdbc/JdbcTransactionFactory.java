package com.updownx.mybatisx.transaction.jdbc;

import com.updownx.mybatisx.session.TransactionIsolationLevel;
import com.updownx.mybatisx.transaction.Transaction;
import com.updownx.mybatisx.transaction.TransactionFactory;
import java.sql.Connection;
import javax.sql.DataSource;

/** JdbcTransaction 工厂 */
public class JdbcTransactionFactory implements TransactionFactory {

  @Override
  public Transaction newTransaction(Connection conn) {
    return new JdbcTransaction(conn);
  }

  @Override
  public Transaction newTransaction(
      DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
    return new JdbcTransaction(dataSource, level, autoCommit);
  }
}
