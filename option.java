class Student {

    // Private data members
    private int id;
    private String name;
    private double marks;

    // No-argument constructor
    public Student() {
    }

    // Parameterized constructor
    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // Getters and setters
    public int takeId() {
        return id;
    }

    public void giveId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }
}
class option{
	public static void main(String[]args){
		Student st=new Student();
		st.giveId(10);
		System.out.println(st.takeId());
	}
}