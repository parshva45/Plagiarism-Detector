package edu.northeastern.cs5500.utils;

import java.util.List;

/**
 * @author Praveen Singh
 */
public class Utils {

    private Utils(){
    }

    /**
     * Method creates a 2-D array from 2 arrayLists
     * @param list1 ArrayList of Integers
     * @param list2 ArrayList of Integers
     * @return 2-D array of Integers
     */
    public static Integer[][] getArrayFromList(List<Integer> list1, List<Integer> list2){
        int sizeList1 = list1.size();
        int sizeList2 = list2.size();
        if (sizeList1 < sizeList2){
            updateList(list1, sizeList2-sizeList1);
        }else{
            updateList(list2, sizeList1-sizeList2);

        }
        Integer[] ar1 = list1.toArray(new Integer[list1.size()]);
        Integer[] ar2 = list2.toArray(new Integer[list2.size()]);
        return new Integer[][]{ ar1, ar2 };
    }

    private static void updateList(List<Integer> list, int restVals){
        for(int i=0; i< restVals; i++){
            list.add(-1);
        }
    }
}
