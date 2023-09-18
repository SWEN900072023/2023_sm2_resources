package pessimistic.wait;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LockManagerWait {
	
	private static LockManagerWait instance;
	
	//Key: lockable
	//Value: owner
	private ConcurrentMap<String, String> lockMap;

	public static synchronized LockManagerWait getInstance() {
		if(instance == null) {
			instance = new LockManagerWait();
		}
		return instance;
	}
	
	private LockManagerWait() {
		lockMap = new ConcurrentHashMap<String, String>();
	}
	
	public synchronized void acquireLock(String lockable, String owner) {
		while(lockMap.containsKey(lockable)) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		lockMap.put(lockable, owner);
	}
	
	public synchronized void releaseLock(String lockable, String owner) {
		//TODO you would usually check the owner of the lock is the
		// one attempting to release it
		lockMap.remove(lockable);
		notifyAll();
	}
}
