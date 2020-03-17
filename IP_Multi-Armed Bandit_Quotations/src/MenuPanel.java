import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {

	private JButton showquotation, authormostliked;
	private JLabel welcomepicture, note, pictureemerson, picturemaugham, description;
	private JLabel overallscore;
	private MainFrame mainframe;
	private JLabel label;
	double scoreE, scoreM, scoreA;
	int likeE, likeM, likeA, dislikeE, dislikeM, dislikeA, quotationsshownAll;
	LoginPanel loginpanel;

	public MenuPanel(LoginPanel login) throws IOException {
		loginpanel = login;
		User object = User.getObject();
		mainframe = new MainFrame();

		setBackground(Color.orange);
		JPanel welcomePanel = new JPanel();
		welcomePanel.setPreferredSize(new Dimension(800, 100));
		welcomePanel.setBackground(Color.white);
		welcomepicture = new JLabel("");
		welcomepicture
				.setIcon(new ImageIcon("Resources" + System.getProperty("file.separator") + "welcomepicture.png"));
		welcomePanel.add(welcomepicture);
		add(welcomePanel);

		JPanel authorPanel = new JPanel(new BorderLayout());
		authorPanel.setPreferredSize(new Dimension(800, 200));
		authorPanel.setBackground(Color.white);
		pictureemerson = new JLabel("");
		pictureemerson.setIcon(new ImageIcon("Resources" + System.getProperty("file.separator") + "maughammenu.jpg"));
		authorPanel.add(pictureemerson, BorderLayout.WEST);

		description = new JLabel(
				"<html>In this game we are showing you <br> different quotations from two different authors.<br>"
						+ "Maybe your are already knowing both of them <br>and you are interested which one is your preferred author.<br>"
						+ "You can find it out by clicking on like and dislike<br> and after you have done many of the quotations<br>"
						+ "you can have your result by clicking on <br>which is the author I liked most.<br> HAVE FUN<html>");
		description.setFont(new Font("Calibri", Font.ITALIC, 16));
		description.setForeground(Color.DARK_GRAY);
		authorPanel.add(description, BorderLayout.CENTER);
		picturemaugham = new JLabel("");
		authorPanel.add(picturemaugham, BorderLayout.EAST);
		picturemaugham.setIcon(new ImageIcon("Resources" + System.getProperty("file.separator") + "emersonmenu.jpg"));
		add(authorPanel);

		JPanel QuotationPanel = new JPanel();
		QuotationPanel.setPreferredSize(new Dimension(800, 150));
		QuotationPanel.setBackground(Color.yellow);

		label = new JLabel("");
		label.setFont(new Font("Arial black", Font.BOLD, 18));
		QuotationPanel.add(label);
		showquotation = new JButton("Show the quotation and choose if you like or dislike them");
		showquotation.setBackground(Color.orange);
		showquotation.setOpaque(true);
		showquotation.setPreferredSize(new Dimension(400, 100));
		QuotationPanel.add(showquotation);
		showquotation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainframe.changeToQuotation();
			}

		});
		add(QuotationPanel);

		JPanel endPanel = new JPanel(new BorderLayout());
		endPanel.setPreferredSize(new Dimension(800, 100));
		endPanel.setBackground(Color.orange);
		note = new JLabel(
				"<html>Please note that you are not able to play anymore<br> if you click on this button:<html>");
		note.setForeground(Color.red);
		note.setFont(new Font("Arial black", Font.BOLD, 16));
		endPanel.add(note, BorderLayout.NORTH);

		authormostliked = new JButton("Which is the author I liked most?");
		authormostliked.setOpaque(true);
		authormostliked.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (object.getLikeE() < 4 && object.getLikeM() < 4) {
					JOptionPane.showMessageDialog(null,
							"Please note that your entered results are still not satisfactory"
									+ "for showing your prefered author.");
				} else if (object.getScoreE() > object.getScoreM()) {
					object.deleteUser(loginpanel.getUsername());
					mainframe.changeToEmerson();

				} else {
					object.deleteUser(loginpanel.getUsername());
					mainframe.changeToMaugham();

				}

			}
		});

		authormostliked.setBackground(Color.yellow);
		authormostliked.setPreferredSize(new Dimension(400, 100));
		endPanel.add(authormostliked, BorderLayout.CENTER);
		add(endPanel);

		JPanel scorePanel = new JPanel();
		scorePanel.setPreferredSize(new Dimension(800, 150));
		scorePanel.setBackground(Color.orange);
		JButton scoreButton = new JButton("Overallscore");

		scoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DecimalFormat f = new DecimalFormat("#0.00"); 
				
				
				String info = "<html>Overallscore:<br>" + "Quoatations shown until now: "
						+ object.getQuotationsshownAll() + "<br>" + "Likes you have given: " + object.getLikeAll()
						+ "<br>Overall sucess score: " + f.format(object.getScoreAll()) + "<html>";
				overallscore = new JLabel(info);
				JOptionPane.showMessageDialog(null, info);
				overallscore.setFont(new Font("Arial black", Font.BOLD, 16));
			}

		});

		scoreButton.setBackground(Color.yellow);
		scoreButton.setOpaque(true);
		scoreButton.setPreferredSize(new Dimension(150, 50));
		scorePanel.add(scoreButton);
		add(scorePanel);

	}
}