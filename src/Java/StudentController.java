package Java;

import java.util.HashMap;

public class StudentController {
    // HashMap för att lagra studenter
    private HashMap<Integer, Student> students;
    private static StudentController singletonInstance;
    private FileDirectory fileDirectory;
    private int nextId;

    private StudentController() {
        students = new HashMap<>();
        fileDirectory = new FileDirectory(this);
        fileDirectory.loadNextStudentId();
    }

    // Metod för att få den enda instansen
    public static StudentController getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new StudentController();
        }
        return singletonInstance;
    }

    // Metod för att lägga till en student
    public void addStudent(Student student) {
        student.setStudentId(nextId);
        students.put(student.getStudentId(), student);
        nextId++;
    }

    // Metod för att söka efter en student efter Id
    public Student getStudent(int studentId){
        return students.get(studentId);
    }

    // Metod för att hämta studenterna från HashMapen
    public HashMap<Integer, Student> getStudents() {
        return students;
    }

    public int getNextId(){
        return nextId;
    }

    public void setNextId(int nextId){
        this.nextId = nextId;
    }
}
