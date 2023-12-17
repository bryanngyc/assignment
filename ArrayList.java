package adt;

/**
 * @author Frank M. Carrano
 * @version 2.0
 */

import java.io.Serializable;

public class ArrayList<T extends Comparable<T>> implements ListInterface<T>, Serializable {//Only stores the object which implements the EntityInterface

  private T [] array;
  private int numberOfEntries;
  private int lastEntry;
  private static final int DEFAULT_CAPACITY = 5;

  public ArrayList() {
    this(DEFAULT_CAPACITY);
  }

  public ArrayList(int initialCapacity) {
    numberOfEntries = 0;
    array = (T[]) new Comparable[initialCapacity];
  }
  
  @Override
    public boolean add(T newEntry) {
    if (isArrayFull()) {
        doubleArray();
    }

    int i = binarySearch(newEntry);
    if (i < 0) {
        i = -(i); // Adjusting the index to insert at the correct position
    }

    if (i >= 0 && i < numberOfEntries && newEntry.compareTo(array[i]) == 0) {
        // Entry already exists in the list, so don't add a duplicate
        return false;
    }

    makeRoom(i); // Adjusting to insert at index i
    array[i] = newEntry;
    numberOfEntries++;
    lastEntry = i;
    return true;
}

  @Override
  public T remove(int givenPosition) {
    T result = null;

    if ((givenPosition >= 0) && (givenPosition <= numberOfEntries)) {
      result = array[givenPosition];

      if (givenPosition < numberOfEntries) {
        removeGap(givenPosition);
      }

      numberOfEntries--;
    }

    return result;
  }

  @Override
  public void clear() {
    numberOfEntries = 0;
    //Q: array = (T[]) new Object[DEFAULT_CAPACITY];
  }

  @Override
  public T getEntry(int givenPosition) {
    T result = null;

    if ((givenPosition >= 0) && (givenPosition <= numberOfEntries)) {
      result = array[givenPosition];
    }

    return result;
  }

  @Override
  public boolean contains(T anEntry) {
    int result = binarySearch(anEntry);

    if (result > 0) {
        return true; // Element found
    }else if (result == 0 && array[result] != null){
        if(array[result].compareTo(anEntry) == 0) {
            return true;
        }
        return false;
    }
    return false; // Element not found
}

  @Override
  public ListInterface<T> intersect(ListInterface<T> anEntry) {
      ListInterface<T> result = new ArrayList<>();
      for(int i = 0; i < numberOfEntries; i++){
          if (anEntry.contains(array[i])){
              result.add(array[i]);
          }
      }
      return result;
  }

    @Override
    public ListInterface<T> rangedSearch(T min, T max) {
        int minLocation = binarySearch(min);        //will be negative
        if (minLocation < 0){
            minLocation = -(minLocation);
        }
        int maxLocation = binarySearch(max);
        if (maxLocation < 0){
            maxLocation = -(maxLocation);
            if (maxLocation == numberOfEntries){
                maxLocation--;
            }
        }
        ListInterface<T> result = new ArrayList<>();
        for(int i = minLocation ; i < maxLocation + 1; i++){
            result.add(array[i]);
        }
        return result;
    }

    @Override
    public boolean union(ListInterface<T> anEntry) {
        if (!anEntry.isEmpty()){
            for(int i = 0; i < anEntry.getNumberOfEntries(); i++){
                add(anEntry.getEntry(i));
            }
            return true;
        }else{
            return false;
        }
    }

  @Override
  public int getNumberOfEntries() {
    return numberOfEntries;
  }

  @Override
  public boolean isEmpty() {
    return numberOfEntries == 0;
  }

  @Override
  public boolean isFull() {
    return false;
  }
  
  @Override
  public int linearSearch(T target) {
    int result = -1;
    int i = 0;
    while (i < numberOfEntries && array[i].compareTo(target) < 0){
        i++;
    }
    if (array[i].compareTo(target) == 0){
        result = i;
    }
    return result;
  }
  
  @Override
  public int binarySearch(T target){
    int first = 0;
    int last = numberOfEntries -1;
    while (first <= last){
        int mid = (first + last) / 2;
        if (target.compareTo(array[mid]) == 0){
            return mid;
        }else if (target.compareTo(array[mid]) < 0){
            last = mid - 1;
        }else{
            first = mid + 1;
        }
    }
    return -(last + 1);
  }

  private void doubleArray() {
    T[] oldArray = array;
    array = (T[]) new Comparable[oldArray.length * 2];
    System.arraycopy(oldArray, 0, array, 0, oldArray.length);
  }

  private boolean isArrayFull() {
    return numberOfEntries == array.length;
  }

  @Override
  public String toString() {
    String outputStr = "";
    for (int index = 0; index < numberOfEntries; ++index) {
      outputStr += array[index] + "\n";
    }

    return outputStr;
  }

  private void makeRoom(int newPosition) {
    if (newPosition < 0 || newPosition > numberOfEntries) {
        throw new IllegalArgumentException("Invalid new position");
    }

    if (isArrayFull()) {
        doubleArray();
    }

    for (int index = numberOfEntries - 1; index >= newPosition; index--) {
        array[index + 1] = array[index];
    }
}

  private void removeGap(int givenPosition) {
    int removedIndex = givenPosition;
    int lastIndex = numberOfEntries;

    for (int index = removedIndex; index < lastIndex; index++) {
      array[index] = array[index + 1];
    }
  }

    @Override
    public T lastEntry() {
        return array[lastEntry];
    }
}
