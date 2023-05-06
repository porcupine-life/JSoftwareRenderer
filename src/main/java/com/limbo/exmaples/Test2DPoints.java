package com.limbo.exmaples;

import com.limbo.Application;
import com.limbo.geom.Point2D;
import com.limbo.math.ColorRGBA;

import java.util.Random;


/**
 * 绘制2D点
 *
 * @author yanmaoyuan
 *
 */
public class Test2DPoints extends Application {

    public static void main(String[] args) {
        Test2DPoints app = new Test2DPoints();
        app.setResolution(1080, 720);
        app.setTitle("2D Points");
        app.setFrameRate(120);
        app.start();
    }

    /**
     * 初始化
     */
    @Override
    protected void initialize() {
        Random rand = new Random();
        /**
         * 随机生成点
         */
        for(int i=0; i<1000; i++) {
            Point2D point = new Point2D();
            point.x = rand.nextInt(width);
            point.y = rand.nextInt(height);
            point.color = new ColorRGBA(rand.nextInt(0x4FFFFFFF));

            // 添加到场景中
            scene.add(point);
        }
    }

    @Override
    protected void update() {
    }

}
