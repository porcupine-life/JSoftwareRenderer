package com.limbo;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * 代表显示图像的窗口
 *
 * @author Limbo
 *
 */
public class Screen {

    // 主窗口
    private final JFrame frame;

    // 用于显示的图像
    private BufferedImage displayImage;

    // Canvas的双缓冲
    private BufferStrategy bufferStrategy;

    public Screen(int width, int height, String title) {
        // 画布
        Canvas canvas = new Canvas();

        // 设置画布的尺寸
        Dimension size = new Dimension(width, height);
        canvas.setPreferredSize(size);
        canvas.setMaximumSize(size);
        canvas.setMinimumSize(size);
        canvas.setFocusable(true);

        // 创建主窗口
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(width, height);
        frame.setTitle(title);
        frame.add(canvas);// 设置画布
        frame.pack();
        frame.setVisible(true);
        centerScreen();// 窗口居中

        // 焦点集中到画布上，响应用户输入。
        canvas.requestFocus();

        // 创建缓冲图像
        displayImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        // 创建双缓冲
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
    }

    /**
     * 使窗口位于屏幕的中央。
     */
    private void centerScreen() {
        Dimension size = frame.getSize();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - size.width) / 2;
        int y = (screen.height - size.height) / 2;
        frame.setLocation(x, y);
    }


    /**
     * 交换缓冲区，将渲染结果刷新到画布上。
     * @param fps
     */
    public void swapBuffer(int fps) {

        Graphics graphics = bufferStrategy.getDrawGraphics();

        // 将BufferedImage绘制到缓冲区
        graphics.drawImage(displayImage, 0, 0, displayImage.getWidth(), displayImage.getHeight(), null);

        // 显示帧率
        graphics.setColor(Color.WHITE);
        graphics.drawString("FPS:" + fps, 2, 16);

        graphics.dispose();

        // 显示图像
        bufferStrategy.show();
    }
}