package com.zhuc.my.jdk.watermark;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

/**
 * 文字水印
 *
 * @blog http://www.micmiu.com
 * @author Michael
 */
public class ImageMarkLogoByText {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String srcImgPath = "d:/a.png";
		String logoText = "我是尉迟承缘";
		String targerPath = "d:/a_text.jpg";
		String targerPath2 = "d:/a_text_rotate.jpg";

		// 给图片添加水印
		ImageMarkLogoByText.markByText(logoText, srcImgPath, targerPath);

		// 给图片添加水印,水印旋转-45
		ImageMarkLogoByText.markByText(logoText, srcImgPath, targerPath2, -45);
	}

	/**
	 * 给图片添加水印
	 *
	 * @param logoText
	 * @param srcImgPath
	 * @param targerPath
	 */
	public static void markByText(String logoText, String srcImgPath, String targerPath) {
		markByText(logoText, srcImgPath, targerPath, null);
	}

	/**
	 * 给图片添加水印、可设置水印的旋转角度
	 *
	 * @param logoText
	 * @param srcImgPath
	 * @param targerPath
	 * @param degree
	 */
	public static void markByText(String logoText, String srcImgPath, String targerPath, Integer degree) {
		// 主图片的路径
		InputStream is = null;
		OutputStream os = null;
		try {
			Image srcImg = ImageIO.read(new File(srcImgPath));

			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null),
					BufferedImage.TYPE_INT_RGB);

			// 得到画笔对象
			// Graphics g= buffImg.getGraphics();
			Graphics2D g = buffImg.createGraphics();

			// 设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0,
					0, null);

			if (null != degree) {
				// 设置水印旋转
				g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
			}

			// 设置颜色
			g.setColor(Color.white);

			// 设置 Font
			g.setFont(new Font("宋体", Font.BOLD, 24));

			float alpha = 0.5f;
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

			// 第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y) .
			g.drawString(logoText, 100, 200);

			g.dispose();

			os = new FileOutputStream(targerPath);

			// 生成图片
			ImageIO.write(buffImg, "JPG", os);

			System.out.println("图片完成添加文字印章。。。。。。");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != is)
					is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}