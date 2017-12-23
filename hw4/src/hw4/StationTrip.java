package hw4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import hw4.DataAnalysis.*;

public class StationTrip {
	//instance var
	private String Station_ID;
	private ArrayList<Trip> fromTrips = new ArrayList<>();
	private ArrayList<Trip> toTrips = new ArrayList<>();
	private int total_no_trips;
	private ArrayList<Double> dist = new ArrayList<>();
	private ArrayList<Double> dur = new ArrayList<>();
	private double avg_trip_dur_from;
	private double avg_trip_dist_from;
	private double max_trip_dur_from;
	private double max_trip_dist_from;
	private double percentage_one_way;
	private int diff_start_end;

	//constructor
	public StationTrip (String SID) {
		Station_ID = SID;		
	}
	
	//methods
	/**
	 * add trips to the from arraylist
	 * @param t
	 */
	public void addFrom(Trip t) {
		fromTrips.add(t);
	}
	/**
	 * add trips to the to arraylist
	 * @param t
	 */
	public void addTo(Trip t) {
		toTrips.add(t);
	}
	/**
	 * populates distance related fields
	 */
	public void populateDist() {
		for (Trip t:fromTrips) {
			dist.add((Double)t.euclidean());
		}
		avg_trip_dist_from = calcAvg(dist);
		if(!dist.isEmpty()) {
			max_trip_dist_from = Collections.max(dist);
		}
		else {
			max_trip_dist_from = 0;
		}
	}
	/**
	 * populates duration related fields
	 */
	public void populateDur() {
		for (Trip t:fromTrips) {
			dur.add(((Integer)t.getDuration()).doubleValue());
		}
		avg_trip_dur_from = calcAvg(dur);
		if(!dist.isEmpty()) {
			max_trip_dur_from = Collections.max(dist);
		}
		else {
			max_trip_dur_from = 0;
		}
	}
	
	/**
	 * calculates average
	 * @param li list
	 * @return average
	 */
	public double calcAvg(List<Double> li) {
		Double sum = 0.;
		if(!li.isEmpty()) {
			for(Double l: li) {
				sum+=l;
			}
			return sum/li.size();
		}
		return sum;
	}
	/**
	 * populates fiels related to number
	 */
	public void populateNo() {
		total_no_trips = fromTrips.size()+toTrips.size();
		diff_start_end = fromTrips.size()-toTrips.size();
		int no_one_way = 0;
		for (Trip t: fromTrips) {
			if (t.getTrip_route_category().equals("One Way")){
				no_one_way++;
			}
		}
		for (Trip s: toTrips) {
			if(s.getTrip_route_category().equals("One Way")) {
				no_one_way++;
			}
		}
		percentage_one_way = ((Integer)no_one_way).doubleValue()/total_no_trips;
	}
	public String getStation_ID() {
		return Station_ID;
	}

	public void setStation_ID(String station_ID) {
		Station_ID = station_ID;
	}

	public ArrayList<Trip> getFromTrips() {
		return fromTrips;
	}

	public void setFromTrips(ArrayList<Trip> fromTrips) {
		this.fromTrips = fromTrips;
	}

	public ArrayList<Trip> getToTrips() {
		return toTrips;
	}

	public void setToTrips(ArrayList<Trip> toTrips) {
		this.toTrips = toTrips;
	}

	public int getTotal_no_trips() {
		return total_no_trips;
	}

	public void setTotal_no_trips(int total_no_trips) {
		this.total_no_trips = total_no_trips;
	}

	public ArrayList<Double> getDist() {
		return dist;
	}

	public void setDist(ArrayList<Double> dist) {
		this.dist = dist;
	}

//	public ArrayList<Integer> getDur() {
//		return dur;
//	}
//
//	public void setDur(ArrayList<Integer> dur) {
//		this.dur = dur;
//	}

	public double getAvg_trip_dur_from() {
		return avg_trip_dur_from;
	}

	public void setAvg_trip_dur_from(double avg_trip_dur_from) {
		this.avg_trip_dur_from = avg_trip_dur_from;
	}

	public double getAvg_trip_dist_from() {
		return avg_trip_dist_from;
	}

	public void setAvg_trip_dist_from(double avg_trip_dist_from) {
		this.avg_trip_dist_from = avg_trip_dist_from;
	}

	public double getMax_trip_dur_from() {
		return max_trip_dur_from;
	}

	public void setMax_trip_dur_from(double max_trip_dur_from) {
		this.max_trip_dur_from = max_trip_dur_from;
	}

	public double getMax_trip_dist_from() {
		return max_trip_dist_from;
	}

	public void setMax_trip_dist_from(double max_trip_dist_from) {
		this.max_trip_dist_from = max_trip_dist_from;
	}

	public double getPercentage_one_way() {
		return percentage_one_way;
	}

	public void setPercentage_one_way(double percentage_one_way) {
		this.percentage_one_way = percentage_one_way;
	}

	public double getDiff_start_end() {
		return diff_start_end;
	}

	public void setDiff_start_end(int diff_start_end) {
		this.diff_start_end = diff_start_end;
	}
	
}
