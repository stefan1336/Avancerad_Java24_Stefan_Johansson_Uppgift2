package Java;

import java.io.*;
import java.util.HashMap;

public class FileDirectory {

    private StudentController studentController;

    public FileDirectory(StudentController studentController) {
        this.studentController = studentController;
    }

    // Metod för att printa ut studenterna från Filen.
    public void printStudentsFromFile(HashMap<Integer, Student> students) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("Files/Students.txt"))) {
            String fullInfo;
            while((fullInfo = bufferedReader.readLine()) != null){
                String[] studentInfo = fullInfo.split(",");
                if(studentInfo.length >= 3){
                    System.out.println(fullInfo);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    // Metod för att ladda studenterna till en lista
    public void loadStudents(HashMap<Integer, Student> students) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Files/Students.txt"))) {
            String fullInfo;
            while ((fullInfo = bufferedReader.readLine()) != null) {
                String[] studentInfo = fullInfo.split(",");

                if (studentInfo.length == 3) {
                    try {
                        int studentID = Integer.parseInt(studentInfo[0].split(":")[1].trim());
                        String firstName = studentInfo[1].split(":")[1].trim().split(" ")[0];
                        String lastName = studentInfo[1].split(":")[1].trim().split(" ")[1];
                        String grade = studentInfo[2].split(":")[1].trim();

                        Student student = new Student(studentID, firstName, lastName, grade);
                        students.put(studentID, student);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Invalid student data: " + fullInfo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("test två");
        }
    }

    // Metod för att spara nästa student ID för att nästa student ska få rätt ID
    public void saveNextStudentId(int nextId){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Files/NextStudentId.txt"))) {
            writer.write(String.valueOf(nextId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metod för att läsa av ID från Filen NextStudentId
    public int loadNextStudentId() {
        existingFile();
        try (BufferedReader reader = new BufferedReader(new FileReader("Files/NextStudentId.txt"))) {
                String line = reader.readLine();
                // Tenary operation för enklare sätt att använda if-else(Om line inte är null, parsa och returnera värdet, annars skicka tillbaka 1;
                return line != null ? Integer.parseInt(line) : 1;
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
    }

    // Metod för att spara studenterna till Filen Students
    public void saveStudentFile(HashMap<Integer, Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Files/Students.txt"))) {
            for(Student student : studentController.getStudents().values()){
                writer.write("ID: " + student.getStudentId() + ", " +
                        "Student name: " + student.getStudentFirstName() + " " + student.getStudentLastName() + ", " +
                        "Grade: " + student.getStudentGrade());
                writer.newLine();
            }
            System.out.println("Update have been made");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Metod för att kolla om Filerna finns
    private void existingFile() {
        File directory = new File("Files");
        if (!directory.exists()) {
            directory.mkdir();
        }
        createStudentFile(directory);
        createNextStudentIdFile(directory);
    }

    // Metod för att skapa studentfilen
    private void createStudentFile(File directory) {
        File studentFile = new File(directory, "Students.txt");
        if (!studentFile.exists()) {
            try{
                studentFile.createNewFile();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    // metod för att skapa studentID filen
    private void createNextStudentIdFile(File directory) {
        File studentIdFile = new File(directory, "NextStudentId.txt");
        if (!studentIdFile.exists()) {
            try {
                studentIdFile.createNewFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(studentIdFile))) {
                    writer.write("1");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
