package Buuuy;

public class Tttt {
    public static void main(String[] args) {
        System.out.println("一个小故事");
        Thread father = new Thread(new Father());
        father.start();
    }
}