package leetcode;

public class ElevatorScheduling {
	/*
	 * There are several criteria to consider in elevator scheduling. For
	 * example, people have predictable behavioral patterns that must be
	 * addressed, including the uppeak and downpeak---respectively 9AM and 5PM,
	 * in many office buildings---which are when elevator efficiency matters the
	 * most. There is often a 1-2 hour two-way peak (lunchtime) to address as
	 * well. Algorithms should consider whether an elevator is full before
	 * assigning it to an elevator call. Sometimes, some blocks of floors have
	 * predictably higher interblock or intrablock traffic than other blocks.
	 * Often, calls on some floors (executive floors, for example) are given
	 * higher priority than others (basements). All of these factors increase
	 * the algorithm sophistication.
	 * 
	 * Still, they tend to be based on the four classic group traffic control
	 * algorithms. 
	 * 
	 * Nearest Car (NC): Elevator calls are assigned to the elevator
	 * best placed to answer that call according to three criteria that are used
	 * to compute a figure of suitability (FS) for each elevator. 
	 * (1) If an
	 * elevator is moving towards a call, and the call is in the same direction,
	 * FS = (N + 2) - d, where N is one less than the number of floors in the
	 * building, and d is the distance in floors between the elevator and the
	 * passenger call. 
	 * (2) If the elevator is moving towards the call, but the
	 * call is in the opposite direction, FS = (N + 1) - d. 
	 * (3) If the elevator
	 * is moving away from the point of call, FS = 1. The elevator with the
	 * highest FS for each call is sent to answer it. 
	 * The search for the
	 * "nearest car" is performed continuously until each call is serviced.
	 * 
	 * Fixed Sectoring Common Sector System (FSO): The building is divided into
	 * as many sectors as there are elevators. Elevators in each sector prefer
	 * calls in that sector. 
	 * 
	 * Fixed Sectoring Priority Timed System (FS4): The
	 * building is divided into up sectors and down sectors, and elevators only
	 * ever treat down calls in down sectors and up calls in up sectors. Each
	 * sector has a priority level, which increases the longer the passengers
	 * wait. The rate of increase can vary from sector to sector and over time.
	 * 
	 * Dynamic Sectoring System (DS): Floors are grouped into dynamic sectors.
	 * Each elevator is allocated to a sector in the sector definition, and the
	 * sectors change size and location based on the position of moving and idle
	 * elevators.
	 */
}
