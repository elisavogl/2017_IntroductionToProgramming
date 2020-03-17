import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {

	public JButton loginButton, registrationButton, instructions, registerButton;
	public JLabel welcome, picture;
	public JLabel usernameLabel;
	JTextField usernameText;
	private MainFrame mainframe;


	public LoginPanel() throws IOException {

		User object = User.getObject();
		setBackground(Color.orange);
		mainframe = new MainFrame();

		JPanel picturePanel = new JPanel();
		picturePanel.setPreferredSize(new Dimension(800, 170));
		picture = new JLabel("");
		picture.setIcon(new ImageIcon("Resources" + System.getProperty("file.separator") + "welcomelogin.png"));
		picturePanel.add(picture);
		add(picturePanel);

		JPanel welcomePanel = new JPanel();
		welcomePanel.setBackground(Color.yellow);
		welcomePanel.setPreferredSize(new Dimension(800, 100));
		welcome = new JLabel(
				"<html>Enter in the world of quotations.<br>Find out what author you prefer without knowing it before.<html>");
		welcome.setFont(new Font("Arial black", Font.BOLD, 18));
		welcome.setForeground(Color.red);
		welcomePanel.add(welcome);
		add(welcomePanel);

		JPanel usernamePanel = new JPanel();
		usernamePanel.setPreferredSize(new Dimension(800, 100));
		usernamePanel.setBackground(Color.orange);
		usernameLabel = new JLabel("Username: ");
		usernamePanel.add(usernameLabel);

		usernameText = new JTextField();
		usernameText.setPreferredSize(new Dimension(150, 50));
		usernamePanel.add(usernameText);

		loginButton = new JButton("Enter in the world of quotations");
		loginButton.setOpaque(true);
		loginButton.setBackground(Color.yellow);
		loginButton.setPreferredSize(new Dimension(300, 50));
		usernamePanel.add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nickname = usernameText.getText();
				if (nickname.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a username to login");
				} else {
					try {
						if (object.searchUser(nickname) == true) {
							DecimalFormat f = new DecimalFormat("#0.00"); 
							String salutation = new String("<html>Hello " + nickname + ",<br> you have"
									+ "successfully logged in to your already existing account.");
							salutation += "<br>Quotationsshown until now: " + object.getQuotationsshownAll();
							salutation += "<br>Your overallsucess score: " + f.format(object.getScoreAll()) + "<html>";

							JOptionPane.showMessageDialog(null, salutation);
							mainframe.changeToMenu();

						} else {
							JOptionPane.showMessageDialog(null,
									"<html>Sorry, we were not able to find your login details.<br>"
											+ "Please try again. <html>");

						}
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (HeadlessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});
		registerButton = new JButton("Register a new account");
		registerButton.setOpaque(true);
		registerButton.setBackground(Color.yellow);
		registerButton.setPreferredSize(new Dimension(250, 50));
		usernamePanel.add(registerButton);
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nickname = usernameText.getText();
				if (usernameText.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter an username to register");
				} else {
					try {
						if (object.searchUser(nickname) == true) {
							JOptionPane.showMessageDialog(null, "Sorry, the username already exists");
						} else {
							object.createUser(nickname);
							JOptionPane.showMessageDialog(null, "We have successfully registered your account.");

						}
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});

		add(usernamePanel);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.orange);

		add(buttonPanel);

		instructions = new JButton("Instructions");
		instructions.setOpaque(true);
		instructions.setBackground(Color.yellow);
		instructions.setPreferredSize(new Dimension(200, 50));
		add(instructions);
		instructions.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String info = "<html>Instructions: <br>"
						+ "<li>The idea of the game is to find out your preferred author by "
						+ "showing quotations without knowing <br>during the game which quotations "
						+ "are from which author.<br> "
						+ "<li>At the beginning the quotations are shown randomly and after a defined"
						+ "percent the author with the highest score will be shown."
						+ "<li>Please note that you have to login to your account or if you are still not "
						+ "registered you can do it also here.<br>"
						+ "In the game you are able to see your own history "
						+ "as well as you can start showing the quotations from the two authors.<br>"
						+ "<li>Please be informed that the score will only be saved if you click on the button "
						+ "CLOSE THE GAME AND SAVE IT<br>"
						+ "<li>You are only able to see the quotations not the author directly.<br>"
						+ "<li>If you would like to know which author you prefer or who is the author "
						+ "from the quotations you liked most you have to click on<br> Which author I liked most<br> "
						+ "and you will be connected to another screen for seeing all the quotations of the author "
						+ "as well as your own score.<br>Please note that after this you are not able to play anymore"
						+ " and your account will be deleted.";

				JLabel label = new JLabel(info);
				JOptionPane.showMessageDialog(null, label);

			}

		});
	}

	public String getUsername() {
		return usernameText.getText();
	}

}
