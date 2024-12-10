package org.example;

public class Compteur extends Thread{
    public static int n;
    private static final Object mutex = new Object();
    public void run() {
        synchronized (mutex) {
            while(n<2000000){
                n++;

                System.out.println(n+" ");
            }



        }

    }
    public static void main(String[] args) {
//        Compteur c1=new Compteur();
//        Compteur c2 =new Compteur();
//        c1.start();c2.start();
        System.out.println(System.currentTimeMillis());
    }
}
