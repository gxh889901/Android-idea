package com.example.test.javalib;

import com.example.test.javalib.algorithms.TwoDimensFindNum;
import com.example.test.javalib.algorithms.FindDuplicates;

public class MyClass {
    public static void main(String[] args){

        int[] ints = new int[]{2,3,1,0,2,5,3};
        System.out.println("FindDuplicates sol1=" +  FindDuplicates.findSameSolution1(ints));
        System.out.println("FindDuplicates sol2=" +  FindDuplicates.findSameSolution2(ints));
        System.out.println("FindDuplicates sol3=" + FindDuplicates.findSameSolution3(ints));

        int[][] arr = {{1, 2, 8, 9}, {2, 3, 9, 12}, {4, 7, 10, 13}};
        int a = 4;
        System.out.println("TwoDimensFindNum sol1" + TwoDimensFindNum.findNum1(arr, a));
        System.out.println("TwoDimensFindNum sol2" + TwoDimensFindNum.findNum2(arr, a));

    }

}
