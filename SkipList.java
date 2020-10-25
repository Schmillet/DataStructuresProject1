// Starter code for Project 2: skip lists
// Do not rename the class, or change names/signatures of methods that are declared to be public.


// ask170003
// jrr170005

import java.util.Iterator;
import java.util.Random;
import java.util.NoSuchElementException;

public class SkipList<T extends Comparable<? super T>> {
    static final int PossibleLevels = 33;
    Entry <T> head, tail;
    Entry <T>[] pred;
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
       pred = new Entry[PossibleLevels];
       size = 0;
	 
       for (int i = 0; i < PossibleLevels; i++) // for all levels, head points to tail
       {
           head.next[i] = tail;
       }
	   
    }


    // Add x to list. If x already exists, reject it. Returns true if new node is added to list
    public boolean add(T x) {
        if (contains(x)) return false; // x exists in list, reject
        
        else  // add if not in list
        {
            int lvl = chooseLevel();
            Entry<T> entry = new Entry(x,lvl);
            findPred(x);
            
            for (int i = 0; i < lvl; i++)  // for all levels new entry is in, add it to list
            {
                entry.next[i] = pred[i].next[i]; //new entry points to value after pred
                pred[i].next[i] = entry;         // pred now points to new entry
                
                
            }
            
            size++;
          
          return true;  
        }
     
	
    }

    // Find smallest element that is greater or equal to x
    public T ceiling(T x) {
        if (contains(x)) return x;
        
        else
        {
            
            return x;
        }
	
    }

    // Does list contain x?
    public boolean contains(T x) {
	return false;
    }

    // Return first element of list
    public T first() {
	if (isEmpty()) throw new NoSuchElementException();
        
        else {
            return (T)head.next[0].element;
        }

    }

    // Find largest element that is less than or equal to x
    public T floor(T x) {
        if (contains(x)) return x;
        
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

    // Is the list empty? Done by Jade Rodriguez.
    public boolean isEmpty() {
        if (size == 0) return true;
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

    // Return the number of elements in the list. Done by Jade Rodriguez
    public int size() {
	return size;
    }

    //Determines entry level when adding to list. Added by Andrew Kolkmeier
    public int chooseLevel(){
        Random r = new Random();
        int lvl = 1 + Integer.numberOfTrailingZeros(r.nextInt());
        return Math.min(lvl, PossibleLevels - 1);
    }
    
    //Helper method to find predecessor of x at each level. Added by Jade Rodriguez
    public void findPred(T x){
        Entry <T> p = head;
        for (int i = PossibleLevels-1; i >= 0; i--)
        {
	    // find largest element less than x
            while (p.next[i].element != null && ((T)p.next[i].element).compareTo(x) < 0){
                p = p.next[i];
            }
	    //store value as pred;
            pred[i]= p;
        }
    }
    
	
}
