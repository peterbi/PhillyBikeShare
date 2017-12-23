package hw4;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class StationReader {
	HashMap<String, Station> Lines = new HashMap<>();

	// constructor
	public StationReader(String filename) {
		try {
			File inputFile = new File(filename);
			Scanner in = new Scanner(inputFile);
			int x = 0;
			String line = in.nextLine();
			while (in.hasNextLine()) {
				line = in.nextLine();
				Station t = new Station(line);
				if (!line.startsWith("\"Sta")) {
					Lines.put(t.getStation_ID(), t);
					//System.out.println(x);
					x++;
				}
			}
			in.close();
		} catch (Exception e) {
			System.out.println("file io problem");
		}
	}
	//
	public HashMap<String, Station> getLines() {
		return Lines;
	}
	public void setLines(HashMap<String, Station> lines) {
		Lines = lines;
	
	}
}
