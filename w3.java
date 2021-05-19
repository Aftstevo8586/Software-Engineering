package taskadd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class w3 {
	
	JFrame frmEnterTaskName;
	private JTextField txt_type;
	
	private JSlider slider_importance;
	private JSpinner spn_frequency;
	private JSpinner spn_duration;

	/**
	 * Create the application.
	 */
	public w3() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	private void initialize() {
		

		frmEnterTaskName = new JFrame();
		frmEnterTaskName.setTitle("Edit task");
		frmEnterTaskName.setBounds(100, 100, 404, 353);
		frmEnterTaskName.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		txt_type = new JTextField();
		txt_type.setBounds(178, 11, 194, 20);
		txt_type.setColumns(10);
		
		
		JLabel lbl_type = new JLabel("Task type");
		lbl_type.setBounds(10, 11, 121, 31);
		
		
		JLabel lbl_duration = new JLabel("Duration");
		lbl_duration.setBounds(10, 75, 98, 23);
		
		JLabel lbl_frequency = new JLabel("Frequency");
		lbl_frequency.setBounds(10, 151, 88, 14);
		
		spn_duration = new JSpinner();
		spn_duration.setBounds(178, 75, 63, 20);
		spn_duration.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		
		JLabel lbl_minutes = new JLabel("Minutes");
		lbl_minutes.setBounds(183, 83, 37, 14);
		
		spn_frequency = new JSpinner();
		spn_frequency.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spn_frequency.setBounds(178, 143, 63, 20);
		
		JLabel lbl_importance = new JLabel("Importance");
		lbl_importance.setBounds(10, 207, 88, 14);
		
		slider_importance = new JSlider();
		slider_importance.setBounds(178, 202, 200, 50);
		slider_importance.setPaintLabels(true);
		slider_importance.setToolTipText("a");
		slider_importance.setMinorTickSpacing(1);
		slider_importance.setMajorTickSpacing(1);;
		slider_importance.setMinimum(1);
		slider_importance.setPaintTicks(true);
		slider_importance.setValue(1);
		slider_importance.setMaximum(10);
		
		
		
		JButton btnNewButton = new JButton("Save changes");
		btnNewButton.setBounds(59, 267, 121, 23);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String type = txt_type.getText();
				int duration = (int) spn_duration.getValue();
				int frequency = (int)spn_frequency.getValue();
				int importance = (int)slider_importance.getValue();
				
				Object[]row = {type, frequency, importance, duration};
				
				w1.UpdateTask(row);
				frmEnterTaskName.dispose();
			}
		});
		
		
		
		GetDefData();
		
		frmEnterTaskName.getContentPane().setLayout(null);
		frmEnterTaskName.getContentPane().add(btnNewButton);
		frmEnterTaskName.getContentPane().add(lbl_duration);
		frmEnterTaskName.getContentPane().add(lbl_type);
		frmEnterTaskName.getContentPane().add(lbl_frequency);
		frmEnterTaskName.getContentPane().add(lbl_importance);
		frmEnterTaskName.getContentPane().add(slider_importance);
		frmEnterTaskName.getContentPane().add(txt_type);
		frmEnterTaskName.getContentPane().add(spn_frequency);
		frmEnterTaskName.getContentPane().add(spn_duration);
		frmEnterTaskName.getContentPane().add(lbl_minutes);
		
		}
	
	public void GetDefData() {
		DefaultTableModel model = (DefaultTableModel)w1.table.getModel();
		int row = w1.table.getSelectedRow(); 
		

		txt_type.setText((String) model.getValueAt(row,0));
		spn_frequency.setValue(model.getValueAt(row,1));
		slider_importance.setValue((int) model.getValueAt(row,2));
		spn_duration.setValue(model.getValueAt(row,3));
		
		
	}
}
