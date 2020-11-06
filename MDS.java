package com.mycompany.assignment4;

import java.util.HashMap; 
import java.util.*;

// If you want to create additional classes, place them in this file as subclasses of MDS

public class MDS {
    
       // class to create entries in the search
        public class Entry {
            private long id;
            private int price;
            private TreeSet<Long> description;
            
            // Entry constructor
            public Entry(long id, TreeSet<Long> description, int price){
                this.id = id;
                this.description = description;
                this.price = price;
            }
        
        }
   
    // Constructors
    public MDS() {
        TreeMap <Long, Entry> treeMap = new TreeMap<>();        // id/entry map
        HashMap<Long, TreeSet<Long>> hashMap = new HashMap<>(); // id/description map
   
    }
    /* Public methods of MDS. Do not change their signatures.
       __________________________________________________________________
       a. Insert(id,price,list): insert a new item whose description is given
       in the list.  If an entry with the same id already exists, then its
       description and price are replaced by the new values, unless list
       is null or empty, in which case, just the price is updated. 
       Returns 1 if the item is new, and 0 otherwise.
    */
    public int insert(int id, int price, java.util.List<Integer> list) {
	return 0;
    }

    // b. Find(id): return price of item with given id (or 0, if not found).
    public int find(int id) {
	return 0;
    }

    /* 
       c. Delete(id): delete item from storage.  Returns the sum of the
       ints that are in the description of the item deleted,
       or 0, if such an id did not exist.
    */
    public int delete(int id) {
	return 0;
    }

    /* 
       d. FindMinPrice(n): given an integer, find items whose description
       contains that number (exact match with one of the ints in the
       item's description), and return lowest price of those items.
       Return 0 if there is no such item.
    */
    public int findMinPrice(int n) {
	return 0;
    }

    /* 
       e. FindMaxPrice(n): given an integer, find items whose description
       contains that number, and return highest price of those items.
       Return 0 if there is no such item.
    */
    public int findMaxPrice(int n) {
	return 0;
    }

    /* 
       f. FindPriceRange(n,low,high): given int n, find the number
       of items whose description contains n, and in addition,
       their prices fall within the given range, [low, high].
    */
    public int findPriceRange(int n, int low, int high) {
	return 0;
    }
    
    /*
     increase the price of every product, whose id is in
     the range [l,h] by r%. Discard any fractional pennies in the new prices
     of items. Note that you are truncating, not rounding.
     Returns the sum of the net increases of the prices.

    */
    public int priceHike(int low, int high, int rate){
        return 0;
    }

    /*
      g. RemoveNames(id, list): Remove elements of list from the description of id.
      It is possible that some of the items in the list are not in the
      id's description.  Return the sum of the numbers that are actually
      deleted from the description of id.  Return 0 if there is no such id.
    */
    public int removeNames(int id, java.util.List<Integer> list) {
	return 0;
    }
}
