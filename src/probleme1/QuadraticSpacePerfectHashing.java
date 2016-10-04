package probleme1;

import java.util.ArrayList;
import java.util.Random;

public class QuadraticSpacePerfectHashing<AnyType> 
{
	private final static int p = 46337;
        
	private int a;
        private int b;
        private int m;
	private AnyType[] items;

        
        // TODO specifier le Guide de Style
	QuadraticSpacePerfectHashing()
	{
		a = 0;
                b = 0;
                items = null;
	}

	QuadraticSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public int Size()
	{
		if( items == null ) return 0;

		return items.length;
	}

	public boolean containsKey(int key)
	{
                if(items == null){
                    return false;
                }
                
                if(items[key] != null){
                        return true;
                }
                return false;
	}

	public boolean containsValue(AnyType x )
	{
		if(items == null){
                    return false;
                }
                
                return items[CustomHash(x)] != null;
	}

	public void remove (AnyType x) {
                if(items == null){
                        return;
                }
            
		int tmp = CustomHash(x);
		if( items[tmp] == x){
                        items[tmp] = null;
                }
                else{
                        // TODO: maybe we should throw a custom exception here
                }
	}

	public int getKey (AnyType x) {
		return ((a * x.hashCode() + b) % p) % m;
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );

		if(array == null || array.isEmpty())
		{
			items = (AnyType[]) new Object[0];
			return;
		}
		if(array.size() == 1)
		{
			a = b = 0;

                        items = (AnyType[]) new Object[1];
                        items[0] = array.get(0);
			return;
		}

		do
		{
                        a = (int) (Math.random() * p);                                               
                        b = (int) (Math.random() * p);
                        m = array.size() * array.size();
			items = (AnyType[]) new Object[m];
                        int tmp;
                        
                    for (AnyType array1 : array) {
                            tmp = CustomHash(array1);
                            items[tmp] = array1;
                    }	
                }
		while( collisionExists( array ) );
	}

	@SuppressWarnings("unchecked")
	private boolean collisionExists(ArrayList<AnyType> array)
	{            
                int tmp1;
                int tmp2;
                
                // On compare chaque item avec chaque autre item une fois O(nLog(n))
                for (int i = 0; i < array.size() - 1; i++) {                           
                        tmp1 = CustomHash(array.get(i));
                        for (int j = i + 1; j < array.size(); j++) {
                                tmp2 = CustomHash(array.get(j));
                                if (tmp1 == tmp2){
                                        return true;
                                }
                        }                    
                }
		return false;
	}
	
        @Override
	public String toString () {
		String result = "";
                
		for (int i = 0; i < items.length; i++) {
                    if (items[i] != null) {
                            if( CustomHash(items[i]) == i){
                                result += "(" + i + ", "+ items[i] +"),";
                        }
                    }      
                }				
		return result; 
	}
        
        private int CustomHash(AnyType object){
            return ((a * object.hashCode() + b) % p) % m;
        }
}
