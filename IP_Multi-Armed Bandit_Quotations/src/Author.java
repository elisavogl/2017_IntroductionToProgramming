import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Author {

	private String quotations;
	private String authorname;
	private String authorMaugham = "Resources" + System.getProperty("file.separator") + "W.SomersetMaugham.dat";
	private String authorEmerson = "Resources" + System.getProperty("file.separator") + "RalphWaldoEmerson.dat";
	private BufferedReader reader;
	ArrayList<Author> authorlist;
	ArrayList<Author> Emersonlist;
	ArrayList<Author> Maughamlist;

	public Author(String authorname, String quotations) {
		this.quotations = quotations;
		this.authorname = authorname;
	}

	public Author() throws IOException {
		authorlist = new ArrayList<Author>();
		Emersonlist = new ArrayList<Author>();
		Maughamlist = new ArrayList<Author>();
		readMaugham();
		readEmerson();
		authorlist.addAll(Emersonlist);
		authorlist.addAll(Maughamlist);

	}

	public ArrayList<Author> getList() {
		return authorlist;
	}

	public ArrayList<Author> getEmersonList() {
		return Emersonlist;
	}

	public ArrayList<Author> getMaughamList() {
		return Maughamlist;
	}

	public ArrayList<Author> readMaugham() throws IOException {

		String line = "";
		String separator = ";";
		String[] AllQuotations;

		reader = new BufferedReader(new FileReader(authorMaugham));
		while ((line = reader.readLine()) != null) {
			AllQuotations = line.split(separator);
			String authorname = AllQuotations[0];
			String quotation = AllQuotations[1];
			Author author = new Author(authorname, quotation);

			Maughamlist.add(author);
		}

		return Maughamlist;

	}

	public ArrayList<Author> readEmerson() throws IOException {

		String line = "";
		String separator = ";";
		String[] AllQuotations;

		reader = new BufferedReader(new FileReader(authorEmerson));
		while ((line = reader.readLine()) != null) {
			AllQuotations = line.split(separator);
			String authorname = AllQuotations[0];
			String quoation = AllQuotations[1];
			Author author = new Author(authorname, quoation);

			Emersonlist.add(author);
		}

		return Emersonlist;

	}

	public String getRandom() throws NumberFormatException, FileNotFoundException, IOException {

		User object = User.getObject();

		if (object.getLikeE() < 4 && object.getLikeM() < 4) {
			Random random = new Random();
			int size = authorlist.size();
			int number = random.nextInt(size);
			return (authorlist.get(number).getQuoations());
		} else {
			if (object.getScoreE() > object.getScoreM()) {
				Random random = new Random();
				int size = Emersonlist.size();
				int number = random.nextInt(size);
				return (Emersonlist.get(number).getQuoations());
			} else {
				Random random = new Random();
				int size = Maughamlist.size();
				int number = random.nextInt(size);
				return (Maughamlist.get(number).getQuoations());
			}
		}
	}

	public String getAuthorname() {
		return authorname;
	}

	public String getQuoations() {
		return quotations;
	}

	public String toString() {
		return authorname + ";" + quotations + ";\n";
	}

}
