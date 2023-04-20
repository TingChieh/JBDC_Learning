package Thread;

// public class Test extends Thread {
//     @Override
//     public void run() {
//         for (int i = 0; i < 5; i++) {
//             System.out.println("Thread" + i + "run method");
//             try {
//                 Thread.sleep(2000);
//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//             }
//         }
//         System.out.println("end");
//     }
public class Test implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread" + i + "run method");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        Thread myThread = new Thread(new Test());
        System.out.println("线程开始");
        myThread.start();
    }
}
