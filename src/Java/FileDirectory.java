package Java;

import java.io.*;
import java.util.HashMap;

public class FileDirectory {

    private StudentController studentController;

    public FileDirectory(StudentController studentController) {
        this.studentController = studentController;
    }

    // Lägg till en ny student till Filen med alla studenter med diverse information
    public void addStudentToFile(Student student) {
        try{
            File fileDirectory = new File("Files");
            if(!fileDirectory.exists()){
                fileDirectory.mkdir();
            }

            File file = new File(fileDirectory, "Students.txt");
            // För att inte skriva över studenten
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                bufferedWriter.write("ID: " + student.getStudentId() + ", " +" Student name: " + student.getStudentFirstName() + " " + student.getStudentLastName() + "," + "Grade: " + student.getStudentGrade());
                bufferedWriter.newLine();
            }
                // Ha kvar?
//            bufferedWriter.close();
            System.out.println("Saved new Student to file");
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public void readStudentsFromFile(HashMap<Integer, Student> students) {
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

    public void loadAllStudents(HashMap<Integer, Student> students) {
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
        }
    }

// kanske använd
//    private Student parseStudent(String line){
//        try{
//            String[] studentInfo = line.split(",");
//            if(studentInfo.length >= 3){
//                String id = studentInfo[0].trim();
//                String fullName = studentInfo[1].trim();
//                String studentGrade = studentInfo[2].trim();
//
//                int studentID = Integer.parseInt(id.split(":")[1].trim());
//                String[] name = fullName.split(":")[1].trim().split(" ");
//                String firstName = name[0].trim();
//                String lastName = name[1].trim();
//                String grade = studentGrade.split(":")[1].trim();
//
//                return new Student(studentID, firstName, lastName, grade);
//            }
//
//        }
//        catch(Exception e){
//            System.out.println("Cant parse line");
//            e.printStackTrace();
//        }
//        return null;
//    }

//    public void loadAllStudents(){
//        File file = new File("Files/Students.txt");
//        if (file.exists()) {
//            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
//                String line;
//
//                while ((line = bufferedReader.readLine()) != null) {
//                    String[] studentInfo = line.split(",");
//                    if (studentInfo.length >= 3) {
//                        try {
//                            int studentID = Integer.parseInt(studentInfo[0].split(":")[1].trim());
//                            String firstName = studentInfo[1].split(":")[1].trim();
//                            String lastName = studentInfo[2].split(":")[1].trim();
//                            String grade = studentInfo[3].split(":")[1].trim();
//
//                            if(!studentController.getStudents().containsKey(studentID)){
//                            Student student = new Student(studentID, firstName, lastName, grade);
//                            studentController.addStudent(student);
//                            }
//
//                        } catch (Exception e) {
//                            System.out.println("Error reading students from file");
//                        }
//                    }
//
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println("There are no current students in the file.");
//        }
//    }

    public void saveNextStudentId(int nextId){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Files/NextStudentId.txt"))) {
            writer.write(String.valueOf(nextId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadNextStudentId() {
        ensureFileExist();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("Files/NextStudentId.txt"))) {
                String id = reader.readLine();
                if (id != null) {
                    studentController.setNextId(Integer.parseInt(id));
                }

        } catch (IOException e) {
            studentController.setNextId(1);
        }
    }

    private void ensureFileExist() {
        File directory = new File("Files");
        if (!directory.exists()) {
            directory.mkdir();
            System.out.println("Directory 'Files' created.");
        }

        File studentIdFile = new File(directory, "NextStudentId.txt");
        if (!studentIdFile.exists()) {
            try {
                studentIdFile.createNewFile();
                System.out.println("File 'NextStudentId.txt' created.");

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(studentIdFile))) {
                    writer.write("1");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadStudentId(){
        try (BufferedReader reader = new BufferedReader(new FileReader("Files/NextStudentId.txt"))) {
            String id = reader.readLine();
            if (id != null) {
                studentController.setNextId(Integer.parseInt(id));
            }
        } catch (IOException e) {
            studentController.setNextId(1);
        }
    }

    public void saveStudentFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Files/Students.txt"))) {
        for(Student students : studentController.getStudents().values()){
            writer.write("ID: " + students.getStudentId() + ", " +
                    "Student name: " + students.getStudentFirstName() + " " + students.getStudentLastName() + ", " +
                    "Grade: " + students.getStudentGrade());
            writer.newLine();
        }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
