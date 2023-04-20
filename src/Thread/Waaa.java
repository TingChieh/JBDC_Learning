package Thread;

public class Waaa implements Runnable{
    private boolean isStop;//标志线程是否停止
	
	@Override
	public void run() {
		while(!isStop)
			System.out.println("这是我线程的run方法");
	}
	
	public void stop() {
		isStop=true;
	}
	
	public static void main(String[] args) {
		Waaa myRunnable=new Waaa();
		Thread myThread=new Thread(myRunnable);
		myThread.start();
		try {
			//让main函数的线程先暂停一会儿
			//让main函数的线程暂停2毫秒，这个时候我们自己创建的线程在执行
			Thread.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		myRunnable.stop();
		System.out.println("线程停止！");
	}

}
