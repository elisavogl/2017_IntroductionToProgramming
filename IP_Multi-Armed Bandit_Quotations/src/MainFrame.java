import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {
	private static JPanel contentpane;
	private static CardLayout cardlayout;
	private static LoginPanel loginpanel;
	private static MenuPanel menupanel;
	private static QuotationPanel quotationpanel;
	private static AuthorEmerson authoremerson;
	private static AuthorMaugham authormaugham;
	ArrayList<Author> authorlist;
	Author author;
	int numberQ;
	int i, j;

	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame("Movie collection");
		cardlayout = new CardLayout();
		contentpane = new JPanel(cardlayout);
		contentpane.setPreferredSize(new Dimension(800, 650));
		loginpanel = new LoginPanel();
		quotationpanel = new QuotationPanel(loginpanel);
		menupanel = new MenuPanel(loginpanel);
		authoremerson = new AuthorEmerson();
		authormaugham = new AuthorMaugham();

		contentpane.add(loginpanel, "loginpanel");
		contentpane.add(quotationpanel, "quotationpanel");
		contentpane.add(menupanel, "menupanel");
		contentpane.add(authoremerson, "authoremerson");
		contentpane.add(authormaugham, "authormaugham");

		frame.add(contentpane);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createMenu(frame);

		frame.pack();
		frame.setVisible(true);

	}

	public void changeToLogin() {
		cardlayout.show(contentpane, "loginpanel");
	}

	public void changeToRegister() {
		cardlayout.show(contentpane, "registerpanel");
	}

	public void changeToQuotation() {
		cardlayout.show(contentpane, "quotationpanel");
	}

	public void changeToMenu() {
		cardlayout.show(contentpane, "menupanel");
	}

	public void changeToMaugham() {
		cardlayout.show(contentpane, "authormaugham");
	}

	public void changeToEmerson() {
		cardlayout.show(contentpane, "authoremerson");
	}

	private static void createMenu(JFrame frame) {
		JMenu menu = new JMenu("Menu");
		MenuActionListener menuAcListener = new MenuActionListener();

		JMenuItem exitMenu = new JMenuItem("Exit");
		JMenuItem aboutMenu = new JMenuItem("About");

		exitMenu.addActionListener(menuAcListener);
		menu.add(exitMenu);

		aboutMenu.addActionListener(menuAcListener);
		menu.add(aboutMenu);

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
	}

	private static class MenuActionListener implements ActionListener {

		public void actionPerformed(ActionEvent qst) {
			JMenuItem source = (JMenuItem) qst.getSource();
			String text = source.getText();
			if (text.equals("Exit")) {

				System.exit(0);
			}

			if (text.equals("About")) {
				String info = "<html>This application written by Elisa Vogl<br> gives the "
						+ "opportunity to try a Multi Armed Bandit Game <br> which shows"
						+ "quotations of two different authors.<br> During the game"
						+ "you are not able to see the score of the author<br> because with this"
						+ "strategy you like the quotations <br> and you are not able to find out"
						+ "which author is your prefered until the end of the game.<br> "
						+ "When you would like to finish the "
						+ "whole game <br>you have to click only on <br>Which is the author I liked most<br>"
						+ "and you will see the result. <br> "
						+ "Number of classes in total: 8<br>"
						+ "Number of methods: 17 (without get and set methods for the User and Author) "
						+ "Number of total lines: 1275";

				JPanel creditsPanel = new JPanel();

				JTextArea textArea = new JTextArea(info);
				textArea.setName("About");

				creditsPanel.add(textArea);

				JOptionPane.showMessageDialog(null, creditsPanel, "Info", 1);

			}
		}
	}
}
