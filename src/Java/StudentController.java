package Java;

import java.util.HashMap;

public class StudentController {
    // HashMap för att lagra studenter
    private HashMap<Integer, Student> students;
    private static StudentController singletonInstance;

    private StudentController() {
        students = new HashMap<>();
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
        if(students.containsKey(student.getStudentId())){
            System.out.println("Student with" + student.getStudentId() + " already exists");
        }
        else{
            // Lägg till student
            students.put(student.getStudentId(), student);
        }
    }

    // Metod för att displaya alla studenter
    public void showStudents(){
        if(students.isEmpty()){
            System.out.println("No students found");
        }
        else{
            for(Student student : students.values()){
                System.out.println(student);
            }
        }
    }

    public void loadAllStudents(){

    }

    // Metod för att söka efter en student efter Id
    public Student getStudent(int studentId){
        return students.get(studentId);
    }

    // Metod för att hämta studenterna från HashMapen
    public HashMap<Integer, Student> getStudents() {
        return students;
    }
}
