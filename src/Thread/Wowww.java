package Thread;

public class Wowww implements Runnable{
    private String name;
	
	Wowww(String s){
		this.name=s;
	}
	
	@Override
	public void run() {
		System.out.println("这是线程"+name);
		for(int i=1;i<=6;i++) {
			System.out.println(name+":"+i);
			//能被 4 整除时，就先执行main线程
			if(i%4==0) {
				Thread.yield();
				System.out.println("线程暂停");
			}
		}
	}

	public static void main(String[] args) {
		//开两个线程
		Runnable r1=new Wowww("S1");
		Runnable r2=new Wowww("S2");
		Thread t1=new Thread(r1);
		Thread t2=new Thread(r2);
		t1.start();
		t2.start();
		try {
			//main线程休眠 3000ms
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("线程结束");
	}

}
