package hw4;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class DataAnalysis {
	private HashMap<String, Trip> Lines = new HashMap<>();
	private HashMap<String, Station> Sta = new HashMap<>();
	private HashMap<String, StationTrip> st = new HashMap<>();
	//local var
	//constructor
	public DataAnalysis(TripReader tr, StationReader st) {
		Lines = tr.getLines();
		Sta = st.getLines();
		
	}
	
	//methods
	/**
	 * Returns routes of that category
	 * @param routeCat
	 * @return number of trips in that route category
	 */
	public int getNoRouteCat(String routeCat, int year) {
		int journey = 0;
		for (String tripID : Lines.keySet()) {
			//System.out.println(Lines.get(tripID).getTrip_route_category());
			if(Lines.get(tripID).getTrip_route_category().matches(routeCat)&&Lines.get(tripID).getStart_time().get(Calendar.YEAR)==year) {
				journey++;
			}
		}
		return journey;
	}
	
	/**
	 * Gets total number of trips
	 * @return total number of trips
	 */
	public int getTotalTrip() {
		return Lines.size();
	}
	
	/**
	 * returns total number of stations
	 * @return number of stations 
	 */
	public int getNoSta() {
		return Sta.size();
	}
	
	public int getNoStaYearStatus(String s, int year) {
		int n = 0;
		for (String sID : Sta.keySet()) {
			if((Sta.get(sID).getStatus().matches(s))&&(Sta.get(sID).getGo_live_date().get(Calendar.YEAR))==year) {
				n++;
			}
		}
		return n;
	}
	/**
	 * gets % of trips ending at s
	 * @param s end point
	 * @return int
	 */
	public double getPerEnd(String s) {
		double journey = 0.;
		double total =0.;
		for (String tripID : Lines.keySet()) {
			if(Lines.get(tripID).getEnd_station().matches(s)) {
				journey++;
				total++;
			}
		}
		if(total!=0) {
			return journey/total;
		}
		return 0;
	}

	/**
	 * Returns the month with most trips by pass holder type
	 * @param passType pass holder type
	 * @return number representing the month
	 */
	
	public int getMonthMostByPass(String passType) {
		int month;
		int n=0;
		HashMap<Integer, Integer> monthTrip = new HashMap<>();//populate hashmap
		for (String tripID : Lines.keySet()) {
			if(Lines.get(tripID).getPassholder_type().matches(passType)) {
				month=Lines.get(tripID).getStart_time().get(Calendar.MONTH);
				monthTrip.putIfAbsent(month, 0); // if no entry, put 0;
				n = monthTrip.get(month)+1;
				monthTrip.put(month, n);
			}
		}
		//get largest month
		int maxMonth = 0;
		int max = 0;
		for (Integer Month : monthTrip.keySet()) {
			if (monthTrip.get(Month) > max) {
				maxMonth = Month;
				max = monthTrip.get(Month);
			}
		}
		return maxMonth;
		
	}
	
	/**
	 * Returns bike with most duration
	 * @return bike ID w most duration
	 */
	public String getBikeMostByDur() {
		String bID;
		double t=0;
		HashMap<String, Double> bikeDur = new HashMap<>();//populate hashmap
		for (String tripID : Lines.keySet()) {
			Trip tr = Lines.get(tripID);
				bID=tr.getBike_id();
				bikeDur.putIfAbsent(bID, 0.0); // if no entry, put 0;
				double ti = (tr.getEnd_time().getTimeInMillis()-tr.getStart_time().getTimeInMillis())/1000;
				t = bikeDur.get(bID)+ti;
				bikeDur.put(bID, t);
		}
		//get largest bike
		String maxBID = "";
		double max = 0.0;
		for (String biID : bikeDur.keySet()) {
			if (bikeDur.get(biID) > max) {
				maxBID= biID;
				max = bikeDur.get(biID);
			}
		}
		return maxBID;
		
	}
	
	/**
	 * returns number of trips between hours of day
	 * @param a start time in 24h format
	 * @param b end time in 24h format
	 * @return number of trips between a and b
	 */
	public int getNoTripsBtw(int a, int b) {
		int trips = 0;
		for (String tripID: Lines.keySet()) {
			Trip tr = Lines.get(tripID);
			int st = tr.getStart_time().get(Calendar.HOUR_OF_DAY);
			int en = tr.getEnd_time().get(Calendar.HOUR_OF_DAY);
			if (st>=a&&en<=b) {
				trips++;
			}
		}
		return trips;
		
	}
	/**
	 * returns bikes in use at certain time formated yyyy-MM-dd HH:mm:ss
	 * @param s time string
	 * @return no of bikes
	 */
	public int getNoBikeUsedAt(String s) {
		Calendar in = Calendar.getInstance();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			in.setTime(ft.parse(s.replaceAll("\"","")));
		} catch(ParseException e) {
			System.out.println("invalid date "+s);
		}
		int no = 0;
		for (String tripID: Lines.keySet()) {
			Trip tr = Lines.get(tripID);
			Calendar st = tr.getStart_time();
			//System.out.println(st.toD);
			Calendar en = tr.getEnd_time();
			//System.out.println(in.toString());
			if (st.before(in)&&en.after(in)) {
				no++;
			}
		}
		return no;
	}
	
	/**
	 * get the info for the longest trip
	 * @return string containing info for longest trip
	 */
	public String getLongestTrip() {
		double distance = 0;
		String TID = "";
		String result = "";
		for (String tripID: Lines.keySet()) {
			double dist = euclidean(tripID);
			if (dist>distance) {
				TID = tripID;
				distance = dist;
			}
		}
		//get info
		Trip ltr = Lines.get(TID);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startDateStr = dateFormat.format(ltr.getStart_time().getTime());
		String endDateStr = dateFormat.format(ltr.getEnd_time().getTime());
		result = "Trip_id: "+ ltr.getTrip_id() 
				+ "\nduration: " + Integer.toString(ltr.getDuration())
				+ "\nstart time: " 	+ startDateStr
				+ "\nend_time: " + endDateStr
				+ "\nstart_lat: " + Double.toString(ltr.getStart_lat()) 
				+ "\nstart_lon: " + Double.toString(ltr.getStart_lon()) 
				+ "\nend_station: " + ltr.getEnd_station()
				+ "\nend_lat: " + Double.toString(ltr.getEnd_lat())
				+ "\nend_lon: " + Double.toString(ltr.getEnd_lon())
				+ "\nbike_id: " + ltr.getBike_id()
				+ "\nplan_duration: " + Integer.toString(ltr.getPlan_duration())
				+ "\ntrip_route_category: " + ltr.getTrip_route_category()
				+ "\npass_holder_type: " + ltr.getPassholder_type();
		
		
		
		
		return result;
	}

	/**gets eucledean distance
	 * @param tripID
	 * @return
	 */
	public double euclidean(String tripID) {
		Trip tr = Lines.get(tripID);
		double lon = Math.abs(tr.getStart_lon()-tr.getEnd_lon());
		double lat = Math.abs(tr.getStart_lat()-tr.getEnd_lat());
		double dist = Math.sqrt((Math.pow(lon, 2))+Math.pow(lat, 2));
		return dist;
	}
	
	/**
	 * gets the number of trips involving stations with a unique go-live date
	 * @return number of trips
	 */
	public int getNoTripsUniqGoLive() {
		//get list of lone stations that went live at certain date
		int tripsGL = 0;
		ArrayList<String> ids = getStationUniqGoLive();
		
		//find trips associated with above stations
		for (String tripID: Lines.keySet()) {
			Trip tr = Lines.get(tripID);
			for (String id: ids) {
				if (tr.getEnd_station()==id||tr.getStart_station()==id) {
					tripsGL++;
				}
			}
			
		}
		
		return tripsGL;
	}

	/**Stations with unique go live dates
	 * @return arraylist of stations IDS
	 */
	public ArrayList<String> getStationUniqGoLive() {
		HashMap<String, int[]> goLiveDate = new HashMap<>();
		int[] pair = new int[] {0,0};
		ArrayList<String> ids = new ArrayList<>();
		
		for (String stID: Sta.keySet()) {
			Station st = Sta.get(stID);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String goLive = dateFormat.format(st.getGo_live_date().getTime());
			goLiveDate.putIfAbsent(goLive, pair);
			pair = goLiveDate.get(goLive);
			pair[0]++;
			pair[1] = Integer.parseInt(st.getStation_ID());
		}
		
		for (String dat: goLiveDate.keySet()) {
			int[] times = goLiveDate.get(dat);
			if(times[0]==1) {
				ids.add(Integer.toString(times[1]));
			}
		}
		return ids;
	}
	
