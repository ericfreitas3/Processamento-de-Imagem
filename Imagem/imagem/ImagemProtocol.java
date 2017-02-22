/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagem;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author aluno
 */
public class ImagemProtocol {

	public ImagemProtocol(){

	}

	public static File abrirArquivo(JPanel pane) {
		File fLocal = new File("");
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int i = fc.showOpenDialog(pane);
		if (i == 0) {
			fLocal = fc.getSelectedFile();			
		} else {
			System.out.println("Operação cancelada");
		}
		return fLocal;
	}

	public static BufferedImage bufferImagem(File f) {
		BufferedImage imgBuff = null;
		try {
			imgBuff = ImageIO.read(f);
		} catch (IOException ex) {
			Logger.getLogger(Imagem.class.getName()).log(Level.SEVERE, null, ex);
		}
		return imgBuff;
	}

	public static BufferedImage byteToImage( byte[] bytes )
	{
		BufferedImage result = null;

		ByteArrayInputStream bis = new ByteArrayInputStream( bytes );

		try
		{
			result = ImageIO.read( bis );
		}
		catch ( Exception e )
		{
		}
		finally
		{
			try
			{
				bis.close();
			}
			catch ( Exception e )
			{
			}
		}

		return result;
	}

	public static byte[] imageToByte(Image image) {	
		BufferedImage bi = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_RGB);
		Graphics bg = bi.getGraphics();
		bg.drawImage(image, 0, 0, null);
		bg.dispose();
		ByteArrayOutputStream buff = new ByteArrayOutputStream();		
		try {  
			ImageIO.write(bi, "JPG", buff);  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return buff.toByteArray();		
	}

	//    public static Image byteToImage(byte[] bytes) {
	//		if(bytes == null) {
	//			return null;
	//		}else {
	//			return Toolkit.getDefaultToolkit().createImage(bytes);		   
	//		}
	//	}

	public static BufferedImage enlarge(BufferedImage image, int n) {
		int w = n * image.getWidth();
		int h = n * image.getHeight();        
		BufferedImage enlargedImage = new BufferedImage(w, h, image.getType());        
		for (int y=0; y < h; ++y)
			for (int x=0; x < w; ++x)
				enlargedImage.setRGB(x, y, image.getRGB(x/n, y/n));        
		return enlargedImage;
	}



	//Filtros

	public static BufferedImage limiar(BufferedImage image, int t) {
		int width = image.getWidth();
		int height = image.getHeight();
		Color rgb;
		int l;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				rgb = new Color(image.getRGB(i, j));
				l = (rgb.getRed() + rgb.getGreen() + rgb.getBlue()) / 3;
				if (l > t) {
					image.setRGB(i, j, 0xffffff);
				} else {
					image.setRGB(i, j, 0x000000);
				}
			}
		}
		return image;
	}

	public static BufferedImage toGray(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				int r = (int) ((rgb & 0x00FF0000) >>> 16);
				int g = (int) ((rgb & 0x0000FF00) >>> 8);
				int b = (int) (rgb & 0x000000FF);
				int gs = (r + g + b) / 3;
				Color color = new Color(gs, gs, gs);
				image.setRGB(i, j, color.getRGB());
			}
		}
		return image;
	}
	
	public static BufferedImage red(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				int r = (int) ((rgb & 0x00FF0000) >>> 16);
				int g = 0;
				int b = 0;
				Color color = new Color(r, g, b);
				image.setRGB(i, j, color.getRGB());
			}
		}
		return image;
	}

	public static BufferedImage green(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				int r = 0;
				int g = (int) ((rgb & 0x0000FF00) >>> 8);
				int b = 0;
				Color color = new Color(r, g, b);
				image.setRGB(i, j, color.getRGB());
			}
		}
		return image;
	}

	public static BufferedImage blue(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				int r = 0;
				int g = 0;
				int b = (int) (rgb & 0x000000FF);
				Color color = new Color(r, g, b);
				image.setRGB(i, j, color.getRGB());
			}
		}
		return image;
	}
	
	public static BufferedImage negativoRGB(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				int r = 255 - (int) ((rgb & 0x00FF0000) >>> 16);
				int g = 255 - (int) ((rgb & 0x0000FF00) >>> 8);
				int b = 255 - (int) (rgb & 0x000000FF);
				Color color = new Color(r, g, b);
				image.setRGB(i, j, color.getRGB());
			}
		}
		return image;
	}
	
	public static BufferedImage brilhoAdd(BufferedImage image, int c) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				int r = (int) ((rgb & 0x00FF0000) >>> 16) + c;
				int g = (int) ((rgb & 0x0000FF00) >>> 8) + c;
				int b = (int) (rgb & 0x000000FF) + c;
				if (r > 255) {
					r = 255;
				}
				if (r < 0) {
					r = 0;
				}
				if (g < 0) {
					g = 0;
				}
				if (g > 255) {
					g = 255;
				}
				if (b < 0) {
					b = 0;
				}
				if (b > 255) {
					b = 255;
				}
				Color color = new Color(r, g, b);
				image.setRGB(i, j, color.getRGB());
			}
		}
		return image;
	}
	
	public static BufferedImage brilhoMult(BufferedImage image, float c) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				int r = (int) (((rgb & 0x00FF0000) >>> 16) * c);
				int g = (int) (((rgb & 0x0000FF00) >>> 8) * c);
				int b = (int) ((rgb & 0x000000FF) * c);
				if (r > 255) {
					r = 255;
				}
				if (r < 0) {
					r = 0;
				}
				if (g < 0) {
					g = 0;
				}
				if (g > 255) {
					g = 255;
				}
				if (b < 0) {
					b = 0;
				}
				if (b > 255) {
					b = 255;
				}
				Color color = new Color(r, g, b);
				image.setRGB(i, j, color.getRGB());
			}
		}
		return image;
	}
}
