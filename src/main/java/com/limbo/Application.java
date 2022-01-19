package com.limbo;

/**
 * 应用程序主类
 *
 * @author Limbo
 */
public class Application {

    protected int width;
    protected int height;
    protected String title;

    // 显示器
    private Screen screen;

    // 运行状态
    private volatile boolean isRunning;

    // 固定帧率
    private boolean fixedFrameRate;
    private long fixedTime;
    // 帧率（FPS）
    private int framePerSecond;
    // FPS队列
    private final static int QUEUE_LENGTH = 60;
    private final float[] fps = new float[QUEUE_LENGTH];

    /**
     * 构造方法
     */
    public Application() {
        width = 800;
        height = 600;
        title = "JSoftwareRenderer";

        // 改变运行状态
        isRunning = true;

        // 关闭固定帧率
        setFrameRate(0);
    }

    /**
     * 启动程序
     */
    public void start() {

        // 计时器
        long startTime = System.nanoTime();
        long previousTime = System.nanoTime();
        long deltaTime;
        float delta;

        screen = new Screen(width, height, title);

        // 初始化
        initialize();

        while (isRunning) {
            // 计算间隔时间
            deltaTime = System.nanoTime() - previousTime;

            // 如果使用固定帧率
            if (fixedFrameRate && deltaTime < fixedTime) {
                // 线程等待时间（纳秒）
                long waitTime = fixedTime - deltaTime;

                long millis = waitTime / 1000000;
                long nanos = waitTime - millis * 1000000;
                try {
                    Thread.sleep(millis, (int) nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 重新计算间隔时间
                deltaTime = System.nanoTime() - previousTime;
            }

            previousTime = System.nanoTime();
            delta = deltaTime / 1000000000.0f;

            // 更新FPS
            updateFramePerSecond(delta);

            // 更新逻辑
            update();

            // 更新画面
            render();

        }

        // 计算总运行时间
        long totalTime = System.nanoTime() - startTime;
        System.out.println("运行总时间：" + totalTime / 1000000000.0f);
    }

    /**
     * 初始化
     */
    protected void initialize() {
        // TODO
    }

    /**
     * 更新场景
     */
    protected void update() {
        // TODO
    }

    /**
     * 绘制场景
     */
    protected void render() {
        // 交换画布缓冲区，显示画面
        screen.swapBuffer(framePerSecond);
    }

    /**
     * 停止程序
     */
    public void stop() {
        isRunning = false;
    }

    /**
     * 设置固定帧率
     *
     * @param rate 帧率
     */
    public void setFrameRate(int rate) {
        if (rate <= 0) {
            this.fixedFrameRate = false;
        } else {
            this.fixedFrameRate = true;
            this.fixedTime = 1000000000 / rate;
        }
    }

    /**
     * 更新FPS
     */
    private void updateFramePerSecond(float delta) {
        // 队列左移
        for (int i = 0; i < QUEUE_LENGTH - 1; i++) {
            fps[i] = fps[i + 1];
        }
        // 当前帧入列
        fps[QUEUE_LENGTH - 1] = 1 / delta;

        // 统计不为0的帧数
        int count = 0;
        int sum = 0;
        for (int i = 0; i < QUEUE_LENGTH; i++) {
            if (fps[i] > 0) {
                count++;
                sum += fps[i];
            }
        }

        // 求平均值
        framePerSecond = sum / count;
    }


    /**
     * 设置分辨率
     * @param width
     * @param height
     */
    public void setResolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * 设置标题
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
