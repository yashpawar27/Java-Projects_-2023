package programs;

import java.awt.Dimension;

import javax.swing.JPanel;

public class FilePanel extends JPanel {
	private int WIDTH = 600, HEIGHT = 600;
	private String fileName = "src/files/mydata.txt";
	FileWrite writing;
	FileRead reading;

	public FilePanel() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		writing = new FileWrite(fileName);
		reading = new FileRead(fileName);
		addToFile();
		readFromFile();
	}

	private void readFromFile() {
		reading.readFile();
		for (String output : reading.in) {
			System.out.println(output);
		}

	}

	private void addToFile() {
		writing.setData("Mike" + "\n");
		writing.setData("Christopher" + "\n");
		writing.setData("Amy" + "\n");
		writing.setData("Bryan" + "\n");
		writing.setData("Gavin" + "\n");
		writing.setData("Liam" + "\n");
		writing.setData("Aven" + "\n");
		//Appends the Data to the mydata.txt file
		writing.writeAppend();
		//OverWrites everything in the list
		writing.writeOver();
	}

}
