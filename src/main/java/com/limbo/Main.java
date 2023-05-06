package com.limbo;

import com.limbo.geom.Point2D;
import com.limbo.math.ColorRGBA;

/**
 * Hello world!
 */
public class Main extends Application{
    public static void main(String[] args) throws InterruptedException {
        Main app = new Main();
        app.setResolution(720, 405);
        app.setTitle("Java软光栅渲染器 - Main");
//        app.setFrameRate(120);
        app.start();
    }

    @Override
    protected void initialize() {
        // 粉色
        ColorRGBA pink = new ColorRGBA(0xFF00FFFF);

        // 画一个方块
        for (int y = 100; y < 200; y++) {
            for (int x = 100; x < 200; x++) {
                Point2D point = new Point2D();
                point.x = x;
                point.y = y;
                point.color = pink;

                // 添加到场景中
                scene.add(point);
            }
        }
    }

    @Override
    protected void update() {
        // TODO Auto-generated method stub
    }
}
