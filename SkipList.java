// Starter code for Project 2: skip lists
// Do not rename the class, or change names/signatures of methods that are declared to be public.


// ask170003

import java.util.Iterator;
import java.util.Random;

public class SkipList<T extends Comparable<? super T>> {
    static final int PossibleLevels = 33;
    Entry head;
    Entry tail;
    int size;

    static class Entry<E> {
        E element;
        Entry[] next;
        Entry prev;

        //Entry constructor
	    public Entry(E x, int lev) {
	        element = x;
	        next = new Entry[lev];
	        // add more code as needed
	    }

        public E getElement() {
            return element;
        }
    }
    
    

    // Skiplist Constructor
    public SkipList() { 
       head = new Entry(null, PossibleLevels);
       tail = new Entry(null, PossibleLevels);
    }


    // Add x to list. If x already exists, reject it. Returns true if new node is added to list
    public boolean add(T x) {
        if (contains(x)== true) return false;
        
        else
        {
         
          
          return true;  
        }
     
	
    }

    // Find smallest element that is greater or equal to x
    public T ceiling(T x) {
        if (contains(x)== true) return x;
        
        else
        {
            
            return x;
        }
	
    }

    // Does list contain x?
    public boolean contains(T x) {
        T node = x;


        
	return false;
    }

    // Return first element of list
    public T first() {
	return null;
    }

    // Find largest element that is less than or equal to x
    public T floor(T x) {
        if (contains(x)== true) return x;
        
        else
        {
            
            return x;
        }
    }

    // Return element at index n of list.  First element is at index 0. Done by Andrew Kolkmeier
    public T get(int n) {
        if(i > size-1){
            throw new NullPointerException();
        }
        else{
            Entry p = head;

            for(int i = 0; i<n; i++){
                p = p.next[0];
            }
            return p;
        }
    }

    // Is the list empty?
    public boolean isEmpty() {
        if (head.getElement() == null) return true;
        else return false;
    }

    // Iterate through the elements of list in sorted order
    public Iterator<T> iterator() {
	return null;
    }

    // Return last element of list
    public T last() {
	return null;
    }

    // Remove x from list.  Removed element is returned. Return null if x not in list
    public T remove(T x) {
	return null;
    }

    // Return the number of elements in the list
    public int size() {
	return 0;
    }

    //Determines entry level when adding to list. Added by Andrew Kolkmeier
    public int chooseLevel(){
        Random r = new Random();
        int lvl = 1 + Integer.numberOfTrailingZeros(r.nextInt());
        return min(lvl, PossibleLevels - 1);
    }
}