package taskadd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class w1 {

	private JFrame frmAddeditTasks;

	static JTable table;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					w1 window = new w1();
					window.frmAddeditTasks.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	

	
	public w1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddeditTasks = new JFrame();
		frmAddeditTasks.setTitle("Add/Edit tasks");
		frmAddeditTasks.setBounds(100, 100, 573, 302);
		frmAddeditTasks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNewButton = new JButton("Add task");
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				w2 window_add = new w2();
				window_add.frmEnterTaskName.setVisible(true);
			}
		});
		
		JButton btnNewButton_1 = new JButton("Delete task");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
		public void mouseClicked(MouseEvent e) {
				
				if(table.getSelectedRow() != -1) {
					
					
					DefaultTableModel model = (DefaultTableModel)table.getModel();
			        model.removeRow(table.getSelectedRow());
			       
				}
				
		
			}
		});
	
		JButton btnNewButton_2 = new JButton("Edit task");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				w3 window_add = new w3();
				
				window_add.frmEnterTaskName.setVisible(true);
			}
		});
		
	
		GroupLayout groupLayout = new GroupLayout(frmAddeditTasks.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(btnNewButton_2))
					.addGap(70)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(107)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_2)
					.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
					.addComponent(btnNewButton_1)
					.addGap(42))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		table = new JTable(4,4);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Type", "Frequency", "Importance", "Duration"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		frmAddeditTasks.getContentPane().setLayout(groupLayout);
	
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			 public void valueChanged(ListSelectionEvent event) {
			  btnNewButton_1.setEnabled(true);
			  btnNewButton_2.setEnabled(true);
			  
			 }
			});
	
	}
	
	
	
	public static void UpdateTask(Object[] s) {
		int row = w1.table.getSelectedRow(); 
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		
		for(int i = 0;i<s.length;i++) {
			model.setValueAt(s[i], row, i);
		}
	}
	
	public static void AddTask(Object[] s) {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.addRow(s);
		
	}
}
