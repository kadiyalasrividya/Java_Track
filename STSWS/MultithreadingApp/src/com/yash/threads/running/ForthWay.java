package com.yash.threads.running;
import java.util.concurrent.TimeUnit;

class FourthTask implements Runnable{
	private static int count=0;
	private int id;
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("<id : "+id+" :Count Down : >"+i);
			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}		
	}	
	public FourthTask() {
		id=++count;
		
	}	
}

public class ForthWay {
	public static void main(String[] args) {
		System.out.println("---------main starts-----------");
		new Thread(new FourthTask()).start();
		Thread t=new Thread(new FourthTask());
		t.start();
		System.out.println("---------main ends-----------");
	}
}
