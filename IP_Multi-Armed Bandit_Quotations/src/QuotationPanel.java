
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class QuotationPanel extends JPanel {
	private MainFrame mainframe;
	private JLabel quotations, information;
	private JButton like, dislike, finish;
	private Author author;
	private ArrayList<Author> authorlist;
	LoginPanel logPanel;

	public QuotationPanel(LoginPanel lp) throws IOException {

		User object = User.getObject();
		logPanel = lp;
		mainframe = new MainFrame();
		author = new Author();
		authorlist = author.getList();

		setBackground(Color.yellow);

		JPanel informationpanel = new JPanel();
		informationpanel.setPreferredSize(new Dimension(800, 100));
		informationpanel.setBackground(Color.yellow);
		information = new JLabel("<html>Please choose if you like<br> or dislike the following quotation: <html>");
		information.setFont(new Font("Arial black", Font.BOLD, 25));
		informationpanel.add(information);
		add(informationpanel);

		JPanel qPanel = new JPanel();
		qPanel.setBackground(Color.yellow);
		qPanel.setPreferredSize(new Dimension(800, 300));
		Border border = BorderFactory.createLineBorder(Color.orange, 15);
		quotations = new JLabel("");
		quotations.setBorder(border);
		quotations.setText(author.getRandom().toString());
		quotations.setFont(new Font("Arial black", Font.BOLD, 20));
		qPanel.add(quotations);
		add(qPanel);

		JPanel buttonpanel = new JPanel();
		buttonpanel.setBackground(Color.yellow);
		like = new JButton("Like");
		like.setPreferredSize(new Dimension(200, 50));
		buttonpanel.add(like);
		like.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				object.incrementLikeAll();
				object.incrementQuotationsshonwAll();

				for (int i = 0; i < authorlist.size(); i++) {

					if (authorlist.get(i).getAuthorname().equals("E")
							&& authorlist.get(i).getQuoations().equals(quotations.getText())) {
						object.incrementLikeE();

					} else if (authorlist.get(i).getAuthorname().equals("M")
							&& authorlist.get(i).getQuoations().equals(quotations.getText())) {
						object.incrementLikeM();
					}
				}

				try {
					quotations.setText(author.getRandom().toString());
				} catch (NumberFormatException e) {

					e.printStackTrace();
				} catch (FileNotFoundException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}

			}

		});

		dislike = new JButton("Dislike");
		dislike.setPreferredSize(new Dimension(200, 50));
		buttonpanel.add(dislike);
		dislike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				object.incrementDislikeAll();
				object.incrementQuotationsshonwAll();
				// userlist.get(0).incrementLikeAll();
				for (int i = 0; i < authorlist.size(); i++) {

					if (authorlist.get(i).getAuthorname().equals("E")
							&& authorlist.get(i).getQuoations().equals(quotations.getText())) {
						object.incrementDislikeE();

					} else if (authorlist.get(i).getAuthorname().equals("M")
							&& authorlist.get(i).getQuoations().equals(quotations.getText())) {
						object.incrementDislikeM();
					}
				}

				try {
					quotations.setText(author.getRandom().toString());
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}

			}

		});

		finish = new JButton("Close the game and save it");
		finish.setPreferredSize(new Dimension(200, 50));
		buttonpanel.add(finish);
		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				object.calculateScoreE();
				object.calculateScoreM();
				object.calculateScoreAll();
				object.updateFile(logPanel.getUsername());
				mainframe.changeToMenu();
				

			}

		});

		add(buttonpanel);
	}

}
