//name: Kevin Mathew
//id number: 112167040
//recitation: 02

/**
 * This class Represents a planner which contains an array of Course objects each with their own properties
 * @author Kevin Mathew
 *
 */
public class Planner extends Course implements Cloneable {
	private final int MAX_COURSES = 50;
	private Course[] course_plan = new Course[MAX_COURSES]; 
	private static int totalCourses;  
	private int ePosition; 
	
	public Planner() {
		totalCourses = 0; 
	}
	
	/**
	 * 
	 * @return 
	 * totalCourses represents the number of courses inside the planner
	 */
	public int size() {
		return totalCourses;
	}
	
	/**
	 * 
	 * @param newCourse
	 * Course object that contains details for inputed course
	 * @param position
	 * The intended position for the course
	 * 
	 * 
	 * @throws FullPlannerException
	 * The exception for when the planner is full == MAX_COURSES(50)
	 * @throws IllegalArgumentException
	 * Specified position is not within the range of 0 - totalCourses + 1
	 */
	public void addCourse(Course newCourse, int position) throws FullPlannerException, IllegalArgumentException {
		if (totalCourses == MAX_COURSES)   
		{
			throw new FullPlannerException(); 
		}
		if (position < 0 || position > (totalCourses + 1)) {
			throw new IllegalArgumentException("Position is not in valid range"); 
		} 
			for (int i = position; i < totalCourses; i++) {
				course_plan[i + 1] = course_plan[i];  	//move all other courses up anoher position. 
		}
			course_plan[position-1] = newCourse; 
			totalCourses++; 
	}
	/**
	 * 
	 * @param newCourse
	 * Course object that contains details for inputed course
	 */
	public void addCourse(Course newCourse) {
		course_plan[size()] = newCourse;
		totalCourses++; 
	}
	
	/**
	 * 
	 * @param position
	 * The intended position for the course
	 * 
	 * @throws IllegalArgumentException
	 * Specified position is not within the range of 0 - totalCourses + 1
	 */
	public void removeCourse(int position) throws IllegalArgumentException {
		if (position < 0 || position > (totalCourses + 1)) {
			throw new IllegalArgumentException("Position is not in valid range"); 
		} 
		
		course_plan[position - 1] = null; //set specificed position to null
		
		for (int i = 0; i < totalCourses - 1; i++) {
			course_plan[i] = course_plan[i+1];	//move all other courses up on position
	}
			totalCourses--; 
	}
	/**
	 * 
	 * @param position
	 * The intended position for the course
	 * 
	 * @return
	 * The course at the specified position
	 * 
	 * @throws NullPointerException
	 * Position specified has no course in the position (null)
	 */
	public Course getCourse(int position) throws NullPointerException {
		if (course_plan[position-1] == null) {
			throw new NullPointerException("No course in that position"); 
		}
		return course_plan[position-1];
	}
	/**
	 * 
	 * @param planner
	 * Planner object used to find to specific course for the department
	 * 
	 * @param department
	 *  specified department of the course
	 */
	public static void filter(Planner planner, String department) {
		System.out.print("Planner:\n");
		System.out.printf("%-8s%-17s%-15s%-10s%-14s%-8s", "No","Course Name","Department","Code","Section","Instructor");
		System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
		for(int i = 0; i < totalCourses; i++) {
			Course course = planner.getCourse(i+1);
			if (course.getDepartment().equals(department)) {
				System.out.printf("%-8s%-17s%-15s%-10s%-14s%-8s%n",i+1,course.getName(),course.getDepartment(),course.getCode(),course.getSection(),course.getInstructor());
			}
		}
		System.out.println();
	}
	/**
	 * 
	 * @param course
	 * Course object that contains details for inputed course
	 * @return
	 * true or false value whether the course exists in the planner
	 */
	public boolean exists(Course course) {
		for(int i = 0; i < totalCourses; i++) {
				if (course.equals(course_plan[i]))
				{	
					ePosition = i; 
					return true;
				}
			}
					return false; 
		}
	/**
	 * Custom method made to store position of the existing course
	 * @return
	 * position of the existing course.
	 */
	public int getEposition() {
		return ePosition + 1; 
	}
	/**
	 * @return 
	 * cloned backup planner
	 */
	public Object clone() throws CloneNotSupportedException {
		Planner backup_plan = (Planner) super.clone();
		backup_plan.course_plan = this.course_plan.clone();	//making deep copy of the city
		return backup_plan; 
	}
	
	/**
	 * prints all the courses implementing the String toString function
	 */ 
	public void printAllCourses() {
		System.out.print(this);
	}
	
	/**
	 * toString function for the planner
	 */
	public String toString() {		
		String header = String.format("%-8s%-25s%-15s%-10s%-14s%-8s", "No.","Course Name","Department","Code","Section","Instructor");
		String dashes = "------------------------------------------------------------------------------------------------------------------------------------";
		
		String courseList = "";
		for (int i = 0; i < totalCourses; i++) {
			Course course = course_plan[i]; 
			courseList += String.format("%-8d%-25s%-15s%-10d%-14s%-8s%n",i+1,course.getName(),course.getDepartment(),course.getCode(),course.getSection(),course.getInstructor());
		}
		
		return "Planner:\n" + header + "\n" + dashes + "\n" + courseList + "\n"; 
	
	}
}
	