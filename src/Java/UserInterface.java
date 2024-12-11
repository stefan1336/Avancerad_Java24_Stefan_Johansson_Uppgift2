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
        loadAllStudents();
        System.out.println("Hey what would you like to do?");
        systemOptions();
        sc.close();
    }

    private void systemOptions(){
        System.out.println("1. Add new student");
        System.out.println("2. Search for a student");
        System.out.println("3. Display all students");
        System.out.println("4. Read Student Poster from a File");
        System.out.println("5. Exit");
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
                    System.out.println("Search for a student");
                    break;
                    case "3":
                        displaySavedStudents();
                        break;
                            case "4":
                                readStudentPoster();
                                break;
                                case "5":
                                    exitProgram();
                                    break;
                                    default:
                                        System.out.println("Invalid option");
                                        userAnswer(); // Kunna köra programmet igen om man skriver in fel
        }
    }

    private void loadAllStudents(){
        fileDirectory.loadAllStudents();
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
        System.out.println("New student added with id " + student.getStudentId());
        fileDirectory.addStudentToFile(student);
    }

    private void validateId(){
        System.out.println("test");
    }

    // Metod för att söka efter en specifik student med hjälp av Student-ID
    private void searchForStudent() {
        System.out.println("Enter student ID");
        int studentId = sc.nextInt();
        sc.nextLine();

        Student student = studentController.getStudent(studentId);
        if(student != null){
            System.out.println("Student: " + student);
        }
        else{
            System.out.println("Student not found");
        }
    }

    private void displaySavedStudents() {
        studentController.showStudents();
        System.out.println("Display all saved students");
    }

//    private void saveStudentPoster() {
//        System.out.println("Save Student Poster to a File");
//        fileDirectory.addStudentToFile(studentController.getStudents());
//    }

    private void readStudentPoster() {
        System.out.println("Read Student Poster from a File");
        fileDirectory.readStudentsFromFile(studentController.getStudents());
    }

    private void exitProgram() {
        System.out.println("Exit");
        sc.close();
        System.exit(0);
    }

    private void continueAddingStudent() {
        System.out.println("Do you want to continue? or do you want to exit");
        System.out.println("Type c if you want to continue or e if you want to close the program");
        String userAnswer = sc.nextLine();
        if(userAnswer.equals("c")){
            systemOptions();
        }
        else if(userAnswer.equals("e")){
            exitProgram();
        }
        else{
            System.out.println("Invalid option");
        }
    }
}
