package Thread;

//定义一个实现 Runnable 接口的类 RunnableDemo
class RunnableDemo implements Runnable {
   private Thread t; //定义一个线程对象
   private String threadName; //定义线程名
   
   //定义一个构造方法，传入线程名
   RunnableDemo( String name) {
      threadName = name; //将传入的线程名赋值给线程对象
      System.out.println("Creating " +  threadName );
   }
   
   //实现 Runnable 接口的 run() 方法
   public void run() {
      System.out.println("Running " +  threadName );
      try {
         for(int i = 4; i > 0; i--) {
            System.out.println("Thread: " + threadName + ", " + i);
            // 让线程睡眠一会
            Thread.sleep(50);
         }
      }catch (InterruptedException e) {
         System.out.println("Thread " +  threadName + " interrupted.");
      }
      System.out.println("Thread " +  threadName + " exiting.");
   }
   
   //定义一个 start() 方法，启动线程
   public void start () {
      System.out.println("Starting " +  threadName );
      if (t == null) { //如果线程对象为 null，则创建线程
         t = new Thread (this, threadName);
         t.start (); //启动线程
      }
   }
}
 
//定义一个 TestThread 类
public class TestThread {
 
   public static void main(String args[]) {
      //创建 RunnableDemo 对象，传入线程名
      RunnableDemo R1 = new RunnableDemo( "Thread-1");
      R1.start(); //启动线程
      
      //创建另一个 RunnableDemo 对象，传入线程名
      RunnableDemo R2 = new RunnableDemo( "Thread-2");
      R2.start(); //启动线程
   }   
}
