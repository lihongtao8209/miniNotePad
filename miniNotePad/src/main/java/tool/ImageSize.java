package tool;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class ImageSize {
	private int height = 0;
	private int width  = 0;
	private Image image=null;
	private BufferedInputStream in = null;
	private String imagePath = null;
	public ImageSize(String imagePath) throws Exception {
		this.imagePath = imagePath;
		in = new BufferedInputStream(new FileInputStream(this.imagePath));
		// 字节流转图片对象
		image = ImageIO.read(in);
		// 获取图像的高度,宽度
		height = image.getHeight(null);
		width = image.getWidth(null);
	}

	public void setSize() throws IOException {
		// 构建图片流
		BufferedImage bufferImage = new BufferedImage(width / 2, height / 2, BufferedImage.TYPE_INT_RGB);
		// 绘制改变尺寸后的图
		bufferImage.getGraphics().drawImage(image, 0, 0, width / 2, height / 2, null);
		// 输出流
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("E:/copy.png"));
		ImageIO.write(bufferImage, "PNG", out);
		in.close();
		out.close();
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public static void main(String[] args)
	{
		try {
			ImageSize imageSize = new ImageSize("F:\\work\\Java\\web\\miniNotePad\\src\\main\\java\\image\\JminiNotePad.png");
			imageSize.setSize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testcase() {
		try {
			ImageSize imageSize = new ImageSize("F:\\work\\Java\\web\\miniNotePad\\src\\main\\java\\image\\JminiNotePad.png");
			imageSize.setSize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
