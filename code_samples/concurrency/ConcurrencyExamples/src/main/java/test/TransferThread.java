package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

import optimistic.TransferOptimistic;
import optimistic.TransferOptimisticWrong;
import pessimistic.exception.TransferPessimisticEx;
import pessimistic.wait.TransferPessimisticWait;
import systrans.TransferAcid;
import systrans.TransferNoAcid;
import transfer.Transfer;
import utils.DBUtils;
import utils.TransferType;

public class TransferThread extends Thread {

	private TransferType transferType;
	private CountDownLatch startLatch;
	private String fromAccount;
	private String toAccount;
	private int amount;

	public TransferThread(String fromAccount, String toAccount, int amount, TransferType transferType, CountDownLatch startLatch) {
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
		this.transferType = transferType;
		this.startLatch = startLatch;
	}

	@Override
	public void run() {
		try {
			startLatch.await();
			
			try (Connection conn = DBUtils.getConnection()) {
				Transfer transfer = switch (transferType) {
					case ACID -> new TransferAcid(conn);
					case NO_ACID -> new TransferNoAcid(conn);
					case OPTIMISTIC -> new TransferOptimistic(conn);
					case OPTIMISTIC_WRONG -> new TransferOptimisticWrong(conn);
					case PESSIMISTIC_EX -> new TransferPessimisticEx(conn);
					case PESSIMISTIC_WAIT -> new TransferPessimisticWait(conn);
				};
				transfer.transfer(fromAccount, toAccount, amount);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
