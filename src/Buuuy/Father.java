package Buuuy;

public class Father implements Runnable {

    @Override
    public void run() {
        System.out.println("爸爸想抽烟，发现烟抽完了");
        System.out.println("爸爸让儿子去买烟");
        // 创建儿子的线程
        Thread son = new Thread(new BuyOr());
        son.start();
        System.out.println("爸爸等儿子买烟回来");
        try {
            // 等son执行完，father线程才能继续执行
            son.join();
        } catch (InterruptedException e) {
            System.out.println("爸爸出门找儿子看去哪儿了");
            e.printStackTrace();
        }
        System.out.println("儿子买烟回来了，被妈妈没收了");
    }
}
