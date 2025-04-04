import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;


public class Course {
   
    private final Map<String, String> eb1 = new HashMap<>();
    private final Map<String, String> cb1 = new HashMap<>();
    private final Map<String, String> eb2 = new HashMap<>();
    private final Map<String, String> eb3 = new HashMap<>();

    public Course() {
        addCourses();
    }

    // Assign Score Method
    public String assignScore(String programmeCode, String regNo, String courseCode, int score) {
        Student student = new Student(regNo);
        Map<String, String> selectedProgramme;

        // Select the appropriate programme map
        switch (programmeCode.toLowerCase()) {
            case "eb1": selectedProgramme = eb1; break;
            case "cb1": selectedProgramme = cb1; break;
            case "eb2": selectedProgramme = eb2; break;
            case "eb3": selectedProgramme = eb3; break;
            default:
                return "Invalid programme code!";
        }

        // Check if courseCode exists in the selected programme
        if (selectedProgramme.containsKey(courseCode)) {
            student.assignStudentResults(courseCode, score);
            return "Score assigned successfully!";
        } else {
            return "Error: Course " + courseCode + " is not part of " + programmeCode;
        }
    }

   


    public String assignLecturerCourse(String lecturerID, String courseCode) {
        // Validate inputs
        if (lecturerID == null || courseCode == null || lecturerID.trim().isEmpty() || courseCode.trim().isEmpty()) {
            return "Error: All fields are required!";
        }

        // Format input
        lecturerID = lecturerID.trim();
        courseCode = courseCode.trim().toUpperCase();
        String fileName = lecturerID + ".txt"; // Store assignments in a file named after lecturerID

        try {
            // Check if the file exists and if the course is already assigned
            File file = new File(fileName);
            boolean courseExists = false;

            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    if (scanner.nextLine().contains(courseCode)) {
                        courseExists = true;
                        break;
                    }
                }
                scanner.close();
            }

            if (courseExists) {
                return "Error: Course already assigned to this lecturer!";
            } else {
                // Append course assignment to the file
                FileWriter writer = new FileWriter(fileName, true);
                writer.write("Course Assigned: " + courseCode + "\n");
                writer.close();
                return "Success: Course " + courseCode + " assigned to Lecturer " + lecturerID + " successfully!";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: Unable to assign course!";
        }
    }


    public void attachCourseToAProgramme(String programmeCode, String courseCode, String courseName) {
        // Validate inputs
        if (programmeCode == null || courseCode == null || courseName == null ||
            programmeCode.trim().isEmpty() || courseCode.trim().isEmpty() || courseName.trim().isEmpty()) {
            System.out.println("Error: All fields are required!");
            return;
        }
    
        // Normalize input
        programmeCode = programmeCode.trim().toLowerCase();
        courseCode = courseCode.trim().toUpperCase();
        courseName = courseName.trim();
    
        // Select the correct programme map
        Map<String, String> selectedProgramme;
        switch (programmeCode) {
            case "eb1": selectedProgramme = eb1; break;
            case "cb1": selectedProgramme = cb1; break;
            case "eb2": selectedProgramme = eb2; break;
            case "eb3": selectedProgramme = eb3; break;
            default: selectedProgramme = null;
        }
    
        // Validate programme code
        if (selectedProgramme == null) {
            System.out.println("Error: Invalid Programme Code! Supported codes: eb1, cb1, eb2, eb3.");
            return;
        }
    
        // Check if course already exists
        if (selectedProgramme.containsKey(courseCode)) {
            System.out.println("Warning: Course " + courseCode + " already exists in " + programmeCode);
        } else {
            // Add course to the programme
            selectedProgramme.put(courseCode, courseName);
            System.out.println("Success: Course " + courseCode + " added to " + programmeCode + " successfully!");
        }
    }
    

    // Getters for course lists
    public Map<String, String> eb1Courses() { return this.eb1; }
    public Map<String, String> cb1Courses() { return this.cb1; }
    public Map<String, String> eb2Courses() { return this.eb2; }
    public Map<String, String> eb3Courses() { return this.eb3; }

    // Method to add courses
    private void addCourses() {
        cb1.put("MATH241", "Probability and Statistics");
        
        eb1.put("COSC101", "Introduction to Computer Science");
        eb1.put("COSC102", "Computer Logics");
        eb1.put("COSC110", "Introduction to Networking");
        eb1.put("COSC142", "Techno Entrepreneurship");
        eb1.put("COSC210", "Data Structures and Algorithms");
        eb1.put("COSC220", "Operating Systems");
        eb1.put("COSC230", "Software Engineering");
        eb1.put("COSC240", "Database Management Systems");

        eb3.putAll(eb1); // Copy courses from eb1 to eb3
        eb3.put("COSC250", "Web Development");
        eb3.put("COSC260", "Data Communications and Networks");

        eb2.put("COSC250", "Web Development");
        eb2.put("COSC260", "Data Communications and Networks");
        eb2.put("MATH250", "Discrete Mathematics");
        eb2.put("MATH270", "Calculus III");
        eb2.put("PHY210", "Electronics for Computer Science");

        cb1.put("STA230", "Statistical Methods");
        cb1.put("BIO101", "Introduction to Biology");
        cb1.put("CHEM102", "Organic Chemistry");
        cb1.put("ECON201", "Microeconomics");
        cb1.put("ECON202", "Macroeconomics");
    }
}
