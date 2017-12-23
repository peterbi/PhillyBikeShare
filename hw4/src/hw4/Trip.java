package hw4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Trip {

	//instance var
	private String trip_id;
	private int duration;
	private Calendar start_time = Calendar.getInstance();
	private Calendar end_time = Calendar.getInstance();
	private String start_station;
	private double start_lat;
	private double start_lon;
	private String end_station;
	private double end_lat;
	private double end_lon;
	private String bike_id;
	private int plan_duration;
	private String trip_route_category;
	private String passholder_type;
	
	//constructor
	public Trip(String line) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] parts = line.split(",");
		trip_id = parts[0];
		try {
			duration = Integer.parseInt(parts[1]);
		} catch(NumberFormatException e) {
			System.out.println("invalid duration");
		}
		try {
			start_time.setTime(ft.parse(parts[2].replaceAll("\"","")));
		} catch(ParseException e) {
			System.out.println("invalid date"+parts[2]+parts[2].replaceAll("\"",""));
		}
		try {
			end_time.setTime(ft.parse(parts[3].replaceAll("\"","")));
		} catch(ParseException e) {
			System.out.println("invalid date"+parts[3]);
		}
		start_station = parts[4];
		try {
			start_lat = Double.parseDouble(parts[5]);
		} catch(NumberFormatException e) {
			System.out.println("invalid lat for trip " +trip_id );
		}
		try {
			start_lon = Double.parseDouble(parts[6]);
		} catch (NumberFormatException e) {
			System.out.println("invalid long for trip " +trip_id );
		}
		end_station = parts[7];
		try {
			end_lat = Double.parseDouble(parts[8]);
			
		} catch (NumberFormatException e) {
			System.out.println("invalid lat");
		}
		try {
			end_lon = Double.parseDouble(parts[9]);
		} catch (NumberFormatException e) {
			System.out.println("invalid long");
		}
		bike_id = parts[10].replaceAll("\"","");
		try {
			plan_duration = Integer.parseInt(parts[11]);
		} catch (NumberFormatException e) {
			System.out.println("invalid dur");
		}
		trip_route_category = parts[12].replaceAll("\"","");
		passholder_type = parts[13].replaceAll("\"","");
		
	}
	//methods
	/**gets euclidean distance
	 * @return euclidean distance
	 */
	public double euclidean() {
		double lon = Math.abs(start_lon-end_lon);
		double lat = Math.abs(start_lat-end_lat);
		double dist = Math.sqrt((Math.pow(lon, 2))+Math.pow(lat, 2));
		return dist;
	}

	public String getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Calendar getStart_time() {
		return start_time;
	}

	public Calendar getEnd_time() {
		return end_time;
	}

	public String getStart_station() {
		return start_station;
	}

	public void setStart_station(String start_station) {
		this.start_station = start_station;
	}

	public double getStart_lat() {
		return start_lat;
	}

	public void setStart_lat(double start_lat) {
		this.start_lat = start_lat;
	}

	public double getStart_lon() {
		return start_lon;
	}

	public void setStart_lon(double start_lon) {
		this.start_lon = start_lon;
	}

	public String getEnd_station() {
		return end_station;
	}

	public void setEnd_station(String end_station) {
		this.end_station = end_station;
	}

	public double getEnd_lat() {
		return end_lat;
	}

	public void setEnd_lat(double end_lat) {
		this.end_lat = end_lat;
	}

	public double getEnd_lon() {
		return end_lon;
	}

	public void setEnd_lon(double end_lon) {
		this.end_lon = end_lon;
	}

	public String getBike_id() {
		return bike_id;
	}

	public void setBike_id(String bike_id) {
		this.bike_id = bike_id;
	}

	public int getPlan_duration() {
		return plan_duration;
	}

	public void setPlan_duration(int plan_duration) {
		this.plan_duration = plan_duration;
	}

	public String getTrip_route_category() {
		return trip_route_category;
	}

	public void setTrip_route_category(String trip_route_category) {
		this.trip_route_category = trip_route_category;
	}
	
	
	public String getPassholder_type() {
		return passholder_type;
	}

	public void setPassholder_type(String passholder_type) {
		this.passholder_type = passholder_type;
	}
	
}
