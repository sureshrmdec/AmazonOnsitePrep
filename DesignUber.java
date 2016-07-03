package leetcode;

public class DesignUber {
	/*
	 * Objects: Location, Person: Driver, Person: Customer CarType: Car,
	 * BookingType: Booking, Feedback, Map Location: {Address, Lat, Long}
	 * 
	 * Customer extends Person: {List<Booking> past, Booking current (empty),
	 * Location}
	 * 
	 * Driver extends Person: {Car, Booking (empty), List<Feedback>}
	 * 
	 * Car extends CarType: {Location, Driver}
	 * 
	 * Booking extends BookingType: {Customer, Driver, Start, Destination, Fare,
	 * Type_of_booking} calculate_distance
	 * 
	 * Note: this isn't the shortest distance problem, it is the nearest
	 * car/point problem
	 * 
	 * Map: {Graph<Car> free_cars}
	 * 
	 * update(Booking ) //updates free_cars when booking made,ended or cancelled
	 * 
	 * find_car() //iterate through points. algorithm is another problem. not
	 * diving into it right now as you asked for OOD.
	 */
}