/**
 * populates stationTrip hashtable
 */
	public void addStationTrip() {
		st = new HashMap<String, StationTrip>();
		
		for (String tripID: Lines.keySet()) {
			Trip tr = Lines.get(tripID);
			String ss = tr.getStart_station();
			st.putIfAbsent(ss, new StationTrip(ss));
			st.get(ss).addFrom(tr);
			String es = tr.getEnd_station();
			st.putIfAbsent(es, new StationTrip(es));
			st.get(es).addTo(tr);
		}
		
	}
	/**
	 * populates stationTrip
	 */
	public void populateStationTrip() {
		for (String stID: st.keySet()) {
			StationTrip sti = st.get(stID);
			sti.populateDist();
			sti.populateDur();
			sti.populateNo();
		}
	}
	/**
	 * prints report
	 */
	public void printReport() {
		try {
			PrintWriter out = new PrintWriter("sum.txt");
			for(String stID: st.keySet()) {
				StationTrip sti = st.get(stID);
				out.println(sti.getStation_ID()+','+sti.getTotal_no_trips()+','+sti.getAvg_trip_dur_from()
				+','+sti.getAvg_trip_dist_from()+','+sti.getMax_trip_dur_from()+','+sti.getAvg_trip_dist_from()
				+','+sti.getPercentage_one_way()+','+sti.getDiff_start_end());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * returns least popular end station
	 * @return least pop end station
	 */
	public String leastPopEnd() {
		int size = 100000;
		String least = "";
		for (String stID: st.keySet()) {
			StationTrip sti = st.get(stID);
			//System.out.println(sti.getToTrips().size());
			if( sti.getToTrips().size()<size) {
				size=sti.getToTrips().size();
				//System.out.println(size);
				least = sti.getStation_ID();
			}
		}
		return least;
	}
	/**
	 * returns the more popular by trip between indego flex and 30
	 * @return
	 */
	public String mostPopPass() {
		int cIF=0;
		int cI30=0;
		for (String tripID: Lines.keySet()) {
			Trip t=Lines.get(tripID);
			if (t.getPassholder_type().matches("IndegoFLex")) {
				cIF++;
			}
			else if (t.getPassholder_type().matches("Indego30")) {
				cI30++;
			}
		}
		if (cIF>cI30) {return "cIF";}
		else {return "cI30";}
	}
	/**
	 * returns 
	 * @return
	 */
	public String mostPopPassRT() {
		int cIF=0;
		int cI30=0;
		for (String tripID: Lines.keySet()) {
			Trip t=Lines.get(tripID);
			if(t.getTrip_route_category().matches("Roundtrip")) {
			if (t.getPassholder_type().matches("IndegoFLex")) {
				cIF++;
			}
			else if (t.getPassholder_type().matches("Indego30")) {
				cI30++;
			}
		}
		}
		if (cIF>cI30) {return "cIF";}
		else {return "cI30";}
	}
	
}
