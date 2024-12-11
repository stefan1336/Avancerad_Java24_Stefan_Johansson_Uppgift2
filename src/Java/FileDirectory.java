package Java;

import java.io.*;
import java.util.HashMap;

public class FileDirectory {

    private StudentController studentController;

    public FileDirectory(StudentController studentController) {
        this.studentController = studentController;
    }

    // Lägg till en ny student till Filen med alla studenter med diverse information
    public void addStudentToFile(HashMap<Integer, Student> students) {
        try{
            File FileDirectory = new File("Files");
            if(!FileDirectory.exists()){
                FileDirectory.mkdir();
            }
            File file = new File(FileDirectory, "Students.txt");
            // För att inte skriva över studenten
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for(Student student : students.values()){
                bufferedWriter.write("ID: " + student.getStudentId() + ", " +" Student name: " + student.getStudentFirstName() + " " + student.getStudentLastName() + "," + "Grade: " + student.getStudentGrade());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            System.out.println("Saved new Student to file");
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public void readStudentsFromFile(HashMap<Integer, Student> students) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("Files/Students.txt"))) {
            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] studentInfo = line.split(",");

                if(studentInfo.length >= 3){
                    try{
                        int studentID = Integer.parseInt(studentInfo[0].split(":")[1].trim());
                        String firstName = studentInfo[1].split(":")[1].trim();
                        String lastName = studentInfo[2].split(":")[1].trim();
                        String grade = studentInfo[3].split(":")[1].trim();

                        students.put(studentID, new Student(studentID, firstName,lastName,grade));
                    }
                    catch(Exception e){
                        System.out.println("Error reading students from file");
                    }
                }

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void loadAllStudents(){
        File file = new File("Files/Students.txt");
        if(file.exists()){
            readStudentsFromFile(studentController.getStudents());
        }
        else{
            System.out.println("There are no current studens in the file");
        }
    }

    private void printStudent(Student student) {
        try{
            File FileDirectory = new File("Files");
            if(!FileDirectory.exists()){
                FileDirectory.mkdir();
            }
            File file = new File(FileDirectory, "Student.txt");
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write("Student: " +student.toString());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
