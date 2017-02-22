package imagem;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ImagemClient extends JFrame{

	private static final long serialVersionUID = 1L;


	public static JPanel pane;
	public static JLabel dim;
	public static VisualizaImg paneImg;
	public JButton btnAbrir, btnSalvar, btnOrigin, btnGray, btnR, btnG, btnB, btnN, convertRGB, bAdd, bMult, media, mediana, limiar, blend;
	public static JTextField brilhoA;


	public JTextField brilhoM;


	public JTextField mask;


	public JTextField mask2;


	public JTextField threshold;
	public static Image image;
	public static File f;
	public static String btn = "", btnA = "";


	public ImagemClient() {

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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

		paneImg = new VisualizaImg();
		paneImg.setBounds(50, 100, 600, 400);
		pane.add(paneImg);

		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				f = ImagemProtocol.abrirArquivo(pane);
				image = ImagemProtocol.bufferImagem(f);
				dim.setText("Dimensões da Imagem: "+image.getHeight(null) +" x "+ image.getHeight(null));
				paneImg.setImg(image);
				//				byte[] imageArray = null;
				//				imageArray = ImagemProtocol.imageToByte(img);
				//				img = ImagemProtocol.byteToImage(imageArray);


			}
		}
				);

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		}
				);

		btnOrigin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn = "original";
			}
		}
				);

		btnGray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn = "gray";
			}
		}
				);

		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn = "red";
			}
		}
				);

		btnG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn = "green";
			}
		}
				);

		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn = "blue";
			}
		}
				);

		btnN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn = "negativo";
			}
		}
				);

		bAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn = "brilhoadd";
			}
		}
				);

		bMult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn = "brilhomult";
			}
		}
				);
		media.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn = "media";
			}
		}
				);

		mediana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn = "mediana";
			}
		}
				);

		limiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn = "limiar";
			}
		}
				);

		blend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn = "blend";
			}
		}
				);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				//if (JOptionPane.showConfirmDialog(null,"Deseja sair")==JOptionPane.OK_OPTION){
				btn = "exit";

				//System.exit(0);
				//}
			}
		}
				);


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

	public static void main(String[] args) throws IOException {

		ImagemClient a = new ImagemClient();
		byte[] imageArray = null;

		Socket s = new Socket("localhost", 4444);
		DataInputStream in = new DataInputStream(s.getInputStream());
		DataOutputStream out = new DataOutputStream(s.getOutputStream());

		while (true) {
			if(ImagemClient.image == null){
				a.btnAbrir.doClick();				
			}
			imageArray = ImagemProtocol.imageToByte(ImagemClient.image);
			out.writeInt(imageArray.length); 	// write length of the message
			out.write(imageArray);           	// write the message
			
			while(true){
				
				if(ImagemClient.btn.equalsIgnoreCase("")){System.out.println("");
					if(ImagemClient.btn.equalsIgnoreCase("original")){
						out.writeUTF("original");
						break;
					}
					if(ImagemClient.btn.equalsIgnoreCase("gray")){
						out.writeUTF("gray");
						break;
					}
					if(ImagemClient.btn.equalsIgnoreCase("red")){
						out.writeUTF("red");
						break;
					}
					if(ImagemClient.btn.equalsIgnoreCase("green")){
						out.writeUTF("green");
						break;
					}
					if(ImagemClient.btn.equalsIgnoreCase("blue")){
						out.writeUTF("blue");
						break;
					}
					if(ImagemClient.btn.equalsIgnoreCase("negativo")){
						out.writeUTF("negativo");
						break;
					}
					if(ImagemClient.btn.equalsIgnoreCase("brilhoadd")){
						out.writeUTF("brilhoadd");
						break;
					}
					if(ImagemClient.btn.equalsIgnoreCase("brilhomult")){
						out.writeUTF("brilhomult");
						break;
					}
					if(ImagemClient.btn.equalsIgnoreCase("media")){
						out.writeUTF("media");
						break;
					}
					if(ImagemClient.btn.equalsIgnoreCase("mediana")){
						out.writeUTF("mediana");
						break;
					}
					if(ImagemClient.btn.equalsIgnoreCase("limiar")){
						out.writeUTF("limiar");
						break;
					}
					if(ImagemClient.btn.equalsIgnoreCase("blend")){
						out.writeUTF("blend");
						break;
					}
					if(ImagemClient.btn.equalsIgnoreCase("exit")){
						break;
					}
				}
			}
			
			if (ImagemClient.btn.equalsIgnoreCase("exit")){
				out.writeUTF("exit");
				break;
			}else{			
				int length = in.readInt();                    	// read length of incoming message
				byte[] message = new byte[length];
				in.readFully(message, 0, message.length);	 	// read the message
				Image img = ImagemProtocol.byteToImage(message);
				ImagemClient.paneImg.setImg(img);
			}
			ImagemClient.btn = ""; 
		}
		in.close();
		out.close();
		s.close();
		System.exit(0);


	}
}
