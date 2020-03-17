import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class User {

	private String username;
	private int likeE, likeM, likeAll, dislikeE, dislikeAll, dislikeM, quotationsshownAll;
	private double scoreM, scoreE, scoreAll;
	BufferedReader reader;
	int linecount = 0;
	private String USER_FILE = "Resources" + System.getProperty("file.separator") + "User.dat";
	int newuser;
	private PrintWriter writer;
	private static User object = new User();

	private User() {

	}

	// instantiating for Singleton Pattern
	public static User getObject() {
		return object;
	}

	

	public void createUser(String input) {
		likeE = 0;
		likeM = 0;
		likeAll = 0;
		dislikeE = 0;
		dislikeM = 0;
		dislikeAll = 0;
		username = input;
		quotationsshownAll = 0;
		scoreE = 0.0;
		scoreM = 0.0;
		scoreAll = 0.0;

		try {
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(USER_FILE, true)));
			writer.write(username + ";" + likeE + ";" + likeM + ";" + likeAll + ";" + dislikeE + ";" + dislikeM + ";"
					+ dislikeAll + ";" + quotationsshownAll + ";" + scoreE + ";" + scoreM + ";" + scoreAll + ";\n");
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteUser(String linetoRemove) {
		String filenamenew = "TempUsers.dat";
		File oldfile = new File(USER_FILE);
		File newfile = new File(filenamenew);
		String line;
		try {
			reader = new BufferedReader(new FileReader(USER_FILE));
			writer = new PrintWriter(new BufferedWriter(new FileWriter(filenamenew)));
			while ((line = reader.readLine()) != null) {
				String trimmedLine = line.trim();
				if (trimmedLine.startsWith(linetoRemove))
					continue;
				writer.write((line) + System.getProperty("line.separator"));

			}

			writer.close();
			reader.close();
			oldfile.delete();
			newfile.renameTo(oldfile);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateFile(String usernameToupdate) {
		String usernameFile = new String("");
		String filenamenew = "TempUsers.dat";
		File oldfile = new File(USER_FILE);
		File newfile = new File(filenamenew);
		String[] allUsers;
		String separator = ";";
		try {
			try {
				reader = new BufferedReader(new FileReader(USER_FILE));
				writer = new PrintWriter(new BufferedWriter(new FileWriter(filenamenew)));
				String line;
				line = reader.readLine();
				while (line != null) {
					allUsers = line.split(separator);
					usernameFile = allUsers[0];

					if (usernameFile.equals(usernameToupdate)) {

						username = usernameFile;

						writer.write(username + ";" + getLikeE() + ";" + getLikeM() + ";" + getLikeAll() + ";"
								+ getDislikeE() + ";" + getDislikeM() + ";" + getDislikeAll() + ";"
								+ getQuotationsshownAll() + ";" + getScoreE() + ";" + getScoreM() + ";" + getScoreAll()
								+ ";\n");
					} else {
						writer.write(line + "\n");
					}
					line = reader.readLine();
				}
			} catch (EOFException eof) {
				reader.close();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.close();
		oldfile.delete();
		newfile.renameTo(oldfile);
	}

	public boolean searchUser(String input) throws NumberFormatException, IOException {

		reader = new BufferedReader(new FileReader(USER_FILE));
		String read_username = new String("");
		String line = new String("");
		line = reader.readLine();
		while (line != null) {
			String[] users = line.split(";");
			read_username = users[0];
			int likeE_1 = Integer.parseInt(users[1]);
			int likeM_1 = Integer.parseInt(users[2]);
			int likeA_1 = Integer.parseInt(users[3]);
			int dislikeE_1 = Integer.parseInt(users[4]);
			int dislikeM_1 = Integer.parseInt(users[5]);
			int dislikeA_1 = Integer.parseInt(users[6]);
			int quotationA_1 = Integer.parseInt(users[7]);
			double scoreE_1 = Double.parseDouble(users[8]);
			double scoreM_1 = Double.parseDouble(users[9]);
			double scoreA_1 = Double.parseDouble(users[10]);

			if (input.equals(read_username)) {

				likeE = likeE_1;
				likeM = likeM_1;
				likeAll = likeA_1;
				dislikeE = dislikeE_1;
				dislikeM = dislikeM_1;
				dislikeAll = dislikeA_1;
				quotationsshownAll = quotationA_1;
				scoreE = scoreE_1;
				scoreM = scoreM_1;
				scoreAll = scoreA_1;
				username = read_username;

				reader.close();
				return true;
			} else {

				line = reader.readLine();
			}

		}
		reader.close();
		return false;
	}

	public int getLikeE() {
		return likeE;
	}

	public void setLikeE(int newsetLikeE) {
		likeE = newsetLikeE;
	}

	public int getLikeM() {
		return likeM;
	}

	public void setLikeM(int newsetLikeM) {
		likeM = newsetLikeM;
	}

	public int getDislikeE() {
		return dislikeE;
	}

	public int getLikeAll() {
		return likeAll;
	}

	public void setLikeAll(int newlikeAll) {
		likeAll = newlikeAll;
	}

	public void setDislikeE(int newdislikeE) {
		dislikeE = newdislikeE;
	}

	public int getDislikeM() {
		return dislikeM;
	}

	public void setDislikeM(int newsdislikeM) {
		this.dislikeM = newsdislikeM;
	}

	public int getDislikeAll() {
		return dislikeAll;
	}

	public void setDislikeAll(int newdislikeAll) {
		this.dislikeAll = newdislikeAll;
	}

	public int getQuotationsshownAll() {
		return quotationsshownAll;
	}

	public void setQuotationsshownAll(int newquotationsshownAll) {
		this.quotationsshownAll = newquotationsshownAll;
	}

	public double getScoreE() {
		return scoreE;
	}

	public void setScoreE(double newscoreE) {
		this.scoreE = newscoreE;
	}

	public double getScoreM() {
		return scoreM;
	}

	public void setScoreM(double newscoreM) {
		this.scoreM = newscoreM;
	}

	public double getScoreAll() {
		return scoreAll;
	}

	public void setScoreAll(double newscoreAll) {
		this.scoreAll = newscoreAll;
	}

	public void incrementLikeE() {
		likeE += 1;

	}

	public void incrementLikeM() {
		likeM += 1;
	}

	public void incrementDislikeE() {
		dislikeE += 1;
	}

	public void incrementDislikeM() {
		dislikeM += 1;
	}

	public void incrementDislikeAll() {
		dislikeAll += 1;
	}

	public void incrementLikeAll() {
		likeAll += 1;
	}

	public void incrementQuotationsshonwAll() {
		quotationsshownAll += 1;
	}

	public void calculateScoreE() {
		DecimalFormat f = new DecimalFormat("#0.00"); 
		f.format(scoreE);
		if (likeE == 0 && dislikeE == 0) {
			scoreM = 0.0;
		} else {
			scoreE = (((float) likeE / (likeE + dislikeE)));
		}

	}

	public void calculateScoreM() {
		DecimalFormat f = new DecimalFormat("#0.00"); 
		f.format(scoreM);
		if (likeM == 0 && dislikeM == 0) {
			scoreM = 0.0;
		} else {
			scoreM = (((float) likeM / (likeM + dislikeM)));
		}
	}

	public void calculateScoreAll() {
		DecimalFormat f = new DecimalFormat("#0.00"); 
		f.format(scoreAll);
		
		if (quotationsshownAll == 0) {
			scoreAll = 0.0;
		} else {
			scoreAll = (((float) likeAll / (quotationsshownAll)));
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String newusername) {
		username = newusername;
	}

	public String toString() {
		return username + ";" + likeE + ";" + likeM + ";" + likeAll + ";" + dislikeE + ";" + dislikeM + ";" + dislikeAll
				+ ";" + quotationsshownAll + ";" + scoreM + ";" + scoreE + ";" + scoreAll + ";\n";
	}
}