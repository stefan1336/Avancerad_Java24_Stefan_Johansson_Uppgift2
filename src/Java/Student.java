package Java;

public class Student {
    private int studentId;
    private String studentFirstName;
    private String studentLastName;
    private String studentGrade;

    public Student(int studentId, String studentFirstName, String studentLastName, String studentGrade) {
        this.studentId = studentId;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.studentGrade = studentGrade;
    }

    @Override
    public String toString() {
        return "Student ID = " + studentId + ", studentFirstName = " + studentFirstName + ", studentLastName = " + studentLastName + ", studentGrade = " + studentGrade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(String studentGrade) {
        this.studentGrade = studentGrade;
    }
}

