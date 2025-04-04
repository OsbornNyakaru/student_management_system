//imports
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;

//class Student
public class Student{
    private String regNo;
    private String fullname;
    private String programme;
    private String email;
    private String phoneNo;
    private ArrayList<String> courseCode;
    private ArrayList<String> courseCodeMarks;
    public String message = "";  // Made public to access from GUI

    //constructor for class student
    public Student(String regNo){
        if (!AdminGUI.isValidRegNo(regNo)) {
            throw new IllegalArgumentException("Invalid registration number format. Use format: EB1/66784/23");
        }
        this.regNo=regNo;
        this.courseCode=new ArrayList<>(); 
        this.courseCodeMarks=new ArrayList<>(); 

    }

    //method for register Student
    public void registerStudent(String fullname , String programme , String email ,String phoneNo){
        this.fullname=fullname;
        this.programme=programme;
        this.email=email;
        this.phoneNo=phoneNo;
       String filename = regNo.replace("/", "_") + ".txt";
        File file = new File(filename);

        try {
            if (file.createNewFile()) {
                // File doesn't exist, create & write data
                try (BufferedWriter myWriter = new BufferedWriter(new FileWriter(filename))) {
                    myWriter.write("Reg No: " + regNo + "\n");
                    myWriter.flush();
                    myWriter.write("Full Name: " + fullname + "\n");
                    myWriter.flush();
                    myWriter.write("Programme: " + programme + "\n");
                    myWriter.flush();
                    myWriter.write("Email: " + email + "\n");
                    myWriter.flush();
                    myWriter.write("Phone: " + phoneNo + "\n");
                }
                this.message = "Successfully registered: " + regNo;
            } else {
                this.message = "The user already exists.";
            }
        } catch (IOException e) {
            this.message = "Error: " + e.getMessage();
        }

    }


    public void enrollCourse(String course) {
        try {
            File myObj = new File(regNo + ".txt");
            if (myObj.exists()) {
                FileWriter writer = new FileWriter(myObj, true); // Append mode
                writer.append("\nCourses Enrolled:\n--------------------------------------------------\n");
                writer.append(course).append("\n");
                writer.close();
                this.message = "Enrolling successful";
            } else {
                this.message = "The user does not exist! Register first!";
            }
        } catch (Exception e) {
            this.message = "Server: error enrolling course!";
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, message, "Notification", JOptionPane.PLAIN_MESSAGE);
    }

 
//assign student scores
public void assignStudentResults(String code, int score) {
    try {
        File myObj = new File(regNo + ".txt");
        if (myObj.exists()) {
            // Read registered courses
            List<String> registeredCourses = new ArrayList<>();
            Scanner scanner = new Scanner(myObj);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("CourseCode: ")) {
                    registeredCourses.add(line.split(":")[1].trim());
                }
            }
            scanner.close();

            // Check if the course is registered
            if (registeredCourses.contains(code)) {
                // Append results
                FileWriter fw = new FileWriter(regNo + ".txt", true); // Enable append mode
                BufferedWriter writer = new BufferedWriter(fw);

                writer.newLine();
                writer.write("--------------------------------------------------");
                writer.newLine();
                writer.write("Results Section:");
                writer.newLine();
                writer.write("CourseCode: " + code + " | Score: " + score);
                writer.newLine();
                writer.close();

                this.message = "Results assigned successfully!";
            } else {
                this.message = "Error: Course " + code + " is not registered for this student!";
            }
        } else {
            this.message = "Unregistered student!";
        }
    } catch (Exception e) {
        this.message = "Error assigning results!";
        e.printStackTrace();
    }
}

    // method for searching a student
    public void searchStudent() {
        try {
            File myObj = new File(regNo + ".txt");
            
            if (myObj.exists()) {
                StringBuilder details = new StringBuilder();
                Scanner scanner = new Scanner(myObj);
                
                while (scanner.hasNextLine()) {
                    details.append(scanner.nextLine()).append("\n");
                }
                
                scanner.close();
                Reusable reusable = new Reusable(details.toString());
                this.message = "Student exists!\n" + details;
            } else {
                this.message = "The student does not exist!";
            }
    
        } catch (Exception e) {
            this.message = "Error accessing student!";
            e.printStackTrace();
        }
    }
    


//method for retrieving students results
    public void retrieveStudentResults(String courseCode){
        try{
            File myobj=new File(regNo+".txt");
            if(myobj.exists()){
                Scanner scanner=new Scanner(myobj);
                String line;
                while ((line=scanner.nextLine()) != null) {
                    if(line.contains("+"+courseCode)){
                        this.message=line;
                        scanner.close();
                        return;
                    }
                }
                scanner.close();
            }

            else{
                this.message="no student details found register first !";
            }

        
        }
        catch(Exception e){
           this.message="server error: searching student result";
            e.printStackTrace();
        }

    }
public void updateResults(){
   this.message="hahaha unimplemented !";
}


    

    
}