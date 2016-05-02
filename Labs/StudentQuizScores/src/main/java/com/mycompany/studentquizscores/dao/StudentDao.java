/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.studentquizscores.dao;

import com.mycompany.studentquizscores.dto.Student;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class StudentDao {

    // CRUD
    private List<Student> students = new ArrayList();
    private int nextId = 1;
    private File studentsDataFile = new File("studentsDataFile.txt");

    public StudentDao() {
        students = decode();
        if (students == null ){
            students = new ArrayList();
            System.out.println("The list was empty, making a new one.");
        }
        nextId = determineNextId();

    }

    public Student create(Student student) {
        student.setId(nextId);
        nextId++;
        students.add(student);

        encode();

        return student;
    }

    public Student get(Integer id) {

        for (Student myStudent : students) {
            if (myStudent.getId() == id) {
                return myStudent;
            }

        }
        return null;
    }

    public void update(Student student) {

        for (Student myStudent : students) {

            if (myStudent.getId() == student.getId()) {
                students.remove(myStudent);
                students.add(student);
            }

        }

        encode();

    }

    public void delete(Student student) {

        Student found = null;

        for (Student myStudent : students) {

            if (myStudent.getId() == student.getId()) {
                found = myStudent;
                break;
            }

        }

        if (found != null) {
            students.remove(found);
        }

        encode();

    }

    public int size() {
        return students.size();
    }

    public String getNameAndNumber() {
        String nameAndNumber = "";

        for (Student myStudent : students) {
            nameAndNumber += myStudent.getId() + "\t" + myStudent.getStudentName() + "\n";

        }

        return nameAndNumber;
    }

    private int determineNextId() {
        int highestId = 1;

        for (Student student : students) {
            if (highestId < student.getId()) {
                highestId = student.getId();
            }
        }

        highestId++;
        return highestId;

    }

    private void encode() {

        final String TOKEN = "::";

        try {
            PrintWriter out = new PrintWriter(new FileWriter(studentsDataFile));

            for (Student myStudent : students) {
                out.print(myStudent.getId());
                out.print(TOKEN);
                out.print(myStudent.getStudentName());
                out.print(TOKEN);
                out.print(myStudent.getStudentName());
                out.println("");
            }

            out.flush();
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private List<Student> decode() {

        List<Student> studentList = new ArrayList<>();

        final String TOKEN = "::";

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(studentsDataFile)));

            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                Student student = new Student();

                int id = Integer.parseInt(stringParts[0]);
                student.setId(id);

                student.setStudentName(fixNull(stringParts[1]));

//                String lastName = stringParts[2];
//
//                lastName = fixNull(lastName);
//
//                student.setLastName(lastName);

                studentList.add(student);

            }

            sc.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return studentList;
    }

    private String fixNull(String lastName) {
        if (lastName.equalsIgnoreCase("null")) {
            lastName = null;
        }
        return lastName;
    }

}
