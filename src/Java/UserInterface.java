package Java;

import java.util.HashMap;
import java.util.Scanner;

public class UserInterface {
    private Scanner sc;
    private StudentController studentController;
    private FileDirectory fileDirectory;

    public UserInterface(){
        sc = new Scanner(System.in);
        studentController = StudentController.getInstance();
        this.fileDirectory = new FileDirectory(studentController);
    }

    // Metod där programmet körs. Laddar filen för att hämta studenter och laddar filen för att kunna hämta vad nästa ID ska vara
    public void runProgram() {
        fileDirectory.loadStudents(studentController.getStudents());
        int loadNextId = fileDirectory.loadNextStudentId();
        studentController.setNextId(loadNextId);
        System.out.println("Hey what would you like to do?");
        systemOptions();
        sc.close();
    }

    private void systemOptions(){
        System.out.println("1. Add new student");
        System.out.println("2. Search for a student");
        System.out.println("3. Display all students");
        System.out.println("4. Exit");
        userAnswer();
    }

    private void userAnswer() {
        String userAnswer;
        userAnswer = sc.nextLine();
        switch (userAnswer){
            case "1":
                addStudent();
                break;
                case "2":
                    searchForStudent();
                    break;
                    case "3":
                        displayStudentPoster();
                        continueAddingStudents();
                        break;
                            case "4":
                                exitProgram();
                                break;
                                    default:
                                        System.out.println("Invalid answer, im asking you again. What would you like to do?");
                                        systemOptions();
        }
    }

    private void loadAllStudents(){
        System.out.println("This is all students:");
        fileDirectory.loadStudents(studentController.getStudents());
    }

    // Metod för att lägga till ny student
    private void addStudent() {
        System.out.println("Enter student first name: ");
        String studentName = sc.nextLine();
        System.out.println("Enter student last name: ");
        String studentLastName = sc.nextLine();
        System.out.println("Enter student grade: ");
        String studentGrade = sc.nextLine();

        Student student = new Student(0, studentName, studentLastName, studentGrade);
        studentController.addStudent(student);
        System.out.println("New student just added, with ID: " + student.getStudentId());
        continueAddingStudents();
    }

    private HashMap<Integer, Student> findStudents(){
        HashMap<Integer,Student> students = studentController.getStudents();
        if(students.isEmpty()){
            return null;
        }
        return students;
    }

    // Metod för att söka efter en specifik student med hjälp av Student-ID
    private void searchForStudent() {
        HashMap<Integer,Student> students = findStudents();
        if(students == null){
            System.out.println("There are no students in the database");
            continueAddingStudents();
        }
        else{
            // ladda studenterna
            loadAllStudents();
            // Displayar studenterna
            System.out.println("Enter wanted student ID");
            displayStudentPoster();
            selectWantedStudent();
        }
    }

    // Metod för att hämta önskad student efter valt ID
    private void selectWantedStudent(){
        int studentId = sc.nextInt();
        sc.nextLine();

        Student student = studentController.getStudent(studentId);
        displayStudent(student);
    }

    private void displayStudent(Student student){
        if(student != null){
            System.out.println("Student Name " + student.getStudentFirstName() + " " + student.getStudentLastName() + " Current grade " + student.getStudentGrade());
            continueAddingStudents();
        }
        else{
            System.out.println("Student not found");
            searchForStudent();
        }
    }

    // Metod för att kunna läsa alla studenter från Filen
    private void displayStudentPoster() {
        HashMap<Integer, Student> students = findStudents();
        if(students == null){
            System.out.println("There are no students in the database");
            continueAddingStudents();
        }
        else{
            System.out.println("Here are the students in the database:");
           fileDirectory.printStudentsFromFile(studentController.getStudents());
        }
    }

    // Metod för att stänga programmet
    private void exitProgram() {
        System.out.println("Exit");
        fileDirectory.saveStudentFile(studentController.getStudents());
        fileDirectory.saveNextStudentId(studentController.getNextId());
        sc.close();
        System.exit(0);
    }

    // Metod för att kunna fortsätta lägga till studenter
    private void continueAddingStudents() {
        System.out.println("Do you want to continue? or do you want to exit");
        System.out.println("Type c if you want to continue or e if you want to close the program");
        String userAnswer = sc.nextLine();
            if(userAnswer.equalsIgnoreCase("c")){
                systemOptions();
            }
            else if(userAnswer.equalsIgnoreCase("e")){
                exitProgram();
            }
            else{
                System.out.println("Invalid option, im asking you again");
                continueAddingStudents();
            }
    }
}
