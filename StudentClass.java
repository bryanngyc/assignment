/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Bryan
 */
public class StudentClass implements Serializable, Comparable<StudentClass> {
    private String studentID;
    
    public StudentClass(String studentID){
        this.studentID = studentID;
    }
    
    public String getStudentID(){
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    
    @Override
    public int compareTo(StudentClass o){
        return studentID.compareTo(o.getStudentID());
    }
    
    @Override
    public String toString(){
        return studentID;
    }
    
    
}
    

