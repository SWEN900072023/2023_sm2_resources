package optimistic;

import java.sql.Connection;
import java.sql.SQLException;

import transfer.Transfer;
import utils.DBUtils;

public class TransferOptimisticWrong implements Transfer {

	private Connection conn;

	public TransferOptimisticWrong(Connection conn) {
		this.conn = conn;
	}

	public void transfer(String fromAccount, String toAccount, int amount) {
		Account from = Account.find(fromAccount, conn);
		Account to = Account.find(toAccount, conn);

		if (from.getBalance() >= amount) {
			from.addBalance((-1) * amount);
			to.addBalance(amount);

			from.update(conn);
			to.update(conn);
		}
	}

	public static void main(String[] args) {
		try (Connection conn = DBUtils.getConnection()) {
			TransferOptimisticWrong transfer = new TransferOptimisticWrong(conn);
			transfer.transfer("Alice", "Bob", 10);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
