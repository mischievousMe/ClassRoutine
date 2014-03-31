package class_routine.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import class_routine.ClassRoutineApplication;
import class_routine.model.Admin;
import class_routine.model.Fonts;
import class_routine.model.Palette;

public class AdminMenu {
	public Admin curAdmin;
	public boolean hasLogged;
	public AdminMenu(Admin newAdmin) {
		curAdmin = newAdmin;
		hasLogged = true;
	}
	
	JFrame frame;
	JButton pendingRequests;
	JButton viewRoutine;
	JButton logout, modifyHalls, modifyCourses, modifyLecturers, thisSemCourses, modifyRoutine;
	JPanel menuPanel,topPanel;
	GridBagConstraints c;
	JLabel welcomeLabel;
	
	public void showAdminMenu() {
		String user = curAdmin.getUserID();
		if(user.equals("superusr")) {
			showSuperAdminMenu();
		}
		else {
			showDepartmentalAdminMenu();
		}
	}
	public void showDepartmentalAdminMenu() {
		
		frame = new JFrame("Admin Menu");
		menuPanel = new JPanel(new GridBagLayout());
		topPanel = new JPanel();
		c = new GridBagConstraints();
		menuPanel.setBackground(Palette.pastelBlue);
		welcomeLabel = new JLabel("Welcome "+curAdmin.getUserID()+"!");
		modifyHalls = new JButton("Modify Halls Data");
		modifyHalls.setForeground(Palette.dark_lavender);
		modifyHalls.setFont(Fonts.avenir);
		modifyCourses = new JButton("Modify Courses Data");
		modifyCourses.setForeground(Palette.dark_lavender);
		modifyCourses.setFont(Fonts.avenir);
		modifyLecturers = new JButton("Modify Lecturers Data");
		modifyLecturers.setForeground(Palette.dark_lavender);
		modifyLecturers.setFont(Fonts.avenir);
		thisSemCourses = new JButton("Courses List in this Semester");
		thisSemCourses.setForeground(Palette.dark_lavender);
		thisSemCourses.setFont(Fonts.avenir);
		modifyRoutine = new JButton("Modify Routine");
		modifyRoutine.setForeground(Palette.dark_lavender);
		modifyRoutine.setFont(Fonts.avenir);
		viewRoutine = new JButton("View Routine");
		viewRoutine.setForeground(Palette.dark_lavender);
		viewRoutine.setFont(Fonts.avenir);
		
		c.insets = new Insets(10,10,10,10);
		c.gridx = 0;
		c.gridy = 0;
		menuPanel.add(modifyHalls,c);
		c.gridx = 0;
		c.gridy = 1;
		menuPanel.add(modifyLecturers,c);
		c.gridx = 0;
		c.gridy = 2;
		menuPanel.add(modifyCourses,c);
		c.gridx = 0;
		c.gridy = 3;
		menuPanel.add(thisSemCourses,c);
		c.gridx = 0;
		c.gridy = 4;
		menuPanel.add(modifyRoutine,c);
		c.gridx = 0;
		c.gridy = 5;
		menuPanel.add(viewRoutine,c);
		
		frame.setVisible(true);
		frame.setSize(1200,800);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frame.getContentPane().add(menuPanel,BorderLayout.CENTER);
	}
	public void showSuperAdminMenu() {
		
		frame = new JFrame("SuperAdmin Menu");
		menuPanel = new JPanel(new GridBagLayout());
		topPanel = new JPanel();
		c = new GridBagConstraints();
		welcomeLabel = new JLabel("Welcome "+curAdmin.getUserID()+"!");
		pendingRequests = new JButton("Pending Admin Requests");
		pendingRequests.setFont(Fonts.avenir);
		pendingRequests.setForeground(Color.MAGENTA);
		viewRoutine = new JButton("View Routine");
		viewRoutine.setFont(Fonts.avenir);
		viewRoutine.setForeground(Color.MAGENTA);
		logout = new JButton("Log Out");
		logout.setFont(Fonts.avenir);
		logout.setForeground(Color.MAGENTA);
		
		frame.setVisible(true);
		frame.setSize(1200,800);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		menuPanel.setBackground(Palette.lavender);
		
		frame.getContentPane().add(menuPanel,BorderLayout.CENTER);
		frame.getContentPane().add(topPanel,BorderLayout.NORTH);
		//menuPanel.add(pendingRequests);
		
		c.insets = new Insets(10,10,10,10);
		c.gridx = 0;
		c.gridy = 0;
		menuPanel.add(pendingRequests,c);
		c.gridx = 0;
		c.gridy = 1;
		menuPanel.add(viewRoutine,c);
		c.gridx = 0;
		c.gridy = 2;
		menuPanel.add(logout,c);
		
		welcomeLabel.setForeground(Palette.pastelBlue);
		welcomeLabel.setFont(Fonts.avenir);
		topPanel.add(welcomeLabel);
		topPanel.setBackground(Palette.violet_red);
		
		pendingRequests.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PendingRequestsMenu prMenu = new PendingRequestsMenu(curAdmin);
				frame.dispose();
				try {
					prMenu.pending_requests_ui();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		viewRoutine.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				
			}
		});
		
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ClassRoutineApplication.createFirstScreen();
			}
			
		});
		
	}
}
