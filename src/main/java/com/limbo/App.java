package com.limbo;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        Application app = new Application();
        app.setResolution(720, 405);
        app.setTitle("Java软光栅渲染器 - Main");
        app.setFrameRate(120);
        app.start();
    }
}
