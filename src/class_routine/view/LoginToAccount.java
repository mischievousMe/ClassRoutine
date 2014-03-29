package class_routine.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import class_routine.ClassRoutineApplication;
import class_routine.controller.AdminController;
import class_routine.model.Admin;
import class_routine.model.Fonts;
import class_routine.model.Palette;

public class LoginToAccount {
	JLabel userLabel, passLabel;
	JTextField userID;
	JPasswordField password;
	JButton enterButton;
	JTextField jt;
	JButton firstScreen;
	JPanel loginPanel,menuPanel;
	GridBagConstraints c,c1;
	public static JFrame frame;
	public boolean hasLogged;
	JButton myMenu;
	
	public Admin curAdmin = null;
	public LoginToAccount(Admin newAdmin, boolean loggedIn)  {
		curAdmin = newAdmin;
		hasLogged = loggedIn;
	}
	
	public void login_ui() {
		userLabel = new JLabel("User ID: ");
		passLabel = new JLabel("Password: ");
		userID = new JTextField("myuserid",15);
		password = new JPasswordField(15);
		enterButton = new JButton("LOGIN");
		jt = new JTextField(50);
		firstScreen = new JButton("Back");
		loginPanel = new JPanel(new GridBagLayout());
		menuPanel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		c1 = new GridBagConstraints();
		myMenu = new JButton("See Menu");
		
		frame = new JFrame("Admin Login");
		frame.setVisible(true);
		frame.setSize(1200,800);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		userID.setFont(Fonts.markerFelt);
		passLabel.setFont(Fonts.comicSansMS);
		passLabel.setForeground(Color.white);
		password.setFont(Fonts.markerFelt);
		userLabel.setFont(Fonts.comicSansMS);
		userLabel.setForeground(Color.white);
		enterButton.setFont(Fonts.avenir);
		loginPanel.setBackground(Palette.aquamarine);
		firstScreen.setBackground(Palette.baby_blue);
		firstScreen.setFont(Fonts.avenir);
		
		frame.getContentPane().add(loginPanel,BorderLayout.CENTER);
		
		myMenu.setFont(Fonts.avenir);
		//myMenu.setBackground(Color.WHITE);
		//myMenu.setForeground(Color.MAGENTA);
		
		c.insets = new Insets(10,10,10,10);
		c.gridx = 0;
		c.gridy = 0;
		loginPanel.add(userLabel,c);
		c.gridx = 0;
		c.gridy = 1;
		loginPanel.add(userID,c);
		c.gridx = 0;
		c.gridy = 2;
		loginPanel.add(passLabel,c);
		c.gridx = 0;
		c.gridy = 3;
		loginPanel.add(password,c);
		c.gridx = 0;
		c.gridy = 6;
		loginPanel.add(enterButton,c);
		c.gridx = 0;
		c.gridy = 7;
		loginPanel.add(jt,c);
		c.gridx = 0;
		c.gridy = 10;
		loginPanel.add(firstScreen,c);
		
		enterButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String input1 = userID.getText();
				char[] input2 = password.getPassword();
				curAdmin.setUserID(input1);
				String input22 = new String(input2);
				if(input22.length() >= 6 && input1.length() >= 6) {
					curAdmin.setPassWord(input22);
					jt.setBackground(Color.green);
					jt.setFont(Fonts.markerFelt);
					//jt.setText(curAdmin.getUserID());
					Admin preAdmin = new Admin("", "");
					try {
						preAdmin = AdminController.findByUserId(curAdmin.getUserID());
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//jt.setText("Done!");
					if( preAdmin.getUserID().equals(curAdmin.getUserID()) && preAdmin.getPassword().equals(curAdmin.getPassword()) ) {
						hasLogged = true;
						AdminMenu newMenu = new AdminMenu(curAdmin);
						frame.dispose();
						newMenu.showAdminMenu();
					}
					else {
						jt.setBackground(Color.red);
						jt.setText("Wrong credentials. Login again!");
					}
				}
				else {
					jt.setBackground(Color.red);
					jt.setFont(Fonts.markerFelt);
					curAdmin.setUserID("");
					jt.setText("Error!! Login Again");
				}
			}
			
		});
		
		firstScreen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ClassRoutineApplication.createFirstScreen();
			}
			
		});

	}

}
