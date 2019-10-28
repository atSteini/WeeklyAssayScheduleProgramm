package pack1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmAbout extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAbout frame = new FrmAbout();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmAbout() {
		setTitle("About");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 500, 467, 213);
		
		FrmMain.setUI("Windows");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTextArea txtrWeeklyAssaySchedule = new JTextArea();
		txtrWeeklyAssaySchedule.setWrapStyleWord(true);
		txtrWeeklyAssaySchedule.setEditable(false);
		txtrWeeklyAssaySchedule.setLineWrap(true);
		txtrWeeklyAssaySchedule.setText("Weekly Assay Schedule Programm, or short WASP, is a Programm written by Florian Steinkellner, which helps you manage a lot of Assays. It creates appointments for Students, you can set the Amount of Students that should be testet per Week and the Programm creates a Schedule for those Students ");
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Exit Frame About: User Exit");
				setVisible(false);
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnClose))
				.addComponent(txtrWeeklyAssaySchedule, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(txtrWeeklyAssaySchedule, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnClose))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
