package pack1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.corba.se.spi.ior.MakeImmutable;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;

public class FrmMain extends JFrame {

	public static Student[] allStudents;
	boolean inputError = false;
	
	public static int numWeeks, numStu;
	
	public static Color 	errorColor = 	new Color(250, 50, 100), 
							okColor = new Color(50, 200, 100);
	
	private JPanel contentPane;
	JFileChooser fileChooser, fileSetter;
	JLabel lblMessage;
	JSpinner spCycle;
	
	boolean gen = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMain frame = new FrmMain();
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
	public FrmMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 500, 320, 260);
		
		setTitle("WASP");
		setUI("Windows");

		fileChooser = new JFileChooser();
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSaveGenerated = new JMenuItem("Save Generated");
		mntmSaveGenerated.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gen) {
					JFileChooser fileChooser = new JFileChooser();
					String path = "";
					
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					
					fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
					
					fileChooser.setFileFilter(new FileNameExtensionFilter("Comma Seperated Values (*.csv)", "csv"));
					
					Date date = new Date(); 
					SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd_HHmm");
					String currentDate = formatter.format(date);
					
					fileChooser.setSelectedFile(new File("Schedule_" + currentDate + ".csv"));
					
					int result = fileChooser.showSaveDialog(getParent());

					if (result == JFileChooser.APPROVE_OPTION) {
						path = fileChooser.getSelectedFile().getAbsolutePath();		
						int endindex = 0;
						
						for(int i = 0; i < path.length(); i++) {
							if(path.charAt(i) == '.') {
								endindex = i;
								break;
							}
						}
						
						if(!path.substring(endindex, path.length()).equals(".csv")){
							JOptionPane.showMessageDialog(null, "Unknown Filetype. File will be saved as '.csv'", "Unknown File-Extension", JOptionPane.INFORMATION_MESSAGE);
							
							if(endindex == 0) {
								path += ".csv";
							}else {
								path = path.substring(0, endindex) + ".csv";
							}
						}
			
						//System.out.println(path);
					}
					
					ArrayOperations.writeData(path);
				}else {
					JOptionPane.showMessageDialog(null, "Please generate a Schedule first.", "Nothing to Export", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnFile.add(mntmSaveGenerated);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.err.println("Exit Application: User Exit");
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame f = new FrmAbout();
				f.setVisible(true);
			}
		});
		mnHelp.add(mntmAbout);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnLoadStudentList = new JButton("Load Student List");
		btnLoadStudentList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allStudents = null;
				
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setFileFilter(new FileNameExtensionFilter("Comma Seperated Values (*.csv)", "csv"));

				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(getParent());

				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					
					BufferedReader input;
					try {
						input = new BufferedReader(new FileReader(selectedFile));

						String line;
						int index = 0;
						boolean error = false;
						
						while ((line = input.readLine()) != null) {
							char split = ArrayOperations.getSplittingChar(line);
							if(split == 'e') {
								error = true;
								break;
							}
							
							try {
								allStudents = ArrayOperations.addToArray(allStudents, new Student(ArrayOperations.csvToArray(line, split)[0], ArrayOperations.csvToArray(line, split)[1], index));
							}catch (Exception e1) {
								error = true;
							}
							
							index++;
						}
						input.close();
						
						if(!error) {
							spCycle.setModel(new SpinnerNumberModel(4, 1, index, 1));
							numWeeks = (int) spCycle.getValue();
							numStu = getNumStu();
						}else {
							spCycle.setModel(new SpinnerNumberModel(0, 0, index, 1));
						}
						
						lblMessage.setForeground(error ? errorColor : okColor);
						lblMessage.setText(error ? "Loading Failed!" : "Loaded " + index +" Students Succesfully!");
					} 
					catch (FileNotFoundException e1) {
						lblMessage.setForeground(errorColor);
						lblMessage.setText("File can not be Found!");
						e1.printStackTrace();
					} catch (IOException e2) {
						lblMessage.setForeground(errorColor);
						lblMessage.setText("File can not be loaded");
						e2.printStackTrace();
					}
				}
			}

		});
		
		JLabel lblCycle = new JLabel("Number of Weeks per Cycle");
		
		spCycle = new JSpinner();
		spCycle.setModel(new SpinnerNumberModel(0, 0, null, 0));
		spCycle.setBackground(Color.RED);
		spCycle.setForeground(Color.RED);
		spCycle.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				numWeeks = (int) spCycle.getValue();
				
				numStu = getNumStu();
			}
		});
		
		JButton btnGeneratePlan = new JButton("Generate Schedule");
		btnGeneratePlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(allStudents != null) {	
					ArrayOperations.setXFalse();
					
					gen = true;
					
					Frame f = new FrmSchedule();
					f.setVisible(true);
				}else {
					lblMessage.setForeground(errorColor);
					lblMessage.setText("Please Load the Students first!");
				}
			}
		});
		
		lblMessage = new JLabel("");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGeneratePlan, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblCycle, GroupLayout.PREFERRED_SIZE, 124, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(spCycle, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
						.addComponent(btnLoadStudentList, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblMessage)
							.addPreferredGap(ComponentPlacement.RELATED, 214, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLoadStudentList)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCycle)
						.addComponent(spCycle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
					.addComponent(lblMessage)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnGeneratePlan)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * Calculates the Number of Students per Week.
	 * @return the Number.
	 */
	private int getNumStu() {
		double numStu = (double)allStudents.length / (double)numWeeks;
		numStu = Math.ceil(numStu);
		
		return (int) numStu;
	}
	
	/**
	 * Get an installed UI Layout.
	 * @param s the Layout Name.
	 */
	static void setUI(String s) {
		for(UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()) {
			//System.out.println(info.getName());
			if(s.equals(info.getName()) ){
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Gets the Current System Look And Feel.
	 */
	static void getLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
