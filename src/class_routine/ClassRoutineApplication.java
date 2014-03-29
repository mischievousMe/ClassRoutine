
package class_routine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import class_routine.*;
import class_routine.model.Admin;
import class_routine.model.Fonts;
import class_routine.model.Palette;
import class_routine.view.AdminSignUp;
import class_routine.view.LoginToAccount;

public class ClassRoutineApplication {
	
	//set the path of the pic accordingly
	public static String pic_path = "/Users/shalini/Desktop/java/ClassRoutine/src/class_routine/class.jpg";
	
	public static JFrame appFrame;
	
	public static void createFirstScreen() {
		
		appFrame = new JFrame("Class Routine Automated Generation System");
		appFrame.setVisible(true);
		appFrame.setSize(1200, 800);
		appFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JButton signUp = new JButton("SIGN UP");
		signUp.addActionListener(new SignUp());
		signUp.setFont(Fonts.comicSansMS);
		signUp.setBorderPainted(true);
		JButton login = new JButton("LOGIN");
		login.addActionListener(new Login());
		login.setFont(Fonts.comicSansMS);
		login.setBorderPainted(true);
		JButton viewRoutine = new JButton("VIEW ROUTINE");
		viewRoutine.setFont(Fonts.comicSansMS);
		viewRoutine.addActionListener(new ViewRoutine());
		viewRoutine.setBorderPainted(true);
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Palette.pastelBlue);
		
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File(pic_path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		appFrame.getContentPane().add(picLabel,BorderLayout.CENTER);
		
		JLabel titleLabel = new JLabel("Class Routine Automated Generation System");
		titleLabel.setFont(Fonts.markerFelt);
		titleLabel.setForeground(Palette.turquoise);
		
		JLabel appTag = new JLabel("Plan efficiently with us");
		appTag.setForeground(Palette.turquoise2);
		appTag.setFont(Fonts.cursive);
		
		JPanel titlePanel = new JPanel(new GridBagLayout());
		titlePanel.setBackground(Color.white);
		
		appFrame.getContentPane().add(titlePanel,BorderLayout.NORTH);
		appFrame.getContentPane().add(panel,BorderLayout.SOUTH);
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(signUp,c);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(login,c);
		c.gridx = 0;
		c.gridy = 2;
		panel.add(viewRoutine,c);
		
		GridBagConstraints ct = new GridBagConstraints();
		ct.insets = new Insets(10,10,10,10);
		ct.gridx = 0;
		ct.gridy = 0;
		titlePanel.add(titleLabel,ct);
		ct.gridx = 0;
		ct.gridy = 1;
		titlePanel.add(appTag,ct);

		
		Container w = appFrame.getContentPane();
		w.setBackground(Color.white);
		
	}
	
	public static void main(String[] args) {
		
		createFirstScreen();
		
	}
	

	static class SignUp implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			AdminSignUp signUpView = new AdminSignUp(new Admin("",""));
			appFrame.dispose();
			signUpView.sign_up_gui();
		}
		
	}

	static class Login implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			LoginToAccount loginView = new LoginToAccount(new Admin("",""),false);
			appFrame.dispose();
			loginView.login_ui();
		}
		
	}

	static class ViewRoutine implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		
		}		
	}
}
			
		
