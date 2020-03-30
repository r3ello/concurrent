package concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class UsingSincronize {
	private static final Logger LOG = Logger.getLogger("UsingSincronize");
	private  volatile int count;
	
	
	 
	public synchronized int getIncrementCount() 
	{
		 LOG.info("Thread - " + Thread.currentThread().getName() + " lock");
		 int i=0;
		 while(i++<5) {
		 count++;
		 LOG.info("count = " + count +" to Thread - " + Thread.currentThread().getName());
		 }
		 
		 LOG.info("Thread - " + Thread.currentThread().getName() + " unlock");
		return count;
	} 
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UsingSincronize c= new UsingSincronize();
		final ExecutorService service = Executors.newFixedThreadPool(3);
	    service.execute(c::getIncrementCount);
	    service.execute(c::getIncrementCount);

	        service.shutdown();

	}

	
}

