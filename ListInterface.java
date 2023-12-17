package adt;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author USER
 */
public interface ListInterface<T> {
    public boolean add(T newEntry);                                             //Modified
    //Task: Add new item into the list with sorted location
    
    public T remove(int givenPosition);                                         
    //Remove the item at the particular location
    
    public void clear();
    //Remove all the item in the list
    
    public T getEntry(int givenPosition);
    //Getter for item in particular location
    
    public boolean contains(T anEntry);                                         //Modified
    //Same as search
    
    public ListInterface<T> intersect(ListInterface<T> anEntry);                //Added
    //Check Intersection
    
    public ListInterface<T> rangedSearch(T min, T max);                         //Added
    //Search within range
    
    public boolean union(ListInterface<T> anEntry);                             //Added
    //Merge the list into the current array list.
    
    public int getNumberOfEntries();
    //Get the number of total item inserted
    
    public boolean isEmpty();
    // Check if the array list is empty
    
    public boolean isFull();
    // Check if the array list is full
    
    public T lastEntry();                                                       //Added
    // get the last entry of the sorted list
    
    public int linearSearch(T target);
    // Search with one target and datatype
    
    public int binarySearch(T target);                                          
    // Search with one target and datatype
}
