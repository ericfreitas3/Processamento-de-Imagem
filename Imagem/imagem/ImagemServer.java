/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;


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
/**
 *
 * @author aluno
 */
public class ImagemServer implements Runnable{

	Socket s;

	ImagemServer(Socket ns){
		s = ns;
	}

	public void run(){
		try {
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());

			BufferedImage img;
			String r="";

			while (true) {
				int length = in.readInt();                    	// read length of incoming message
				if(length > 0) {
					byte[] message = new byte[length];

					in.readFully(message, 0, message.length); 	// read the message

					System.out.println("Bytes originais: " + message.length);

					r = in.readUTF();						// read operation and parameter
					
					if(r.equalsIgnoreCase("exit")) break;
					img = ImagemProtocol.byteToImage(message);
					System.out.print("Operação: " + r + " Bytes processados: ");

					switch(r){
					case "originial":
						//img = ImagemProtocol.toImage(img, 150);
						break;
					case "gray":
						img = ImagemProtocol.toGray(img);
						break;
					case "red":
						img = ImagemProtocol.red(img);
						break;
					case "green":
						img = ImagemProtocol.green(img);
						break;
					case "blue":
						img = ImagemProtocol.blue(img);
						break;
					case "negativo":
						img = ImagemProtocol.negativoRGB(img);
						break;
					case "brilhoadd":
						img = ImagemProtocol.brilhoAdd(img, 150);
						break;
					case "brilhomult":
						img = ImagemProtocol.brilhoMult(img, (float)0.5);
						break;
					}


					message = ImagemProtocol.imageToByte(img);
					System.out.println(message.length);

					out.writeInt(message.length); // write length of the message
					out.write(message);           // write the message

				}

			}
			in.close();
			out.close();
		} catch (IOException ex) {
			Logger.getLogger(ImagemServer.class.getName()).log(Level.SEVERE, null, ex);
		}

		try {
			s.close();
		} catch (IOException ex) {
			Logger.getLogger(ImagemServer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}


	public static void main(String[] args) throws IOException {
		Executor exec = Executors.newCachedThreadPool();
		ServerSocket s = new ServerSocket(4444);
		while (true) {
			Socket ns = s.accept();
			exec.execute(new ImagemServer(ns));
		}
	}
}
