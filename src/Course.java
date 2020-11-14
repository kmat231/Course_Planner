//name: Kevin Mathew
//id number: 112167040
//recitation: 02

/**
 * 
 * @author Kevin Mathew
 *
 */
public class Course implements Cloneable { // prevents CloneNotSupportedException
	private String name;
	private String department;
	private int code;
	private byte section;
	private String instructor;

	public Course() {
	}

	/**
	 * Constructor used to create a new course
	 * 
	 * @param name
	 * course name 
	 * @param department
	 * department of the course
	 * @param code
	 * course code
	 * @param section
	 * course section
	 * @param instructor
	 * course instructor
	 */
	public Course(String name, String department, int code, byte section, String instructor) {
		this.name = name;
		this.department = department;
		this.code = code;
		this.section = section;
		this.instructor = instructor;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setSection(byte section) {
		this.section = section;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getName() {
		return name;
	}

	public String getDepartment() {
		return department;
	}

	public int getCode() {
		return code;
	}

	public byte getSection() {
		return section;
	}

	public String getInstructor() {
		return instructor;
	}

	/**
	 * @return
	 * copy of the Course object returned 
	 * @throws
	 * if the cloning process is not handled, exception is taken
	 */
	public Object clone() throws CloneNotSupportedException {
		Course copyCourse = (Course) super.clone();
		return copyCourse;
	}

	/**
	 * @param obj
	 * object to check whether they are equal
	 * 
	 * @return
	 * true if the object is equal to the course specified
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Course) {
			Course course = (Course) obj;
			if (this.getName().equals(course.getName()) && this.getDepartment().equals(course.getDepartment())
					&& this.getCode() == course.getCode() && this.getSection() == course.getSection()
					&& this.getInstructor().equals(course.getInstructor())) {
				return true;
			}
		}
		return false;

	}

}
