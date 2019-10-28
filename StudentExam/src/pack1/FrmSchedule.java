package pack1;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmSchedule extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	
	int rowHeight = 30;
	public static String[][] data;
	public static String[] header;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSchedule frame = new FrmSchedule();

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
	public FrmSchedule() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, FrmMain.allStudents.length * (rowHeight+4));
		
		//FrmMain.getLookAndFeel();
		setTitle("Schedule");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		FrmMain.setUI("Windows");
				
		//Data		
		data =			ArrayOperations.StudentArrayToData(FrmMain.allStudents, FrmMain.numWeeks+1, FrmMain.numStu);
		header = 		ArrayOperations.makeHeader("Surname", FrmMain.numWeeks); 
				
		//Table
		table = new JTable(data, header);
		
		//Row Height and Cell Spacing
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		for(int i = 0; i < data.length; i++) {
			table.setRowHeight(i, rowHeight);
		}
		
		for (int i = 0; i < data[0].length; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
		
		// Header Color
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(new Color(80, 220, 220));
		
		for (int i = 0; i < table.getModel().getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		}
	     
		//ScrollPane
		JScrollPane scroll = new JScrollPane(table);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		//GroupLayout
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(3)
					.addComponent(scroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(scroll))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
