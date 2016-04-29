/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labs;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class StudentQuizScores {

    // java.util.HashMap<String, java.util.ArrayList<Integer>> scoreMap = new java.util.HashMap<>();
    //java.util.ArrayList<Integer> scoreList = null;
    //java.util.HashMap<Integer, Student> studentIdMap = new java.util.HashMap<>();
    java.util.ArrayList<Student> studentIdList = new java.util.ArrayList<>();
    java.util.HashMap<String, Student> nameToStudentMap = new java.util.HashMap<>();

    ConsoleIO consoleIo = new ConsoleIO();

    public StudentQuizScores() {
        loadStudentData();
    }

    public final void loadStudentData() {

        java.util.HashMap<String, java.util.ArrayList<Integer>> scoreMap = Student.loadStudentData();

        populateStudentIdMap(scoreMap);

    }

    public void populateStudentIdMap(java.util.HashMap<String, java.util.ArrayList<Integer>> scoreMap) {
        Set<String> keySet = scoreMap.keySet();

        //int studentId = 100;
        int studentId;

        for (String key : keySet) {

            //studentId++;
            studentId = getNewStudentId();

            Student newStudent = new Student(scoreMap.get(key), studentId, key);
            //studentIdMap.(studentId, newStudent);
            studentIdList.add(studentId, newStudent);

        }

    }

    public int getNewStudentId() {
        return getNewStudentId(studentIdList);
    }

    public int getNewStudentId(java.util.ArrayList<Student> studentIdMap) {
        //Set<Integer> keySet = studentIdMap.keySet();
        Integer newId = 99;

        //studentIdMap.get
        //for (Integer key : keySet) {
        for (Student student : studentIdMap) {
            if (newId <= student.getStudentId()) {
                newId = student.getStudentId() + 1;
            }
        }

        return newId;
    }

    public void run() {
        boolean keepRunning = true;

        while (keepRunning) {

            //Print menu
            String menuString = "=Student Quiz Scores Main Menu=\n"
                    + "1. List Students\n"
                    + "2. Add Students\n"
                    + "3. Remove Students\n"
                    + "4. List Scores For A Student\n"
                    + "5. View Average\n"
                    + "6. View Class Average\n"
                    + "7. Find High Score\n"
                    + "8. Find Low Score\n"
                    + "9. Rename Student\n"
                    + "0. Exit\n";

            //ask for input
            int input = consoleIo.getUserIntInputRange(menuString, 0, 9);

            switch (input) {
                case 1:
                    listStudents();
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    removeStudent();
                    break;
                case 4:
                    listScores();
                    break;
                case 5:
                    viewAverage();
                    break;
                case 6:
                    viewClassAverage();
                    break;
                case 7:
                    findHighestScore();
                    break;
                case 8:
                    findLowestScore();
                    break;
                case 9:
                    //renameStudent();
                    break;
                case 0:
                    keepRunning = false;
                    break;
                default:
                    consoleIo.printStringToConsole("If you are see this message, something is wrong\n"
                            + "Please contact the vender.");
            }

        }

    }

    //public java.util.HashMap<String, Student> listStudents() {
    public java.util.ArrayList<Student> listStudents() {
        return listStudents(studentIdList);

    }

    public java.util.ArrayList<Student> listStudents(java.util.ArrayList<Student> studentIdList) {

        //public java.util.HashMap<String, Student> listStudents(java.util.ArrayList<Student> studentIdList) {
        //public java.util.HashMap<Integer, String> listStudents(java.util.ArrayList<Students> studentIdList) {
        //public java.util.HashMap<Integer, String> listStudents() {
        //java.util.HashMap<String, java.util.ArrayList<Integer>> ;
        //studentIdMap
        //Set<String> keySet = scoreMap.keySet();
        //Set<Integer> keySet = studentIdMap.keySet();
        //java.util.ArrayList<Student> list
        //java.util.HashMap<String, Student> returnedMap = new java.util.HashMap<>();
        // java.util.HashMap<Integer, String> studentNumberMap = new java.util.HashMap<>();
        java.util.ArrayList<Student> studentCounterList = new java.util.ArrayList<>();

        String studentList = "Student Names:\n";
        int counter = 0;
        for (Student student : studentIdList) {
            //for (Integer key : keySet) {
            // for (int counter = 1; counter < studentIdList.size(); counter++) {
            counter++;
            studentList += counter + ") " + student.getStudentName() + " - \t" + student.getStudentId() + "\n";
            studentCounterList.add(counter, student);
            //returnedMap.put(student.getStudentName(), student);
            //reMapNameToStudent(String name, Student student)
            //studentArrayList.add(counter, studentIdList.get(counter));
            //studentNumberMap.put(counter, key);
        }

        consoleIo.printStringToConsole(studentList);
        // return studentArrayList;
        //return studentNumberMap;
        return studentCounterList;
    }

    //public void java.util.ArrayList<Student> studentIdList = new java.util.ArrayList<>();
    public void reBuildNameToStudentMap() {
        reBuildNameToStudentMap(studentIdList);
    }

    //public void reBuildNameToStudentMap(String name, Student student){
    public void reBuildNameToStudentMap(java.util.ArrayList<Student> studentIdList) {

        for (Student student : studentIdList) {

            nameToStudentMap.put(student.getStudentName(), student);
        }

    }

    @Deprecated
    public java.util.HashMap<Integer, String> listStudents(Boolean z) {

        //public java.util.HashMap<Integer, String> listStudents() {
        //java.util.HashMap<String, java.util.ArrayList<Integer>> ;
        //studentIdMap
        //Set<String> keySet = scoreMap.keySet();
//Set<Integer> keySet = studentIdMap.keySet();
        java.util.HashMap<Integer, String> studentNumberMap = new java.util.HashMap<>();
        java.util.ArrayList<Student> studentArrayList = new java.util.ArrayList<>();

        String studentList = "Student Names:\n";
        //int counter = 0;

        //for (Integer key : keySet) {
        for (int counter = 1; counter < studentIdList.size(); counter++) {
            //counter++;
            studentList += counter + ") " + studentIdList.get(counter) + "\n";
            studentArrayList.add(counter, studentIdList.get(counter));
            //studentNumberMap.put(counter, key);
        }

        consoleIo.printStringToConsole(studentList);
        // return studentArrayList;
        return studentNumberMap;
    }

    public void addStudent() {

        String studentName = consoleIo.getUserStringInput("Please Enter A Student's Name:");

        int newStudentId = getNewStudentId();

        consoleIo.printStringToConsole("This student will be ID number: " + newStudentId + "\n");

        java.util.ArrayList<Integer> scoreKeeper = generateAnArrayListOfScores();

        Student newStudent = new Student(scoreKeeper, newStudentId, studentName);

        studentIdList.add(newStudent);
        reBuildNameToStudentMap();

    }

    /**
     * This method no longer works.
     *
     * @deprecated
     */
    @Deprecated
    public void addStudentOld() {
        String studentName = "";
        boolean containsThatName = true;
        while (containsThatName) {
            studentName = consoleIo.getUserStringInput("Please Enter A Students Name:");

            if (studentName.equals("1")) {
                containsThatName = false;
            } else //containsThatName = scoreMap.containsKey(studentName);
            {
                if (containsThatName) {
                    listStudents();
                    consoleIo.printStringToConsole("The Name You Have Entered is Already Taken.\nYou may choose another name or enter '1' to return to the main menu.");
                }
            }

        }

        if (!studentName.equals("1")) {
            java.util.ArrayList<Integer> scoreList = new java.util.ArrayList<>();

            if (scoreList != null) {

                //generateAnArrayListOfScores(scoreList, studentName);
            } else {
                consoleIo.printStringToConsole("Student Name \"" + studentName + "\" could not be found.");
            }
        }
    }

    // public java.util.ArrayList<Integer> generateAnArrayListOfScores(ArrayList<Integer> scoreList, String studentName) {
//      public java.util.ArrayList<Integer> generateAnArrayListOfScores(String studentName) {
    public java.util.ArrayList<Integer> generateAnArrayListOfScores() {
        java.util.ArrayList<Integer> scoreKeeper = new java.util.ArrayList<>();

        boolean keepScoreing = true;
        while (keepScoreing) {
            String scoreInput = consoleIo.getUserStringInput("Please Enter A Quiz Score To Continue or Any Letter to Exit");
            boolean goodScore = false;
            int scoreInt = 0;

            try {
                scoreInt = Integer.parseInt(scoreInput);
                goodScore = true;
            } catch (NumberFormatException numFormEx) {
                // Exit code here.
            }

            if (goodScore) {
                scoreKeeper.add(scoreInt);
            } else {
                keepScoreing = false;
            }
        }

        return scoreKeeper;
    }

//    public void renameStudent() {
//        
//        
//        String studentName = askForNameOrNumber("Please Enter A Student's Name Or Number To Rename:");
//
//        String newName = consoleIo.getUserStringInput("What would you like \"" + studentName + "\" to be changed to?");
//        java.util.ArrayList<Integer> valueList = scoreMap.remove(studentName);
//        scoreMap.put(newName, valueList);
//
//    }
    public void removeStudent() {

        Student student = advancedAskForStudent("Please Enter A Student's Name Or Number To Remove:");

        String confirm = consoleIo.getUserStringInput("You Are About To Remove \"" + student.getStudentName() + "\" \n Please Enter \"Y\" to confirm. ");

        if (confirm.equals("Y")) {

            boolean success = studentIdList.remove(student);
            //studentIdList.r
            //java.util.ArrayList<Integer> valueList = scoreMap.remove(studentName);

            if (success) {
                consoleIo.printStringToConsole("That name was succesfully removed.");

            } else {
                consoleIo.printStringToConsole("Student Name \"" + student.getStudentName() + "\" could not be found.");

            }
        } else {
            consoleIo.printStringToConsole("Removal Aborted, No Action Has Been Performed.");
        }
    }

    public Student advancedAskForStudent() {

        return advancedAskForStudent("\nPlease Enter A Student's Name or Number:");
    }

    public Student advancedAskForStudent(String prompt) {
        //String studentName = askForNameOrNumber("Please Enter A Student's Name Or Number To Remove:");
        int studentNumber = advancedAskForNameOrNumber(prompt);
        Student student = studentIdList.get(studentNumber);
        return student;
    }

    public int advancedAskForNameOrNumber(String prompt) {
        int theNumberChoosen = 0;
        //java.util.HashMap<Integer, String> studentNumberMap = listStudents();
        java.util.ArrayList<Student> studentCounterList = listStudents();

        String studentName = consoleIo.getUserStringInput(prompt);
        boolean isNumber = false;
        int studentNumber = 0;
        try {
            studentNumber = Integer.parseInt(studentName);
            isNumber = true;
        } catch (NumberFormatException numForEx) {
            // studentName = studentNumberMap.get(studentNumber);

        }
        //studentName = studentNumberMap.get(studentNumber);

        if (isNumber == true) {
            if (studentNumber > 99) {
                // This must be the student's Id Number
                //System.out.println("This must be the student's Id Number");
                theNumberChoosen = studentNumber;
            } else {
                // This is probably the students counter Number
                theNumberChoosen = studentCounterList.get(studentNumber).getStudentId();
            }

        } else if (nameToStudentMap.containsKey(studentName)) {
            theNumberChoosen = nameToStudentMap.get(studentName).getStudentId();
        } else {
            consoleIo.printStringToConsole("The System could not find the name \"" + studentName + "\" in the database.");
        }
        return theNumberChoosen;
    }

    /**
     * This method is no longer supported!
     *
     * @param prompt
     * @return
     * @deprecated
     */
    @Deprecated
    public String askForNameOrNumber(String prompt) {
        //java.util.HashMap<Integer, String> studentNumberMap = listStudents();
        String studentName = consoleIo.getUserStringInput(prompt);
        boolean isNumber = false;
        int studentNumber = 0;
        try {
            studentNumber = Integer.parseInt(studentName);
            isNumber = true;
        } catch (NumberFormatException numForEx) {

        }
        if (isNumber == true) {
            //studentName = studentNumberMap.get(studentNumber);
        }
        return studentName;
    }

    public void listScores() {

        //String studentName = askForNameOrNumber("\nPlease Enter A Student's Name or Number:");
        //String studentName = askForNameOrNumber("\nPlease Enter A Student's Name or Number:");
        Student student = advancedAskForStudent("\nPlease Enter A Student's Name or Number:");

        //java.util.ArrayList<Integer> scoreList = scoreMap.get(studentName);
        java.util.ArrayList<Integer> scoreList = student.getQuizScores();

        if (scoreList != null) {

            int scoreCounter = 0;
            String scoreString = "Scores for " + student.getStudentName() + ":\n";
            for (Integer score : scoreList) {
                scoreString += (++scoreCounter) + ") " + score + "\n";
            }

            consoleIo.printStringToConsole(scoreString);

        } else {
            consoleIo.printStringToConsole("Data for " + student.getStudentName() + " - " + student.getStudentId() + "\" could not be found.");
        }

    }

    public void viewAverage() {

        //String studentName = askForNameOrNumber("Please Enter A Student's Name:");
        Student student = advancedAskForStudent();

        //java.util.ArrayList<Integer> scoreList = scoreMap.get(studentName);
        //java.util.ArrayList<Integer> scoreList = new java.util.ArrayList<>();
        java.util.ArrayList<Integer> scoreList = student.getQuizScores();

        if (scoreList != null) {

            float averageScore = arrayListAverager(scoreList);
            consoleIo.printStringToConsole("Average Quiz Score For " + student.getStudentName() + ":\n" + getRoundedFloat(averageScore) + " points");

        } else {
            consoleIo.printStringToConsole("Data for " + student.getStudentName() + " - " + student.getStudentId() + "\" could not be found.");
        }

    }

    public float arrayListAverager(ArrayList<Integer> scoreList) {
        int scoreCounter = 0;
        int totalPoints = 0;
        for (Integer score : scoreList) {
            totalPoints += score;
            scoreCounter++;
        }
        float averageScore = totalPoints / scoreCounter;
        return averageScore;
    }

    public void viewClassAverage() {
//        Set<String> keySet = scoreMap.keySet();
        java.util.ArrayList<Integer> totalClassScore = new java.util.ArrayList<>();

        //for (String key : keySet) {
        for (Student student : studentIdList) {
            totalClassScore.addAll(student.getQuizScores());
        }

        float classAverage = arrayListAverager(totalClassScore);

        String classStringAverage = getRoundedFloat(classAverage);

        consoleIo.printStringToConsole("The Class Average is " + classStringAverage + " points.");

    }

    public String getRoundedFloat(float classAverage) {
        java.text.DecimalFormat df = new java.text.DecimalFormat();
        df.setMaximumFractionDigits(2);
        String classStringAverage = df.format(classAverage);
        return classStringAverage;
    }

    public void findLowestScore() {

        //Set<String> keySet = scoreMap.keySet();
        //java.util.ArrayList<String> bottomStudents = new java.util.ArrayList<>();
        Integer bottomScore = determineTheLowestScore();

        //keySet);
        java.util.ArrayList<Student> bottomStudents = determineWhoHasThatScore(bottomScore);

        //java.util.HashMap<String, Integer> topMap = countOccurancesOfScore(topStudents, highestScore);
        String classLeaders = "The highest score was: " + bottomScore + "\n"
                + "Students with this score and number of quizzes for each\n";
        for (Student student : bottomStudents) {
            classLeaders += student.getStudentName() + " - \t" + enumerateOccurancesOfScore(bottomScore, student) + "\n";
        }

        consoleIo.printStringToConsole(classLeaders);

    }

    public void findHighestScore() {

        //Set<String> keySet = scoreMap.keySet();
        //java.util.ArrayList<String> topStudents = new java.util.ArrayList<>();
        Integer highestScore = determineTheHighestScore();
        //keySet);

        java.util.ArrayList<Student> topStudents = determineWhoHasThatScore(highestScore);

        //java.util.HashMap<String, Integer> topMap = countOccurancesOfScore(topStudents, highestScore);
        String classLeaders = "The highest score was: " + highestScore + "\n"
                + "Students with this score and number of quizzes for each\n";
        for (Student student : topStudents) {
            classLeaders += student.getStudentName() + " - \t" + enumerateOccurancesOfScore(highestScore, student) + "\n";
        }

        consoleIo.printStringToConsole(classLeaders);

    }

    
    
    //public java.util.HashMap<Student, Integer> countOccurancesOfScore(Integer selectScore, ArrayList<Student> Students) {
    
    
    
    
    
    /** This method counts the occurences of a select score across the entire class.
     * 
     * @param selectScore
     * @return 
     */
    public int countOccurancesOfScore(Integer selectScore) {
        return countOccurancesOfScore(selectScore, studentIdList);
    }

    /**
     * This method counts occurances across an ArrayList of type Student.
     *
     * @param selectScore
     * @param students
     * @return
     */
    public int countOccurancesOfScore(Integer selectScore, ArrayList<Student> students) {
        //java.util.HashMap<String, Integer> bottomMap = new java.util.HashMap<>();
        int occuranceCounter = 0;

        for (Student student : students) {
            //int occuranceCounter = 0;
            java.util.ArrayList<Integer> tempScore = student.getQuizScores();
            //scoreMap.get(studentName);

            for (Integer score : tempScore) {
                if (Objects.equals(score, selectScore)) {
                    occuranceCounter++;
                }
            }

            //bottomMap.put(studentName, occuranceCounter);
        }

        return occuranceCounter;

    }

    /**
     * This is the method that counts occurances for one student.
     *
     * @param selectScore
     * @param student
     * @return
     */
    public int enumerateOccurancesOfScore(Integer selectScore, Student student) {
        //java.util.HashMap<String, Integer> bottomMap = new java.util.HashMap<>();

        //for (Student student : Students) {
        int occuranceCounter = 0;
        //java.util.ArrayList<Integer> tempScore = scoreMap.get(studentName);

        for (Integer score : student.getQuizScores()) {
            if (Objects.equals(score, selectScore)) {
                occuranceCounter++;
            }
        }

        //bottomMap.put(studentName, occuranceCounter);
        //}
        return occuranceCounter;

    }

    @Deprecated
    public java.util.HashMap<String, Integer> countOccurancesOfScore(ArrayList<String> topStudents, Integer highestScore) {
        java.util.HashMap<String, Integer> bottomMap = new java.util.HashMap<>();

        for (String studentName : topStudents) {
            int occuranceCounter = 0;
            java.util.ArrayList<Integer> tempScore = null;
            //scoreMap.get(studentName);

            for (Integer score : tempScore) {
                if (Objects.equals(score, highestScore)) {
                    occuranceCounter++;
                }
            }

            bottomMap.put(studentName, occuranceCounter);
        }

        return bottomMap;

    }

    public ArrayList<Student> determineWhoHasThatScore(int inputScore) {
        return determineWhoHasThatScore(studentIdList, inputScore);
    }

    public ArrayList<Student> determineWhoHasThatScore(ArrayList<Student> students, int inputScore) {
        ArrayList<Student> selectStudents = new ArrayList<>();

// Iterate through Student
        for (Student student : students) {

            java.util.ArrayList<Integer> tempScore = student.getQuizScores();
            boolean isTiedForLeader = false;

            for (Integer score : tempScore) {
                if (Objects.equals(score, inputScore)) {
                    isTiedForLeader = true;
                }
            }

            if (isTiedForLeader) {
                selectStudents.add(student);
            }
        }
        return selectStudents;
    }

    @Deprecated
    public void determineWhoHasThatScore(Set<String> keySet, Integer highestScore, ArrayList<String> topStudents) {
        // Iterate through Student
        for (String key : keySet) {

            java.util.ArrayList<Integer> tempScore = null; //scoreMap.get(key);
            boolean isTiedForLeader = false;

            for (Integer score : tempScore) {
                if (Objects.equals(score, highestScore)) {
                    isTiedForLeader = true;
                }
            }

            if (isTiedForLeader) {
                topStudents.add(key);
            }
        }
    }

    @Deprecated
    public Integer determineTheHighestScore(Set<String> keySet) {
        // Find the highest Score
        Integer highestScore = 0;
        for (String key : keySet) {

            java.util.ArrayList<Integer> tempScore = null; //scoreMap.get(key);

            for (Integer score : tempScore) {
                if (score > highestScore) {
                    highestScore = score;
                }
            }
        }
        return highestScore;
    }

    public Integer determineTheLowestScore() {

        return determineTheLowestScore(studentIdList);
    }

    public Integer determineTheLowestScore(java.util.ArrayList<Student> studentIdList) {
        // Find the lowest Score
        Integer lowestScore = 0;
        //for (String key : keySet) {

        for (Student student : studentIdList) {

            java.util.ArrayList<Integer> tempScore = student.getQuizScores();

            for (Integer score : tempScore) {
                if (score < lowestScore) {
                    lowestScore = score;
                }
            }
        }
        return lowestScore;
    }

    public Integer determineTheHighestScore() {
        return determineTheHighestScore(studentIdList);
    }

    public Integer determineTheHighestScore(java.util.ArrayList<Student> studentIdList) {
        // Find the lowest Score
        Integer highestScore = 0;
        //for (String key : keySet) {

        for (Student student : studentIdList) {

            java.util.ArrayList<Integer> tempScore = student.getQuizScores();

            for (Integer score : tempScore) {
                if (score < highestScore) {
                    highestScore = score;
                }
            }
        }
        return highestScore;
    }

    @Deprecated
    public Integer determineTheLowestScore(Set<String> keySet) {
        // Find the lowest Score
        Integer lowestScore = 0;
        for (String key : keySet) {

            java.util.ArrayList<Integer> tempScore = null;//scoreMap.get(key);

            for (Integer score : tempScore) {
                if (score < lowestScore) {
                    lowestScore = score;
                }
            }
        }
        return lowestScore;
    }

}
