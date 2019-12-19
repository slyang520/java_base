package com.slyang.test.concurrent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkBlur extends RecursiveAction {

    private int[] mSource;
    private int mStart;
    private int mLength;
    private int[] mDestination;

    //  处理大小，应该是奇数
    private int mBlurWidth = 15;

    /**
     * @param src    源
     * @param start  开始处理索引
     * @param length 处理个数
     * @param dst    目标承载
     */
    public ForkBlur(int[] src, int start, int length, int[] dst) {
        mSource = src;
        mStart = start;
        mLength = length;
        mDestination = dst;
    }

    /**
     * 计算平均值和填充目标值的逻辑，如果看不懂可以不必深究
     */
    protected void computeDirectly() {
        int sidePixels = (mBlurWidth - 1) / 2;
        for (int index = mStart; index < mStart + mLength; index++) {
            // 计算平均值
            float rt = 0, gt = 0, bt = 0;
            for (int mi = -sidePixels; mi <= sidePixels; mi++) {
                int mindex = Math.min(Math.max(mi + index, 0),
                        mSource.length - 1);
                int pixel = mSource[mindex];
                rt += (float) ((pixel & 0x00ff0000) >> 16)
                        / mBlurWidth;
                gt += (float) ((pixel & 0x0000ff00) >> 8)
                        / mBlurWidth;
                bt += (float) ((pixel & 0x000000ff) >> 0)
                        / mBlurWidth;
            }

            // 重组目标像素
            int dpixel = (0xff000000) |
                    (((int) rt) << 16) |
                    (((int) gt) << 8) |
                    (((int) bt) << 0);
            mDestination[index] = dpixel;
        }
    }

    protected static int sThreshold = 100000;

    /**
     * 现在您实现了抽象compute()方法，它可以直接执行模糊，也可以将其分成两个较小的任务。
     * 简单的数组长度阈值有助于确定工作是执行还是拆分。
     */
    @Override
    protected void compute() {
        // 如果当前处理的个数小于 定义的 100000 个，那么就直接计算
        if (mLength < sThreshold) {
            computeDirectly();
            return;
        }

        // 否则进行拆分成2个任务
        int split = mLength / 2;

        // 第一个任务处理：前一半的工作
        // 第二个任务处理：剩下的工作
        invokeAll(new ForkBlur(mSource, mStart, split, mDestination),
                new ForkBlur(mSource, mStart + split, mLength - split,
                        mDestination));
    }


    // Plumbing follows.
    public static void main(String[] args) throws Exception {
        String srcName = "/Users/slyang/openSourceProject/java/java_base/src/test/mmexport1560839644914.jpg";
        File srcFile = new File(srcName);
        BufferedImage image = ImageIO.read(srcFile);

        System.out.println("原图: " + srcName);

        BufferedImage blurredImage = blur(image);

        String dstName = "/Users/slyang/openSourceProject/java/java_base/src/test/mmexport1560839644914_blur.jpg";
        File dstFile = new File(dstName);
        ImageIO.write(blurredImage, "jpg", dstFile);

        System.out.println("输出图: " + dstName);

    }

    public static BufferedImage blur(BufferedImage srcImage) {
        int w = srcImage.getWidth();
        int h = srcImage.getHeight();

        int[] src = srcImage.getRGB(0, 0, w, h, null, 0, w);
        int[] dst = new int[src.length];

        System.out.println("像素数组大小：" + src.length);
        System.out.println("边界大小：" + sThreshold);

        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(Integer.toString(processors) + " 个处理器可用");

        // 使用一个fork，我们最终是想要处理完这一个图片。所以这里的输入参数是 0 - src.length
        // 但是处理里面会根据定义的边界进行递归细分任务
        ForkBlur fb = new ForkBlur(src, 0, src.length, dst);

        // 再定义一个池
        ForkJoinPool pool = new ForkJoinPool();

        long startTime = System.currentTimeMillis();
        // 提交给池
        pool.invoke(fb);

        long endTime = System.currentTimeMillis();

        System.out.println("图形模糊耗时 " + (endTime - startTime) +
                " milliseconds.");

        BufferedImage dstImage =
                new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        dstImage.setRGB(0, 0, w, h, dst, 0, w);

        return dstImage;
    }

}
