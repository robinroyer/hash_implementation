package probleme2;


public class MyHashMap<KeyType, ValueType>
{
   private DoubleHashingTable<Entry <KeyType, ValueType>> items;
   private static final int DEFAULT_TABLE_SIZE = 11;
   private int currentSize;              // The number of occupied cells

   
   private static class DoubleHashingTable<Entry<KeyType, ValueType>>
   {
       public KeyType myKey;
       public ValueType myValue;   // the element
       public boolean isActive;  // false if marked deleted

       public DoubleHashingTable()
       {
         //Do nothing   
       }

       public DoubleHashingTable( KeyType k, ValueType v )
       {
           this( k,v, true );
       }

       public DoubleHashingTable( KeyType k, ValueType v, boolean i )
       {
           myKey  = k;
           myValue = v;
           isActive = i;
       }

	public void insert(Entry<KeyType, ValueType> entry) {
		
	}

	public Object get(Entry<KeyType, ValueType> entry) {
		
		return null;
	}

	public int nbElement() {
		
		return 0;
	}
   }
   
   public MyHashMap( )
   {
	  //this( DEFAULT_TABLE_SIZE );
    //  items = new DoubleHashingTable< Entry<KeyType,ValueType> >();
   }

   
   //Function from the book QuadraticProbingHashTable.java
   //http://users.cis.fiu.edu/~weiss/dsaajava2/code/QuadraticProbingHashTable.java
   //To adapt for KeyType, ValueType instead of Anytype
   
 //Allocate 
   /*private void allocateArray( int arraySize )
   {
       items = new DoubleHashingTable< Entry<KeyType, ValueType> > [ nextPrime( currentSize ) ];
   }*/
   
   
//   /**
//    * Insert into the hash table. If the item is
//    * already present, do nothing.
//    * @param x the item to insert.
//    */
//   public void insert( AnyType x )
//   {
//           // Insert x as active
//       int currentPos = findPos( x );
//       if( isActive( currentPos ) )
//           return;
//
//       array[ currentPos ] = new HashEntry<AnyType>( x, true );
//
//           // Rehash; see Section 5.5
//       if( ++currentSize > array.length / 2 )
//           rehash( );
//   }
//
//   /**
//    * Expand the hash table.
//    */
//   private void rehash( )
//   {
//       HashEntry<AnyType> [ ] oldArray = array;
//
//           // Create a new double-sized, empty table
//       allocateArray( nextPrime( 2 * oldArray.length ) );
//       currentSize = 0;
//
//           // Copy table over
//       for( int i = 0; i < oldArray.length; i++ )
//           if( oldArray[ i ] != null && oldArray[ i ].isActive )
//               insert( oldArray[ i ].element );
//   }
//
//   /**
//    * Method that performs quadratic probing resolution.
//    * Assumes table is at least half empty and table length is prime.
//    * @param x the item to search for.
//    * @return the position where the search terminates.
//    */
//   private int findPos( AnyType x )
//   {
//       int offset = 1;
//       int currentPos = myhash( x );
//       
//       while( array[ currentPos ] != null &&
//               !array[ currentPos ].element.equals( x ) )
//       {
//           currentPos += offset;  // Compute ith probe
//           offset += 2;
//           if( currentPos >= array.length )
//               currentPos -= array.length;
//       }
//       
//       return currentPos;
//   }
//
//   /**
//    * Remove from the hash table.
//    * @param x the item to remove.
//    */
//   public void remove( AnyType x )
//   {
//       int currentPos = findPos( x );
//       if( isActive( currentPos ) )
//           array[ currentPos ].isActive = false;
//   }
//
//   /**
//    * Find an item in the hash table.
//    * @param x the item to search for.
//    * @return the matching item.
//    */
//   public boolean contains( AnyType x )
//   {
//       int currentPos = findPos( x );
//       return isActive( currentPos );
//   }
//
//   /**
//    * Return true if currentPos exists and is active.
//    * @param currentPos the result of a call to findPos.
//    * @return true if currentPos is active.
//    */
//   private boolean isActive( int currentPos )
//   {
//       return array[ currentPos ] != null && array[ currentPos ].isActive;
//   }
//
//   /**
//    * Make the hash table logically empty.
//    */
//   public void makeEmpty( )
//   {
//       currentSize = 0;
//       for( int i = 0; i < array.length; i++ )
//           array[ i ] = null;
//   }
//
//   private int myhash( AnyType x )
//   {
//       int hashVal = x.hashCode( );
//
//       hashVal %= array.length;
//       if( hashVal < 0 )
//           hashVal += array.length;
//
//       return hashVal;
//   }
//   
//   private static class HashEntry<AnyType>
//   {
//       public AnyType  element;   // the element
//       public boolean isActive;  // false if marked deleted
//
//       public HashEntry( AnyType e )
//       {
//           this( e, true );
//       }
//
//       public HashEntry( AnyType e, boolean i )
//       {
//           element  = e;
//           isActive = i;
//       }
//   }
//
//   private static final int DEFAULT_TABLE_SIZE = 11;
//
//   private HashEntry<AnyType> [ ] array; // The array of elements
//   private int currentSize;              // The number of occupied cells
//
//   /**
//    * Internal method to allocate array.
//    * @param arraySize the size of the array.
//    */
//    @SuppressWarnings("unchecked")
//   private void allocateArray( int arraySize )
//   {
//       array = new HashEntry[ nextPrime( arraySize ) ];
//   }
//
//   /**
//    * Internal method to find a prime number at least as large as n.
//    * @param n the starting number (must be positive).
//    * @return a prime number larger than or equal to n.
//    */
//   private static int nextPrime( int n )
//   {
//       if( n <= 0 )
//           n = 3;
//
//       if( n % 2 == 0 )
//           n++;
//
//       for( ; !isPrime( n ); n += 2 )
//           ;
//
//       return n;
//   }
//
//   /**
//    * Internal method to test if a number is prime.
//    * Not an efficient algorithm.
//    * @param n the number to test.
//    * @return the result of the test.
//    */
//   private static boolean isPrime( int n )
//   {
//       if( n == 2 || n == 3 )
//           return true;
//
//       if( n == 1 || n % 2 == 0 )
//           return false;
//
//       for( int i = 3; i * i <= n; i += 2 )
//           if( n % i == 0 )
//               return false;
//
//       return true;
//   }
   
   
   public void put(KeyType key, ValueType val)
   {
      items.insert(new Entry<KeyType,ValueType>(key,val));
   }

   public ValueType get(KeyType key)
   {
	return null;
     // return (items.get(new Entry<KeyType,ValueType>(key,null) )).value;
   }

   public boolean isEmpty()
   {
      return ( items.nbElement() == 0 ); 
   }

   private static class Entry<KeyType,ValueType>
   {
      public KeyType key;
      public ValueType value;

      public Entry(KeyType key, ValueType value)
      {
         this.key = key;
         this.value = value;
      }

      public boolean equals(Object cmp)
      {
         return this.hashCode() == cmp.hashCode();
      }

      public int hashCode()
      {
         return key.hashCode();
      }
   }    
}
