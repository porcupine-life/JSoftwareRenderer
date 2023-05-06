package com.limbo.renderer;


import com.limbo.math.ColorRGBA;

/**
 * 光栅器，用于绘制基本形状。
 *
 */
public class ImageRaster {

    private final int width;
    private final int height;
    private final byte[] components;

    public ImageRaster(Image image) {
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.components = image.getComponents();
    }

    /**
     * 纯色填充
     *
     * @param color
     */
    public void fill(ColorRGBA color) {
        int length = width * height;
        for (int i = 0; i < length; i++) {
            int index = i * 4;

            // 使用一个判断，避免无谓的赋值。
            if (components[index] != color.r || components[index + 1] != color.g
                    || components[index + 2] != color.b || components[index + 3] != color.a) {
                components[index] = color.r;
                components[index + 1] = color.g;
                components[index + 2] = color.b;
                components[index + 3] = color.a;
            }
        }
    }

    /**
     * 画点
     *
     * @param x
     * @param y
     * @param color
     */
    public void drawPixel(int x, int y, ColorRGBA color) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return;
        }

        int index = (x + y * width) * 4;

        components[index] = color.r;
        components[index + 1] = color.g;
        components[index + 2] = color.b;
        components[index + 3] = color.a;
    }
}
