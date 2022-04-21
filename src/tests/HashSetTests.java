package tests;
import static org.junit.Assert.*;
import org.junit.Test;
import HashSetcustom.*;

import java.util.NoSuchElementException;

public class HashSetTests {
    @Test
    public void createvalidsizehashset(){
        HashSet<String> hashset=new HashSet<String>(3);
        assertEquals(hashset.bucketcount(),3);
    }
    @Test
    public  void failtocreatevalidsize(){
        assertThrows(IllegalArgumentException.class,()->new HashSet<String>(-1));
    }
    @Test
    public void validaddnewelem(){
        HashSet<String> hashset=new HashSet<String>(4);
        hashset.add("Something");
        assertTrue(hashset.find("Something"));
    }
    @Test
    public void failtoaddequalsobjects(){
        HashSet<String> hashSet=new HashSet<>(4);
        hashSet.add("Something");
        int size1=hashSet.size();
        hashSet.add("Something");
        int size2=hashSet.size();
        assertEquals(size1,size2);
    }
    @Test
    public void deleteelemsucess(){
        HashSet<String> hashSet=new HashSet<>(4);
        hashSet.add("Something");
        assertEquals("Something",hashSet.remove("Something"));
    }
    @Test
    public void failtodeleteifthereisnoelement(){
        HashSet<String> hashset=new HashSet<>(4);
        hashset.add("Something");
        assertThrows(NoSuchElementException.class,()->hashset.remove("Somethina"));
    }

}
