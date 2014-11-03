package com.example.mymodule.app2;

public class SimpleThread implements Runnable{
    private Thread t;
    private String threadName;

    public SimpleThread(String name)
    {
        threadName = name;
    }

    public void run()
    {
        int count = 0;
        while (count < 11)
        {
            try {
                Thread.sleep(100);
                System.out.println(count + " " + threadName);
                if (count == 10) System.out.println("DONE! " + threadName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public void start()
    {
        t.start();
    }

    public void main(){
        SimpleThread st = new SimpleThread("hello");
    }
}
