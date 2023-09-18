package systrans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import transfer.Transfer;
import utils.DBUtils;

public class TransferAcid implements Transfer {

	private Connection conn;

	public TransferAcid(Connection conn) {
		this.conn = conn;
	}

	public void transfer(String fromAccount, String toAccount, int amount) {
		try {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
			
			//Start system transaction
			long fromBalance = getBalance(fromAccount);

			if (fromBalance >= amount) {
				updateBalance(fromAccount, (-1) * amount);
				updateBalance(toAccount, amount);
			}

			//end system transaction
			conn.commit();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
				System.out.println("Rolling back transaction");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void updateBalance(String toAccount, long amount) {
		String stmnt = "UPDATE account SET balance = balance + ? WHERE account_name = ?";
		try (PreparedStatement statement = conn.prepareStatement(stmnt)) {
			statement.setLong(1, amount);
			statement.setString(2, toAccount);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private long getBalance(String fromAccount) {
		String stmnt = "SELECT balance FROM account WHERE account_name = ?";
		try (PreparedStatement statement = conn.prepareStatement(stmnt)) {
			statement.setString(1, fromAccount);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getLong(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new IllegalArgumentException("Can't find account: " + fromAccount);
	}
	
	public static void main(String[] args) {
		try(Connection conn = DBUtils.getConnection()) {
			TransferAcid transfer = new TransferAcid(conn);
			transfer.transfer("Alice", "Bob", 10);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
