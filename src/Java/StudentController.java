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
        // ge studenten id
        student.setStudentId(nextId);
        // Lägg till student i hashmap
        students.put(student.getStudentId(), student);
        // öka id för nästa student
        nextId++;
        // Spara nästa id som ska användas till nästa student
        fileDirectory.saveNextStudentId(nextId);
        System.out.println("Student till hashmap " + student);
    }

    // Metod för att displaya alla studenter
//    public void showStudents(){
//        if(students.isEmpty()){
//            System.out.println("No students found");
//        }
//        else{
//            for(Student student : students.values()){
//                System.out.println(student);
//            }
//        }
//    }

    // Metod för att söka efter en student efter Id
    public Student getStudent(int studentId){
        return students.get(studentId);
    }

    public boolean studentExists(int studentId){
        return students.containsKey(studentId);
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
