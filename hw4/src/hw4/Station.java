package hw4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Station {
	//instance var
	private String Station_ID;
	private String Station_Name;
	private Calendar Go_live_date = Calendar.getInstance();
	private String Status;
	
	//constructor
	public Station(String line) {
		SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy");
		String parts[] = line.split(",(?=(?:(?:[^\"]*\"){2})*[^\"]*$)", -1);
		Station_ID = parts[0];
		Station_Name = parts[1];
		try {
			Go_live_date.setTime(ft.parse(parts[2].replaceAll("\"","")));
		} catch(ParseException e) {
			System.out.println("invalid date"+parts[2]+parts[2].replaceAll("\"",""));
		}
		Status = parts [3];
	}

	public String getStation_ID() {
		return Station_ID;
	}

	public void setStation_ID(String station_ID) {
		Station_ID = station_ID;
	}

	public String getStation_Name() {
		return Station_Name;
	}

	public void setStation_Name(String station_Name) {
		Station_Name = station_Name;
	}

	public Calendar getGo_live_date() {
		return Go_live_date;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	
	//methods
	
}
