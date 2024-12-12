package Java;

import java.util.Scanner;

public class UserInterface {
    private Scanner sc = new Scanner(System.in);
    private StudentController studentController;
    private FileDirectory fileDirectory;

    public UserInterface(){
        sc = new Scanner(System.in);
        studentController = StudentController.getInstance();
        this.fileDirectory = new FileDirectory(studentController);
    }
    public void runStudentPoster() {
        // Ladda alla studenter som finns
        fileDirectory.loadStudentId();
//        loadAllStudents();
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
                        continueAddingStudent();
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
        fileDirectory.loadAllStudents(studentController.getStudents());
    }

    private void addStudent() {
        System.out.println("Enter student name: ");
        String studentName = sc.nextLine();
        System.out.println("Enter student last name: ");
        String studentLastName = sc.nextLine();
        System.out.println("Enter student grade: ");
        String studentGrade = sc.nextLine();

        Student student = new Student(0, studentName, studentLastName, studentGrade);

        studentController.addStudent(student);
        System.out.println("New student added with ID: " + student.getStudentId());
        fileDirectory.addStudentToFile(student);
        continueAddingStudent();
    }

    // Metod för att söka efter en specifik student med hjälp av Student-ID
    private void searchForStudent() {
        // ladda studenterna
        loadAllStudents();
        System.out.println("This is all students");
        // Displayar studenterna
        displayStudentPoster();
        System.out.println("Enter wanted student ID");
        int studentId = sc.nextInt();
        sc.nextLine();

        Student student = studentController.getStudent(studentId);

        if(student != null){
            System.out.println("Student Name " + student.getStudentFirstName() + " " + student.getStudentLastName() + " Current grade " + student.getStudentGrade());
        }
        else{
            System.out.println("Student not found");
        }
    }

    // Metod för att kunna läsa alla studenter från Filen
    private void displayStudentPoster() {
        fileDirectory.readStudentsFromFile(studentController.getStudents());
    }

    // Metod för att stänga programmet
    private void exitProgram() {
        System.out.println("Exit");
        sc.close();
        System.exit(0);
        // Här ska allt sparas samt här ska filen uppdateras
    }

    // Metod för att kunna fortsätta lägga till studenter
    private void continueAddingStudent() {
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
                continueAddingStudent();
            }
    }
}
