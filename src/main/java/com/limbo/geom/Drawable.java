package com.limbo.geom;

import com.limbo.renderer.ImageRaster;

/**
 * 代表一个可渲染物体。
 *
 */
public interface Drawable {

    public void draw(ImageRaster imageRaster);

}