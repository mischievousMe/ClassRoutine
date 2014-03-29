package class_routine.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import class_routine.ClassRoutineApplication;
import class_routine.model.Admin;
import class_routine.model.Palette;

public class PendingRequestsMenu {
	Admin curAdmin;
	boolean hasLogged;
	
	JPanel topPanel;
	JPanel adminRequestsPanel;
	JFrame frame;
	JButton logout;
	GridBagConstraints c;
	JLabel welcomeLabel;
	
	public PendingRequestsMenu(Admin newAdmin) {
		curAdmin = newAdmin;
		hasLogged = true;
	}
	
	public void pending_requests_ui() {
		frame = new JFrame("Pending Requests Menu");
		topPanel = new JPanel();
		adminRequestsPanel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		logout = new JButton("Log Out");
		welcomeLabel = new JLabel("Welcome "+curAdmin.getUserID()+"! ");
		welcomeLabel.setForeground(Color.MAGENTA);
		welcomeLabel.setBackground(Color.white);
		topPanel.setBackground(Palette.pastelBlue);
		adminRequestsPanel.setBackground(Palette.alice_blue);
		
		frame.setVisible(true);
		frame.setSize(1200,800);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frame.getContentPane().add(adminRequestsPanel,BorderLayout.CENTER);
		frame.getContentPane().add(topPanel,BorderLayout.NORTH);
		
		topPanel.add(welcomeLabel, BorderLayout.WEST);
		topPanel.add(logout, BorderLayout.EAST);
		
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ClassRoutineApplication.createFirstScreen();
			}
			
		});

	}
}
