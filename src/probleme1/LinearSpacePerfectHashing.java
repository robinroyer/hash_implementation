package probleme1;

import java.util.Random;
import java.util.ArrayList;

public class LinearSpacePerfectHashing<AnyType>
{
	static int p = 46337;

	QuadraticSpacePerfectHashing<AnyType>[] data;
	int a;
        int b;
        int m;        

	LinearSpacePerfectHashing()
	{
		a = 0;
                b = 0;
                m = 0;
                data = null;
	}

	LinearSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );
		
		//Create new hashing table
		if(array == null || array.isEmpty())
		{
                        data = new QuadraticSpacePerfectHashing[0];
			return;
		}
		if(array.size() == 1)
		{
			a = b = 0;
                        m = 1;
                        data = new QuadraticSpacePerfectHashing[1];
                        data[0] = new QuadraticSpacePerfectHashing<>(array);
			return;
		}
                
                ArrayList<AnyType> tempArray;
                
		//Random nb between 0 and P
                a = (int) (Math.random() * p);                                               
                b = (int) (Math.random() * p);
                m = array.size();
                
                data = new QuadraticSpacePerfectHashing[m];

                //Copy ofprevious elements in the new tab
                for (int i = 0; i < m; i++) {
                        tempArray = new ArrayList<>();
                        for (AnyType tempItem : array) {
                                if (getKey(tempItem) == i){
                                        tempArray.add(tempItem);
                                }
                        }
                        if (tempArray.isEmpty()){
                                data[i] = new QuadraticSpacePerfectHashing<>();
                        }
                        else{
                                data[i] = new QuadraticSpacePerfectHashing<>(tempArray);
                        }                        
                }
	}

	public int Size()
	{
		if( data == null ) return 0;

		int size = 0;
		//!return true size = nb of element in tab
		for(int i=0; i<data.length; ++i)
		{
			size += (data[i] == null ? 1 : data[i].Size());
		}
		return size;
	}

	public boolean containsKey(int key)
	{
		//Checkif a element is here at the case key
                if((key < m) && (key >= 0)){
                        return data[key].Size() != 0;
                }        
                return false;
	}
	
	public int getKey (AnyType x) {
		return CustomHash(x);	
	}
	
	public boolean containsValue (AnyType x) {
            int key = CustomHash(x);
            //Check if the element at the case x is not null
            if ((key >= 0) && (key < m) && (data[key].Size() != 0)) {
		return data[key].containsValue(x);                
            }
            return false;
	}
	
	public void remove (AnyType x) {
                int key = CustomHash(x);
                //If key correct and if a the key index there is en element, we romve it
                if ((key >= 0) && (key < m) && (data[key] != null)) {
                        data[key].remove(x);                
                }            		
	}

        @Override
	public String toString () {
		String result = "";
		
                for (int i = 0; i < m; i++) {
                        result += "[" + i + "] -> ";
                        if (data[i] != null) {
                            result += data[i].toString();
                        }
                        result += "\r\n";
                }       
		
		
		return result; 
	}
	        
        /** 
         * This private function is duplicate from QuadraticSpacePerfectHashing
         * Buts we don't want to be dependant from it and it should stay private
         * for internal behaviour.
         */
        private int CustomHash(AnyType object){
	//Hash h(x) = (ax+b)%m
            return  ((a * object.hashCode() + b) % p) % m;
        }
                
}
