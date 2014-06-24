package com.zhuc.my.jdk.watermark;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片水印
 *
 * @blog http://www.micmiu.com
 * @author Michael
 */
public class ImageMarkLogoByIcon {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String srcImgPath = "d:/test/micmiu/java-img-src.png";
		String iconPath = "d:/test/micmiu/java-micmiu-logo.png";
		String targerPath = "d:/test/micmiu/img_mark_icon.jpg";
		String targerPath2 = "d:/test/micmiu/img_mark_icon_rotate.jpg";

		// 给图片添加水印
		ImageMarkLogoByIcon.markImageByIcon(iconPath, srcImgPath, targerPath);
		// 给图片添加水印,水印旋转-45
		ImageMarkLogoByIcon.markImageByIcon(iconPath, srcImgPath, targerPath2,
				-45);
	}

	/**
	 * 给图片添加水印
	 *
	 * @param iconPath水印图片路径
	 * @param srcImgPath源图片路径
	 * @param targerPath目标图片路径
	 */
	public static void markImageByIcon(String iconPath, String srcImgPath,
			String targerPath) {
		markImageByIcon(iconPath, srcImgPath, targerPath, null);
	}

	/**
	 * 给图片添加水印、可设置水印图片旋转角度
	 *
	 * @param iconPath水印图片路径
	 * @param srcImgPath源图片路径
	 * @param targerPath目标图片路径
	 * @param degree水印图片旋转角度
	 */
	public static void markImageByIcon(String iconPath, String srcImgPath,
			String targerPath, Integer degree) {
		OutputStream os = null;
		try {
			Image srcImg = ImageIO.read(new File(srcImgPath));

			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
					srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);

			// 得到画笔对象
			// Graphics g= buffImg.getGraphics();
			Graphics2D g = buffImg.createGraphics();

			// 设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);

			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg
					.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);

			if (null != degree) {
				// 设置水印旋转
				g.rotate(Math.toRadians(degree),
						(double) buffImg.getWidth() / 2, (double) buffImg
								.getHeight() / 2);
			}

			// 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
			ImageIcon imgIcon = new ImageIcon(iconPath);

			// 得到Image对象。
			Image img = imgIcon.getImage();

			float alpha = 0.5f; // 透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));

			// 表示水印图片的位置
			g.drawImage(img, 150, 200, null);

			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

			g.dispose();

			os = new FileOutputStream(targerPath);

			// 生成图片
			ImageIO.write(buffImg, "JPG", os);

			System.out.println("图片完成添加Icon印章。。。。。。");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param iconPath
	 * @param srcImgPath
	 * @param targerPath
	 */
	public static void markJPEGByIcon(String iconPath, String srcImgPath,
			String targerPath) {
		// 主图片的路径
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(srcImgPath);
			// 通过JPEG图象流创建JPEG数据流解码器
			JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
			// 解码当前JPEG数据流，返回BufferedImage对象
			BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
			// 得到画笔对象
			// Graphics g= buffImg.getGraphics();
			Graphics2D g = buffImg.createGraphics();

			// 印章图象的路径 .png可以设置透明度
			ImageIcon imgIcon = new ImageIcon(iconPath);

			// 得到Image对象。
			Image img = imgIcon.getImage();

			float alpha = 0.5f; // 透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));

			// 5,300 .表示你的小图片在大图片上的位置。
			g.drawImage(img, 150, 300, null);

			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

			g.dispose();

			os = new FileOutputStream(targerPath);

			// 创键编码器，用于编码内存中的图象数据。

			JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
			en.encode(buffImg);
			System.out.println("图片完成添加Icon印章。。。。。。");
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