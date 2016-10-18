/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probleme2;

/**
 *
 * @author robinroyer
 */
public class DoubleHashingTable<AnyType>
   {
        /**
         * Element in our hashtable
         */
        private int nbElement;
        
        /**
         * effective size of items
         */
        private int N;
        
        /**
         * The Prime number used in the hash function
         */
        private int R;
        
        /**
         * Array containing our <key, value>
         */
        private AnyType[] items;
        
        /**
         * Dafault prime value size for items
         */
        private static final int DEFAULT_N = 47; 
        
        /**
         * Default prime value for R
         */
        private static final int DEFAULT_R = 13;


        
        
       public DoubleHashingTable()
       {
           
           R = DEFAULT_R;
           N = DEFAULT_N;
           nbElement = 0;
           // allocate the table
           items = (AnyType[]) new Object[N];
       }
       
        /**
         * insert the depending of his hashcode 
         * 
         * @param x object to insert
         */
	public void insert(AnyType x) {	
            // number of collision
            int i = 0;
            
            int hash;
               
            // generate hash to avoid collision
            do{
                hash = CustomHash(x, i++);
            }
            while(collision(hash, x));
            
            // finally insert without collision
            items[hash] = x;  
            
            // increment and check if we need to resize the table
            if(nbElement > (N/2)){
                resize();
            }
	}
        
        /**
         * Get an object depending from its hashCode
         * 
         * @param x object to find
         * @return  return the value found or null if not found
         */
	public AnyType get(AnyType x) {	
            int i = 0;
            int hash;
            do{
                hash = CustomHash(x, i++);
                if(items[hash] == null ){
                    // The value is not found
                    break;
                }
            }
            while(!x.equals(items[hash]));
            return items[hash];
	}
        /**
         * Getter of nbElement
         * 
         * @return the number of element in our hash
         */
	public int nbElement() {		
            return nbElement;
	}
        
        /**
         * 
         * double hashing function: position = (H1 (x) + i H2 (x)) % N
         * @param object object to hash
         * @param i number of collision
         * @return the resulting hash
         */
        private int CustomHash(AnyType object, int i){          
            return ((object.hashCode() % N) + i * (R - (object.hashCode() % R) )) % N;
        }
        
        
        
    private boolean collision(int hash, AnyType x) {        
    if(items[hash] == null){
        return false;
    }        
    // If the same key is provided, it's not a collision
    if( !items[hash].equals(x)){
        nbElement++;
    }                            
    return false;
        
    } 
    
    /**
     * Resize the Hash, generating new R, N
     */
    private void resize() {
        // generate new N
        N = nextPrime(2 * N);
        
        //generate new R
        do{
            R = nextPrime( (int) (Math.random() * N));
        }
        while(R >= N );
                
        AnyType[] oldItems = items;
        
        // allocate array with new size
        items = (AnyType[]) new Object[N];
        
        // copy values with new hash function values
        for (AnyType oldItem : oldItems) {
            insert(oldItem);
        }                                
    }  
        
    /**
    * Internal method to find a prime number at least as large as n.
    * @param n the starting number (must be positive).
    * @return a prime number larger than or equal to n.
    * @author Mark Allen Weiss
    */
   private static int nextPrime( int n )
   {
       if( n <= 0 )
           n = 3;

       if( n % 2 == 0 )
           n++;

       for( ; !isPrime( n ); n += 2 )
           ;

       return n;
   }

   /**
    * Internal method to test if a number is prime.
    * Not an efficient algorithm.
    * @param n the number to test.
    * @return the result of the test.
    * @author Mark Allen Weiss
    */
   private static boolean isPrime( int n )
   {
       if( n == 2 || n == 3 )
           return true;

       if( n == 1 || n % 2 == 0 )
           return false;

       for( int i = 3; i * i <= n; i += 2 )
           if( n % i == 0 )
               return false;

       return true;
   }    
   }
