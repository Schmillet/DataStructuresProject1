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
	int level;

        //Entry constructor
	    public Entry(E x, int lev) {
	        element = x;
	        next = new Entry[lev];
		level = lev;
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
        if (contains(x)) return false;// x exists in list, reject
   
        else  // add if not in list
        {
            int lvl = chooseLevel();
            Entry<T> entry = new Entry(x,lvl);
            
            for (int i = 0; i < lvl; i++)  // for all levels new entry is in, add it to list
            {
                // not the first element in list
                if (pred[i] != null)
                {
                    entry.next[i] = pred[i].next[i]; // new entry points to value after pred
                    pred[i].next[i] = entry;         // pred now points to new entry
                    entry.next[i].prev = entry;      // next now points back to new entry
                }
            }
                
                // not last element in the list
                if (entry.next[0] != null)
                {
                    entry.next[0].prev = entry;  // next points back to new entry
                    entry.prev = pred[0];        // new entry points back to pred
                }
            
                // last element in list
                else 
                {
                    entry.prev = pred[0];    // new entry points back to pred
                    tail.prev = entry;       // tail now points back to new entry
                }
            
             
            
          size++;
          return true;
	  }  
      
	
    }

    // Find smallest element that is greater or equal to x
    public T ceiling(T x) {
        if (contains(x)) return x; // returns x if x is in list
        
        else
        {
            findPred(x); // finds predecessor of x 
            return (T)pred[0].next[0].element; //returns element after pred
        }
	
	
    }

    // Does list contain x?
    public boolean contains(T x) {
        findPred(x); // find predecessor of x
        
        // if pred is not last element, and next element equals x, return true
        return (pred[0].next[0] != tail && ((T)pred[0].next[0].element).compareTo(x) == 0);

    }

    // Return first element of list
    public T first() {
        if (head.next[0]== null) return null;
        
        else{
            return (T)head.next[0].element;
        }

    }

    // Find largest element that is less than or equal to x
    public T floor(T x) {
        if (contains(x)) return x; // element equal to x
        
        else
        {
            findPred(x);		 // finds predecessor of x, even when x isn't in the list
            return pred[0].element;	 // return element before x
        }
    }

    // Return element at index n of list.  First element is at index 0. Done by Andrew Kolkmeier
    public T get(int n) {
        if(n > size-1 || n < 0){
            throw new NullPointerException();
        }
        else{
            Entry<T> p = head;

            for(int i = 0; i<n; i++){
                p = p.next[0];
            }
            return (T)p.next[0].element;
        }
    }

    // Is the list empty? 
    public boolean isEmpty() {
        return(size==0);
    }

    // Iterate through the elements of list in sorted order
    public Iterator<T> iterator() {
	return null;
    }

    // Return last element of list
    public T last() {
        if (tail.prev == null) return null;
        else return (T)tail.prev.element;
    }

    // Remove x from list.  Removed element is returned. Return null if x not in list
    public T remove(T x) {
	if (!contains(x)) return null; // x not in list
 
        else {
            
            Entry entry = pred[0].next[0];
            int lvl = entry.level;
            for (int i = 0; i < lvl; i++)  // for all levels new entry is in, add it to list
            {
                pred[i].next[i] = entry.next[i]; // pred points to element after entry  
            }
            
            // if not the last element in the list
            if (entry.next[0] != null) 
            {
		entry.next[0].prev = pred[0];
	    } 
            
            else 
            {
              tail.prev = pred[0];
	    }
            
        size--;
        return (T)entry.element;
            
        }
         
    }

    // Return the number of elements in the list. 
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
