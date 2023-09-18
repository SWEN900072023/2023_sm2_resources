package pessimistic.exception;

import java.sql.Connection;
import java.sql.SQLException;

import pessimistic.Account;
import transfer.Transfer;
import utils.DBUtils;

public class TransferPessimisticEx implements Transfer {

	private Connection conn;
	
	public TransferPessimisticEx(Connection conn) {
		this.conn = conn;
	}

	public void transfer(String fromAccount, String toAccount, int amount) {
		
		//Start of transaction, acquire locks
		//These methods throw an exception if lock cannot be acquired
		LockManagerEx.getInstance().acquireLock(fromAccount, Thread.currentThread().getName());
		LockManagerEx.getInstance().acquireLock(toAccount, Thread.currentThread().getName());
		
		Account from = Account.find(fromAccount, conn);
		Account to = Account.find(toAccount, conn);

		if (from.getBalance() >= amount) {
			from.addBalance((-1) * amount);
			to.addBalance(amount);
			
			//Should these updates be in a single DB transaction?
			from.update(conn);
			to.update(conn);
		}
		
		//End of transaction, release locks
		LockManagerEx.getInstance().releaseLock(fromAccount, Thread.currentThread().getName());
		LockManagerEx.getInstance().releaseLock(toAccount, Thread.currentThread().getName());
	}


	
	public static void main(String[] args) {
		try(Connection conn = DBUtils.getConnection()) {
			TransferPessimisticEx transfer = new TransferPessimisticEx(conn);
			transfer.transfer("Alice", "Bob", 10);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
