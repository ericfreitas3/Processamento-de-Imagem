package imagem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Imagem extends JFrame {

	private static final long serialVersionUID = 1L;
	private BufferedImage imgBuff, img2;
	private JFileChooser fc;
	private Image img;
	private String path;
	private static File f = new File(""), f2 = new File("");

	private JPanel pane;
	private JLabel dim;
	private VisualizaImg paneImg;
	private JButton btnAbrir, btnSalvar, btnOrigin, btnGray, btnR, btnG, btnB, btnN, convertRGB, bAdd, bMult, media, mediana, limiar, blend;
	private JTextField brilhoA, brilhoM, mask, mask2, threshold;

	public Imagem() {

		pane = new JPanel();

		btnAbrir = new JButton("Abrir");
		btnSalvar = new JButton("Salvar");
		btnOrigin = new JButton("Original");
		btnGray = new JButton("GrayScale");
		btnR = new JButton("Red");
		btnG = new JButton("Green");
		btnB = new JButton("Blue");
		btnN = new JButton("Negativo");
		convertRGB = new JButton("RGB");
		bAdd = new JButton("Add Brilho");
		bMult = new JButton("Mult Brilho");
		mask = new JTextField();
		media = new JButton("Media");
		mediana = new JButton("Mediana");
		limiar = new JButton("Limiar");
		blend = new JButton("Blend");
		brilhoA = new JTextField();
		brilhoM = new JTextField();
		mask = new JTextField();
		mask2 = new JTextField();
		threshold = new JTextField();
		dim = new JLabel("Dimensões da imagem: ");

		setBounds(400, 100, 800, 640);
		setLocationRelativeTo(null);
		setTitle("Processamento de Imagens");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pane.setSize(800, 540);
		pane.setLayout(null);

		dim.setBounds(50, 510, 400, 20);
		dim.setFont(new Font("Sans Serif", Font.ITALIC, 11));
		pane.add(dim);

		btnAbrir.setBounds(10, 10, 100, 25);
		pane.add(btnAbrir);
		btnSalvar.setBounds(120, 10, 100, 25);
		pane.add(btnSalvar);

		btnOrigin.setBounds(685, 10, 100, 25);
		pane.add(btnOrigin);
		btnGray.setBounds(685, 40, 100, 25);
		pane.add(btnGray);
		btnR.setBounds(685, 70, 100, 25);
		pane.add(btnR);
		btnG.setBounds(685, 100, 100, 25);
		pane.add(btnG);
		btnB.setBounds(685, 130, 100, 25);
		pane.add(btnB);
		btnN.setBounds(685, 160, 100, 25);
		pane.add(btnN);

		convertRGB.setBounds(685, 190, 100, 25);
		pane.add(convertRGB);

		bAdd.setBounds(685, 220, 100, 25);
		pane.add(bAdd);
		brilhoA.setBounds(660, 220, 25, 26);
		pane.add(brilhoA);

		bMult.setBounds(685, 250, 100, 25);
		pane.add(bMult);
		brilhoM.setBounds(660, 250, 25, 26);
		pane.add(brilhoM);

		mask.setBounds(660, 280, 25, 26);
		pane.add(mask);
		media.setBounds(685, 280, 100, 25);
		pane.add(media);

		mask2.setBounds(660, 310, 25, 26);
		pane.add(mask2);
		mediana.setBounds(685, 310, 100, 25);
		pane.add(mediana);

		limiar.setBounds(685, 340, 100, 25);
		pane.add(limiar);
		threshold.setBounds(660, 340, 25, 26);
		pane.add(threshold);

		blend.setBounds(685, 370, 100, 25);
		pane.add(blend);

		add(pane);
		
		btnSalvar.setEnabled(false);btnOrigin.setEnabled(false); btnGray.setEnabled(false); btnR.setEnabled(false); btnG.setEnabled(false);
		btnB.setEnabled(false);	btnN.setEnabled(false); convertRGB.setEnabled(false); bAdd.setEnabled(false); bMult.setEnabled(false); 
		media.setEnabled(false); mediana.setEnabled(false); limiar.setEnabled(false); blend.setEnabled(false);

		paneImg = new VisualizaImg();
		paneImg.setBounds(50, 100, 600, 400);
		pane.add(paneImg);

		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f = abrirArquivo();
				setImagens(caminhoArquivo(f));
				btnSalvar.setEnabled(true);btnOrigin.setEnabled(true); btnGray.setEnabled(true); btnR.setEnabled(true); btnG.setEnabled(true);
				btnB.setEnabled(true);	btnN.setEnabled(true); convertRGB.setEnabled(true); bAdd.setEnabled(true); bMult.setEnabled(true); 
				media.setEnabled(true); mediana.setEnabled(true); limiar.setEnabled(true); blend.setEnabled(true);
			}
		}
				);

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarArquivo(img2);
			}
		}
				);

		btnOrigin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toImage(bufferImagem(f));
			}
		}
				);

		btnGray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String[] choices = {"Mono R", "Mono G", "Mono B", "Média RGB", "Quit"};
				//monocromatico
				int op = JOptionPane.showOptionDialog(Imagem.getFrames()[0],
						"Opções Monocromático:",
						"Monocromático", 
						JOptionPane.YES_NO_OPTION, 
						JOptionPane.PLAIN_MESSAGE, 
						null, 
						choices, 
						"Quit");
				switch (op) {
				case 0:
					img2 = toGrayR(bufferImagem(f));
					break;
				case 1:
					img2 =toGrayG(bufferImagem(f));
					break;
				case 2:
					img2 = toGrayB(bufferImagem(f));
					break;
				case 3:
					img2 = toGray(bufferImagem(f));
					break;
				}
				paneImg.setImg(img2);
			}
		}
				);

		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//banda red
				img2 = red(bufferImagem(f));
				paneImg.setImg(img2);
			}
		}
				);

		btnG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//banda green
				img2 = green(bufferImagem(f));
				paneImg.setImg(img2);
			}
		}
				);

		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//banda blue
				img2 = blue(bufferImagem(f));
				paneImg.setImg(img2);
			}
		}
				);

		btnN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] choices = {"RGB", "Y", "R", "G", "B", "Quit"};
				//negativo
				int op = JOptionPane.showOptionDialog(Imagem.getFrames()[0],
						"Opções Negativo:",
						"Negativo", 
						JOptionPane.YES_NO_OPTION, 
						JOptionPane.PLAIN_MESSAGE, 
						null, 
						choices, 
						"Quit");
				switch (op) {
				case 0:
					img2 = negativoRGB(bufferImagem(f));
					break;
				case 1:
					img2 = negativoY(bufferImagem(f));
					break;
				case 2:
					img2 = negativoR(bufferImagem(f));
					break;
				case 3:
					img2 = negativoG(bufferImagem(f));
					break;
				case 4:
					img2 = negativoB(bufferImagem(f));
					break;
				}
				paneImg.setImg(img2);
			}
		}
				);

		convertRGB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//RGB
				img2 = YIQToRGB((bufferImagem(f)));
				paneImg.setImg(img2);
			}
		}
				);

		bAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//aditivo brilho
				if (!brilhoA.getText().isEmpty()) {
					String[] choices = {"RGB", "Y", "Quit"};
					//Brilho
					int op = JOptionPane.showOptionDialog(Imagem.getFrames()[0],
							"Opções brliho aditivo:",
							"Brilho Aditivo", 
							JOptionPane.YES_NO_OPTION, 
							JOptionPane.PLAIN_MESSAGE, 
							null, 
							choices, 
							"Quit");
					switch (op) {
					case 0:
						img2 = brilhoAdd(bufferImagem(f),  Integer.parseInt(brilhoA.getText()));
						break;
					case 1:
						img2 = brilhoAddY(bufferImagem(f),  Float.parseFloat(brilhoA.getText()));
						break;
					}
					paneImg.setImg(img2);
				} else {
					img2 = brilhoAdd(bufferImagem(f), 0);
					paneImg.setImg(img2);
				}
			}
		}
				);

		bMult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//multiplicativo brilho
				if (brilhoM.getText() != "") {
					String[] choices = {"RGB", "Y", "Quit"};
					//Brilho
					int op = JOptionPane.showOptionDialog(Imagem.getFrames()[0],
							"Opções brliho multiplicativo:",
							"Brilho Mutiplicativo", 
							JOptionPane.YES_NO_OPTION, 
							JOptionPane.PLAIN_MESSAGE, 
							null, 
							choices, 
							"Quit");
					switch (op) {
					case 0:
						img2 = brilhoMult(bufferImagem(f),  Integer.parseInt(brilhoM.getText()));
						break;
					case 1:
						img2 = brilhoMultY(bufferImagem(f),  Float.parseFloat(brilhoM.getText()));
						break;
					}
					paneImg.setImg(img2);
				} else {
					img2 = brilhoMult(bufferImagem(f), 0);
					paneImg.setImg(img2);
				}
			}
		}
				);
		media.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(mask.getText()) % 2 != 0) {
					img2 = media2(bufferImagem(f), Integer.parseInt(mask.getText()));
					paneImg.setImg(img2);
				} else {
					JOptionPane.showMessageDialog(null, "Digite um tamanho válido(ímpar) da mascara");
				}
			}
		}
				);

		mediana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(mask2.getText()) % 2 != 0){
					img2 = mediana2(bufferImagem(f),Integer.parseInt(mask2.getText()));
					paneImg.setImg(img2);
				}else {
					JOptionPane.showMessageDialog(null, "Digite um tamanho válido(ímpar) da mascara");
				}
			}
		}
				);

		limiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!threshold.getText().isEmpty()) {
					img2 = limiar(bufferImagem(f), Integer.parseInt(threshold.getText()));
					paneImg.setImg(img2);
				} else {
					img2 = limiarMedia(bufferImagem(f));
					paneImg.setImg(img2);
				}
			}
		}
				);

		blend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				img2 = blend(bufferImagem(f));
				paneImg.setImg(img2);
			}
		}
				);

	}

	public  File abrirArquivo() {
		File fLocal = new File("");
		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int i = fc.showOpenDialog(pane);
		if (i == 0) {
			fLocal = fc.getSelectedFile();
			BufferedImage im = bufferImagem(fLocal);
			int w = im.getWidth();
			int h = im.getHeight();
			dim.setText(" Dimensões da imagem: " + w + " x " + h);
		} else {
			System.out.println("Operação cancelada");
		}
		return fLocal;
	}

	public void salvarArquivo(BufferedImage im) {
		fc.setFileHidingEnabled(false);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int i = fc.showSaveDialog(null);

		if (i == JFileChooser.APPROVE_OPTION) {
			String caminho = String.valueOf(fc.getSelectedFile().getAbsolutePath());
			try {
				File novoF = new File(caminho + ".jpg");
				ImageIO.write(im, "JPG", novoF);
				JOptionPane.showMessageDialog(null, "Arquivo salvo");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Operação cancelada");
		}

	}

	public String caminhoArquivo(File f) {
		return f.getAbsolutePath();
	}

	public BufferedImage bufferImagem(File f) {
		try {
			imgBuff = ImageIO.read(f);
		} catch (IOException ex) {
			Logger.getLogger(Imagem.class.getName()).log(Level.SEVERE, null, ex);
		}
		return imgBuff;
	}

	public void toImage(BufferedImage imgBuff) {
		paneImg.setImg(Toolkit.getDefaultToolkit().createImage(imgBuff.getSource()));
	}

	public void setImagens(String path) {
		paneImg.setImg(path);
		this.path = path;
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
				Color color = new Color(gs,gs,gs);
				image.setRGB(i, j, color.getRGB());
			}
		}
		return image;
	}
	
	public static BufferedImage toGrayR(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				int r = (int) ((rgb & 0x00FF0000) >>> 16);
//				int g = (int) ((rgb & 0x0000FF00) >>> 8);
//				int b = (int) (rgb & 0x000000FF);
//				int gs = (r + g + b) / 3;
				Color color = new Color(r,r,r);
				image.setRGB(i, j, color.getRGB());
			}
		}
		return image;
	}
	
	public static BufferedImage toGrayG(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
//				int r = (int) ((rgb & 0x00FF0000) >>> 16);
				int g = (int) ((rgb & 0x0000FF00) >>> 8);
//				int b = (int) (rgb & 0x000000FF);
//				int gs = (r + g + b) / 3;
				Color color = new Color(g,g,g);
				image.setRGB(i, j, color.getRGB());
			}
		}
		return image;
	}
	
	public static BufferedImage toGrayB(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
//				int r = (int) ((rgb & 0x00FF0000) >>> 16);
//				int g = (int) ((rgb & 0x0000FF00) >>> 8);
				int b = (int) (rgb & 0x000000FF);
//				int gs = (r + g + b) / 3;
				Color color = new Color(b,b,b);
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

	public static BufferedImage negativoY(BufferedImage image) {
		YIQ[][] m = YIQ(image);
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				
				float ny = (float) (1 - m[i][j].y);

				int r = (int) ((ny + 0.956 * m[i][j].i + 0.621 * m[i][j].q) * 255);
				int g = (int) ((ny - 0.272 * m[i][j].i - 0.647 * m[i][j].q) * 255);
				int b = (int) ((ny - 1.106 * m[i][j].i + 1.703 * m[i][j].q) * 255);

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

	public static BufferedImage negativoR(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				int r = 255 - (int) ((rgb & 0x00FF0000) >>> 16);
				int g = 0;//(int) ((rgb & 0x0000FF00) >>> 8);
				int b = 0;//(int) (rgb & 0x000000FF);
				Color color = new Color(r, g, b);
				image.setRGB(i, j, color.getRGB());
			}
		}
		return image;
	}

	public static BufferedImage negativoG(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				int r = 0;//(int) ((rgb & 0x00FF0000) >>> 16);
				int g = 255 - (int) ((rgb & 0x0000FF00) >>> 8);
				int b = 0;//(int) (rgb & 0x000000FF);
				Color color = new Color(r, g, b);
				image.setRGB(i, j, color.getRGB());
			}
		}
		return image;
	}

	public static BufferedImage negativoB(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				int r = 0;//(int) ((rgb & 0x00FF0000) >>> 16);
				int g = 0;//(int) ((rgb & 0x0000FF00) >>> 8);
				int b = 255 - (int) (rgb & 0x000000FF);
				Color color = new Color(r, g, b);
				image.setRGB(i, j, color.getRGB());
			}
		}
		return image;
	}

	public static YIQ[][] YIQ(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		YIQ result[][] = new YIQ[width][height];
		Color rgb;
		for (int i1 = 0; i1 < width; i1++) {
			for (int j = 0; j < height; j++) {
				rgb = new Color(image.getRGB(i1, j));
				float y = 0.299f * rgb.getRed() + 0.587f * rgb.getGreen() + 0.114f * rgb.getBlue();
				float i = 0.596f * rgb.getRed() - 0.275f * rgb.getGreen() - 0.321f * rgb.getBlue();
				float q = 0.212f * rgb.getRed() - 0.523f * rgb.getGreen() + 0.311f * rgb.getBlue();

				y = y / 255;
				i = i / 255;
				q = q / 255;

				if (y > 1) {
					y = 1;
				}
				if (y < 0) {
					y = 0;
				}
				if (i < 0) {
					i = 0;
				}
				if (i > 1) {
					i = 1;
				}
				if (q < 0) {
					q = 0;
				}
				if (q > 1) {
					q = 1;
				}
				result[i1][j] = new YIQ(y, i, q);
				//System.out.println("YIQ: " + result[i1][j].y + " " + result[i1][j].i +" "+ result[i1][j].q);
			}
		}
		return result;
	}

	public static BufferedImage YIQToRGB(BufferedImage image) {
		YIQ[][] imYiq = YIQ(image);
		int width = image.getWidth();
		int height = image.getHeight();

		for (int i1 = 0; i1 < width; i1++) {
			for (int j = 0; j < height; j++) {
				int r = (int) ((imYiq[i1][j].y + 0.956 * imYiq[i1][j].i + 0.621 * imYiq[i1][j].q) * 255);
				int g = (int) ((imYiq[i1][j].y - 0.272 * imYiq[i1][j].i - 0.647 * imYiq[i1][j].q) * 255);
				int b = (int) ((imYiq[i1][j].y - 1.106 * imYiq[i1][j].i + 1.703 * imYiq[i1][j].q) * 255);

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

				//                    System.out.println("YIQ: " + imYiq[i1][j].y + " " + imYiq[i1][j].i +" "+ imYiq[i1][j].q);
				//                    System.out.println("RGB: "+ r + " " + g +" "+ b);
				Color color = new Color(r, g, b);
				image.setRGB(i1, j, color.getRGB());

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
	
	public static BufferedImage brilhoAddY(BufferedImage image, float c) {
		YIQ[][] m = YIQ(image);
		
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				
				float by = (float) (m[i][j].y+c);
				if(by > 1) 
					by = 1;
				if(by < 0)
					by = 0;
				
				int r = (int) ((by + 0.956 * m[i][j].i + 0.621 * m[i][j].q) * 255);
				int g = (int) ((by - 0.272 * m[i][j].i - 0.647 * m[i][j].q) * 255);
				int b = (int) ((by - 1.106 * m[i][j].i + 1.703 * m[i][j].q) * 255);

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
	
	public static BufferedImage brilhoMultY(BufferedImage image, float c) {
		YIQ[][] m = YIQ(image);
		
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				
				float by = (float) (m[i][j].y*c);
				if(by > 1) 
					by = 1;
				if(by < 0)
					by = 0;

				
				int r = (int) ((by + 0.956 * m[i][j].i + 0.621 * m[i][j].q) * 255);
				int g = (int) ((by - 0.272 * m[i][j].i - 0.647 * m[i][j].q) * 255);
				int b = (int) ((by - 1.106 * m[i][j].i + 1.703 * m[i][j].q) * 255);

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
	
	public static Color[][] getMatrizConv(BufferedImage image, int pi, int pj, int n){
		int t  = n/2;
		int x1 = pi - t;
		int x2 = pi + t;
		int y1 = pj - t;
		int y2 = pj + t;
		
		Color [][] matriz = new Color[n][n];
		for(int i = x1, a = 0; i <= x2; i++){
			for(int j = y1, b = 0; j <= y2; j++){
				if((i < 0 || j < 0) || (i > image.getWidth() -1 || j > image.getHeight() -1))
					matriz[a][b] = new Color(0,0,0);
				else
					matriz[a][b] = new Color(image.getRGB(i, j));
				b=b+1;
			}
			a=a+1;
		}
		return matriz;
	}

	public static BufferedImage media2(BufferedImage image, int n) {
		int r=0, g=0, b=0;
		int width = image.getWidth();
		int height = image.getHeight();
		Color[][] ma;
		Color rgb = null;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				ma = getMatrizConv(image, i, j , n);
				for(int x = 0; x < n; x++){
					for(int y = 0; y < n; y++){
						r += ma[x][y].getRed();
						g += ma[x][y].getGreen();
						b += ma[x][y].getBlue();
					}					
				}
				r = r/(n*n);
				g = g/(n*n);
				b = b/(n*n);
				rgb = new Color(r, g, b);
				image.setRGB(i, j, rgb.getRGB());
				r=0;g=0;b=0;
			}
		}
		return image;
	}
	
	public static BufferedImage mediana2(BufferedImage image, int n) {
		int [] r = new int[n*n], g = new int[n*n], b = new int[n*n];
		int red,green,blue, p=0, index = (n*n-1)/2;
		int width = image.getWidth();
		int height = image.getHeight();
		Color[][] ma;
		Color rgb = null;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				ma = getMatrizConv(image, i, j , n);
				for(int x = 0; x < n; x++){
					for(int y = 0; y < n; y++){
						r[p] = ma[x][y].getRed();
						g[p] = ma[x][y].getGreen();
						b[p] = ma[x][y].getBlue();
						p++;
					}				
				}
				Arrays.sort(r);
				Arrays.sort(g);
				Arrays.sort(b);
				
				red = r[index];
				green = g[index];
				blue = b[index];
				
				rgb = new Color(red, green, blue);
				image.setRGB(i, j, rgb.getRGB());
				p=0;
			}
		}
		return image;
	}

	public static BufferedImage limiarMedia(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		Color rgb;
		int l,r=0,g=0,b=0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				rgb = new Color(image.getRGB(i, j));
				r += rgb.getRed() ;
				g += rgb.getGreen();
				b += rgb.getBlue();
			}
		}
		l = (r + g + b) / (width*height*3);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				rgb = new Color(image.getRGB(i, j));
				if ((rgb.getRed() + rgb.getGreen() + rgb.getBlue()) / 3 > l) {
					image.setRGB(i, j, 0xffffff);
				} else {
					image.setRGB(i, j, 0x000000);
				}
			}
		}
		return image;
	}
	
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

	public BufferedImage blend(BufferedImage img) {
		f2 = abrirArquivo();
		BufferedImage imgBlend = bufferImagem(f2);
		if (img.getWidth() != imgBlend.getWidth() || img.getHeight() != imgBlend.getHeight()) {
			JOptionPane.showMessageDialog(Imagem.getFrames()[0], "Selecione imagens de mesmas dimensões. Abra novamente a imagem", "ERROR", JOptionPane.ERROR_MESSAGE, null);
			return null;
		}
		Color rgb, rgb2, rgbBlend;
		int width = img.getWidth();
		int height = img.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				rgb = new Color(img.getRGB(i, j));
				rgb2 = new Color(imgBlend.getRGB(i, j));

				int r = (rgb.getRed() + rgb2.getRed()) / 2;
				int g = (rgb.getGreen() + rgb2.getGreen()) / 2;
				int b = (rgb.getBlue() + rgb2.getBlue()) / 2;

				rgbBlend = new Color(r, g, b);
				img.setRGB(i, j, rgbBlend.getRGB());
			}
		}
		return img;
	}

	public class VisualizaImg extends JPanel {

		private static final long serialVersionUID = 1L;
		private Image img;

		public VisualizaImg() {
		}

		public VisualizaImg(Image img) {
			setImg(img);
		}

		public VisualizaImg(String url) {
			setImg(url);
		}

		protected void paintComponent(final Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, paneImg.getSize().width, paneImg.getSize().height, this);
		}

		public void setImg(Image img) {
			this.img = img;
			this.repaint();
		}

		public void setImg(String url) {
			setImg(Toolkit.getDefaultToolkit().createImage(url));
		}
	}

	public static void main(String[] args) {
		new Imagem();
	}
}