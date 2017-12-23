package hw4;

public class DataTester {
	public static void main(String[] args) {
		StationReader sr = new StationReader("indego-stations-2017-10-20.csv");
		TripReader tr = new TripReader("indego-trips-2017-q3.csv");
		DataAnalysis da = new DataAnalysis(tr, sr);
//		System.out.println("Number of one way trips in 2017: "+da.getNoRouteCat("One Way",2017));
//		System.out.println("Number of stations that went live in 2016 that are still active:"+da.getNoStaYearStatus("Active", 2016));
//		System.out.println("Percentage of trips ending at Philadelphia Zoo: "+ da.getPerEnd("3113"));
//		System.out.println("The monthe with most Indego30 trips is: "+da.getMonthMostByPass("Indego30"));
//		System.out.println("The bike with most duration is: "+da.getBikeMostByDur());
//		System.out.println("The number of trips between 0 and 5 is: "+da.getNoTripsBtw(0, 5));
//		System.out.println("The number bikes used at 2017-07-01 00:19:00 is:"+da.getNoBikeUsedAt("2017-07-01 00:19:00"));
//		System.out.println("The longest trip is:"+da.getLongestTrip());
//		System.out.println("The number of trips involving stations that were the only station to go live on date is:"+da.getNoTripsUniqGoLive());
//		System.out.println("The most popular pass type of pass between indegoFlex and indego30:" + da.mostPopPass());
//		System.out.println("Close stations are");
		
		System.out.println("The most popular pass type between indegoFlex and indego30 for roundtrip: "+da.mostPopPassRT());
		da.addStationTrip();
		da.populateStationTrip();
		da.printReport();
		System.out.println("Least popular end station" + da.leastPopEnd());
	}

}
