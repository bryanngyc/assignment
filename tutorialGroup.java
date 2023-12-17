/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.ArrayList;
import adt.ListInterface;

/**
 *
 * @author Bryan
 */
public class tutorialGroup implements Comparable<tutorialGroup> {

    private String tutorialClassName;
    private String tutorialClassCode;
    private ListInterface<StudentClass> studentID = new ArrayList();

    public tutorialGroup(String tutorialClassName, String tutorialClassCode) {
        this.tutorialClassName = tutorialClassName;
        this.tutorialClassCode = tutorialClassCode;
    }

    public tutorialGroup(String tutorialClassCode) {
        this.tutorialClassCode = tutorialClassCode;
    }

    public String getTutorialClassName() {
        return tutorialClassName;
    }

    public void setTutorialClassName(String tutorialClassName) {
        this.tutorialClassName = tutorialClassName;
    }

    public String getTutorialClassCode() {
        return tutorialClassCode;
    }

    public void setTutorialClassCode(String tutorialClassCode) {
        this.tutorialClassCode = tutorialClassCode;
    }

    public ListInterface<StudentClass> getStudentID() {
        return studentID;
    }

    public boolean addStudentID(StudentClass newEntry) {
        return studentID.add(newEntry);
    }

    public boolean removeStudentFromTutorial(StudentClass target) {
        int found = studentID.binarySearch(target);
        if (found < 0) {
            studentID.remove(found);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Tutorial Group Code: ").append(tutorialClassCode).append("\n");
        result.append("Students:\n");

        ListInterface<StudentClass> students = getStudentID();
        for (int i = 0; i < students.getNumberOfEntries(); i++) {
            result.append("  ").append(i + 1).append(". ").append(students.getEntry(i)).append("\n");
        }

        return result.toString();
    }

    @Override
    public int compareTo(tutorialGroup o) {
        return tutorialClassCode.compareTo(o.getTutorialClassCode());
    }
}
