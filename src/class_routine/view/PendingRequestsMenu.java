package class_routine.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import class_routine.ClassRoutineApplication;
import class_routine.controller.AdminController;
import class_routine.model.Admin;
import class_routine.model.Fonts;
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
	JTable requestTable;
	DefaultTableModel model;
	Object [] data;
	JComboBox departmentList;
	JComboBox approvalBox;
	JCheckBox checkBox;
	String userStr;
	JTextField uField;
	JFrame addFrame;
	
	public PendingRequestsMenu(Admin newAdmin) {
		curAdmin = newAdmin;
		hasLogged = true;
	}
	
	public void pending_requests_ui() throws ClassNotFoundException, SQLException {
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
		
		ArrayList<String>  users = AdminController.fetchAdminRequests();
		String[] columnNames = { "User_ID", "Department","Approval","Save"};
		model = new DefaultTableModel(columnNames,users.size()); 
		JTable table = new JTable(model) {@Override
        public boolean isCellEditable(int arg0, int arg1) {
         
			if(arg1 == 0) {
				return false;
			}
			return true;
        }};
      
        JButton saveButton = new JButton("SAVE");
        saveButton.setFont(Fonts.avenir);
        saveButton.setBackground(Palette.baby_blue);
        saveButton.setForeground(Palette.dark_lavender);
        
        checkBox = new JCheckBox();
        checkBox.setForeground(Palette.aquamarine);
        
        
        String departments[] = {"CSE","ME","BT","ECE","EE","MME"};
        departmentList = new JComboBox(departments);
        String approvals[] = {"Pending","Approved","Rejected"};
        approvalBox = new JComboBox(approvals);
        TableColumn departmentColumn = table.getColumnModel().getColumn(1);
		TableColumn approvalColumn = table.getColumnModel().getColumn(2);
		departmentColumn.setCellEditor(new DefaultCellEditor(departmentList));
		approvalColumn.setCellEditor(new DefaultCellEditor(approvalBox));
		TableColumn userColumn = table.getColumnModel().getColumn(0);
		TableColumn saveColumn = table.getColumnModel().getColumn(3);
		saveColumn.setCellEditor(new DefaultCellEditor(checkBox));
		checkBox.setBorderPainted(true);
		int sz = users.size(),i = 0;
		while(i < sz) {
			  table.setValueAt(users.get(i),i,0);
			  //System.out.println("o - "+users.get(i));
			  i = i+1;
			  
		}
		JScrollPane tableScrollPane = new JScrollPane(table);
		frame.add(tableScrollPane,BorderLayout.CENTER);
		
		
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
        table.setSelectionBackground(Palette.dark_lavender);
        
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        JButton addButton = new JButton("ADD");
        addButton.setFont(Fonts.avenir);
        addButton.setBackground(Palette.baby_blue);
        addButton.setForeground(Palette.dark_lavender);
        
        c.insets = new Insets(10,10,10,10);
		c.gridx = 0;
		c.gridy = 0;
		bottomPanel.add(addButton,c);
		c.gridx = 0;
		c.gridy = 1;
		bottomPanel.add(saveButton,c);
		
		frame.add(bottomPanel,BorderLayout.SOUTH);
		
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ClassRoutineApplication.createFirstScreen();
			}
			
		});
		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				userStr = "";
				
				addFrame = new JFrame();
				JLabel uLabel = new JLabel("New User ID: ");
				addFrame.add(uLabel,BorderLayout.NORTH);
				uField = new JTextField(15);
				addFrame.add(uField,BorderLayout.CENTER);
				JButton button = new JButton("DONE");
				addFrame.add(button,BorderLayout.SOUTH);
				addFrame.setVisible(true);
				addFrame.setSize(200,200);
				addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						userStr = uField.getText();
						if(userStr.length() < 6) {
							uField.setBackground(Color.red);
							uField.setForeground(Color.white);
						}
						else {
							addFrame.dispose();
							Vector row = new Vector();
							row.add(userStr);
							model.addRow(row);
						}
					}
				});
				
			}
		});

		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					//add here
			}
		});
	}
}
