package com.example.test.javalib.algorithms;

/*
题目：在一个二维数组中（每个一维数组的长度相同），
每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。{{1, 2, 8, 9}, {2, 3, 9, 12}, {4, 7, 10, 13}}
https://blog.csdn.net/u011035026/article/details/103886460
 */
public class TwoDimensFindNum {

    //自己想的方法还是太复杂，没有人家的巧妙，想的太复杂
    public static boolean findNum1(int[][] arr, int a){
        if(arr == null || arr.length == 0){
            System.out.println("数组为空");
            return  false;
        }
        int m = 0;//记录对角线所在的行
        int n = 0;//记录对角线所在的列
        int rowLen = arr.length;
        int colLen = arr[0].length;
        //对角线上的数据跟a比较
        for(int i = 0, j = 0; i < arr.length && j < arr[0].length; i++, j++){
            if(arr[i][j] == a ) return true; //数据位于对角线上返回true
            if(i == 0 && j == 0 && arr[i][j] > a) return false; //如果第一个数就比a大，那肯定不包含a,返回false
            if(arr[i][j] > a && arr[i-1][j-1] < a){ //找到大于a的对角线数据，他的上一个对象线的数据小于a,
                m = i; //记录行
                n = j; //记录列
            }
        }
        //从记录的行开始找, 从对角线数据前一个列开始找，从后向前找
        for(int i = colLen-2; i >= 0; i--){
            if(arr[m][i] == a) return true;
            if(arr[m][i] < a) break; //如果找到小于a的数据就不用再往前找了，前面都是小于a的数据
        }

        //从记录的列开始找，从对角线数据前一行开始找，从后向前找
        for(int i = rowLen -2; i >= 0; i--){
            if(arr[i][n] == a) return true;
            if(arr[i][n] < a) break;//如果找到小于a的数据就不用再往前找了，都是小于a的数据
        }

        //如果行列数不等，那最后剩一行或者一列
        if(rowLen > colLen){ //剩余一行
            for(int i = colLen -1; i >= 0; i--){
                if(arr[rowLen-1][i] == a) return true;
                if(arr[rowLen -1][i] < a) break;//如果找到小于a的数据就不用再往前找了，都是小于a的数据
            }
        }else { //剩余列
            for(int i = rowLen -1; i >= 0; i--){
                if(arr[i][colLen-1] == a) return true;
                if(arr[i][colLen-1] < a) break;//如果找到小于a的数据就不用再往前找了，都是小于a的数据
            }
        }

        return false;
    }

    public static boolean findNum2(int[][] arr, int a){
        if(arr == null || arr.length == 0){
            System.out.println("数组为空");
            return false;
        }

        int colLen = arr[0].length;
        int rowLen = arr.length;
        int row = 0;
        int col = colLen-1;
        //从第0行，最后一列开始找，这个数所在行，前面的数都比它小，所在的列后面的数都比他大，由此可以判断a在哪一行，哪一列
        while(row <= rowLen -1 && col >= 0){
            if(arr[row][col] == a) return true;
            if(arr[row][col] > a) col--;//那么当前的列一定都大于a, 所以往前面的列找
            else row++;//如果当前的数小于a,那么当前的行的数一定都小于a, 所以往下面的行继续找
        }

        return false;
    }
}
