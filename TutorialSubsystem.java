/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import entity.StudentClass;
import entity.tutorialGroup;
import java.util.Scanner;

/**
 *
 * @author Bryan
 */
public class TutorialSubsystem {

    Scanner scanner = new Scanner(System.in);

    public int getTutorialMenu() {

        int choice = 0;

        System.out.println("==Tutorial Menu==");
        System.out.println("1. Add a student");
        System.out.println("2. Remove a student");
        System.out.println("3. Change tutorial group for a student");
        System.out.println("4. Find a student");
        System.out.println("5. List all student in a tutorial group");
        System.out.println("6. Filter tutorial group");
        System.out.println("7. Merge a tutorial group");
        System.out.println("8. Generate summary report");
        System.out.println("9. Add a tutorial group");
        System.out.println("10. Quit");
        System.out.print("Enter choice: ");

        while (choice < 1 || choice > 9) {
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 1 || choice > 9) {
                System.out.println(choice + " not found. Please choose ony from 1 - 9");
            }

        }
        return choice;

    }

    public String inputTutorialClassName() {
        System.out.println("Enter tutorial class name");
        String input = scanner.nextLine();
        return input;
    }
    
    public String inputTutorialClassCode(){
        System.out.println("Enter class code: ");
        String input = scanner.nextLine();
        return input;
    }
    
    public String inputStudentId(){
        System.out.println("Enter student ID:");
        String input = scanner.nextLine();
        return input;
    }
    
    public int inputChoice(){
        System.out.println("Choose your options");
        int input = -1;
        try{
            input = scanner.nextInt();
            scanner.nextLine();
        }catch(Exception e){
            
        }return input;
    }
    
    public String searchTutorialToMerge(){
        String tutorialClassCode = inputTutorialClassCode();
        return tutorialClassCode;
    }
    
    public tutorialGroup addNewTutorial(){
        String tutorialClassCode = inputTutorialClassCode();
        String tutorialClassName = inputTutorialClassName();
        return new tutorialGroup(tutorialClassCode,tutorialClassName);
    }
    
    public String searchTutorialToAddStudent(){
        String tutorialClassCode = inputTutorialClassCode();
        return tutorialClassCode;
    }
    
    public String addStudentToTutorial(){
        String studentID = inputStudentId();
        return studentID;
    }
    
    
    public void errorTutorialNotFound(){
        System.out.println("Cannot find the tutorial");
    }
    
    public String searchTutorialToRemoveStudent(){
        String tutorialClassCode = inputTutorialClassCode();
        return tutorialClassCode;
    }
    
     public String searchTutorialToDisplayStudents(){
        String tutorialClassCode = inputTutorialClassCode();
        return tutorialClassCode;
    }
    
    public int removeStudentForTutorial(){
        int choice = inputChoice();
        return choice;
    }
    
    public StudentClass listTutorialForAStudent(){
        String studentID = inputStudentId();
        return new StudentClass(studentID);
    }
    
    public String getNewTutorialGroup(){
        String tutorialID = inputTutorialClassCode();
        return tutorialID;
    }
    
    public String searchStudentByID(){
        String studentID = inputStudentId();
        return studentID;
    }
    
    public StudentClass getStudentIDForChange(){
        String studentID = inputStudentId();
        return new StudentClass(studentID);
    }
    
    public void errorInvalidChoice(){
        System.out.println("Invalid choice");
    }
      
}
