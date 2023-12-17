/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.ArrayList;
import adt.ListInterface;
import boundary.TutorialSubsystem;
import entity.tutorialGroup;
import entity.StudentClass;
import testing.StudentInitializer;

/**
 *
 * @author Bryan
 */
public class TutorialControl {

    private ListInterface<tutorialGroup> tutorialList = new ArrayList<>();
    private TutorialSubsystem tutorialUI = new TutorialSubsystem();
    private ListInterface<StudentClass> studentList = new ArrayList<>();

    public TutorialControl() {
        StudentInitializer studentInitializer = new StudentInitializer();
        studentList = studentInitializer.getStudentList();
    }

    public void runTutorialSubsystem() {
        int choice;
        do {
            choice = tutorialUI.getTutorialMenu();
            switch (choice) {
                case 1:
                    addStudentToTutorial();
                    break;
                case 2:
                    removeStudentFromTutorial();
                    break;
                case 3:
                    changeTutorialGroup();
                    break;
                case 4:
                    findAStudent();
                    break;
                case 5:
                    listStudentOfaTutorial();
                    break;
                case 6:
                    filterTutorialGroup();
                    break;
                case 7:
                    mergeTutorialGroup();
                    break;
                case 8:
                    generateSummary();
                    break;
                case 9:
                    addTutorial();
                    break;
                default:
            }
        } while (choice != 0);
    }

    public void addStudentToTutorial() {
    String tutorialClassCode = tutorialUI.searchTutorialToAddStudent();
    int location = getLocationOfTargetTutorial(tutorialClassCode);
    if (tutorialList.getEntry(location) != null) {
        String studentID = tutorialUI.addStudentToTutorial();

        // Add the student to the studentList if not already present
        int studentLocation = getLocationOfTargetStudent(studentID);
        if (studentLocation < 0) {
            studentList.add(new StudentClass(studentID));
        }

        // Now, add the student to the tutorial group
        tutorialList.getEntry(location).addStudentID(new StudentClass(studentID));
    } else {
        tutorialUI.errorTutorialNotFound();
    }
}

    public void removeStudentFromTutorial() {
        String tutorialID = tutorialUI.searchTutorialToRemoveStudent();
        int location = getLocationOfTargetTutorial(tutorialID);
        ListInterface<StudentClass> result = tutorialList.getEntry(location).getStudentID();
        int i = 0;
        while (result.getEntry(i) != null) {
            System.out.print(i + 1 + ". " + result.getEntry(i));
            i++;
        }
        int choice = tutorialUI.removeStudentForTutorial();
        if (choice <= i && choice > 0) {
            choice--;
            StudentClass target = tutorialList.getEntry(location).getStudentID().getEntry(choice);
            tutorialList.getEntry(location).removeStudentFromTutorial(target);
        } else {
            tutorialUI.errorInvalidChoice();
        }
    }

    public void listStudentOfaTutorial() {

    }

    private void changeTutorialGroup() {
        // Get the student ID to change tutorial group
        StudentClass studentID = tutorialUI.getStudentIDForChange();

        // Search for the current tutorial group of the student
        String currentTutorialCode = findCurrentTutorialGroup(studentID);

        if (currentTutorialCode != null) {
            System.out.println("Current Tutorial Group: " + currentTutorialCode);

            // Prompt the user for the new tutorial group
            String newTutorialCode = tutorialUI.getNewTutorialGroup();

            // Check if the new tutorial group exists
            int newLocation = getLocationOfTargetTutorial(newTutorialCode);
            if (newLocation >= 0) {
                // Remove the student from the current tutorial group
                tutorialList.getEntry(getLocationOfTargetTutorial(currentTutorialCode))
                        .removeStudentFromTutorial(studentID);

                // Add the student to the new tutorial group
                tutorialList.getEntry(newLocation).addStudentID(studentID);

                System.out.println("Tutorial Group Changed Successfully.");
            } else {
                tutorialUI.errorTutorialNotFound();
            }
        } else {
            System.out.println("Student not found in any tutorial group.");
        }
    }

 private void findAStudent() {
    StudentClass studentID = tutorialUI.listTutorialForAStudent();
    int found = 0;

    for (int i = 0; i < tutorialList.getNumberOfEntries(); i++) {
        ListInterface<StudentClass> students = tutorialList.getEntry(i).getStudentID();

        if (students.contains(studentID)) {
            found++;

            System.out.println("StudentID " + studentID.getStudentID() +
                    " is in Tutorial Group " + tutorialList.getEntry(i).getTutorialClassCode());
        }
    }

    if (found == 0) {
        System.out.println("StudentID " + studentID.getStudentID() + " not found in any tutorial group.");
    } else {
        System.out.println(found + " result(s) found.");
    }
}

    private void filterTutorialGroup() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void mergeTutorialGroup() {
        // Get input for the first tutorial group
        String tutorialClassCode1 = tutorialUI.searchTutorialToMerge();
        int location1 = getLocationOfTargetTutorial(tutorialClassCode1);

        // Get input for the second tutorial group
        String tutorialClassCode2 = tutorialUI.searchTutorialToMerge();
        int location2 = getLocationOfTargetTutorial(tutorialClassCode2);

        // Check if both tutorial groups exist
        if (location1 >= 0 && location2 >= 0) {
            // Merge the tutorial groups using the union method
            tutorialList.getEntry(location1).getStudentID().union(tutorialList.getEntry(location2).getStudentID());

            // Remove the second tutorial group after merging
            tutorialList.remove(location2);

            System.out.println("Tutorial Groups Merged Successfully.");
        } else {
            tutorialUI.errorTutorialNotFound();
        }
    }

    private void generateSummary() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void addTutorial() {
        tutorialGroup newTutorial = tutorialUI.addNewTutorial();
        tutorialList.add(newTutorial);
    }

    public int getLocationOfTargetTutorial(String target) {
        tutorialGroup targetTutorial = new tutorialGroup(target);
        int location = tutorialList.binarySearch(targetTutorial);
        return location;
    }

    public int getLocationOfTargetStudent(String target) {
        StudentClass targetStudent = new StudentClass(target);
        int location = studentList.binarySearch(targetStudent);
        return location;
    }

    private String findCurrentTutorialGroup(StudentClass studentID) {
        for (int i = 0; i < tutorialList.getNumberOfEntries(); i++) {
            ListInterface<StudentClass> result = tutorialList.getEntry(i).getStudentID();
            if (result.contains(studentID)) {
                return tutorialList.getEntry(i).getTutorialClassCode();
            }
        }
        return null; // Student not found in any tutorial group
    }

    public static void main(String[] args) {
        TutorialControl tutorialControl = new TutorialControl();
        tutorialControl.runTutorialSubsystem();
    }
}
