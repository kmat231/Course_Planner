//name: Kevin Mathew
//id number: 112167040
//recitation: 02

//menu comes right after result of the query. 

import java.util.Scanner;

/**
 * 
 * @author Kevin Mathew
 *
 */
public class PlannerManager {
	public static void main(String[] args) {
		Planner course_planner = new Planner();
		Planner backupPlanner = new Planner();
		boolean running = true;
		Scanner stdin = new Scanner(System.in);
		while (running) {
			startMenu();
			System.out.print("Enter a selection: ");
			String input = stdin.nextLine();
			input = input.toUpperCase();
			System.out.println();

			switch (input) {
			case "A":				//adding course
				try {
					System.out.print("Enter course name: ");
					String name = stdin.nextLine();
					System.out.print("Enter department: ");
					String department = stdin.nextLine();
					System.out.print("Enter course code: ");
					int code = stdin.nextInt();
					System.out.print("Enter course section: ");
					byte section = stdin.nextByte();
					stdin.nextLine();
					System.out.print("Enter instructor: ");
					String instructor = stdin.nextLine();
					System.out.print("Enter position: ");
					int position = stdin.nextInt();
					System.out.println();

					Course course = new Course(name, department, code, section, instructor);
					course_planner.addCourse(course, position);
					System.out.println(course.getDepartment() + " " + course.getCode() + "." + "0" + course.getSection()
							+ " successfully added to the planner\n");
					stdin.nextLine(); // clears buffer
					break;
				} catch (FullPlannerException error) {
					System.out.println("The planner is full, opening menu....");
					System.out.println();
					break;
				} catch (IllegalArgumentException error) {
					System.out.println("Position is not in valid range, opening menu......");
					System.out.println();
					stdin.nextLine(); // clears buffer
					break;
				}

			case "G":		//get course
				try {
					System.out.print("Enter position: ");
					int position = stdin.nextInt();
					Course course = course_planner.getCourse(position);
					System.out.printf("%-8s%-25s%-15s%-10s%-14s%-8s%n", "No.", "Name", "Department", "Code", "Section",
							"Instructor");
					System.out.println(
							"------------------------------------------------------------------------------------------------------------------------------------");
					System.out.printf("%-8s%-25s%-15s%-10s%-14s%-8s%n", position, course.getName(),
							course.getDepartment(), course.getCode(), course.getSection(), course.getInstructor());
					System.out.println();
					stdin.nextLine();
					break;
				}

				catch (IllegalArgumentException error) {
					System.out.println("Position is not in valid range, opening menu");
					System.out.println();
					stdin.nextLine(); // clears buffer
					break;
				} catch (NullPointerException error) {
					System.out.println("No course in that position");
					System.out.println();
					stdin.nextLine(); // clears buffer
					break;
				}

			case "R":			//remove course
				try {
					System.out.print("Enter position: ");
					int position = stdin.nextInt();
					course_planner.removeCourse(position);
					Course course = course_planner.getCourse(position);
					System.out.println(course.getDepartment() + " " + course.getCode() + "." + "0" + course.getSection()
							+ " has been successfully removed from the planner\n");
					stdin.nextLine(); // clears buffer
					break;
				}

				catch (IllegalArgumentException error) {
					System.out.println("Position is not in valid range, opening menu");
					System.out.println();
					stdin.nextLine(); // clears buffer
					break;
				}

			case "P":		//print courses
				try {
					if (course_planner != null) {
						course_planner.printAllCourses();
						break;
					} else {
						backupPlanner.printAllCourses();
						break;
					}
				} catch (NullPointerException error) {
					System.out.println("Program null pointer");
					break;
				}

			case "F":		//filter courses by department
				System.out.print("Enter department code: ");
				String depart = stdin.nextLine();
				Planner.filter(course_planner, depart);
				break;
			
			case "L":		//look up course
				try {
					System.out.print("Enter course name: ");
					String name = stdin.nextLine();
					System.out.print("Enter department: ");
					String department = stdin.nextLine();
					System.out.print("Enter course code: ");
					int code = stdin.nextInt();
					System.out.print("Enter course section: ");
					byte section = stdin.nextByte();
					stdin.nextLine();
					System.out.print("Enter instructor: ");
					String instructor = stdin.nextLine();

					Course course = new Course(name, department, code, section, instructor);
					if (course_planner.exists(course)) {
						System.out.println();
						System.out.println(course.getDepartment() + " " + course.getCode() + "." + course.getSection()
								+ " is found in the planner at position " + course_planner.getEposition() + "\n");
						break;
					} else {
						System.out.print("The searched course was not found");
						break;
					}

				}

				catch (IllegalArgumentException error) {
					System.out.println("Position is not in valid range, opening menu");
					System.out.println();
					stdin.nextLine(); // clears buffer
					break;
				}

			case "S":	//size of the planner
				System.out.println("There are " + course_planner.size() + " courses in the planner\n");
				break;

			case "B":	//backup of the planner
				try {
					backupPlanner = (Planner) course_planner.clone();
				}

				catch (CloneNotSupportedException error) {
					System.out.println("Backup was not possible\n");
					break;
				}
				System.out.println("Created a backup of the current planner\n");
				break;

			case "PB": //print backup planner
				System.out.println("(Backup)");
				backupPlanner.printAllCourses();
				break;

			case "RB": //revert back to backup planner
				course_planner = backupPlanner;
				try {
					backupPlanner = (Planner) course_planner.clone();
				} catch (CloneNotSupportedException error) {
					System.out.println("Backup was not possible\n");
					break;
				}
				System.out.print("Successfully revereted to the backup copy.\n");
				System.out.println();
				break;
			
			case "Q":		//quit program
				System.out.print("Program terminating successfully.....");
				running = false;
				break;
			}
			;
		}
		stdin.close(); // closes scanner
	}

	/**
	 * 	menu void function
	 */
	public static void startMenu() {
		System.out.println("(A) Add Course");
		System.out.println("(G) Get Course");
		System.out.println("(R) Remove Course");
		System.out.println("(P) Print Courses in Planner");
		System.out.println("(F) Filter by Department Code");
		System.out.println("(L) Look For Course");
		System.out.println("(S) Size");
		System.out.println("(B) Backup");
		System.out.println("(PB) Print Courses in Backup");
		System.out.println("(RB) Revert to Backup");
		System.out.println("(Q) Quit");
		System.out.println();
	}
}
