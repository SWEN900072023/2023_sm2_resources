package test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import utils.TransferType;

public class ConcurrentTransfers {

	public static void main(String[] args) {

		TransferType type =  TransferType.NO_ACID;
        //TransferType type =  TransferType.ACID;
		//TransferType type =  TransferType.OPTIMISTIC;
		//TransferType type =  TransferType.OPTIMISTIC_WRONG;
		//TransferType type =  TransferType.PESSIMISTIC_EX;
		//TransferType type =  TransferType.PESSIMISTIC_WAIT;

        //Config test parameters
		int numThreads = 100;
		CountDownLatch startLatch = new CountDownLatch(1);
        String fromAccount = "Alice";
        String toAccount = "Bob";
        int amount = 10;
        List<Thread> threads = new ArrayList<Thread>();

        //Start threads
        System.out.println("Starting threads");
        for (int i = 0; i < numThreads; i++) {
           Thread t = new TransferThread(fromAccount, toAccount, amount, type, startLatch);
           threads.add(t);
           t.start();

        }
        startLatch.countDown();
        try {
            for (Thread t: threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Threads finished");
    }

}
