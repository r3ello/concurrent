/**
 * 
 */
package concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;


public class UsingAtomicInteger {
	private static final Logger LOG = Logger.getLogger("UsingAtomicInteger");
	private final  AtomicInteger count = new AtomicInteger(0);
	 
	
	public void getIncrementCount() {
		LOG.info("Thread - " + Thread.currentThread().getName() + " lock");
				
		while(true) {
            int currentValue = count.get();
            LOG.info("Thread - " + Thread.currentThread().getName() + " currentValue = " + currentValue);
            int newValue = currentValue+1;
            LOG.info("Thread - " + Thread.currentThread().getName() +" newValue = " + newValue);
            if(count.compareAndSet(currentValue, newValue)) {
            	LOG.info("count = " + count.get()+" by Thread -"+ Thread.currentThread().getName());
            	LOG.info("Thread - " + Thread.currentThread().getName() + " unlock");
                return;
            }
        
		 }
    }
	
	public static void main(String[] args) {
		UsingAtomicInteger c= new UsingAtomicInteger();
		final ExecutorService service = Executors.newFixedThreadPool(3);
	    service.execute(c::getIncrementCount);
	    service.execute(c::getIncrementCount);
        service.shutdown();

	}

}
