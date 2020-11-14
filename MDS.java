import java.util.HashMap; 
import java.util.*;

//Andrew Kolkmeier ask170003
//Jade Rodriguez jrr170005

// If you want to create additional classes, place them in this file as subclasses of MDS

public class MDS {
    
       // class to create entries in the search
        public class Entry {
            private int id;
            private int price;
            private TreeSet<Integer> description;
            
            // Entry constructor
            public Entry(int id, TreeSet<Integer> description, int price){
                this.id = id;
                this.description = description;
                this.price = price;
            }
        
        }
   TreeMap <Integer, Entry> treeMap;
   HashMap<Integer, TreeSet<Integer>> hashMap;
   
    // Constructors
    public MDS() {
        treeMap = new TreeMap<>(); // id/entry map
        hashMap = new HashMap<>(); // id/description map
   
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
        
        // id not found in treemap
        if (treeMap.containsKey(id) == false)
        {
            //create new entry and put entry in tree map according to id
            Entry newEntry = new Entry(id, new TreeSet<>(list), price);
            treeMap.put(id, newEntry);
            
            // iterate through description list
            for (int descId : list)
                
                // hashmap has descId, replace it
                if (hashMap.containsKey(descId))
                {
                    TreeSet<Integer> oldSet = hashMap.get(descId);              // put exsisting id in oldSet
                    oldSet.add(id);                                             
                    hashMap.replace(descId, oldSet);                            // update it
                    
                }
                //hashmap does not have descId, add it
                else
                {
                    TreeSet<Integer> newSet = new TreeSet<>();
                    newSet.add(id);
                    hashMap.put(descId, newSet);
                }
            
           return 1;
        }
        
        // id in treemap
        else
        {
            // update price only if list is empty
            if (list.isEmpty())
            {
 
                Entry newEntry = treeMap.get(id);
                newEntry.price = price;
                treeMap.replace(id, newEntry);
            }
            // list is not empty, update both price and description
            else
            {
                Entry newEntry = new Entry (id, new TreeSet<>(list), price);    // create new entry
                Entry oldDesc = treeMap.get(id);                                // get existing entry
                TreeSet<Integer> oldSet = oldDesc.description;                  
                treeMap.replace(id,newEntry);                                   // replace entry in treeset
                hashMap.remove(id, oldDesc);                                    // remoive old entry in hashmap and add new one
                hashMap.put(id,new TreeSet<>(list));
                
            }
        }
	return 0;
    }

    // b. Find(id): return price of item with given id (or 0, if not found).
    public int find(int id) {
        Entry entry = treeMap.get(id);
        if (entry != null) return entry.price;  // entry found, return price
        else return 0;                          // entry does not exist
    }

    /* 
       c. Delete(id): delete item from storage.  Returns the sum of the
       ints that are in the description of the item deleted,
       or 0, if such an id did not exist.
    */
    public int delete(int id) {
	Entry entry = treeMap.get(id);
        int descSum = 0;
        
        if (entry == null) return 0; // entry does not exist
        
        else
        { 
            for (long i : entry.description){
                descSum += i;       // add desription ints together
            }
            treeMap.remove(id);     // removes entry from tree
            return descSum;         // returns sum of description ints
        }
    }

    /* 
       d. FindMinPrice(n): given an integer, find items whose description
       contains that number (exact match with one of the ints in the
       item's description), and return lowest price of those items.
       Return 0 if there is no such item.
    */
    public int findMinPrice(int n) {
        if(hashMap.containsKey(n)){
            TreeSet<Integer> compSet = hashMap.get(n);
            Entry initEntry = treeMap.get(compSet.first());
            int minPrice = initEntry.price;

            for(int id : compSet)
            {
                Entry entry = treeMap.get(id);
                if(entry.price < minPrice)
                {
                    minPrice = entry.price;
                }
            }
            return minPrice;
        }
        else{
            return 0;
        }
    }

    /* 
       e. FindMaxPrice(n): given an integer, find items whose description
       contains that number, and return highest price of those items.
       Return 0 if there is no such item.
    */
    public int findMaxPrice(int n) {
        if(hashMap.containsKey(n)){
            TreeSet<Integer> compSet = hashMap.get(n);
            Entry initEntry = treeMap.get(compSet.first());
            Integer maxPrice = initEntry.price;

            for(Integer id : compSet)
            {
                Entry entry = treeMap.get(id);
                if(entry.price > maxPrice)
                {
                    maxPrice = entry.price;
                }
            }
            return maxPrice;
        }
        else{
            return 0;
        }
    }

    /* 
       f. FindPriceRange(n,low,high): given int n, find the number
       of items whose description contains n, and in addition,
       their prices fall within the given range, [low, high].
    */
    public int findPriceRange(int n, int low, int high) {
        if(hashMap.containsKey(n)){
                int numOfItems = 0;
                TreeSet<Integer> compSet = hashMap.get(n);

                for(Integer id : compSet){
                    if(treeMap.get(id).price >= low && treeMap.get(id).price <= high){
                        numOfItems +=1;
                    }
                }

                return numOfItems;
        }
        else{
            return 0;
        }
    }
    
    /*
     increase the price of every product, whose id is in
     the range [l,h] by r%. Discard any fractional pennies in the new prices
     of items. Note that you are truncating, not rounding.
     Returns the sum of the net increases of the prices.

    */
    public int priceHike(int low, int high, int rate){
        int hikeAmount = 0;
        for(int i = low; i <= high; i++){
            if(treeMap.containsKey(i)){
                hikeAmount = hikeAmount + (treeMap.get(i).price*(rate/100));
                treeMap.get(i).price = treeMap.get(i).price + (treeMap.get(i).price*(rate/100));
            }
        }
        return hikeAmount;
    }

    /*
      g. RemoveNames(id, list): Remove elements of list from the description of id.
      It is possible that some of the items in the list are not in the
      id's description.  Return the sum of the numbers that are actually
      deleted from the description of id.  Return 0 if there is no such id.
    */
    public int removeNames(int id, java.util.List<Integer> list) {
        if(treeMap.containsKey(id)){
            Entry entry = treeMap.get(id);
            int removedItems = 0;

            for(Integer listElement : list){
                if(entry.description.contains(listElement)){
                    removedItems += listElement;
                    entry.description.remove(listElement);
                }
            }
            return removedItems;
        }
        else{
            return 0;
        }
    }
}
