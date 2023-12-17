package testing;

import adt.ArrayList;
import entity.StudentClass;
import adt.ListInterface;

public class StudentInitializer {
    private ListInterface<StudentClass> studentList = new ArrayList<>();

    public StudentInitializer() {
        // Adding sample student data
        studentList.add(new StudentClass("22WMR14068"));
        studentList.add(new StudentClass("22WMR14069"));
        studentList.add(new StudentClass("22WMR14070"));
        studentList.add(new StudentClass("22WMR14071"));
        studentList.add(new StudentClass("22WMR14072"));
    }

    public ListInterface<StudentClass> getStudentList() {
        return studentList;
    }

    public static void main(String[] args) {
        StudentInitializer studentInitializer = new StudentInitializer();
        ListInterface<StudentClass> students = studentInitializer.getStudentList();

        // Printing the student list
        System.out.println("Student List:");
        for (int i = 0; i < students.getNumberOfEntries(); i++) {
            System.out.println(students.getEntry(i));
        }
    }
}