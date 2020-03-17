import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class AuthorMaugham extends JPanel {
	private JButton close;
	private JLabel name, information, imageemerson;
	static ArrayList<Author> maughamlist;
	static Author authorreader;
	private String[] columnNames;
	private JTable table;
	private DefaultTableModel model;
	private TableRowSorter<TableModel> sorter;
	MainFrame mainframe;

	public AuthorMaugham() throws IOException {

		authorreader = new Author();
		maughamlist = authorreader.getMaughamList();
		setBackground(Color.orange);
		mainframe = new MainFrame();
		JPanel informationPanel = new JPanel(new BorderLayout());
		informationPanel.setPreferredSize(new Dimension(800, 200));
		informationPanel.setBackground(Color.orange);
		imageemerson = new JLabel("");
		imageemerson.setIcon(new ImageIcon("Resources" + System.getProperty("file.separator") + "maugham.jpg"));
		informationPanel.add(imageemerson, BorderLayout.WEST);

		information = new JLabel("<html>You have successfully chosen your preferred Author!<br>"
				+ "From this point on you are not able to play anymore as you are know<br>"
				+ "informed which quotations from which Author you have liked mostly <br>without "
				+ "knowing the name of the author before <br>and without knowing which quotations "
				+ "are from which author.<br> Below you can find the name of the Author<br>and additionally"
				+ "all the quotations from the author. <br>We will see us next time!<html>");
		informationPanel.add(information);

		add(informationPanel, BorderLayout.EAST);

		JPanel namePanel = new JPanel();
		namePanel.setBackground(Color.yellow);
		namePanel.setPreferredSize(new Dimension(800, 100));
		name = new JLabel("Your most rated author is: Mr. W.Somerset Maugham");
		name.setFont(new Font("Arial black", Font.BOLD, 25));
		name.setForeground(Color.red);
		namePanel.add(name);
		add(namePanel);

		JPanel tablePanel = new JPanel(new BorderLayout());
		tablePanel.setPreferredSize(new Dimension(800, 300));
		columnNames = new String[] { "Quotations" };
		table = new JTable(new DefaultTableModel(columnNames, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;

			}
		});
		table.setBackground(Color.darkGray);
		table.setForeground(Color.white);
		model = (DefaultTableModel) table.getModel();
		Object rowData[] = new Object[1];
		for (int i = 0; i < maughamlist.size(); i++) {
			rowData[0] = maughamlist.get(i).getQuoations();
			model.addRow(rowData);
		}
		table.setRowHeight(50);

		sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(500, 300));
		tablePanel.add(scrollPane);
		add(tablePanel);

		close = new JButton("GOODBYE!Logout from the program");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "You have successfully logged out."
						+ "Please note that this is the end of the game. See you SOON!" );
				mainframe.changeToLogin();

			}

		});
		add(close);
	}

}
