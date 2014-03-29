package class_routine.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.*;

import class_routine.ClassRoutineApplication;
import class_routine.controller.AdminController;
import class_routine.model.Admin;
import class_routine.model.Fonts;
import class_routine.model.Palette;

public class AdminSignUp {
	
	JLabel userLabel, passLabel;
	JLabel confirmPassLabel;
	JTextField userID;
	JPasswordField confirmPass;
	JPasswordField password;
	JButton enterButton;
	JTextField jt;
	JButton firstScreen;
	JPanel signUpPanel;
	GridBagConstraints c;
	public static JFrame frame;
	
	public Admin curAdmin = null;
	public AdminSignUp(Admin newAdmin) {
		curAdmin = newAdmin;
	}
	public void sign_up_gui() {
		
		userLabel = new JLabel("User ID: ");
		passLabel = new JLabel("Password: ");
		confirmPassLabel = new JLabel("Confirm Password: ");
		userID = new JTextField("myuserid",15);
		confirmPass = new JPasswordField(15);
		password = new JPasswordField(15);
		enterButton = new JButton("Enter");
		jt = new JTextField(50);
		firstScreen = new JButton("Back");
		signUpPanel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		frame = new JFrame("Sign Up Here for Admin Account!");
		
		frame.setVisible(true);
		frame.setSize(1200,800);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		userID.setFont(Fonts.markerFelt);
		passLabel.setFont(Fonts.comicSansMS);
		passLabel.setForeground(Color.white);
		password.setFont(Fonts.markerFelt);
		userLabel.setFont(Fonts.comicSansMS);
		userLabel.setForeground(Color.white);
		confirmPassLabel.setFont(Fonts.comicSansMS);
		confirmPass.setFont(Fonts.markerFelt);
		confirmPassLabel.setForeground(Color.white);
		enterButton.setFont(Fonts.avenir);
		signUpPanel.setBackground(Palette.dodger_blue);
		firstScreen.setBackground(Palette.lavender);
		firstScreen.setFont(Fonts.avenir);
		
		frame.getContentPane().add(signUpPanel,BorderLayout.CENTER);
		
		
		c.insets = new Insets(10,10,10,10);
		c.gridx = 0;
		c.gridy = 0;
		signUpPanel.add(userLabel,c);
		c.gridx = 0;
		c.gridy = 1;
		signUpPanel.add(userID,c);
		c.gridx = 0;
		c.gridy = 2;
		signUpPanel.add(passLabel,c);
		c.gridx = 0;
		c.gridy = 3;
		signUpPanel.add(password,c);
		c.gridx = 0;
		c.gridy = 4;
		signUpPanel.add(confirmPassLabel,c);
		c.gridx = 0;
		c.gridy = 5;
		signUpPanel.add(confirmPass,c);
		c.gridx = 0;
		c.gridy = 6;
		signUpPanel.add(enterButton,c);
		c.gridx = 0;
		c.gridy = 7;
		signUpPanel.add(jt,c);
		c.gridx = 0;
		c.gridy = 10;
		signUpPanel.add(firstScreen,c);
		enterButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String input1 = userID.getText();
				char[] input2 = password.getPassword();
				char[] input3 = confirmPass.getPassword();
				curAdmin.setUserID(input1);
				String input22 = new String(input2);
				String input33 = new String(input3);
				if(input22.length() >= 6 && input22.equals(input33) && input1.length() >= 6) {
					curAdmin.setPassWord(input22);
					jt.setBackground(Palette.aquamarine);
					jt.setFont(Fonts.markerFelt);
					jt.setText("Welcome "+curAdmin.getUserID()+", your admin account request has been queued! "+curAdmin.getPassword());
					try {
						AdminController.insert(curAdmin);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
					//	e1.printStackTrace();
					}
				}
				else {
					jt.setBackground(Color.red);
					jt.setFont(Fonts.markerFelt);
					curAdmin.setUserID("");
					jt.setText("Error while signing up! Sign Up Again");
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
