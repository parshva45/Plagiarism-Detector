package edu.northeastern.cs5500.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Praveen Singh
 */
public class UtilsTest {

    @Test
    public void testMergingListWorks(){
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        Integer[][] res = Utils.getArrayFromList(list1, list2);
        assertNotNull(res);
    }

    @Test
    public void testWithFirstBigList(){
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        Integer[][] res = Utils.getArrayFromList(list1, list2);
        assertNotNull(res);

        Integer[][] expected= {{1,2,3,4},{1,2,-1,-1}};
        assertArrayEquals(expected, res);
    }

    @Test
    public void testWithSecondBigList(){
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);

        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        Integer[][] res = Utils.getArrayFromList(list1, list2);
        assertNotNull(res);

        Integer[][] expected= {{1,2,-1,-1},{1,2,3,4}};
        assertArrayEquals(expected, res);
    }

}