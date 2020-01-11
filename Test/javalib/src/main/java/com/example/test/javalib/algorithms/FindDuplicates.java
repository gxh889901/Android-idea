package com.example.test.javalib.algorithms;

/*
题目：在一个长度为n的数组里的所有数字都在0到n-1的范围内。
数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。
请找出数组中任意一个重复的数字。例如，如果输入长度为7的数组{2,3,1,0,2,5,3},
那么对应的输出是重复的数字2或者3。
https://blog.csdn.net/u011035026/article/details/103886306
*/

public class FindDuplicates {
    //1.注意审题，
    // 输出的是任意一个重复的数字而不是输出所有重复数字，
    // 注意题目中给出的条件可能会帮助降低复杂度
    //注意循环的使用范围
    //空间复杂度（n的平方）
    public static int findSameSolution1(int[] test){
        if (test == null || test.length < 1) return -1; //写算法的时候一定不要忘记加入判空异常处理
        int sameNum = -1;
        for(int i = 0; i < test.length; i++){
            for(int j = i+1; j < test.length; j++){
                if(test[i] == test[j]){
                    sameNum = test[i];
                    return sameNum;
                }
            }
        }
        return sameNum;
    }

    //时间复杂度O(n) 空间复杂度 O(n）
    //利用数组下标，刚开始本来都想到这里了，但是想着什么indexof可能不行，但是利用Int数组就不用啥indexof，还是基础太薄弱
    public static int findSameSolution2(int[] test){
        if(test == null || test.length < 1) return -1;
        int[] indexIntArray = new int[test.length];
        for(int i = 0; i < test.length; i++){
            indexIntArray[test[i]]++;
            int same = indexIntArray[test[i]];
            if( same > 1){
                return same;
            }
        }
        return -1;
    }

    //算法复杂度为O(n)
    //这个算法的优化依赖的条件中的，数组长度为n,数据范围为0到n-1,长度为7的数字里面的数据不能超过6
    //特意给出数据范围就是为了方便计算，所以一定要审题，抓住题中的条件

    public static int findSameSolution3(int[] test){
        int sameNum = -1;
        if(test == null || test.length < 1){
            System.out.println("数组为空");
            return -1;
        }
        for(int i = 0; i < test.length; i++){
            if(test[i] < 0 || test[i] > test.length-1){
                System.out.println("数据超出范围"); //这里一定要判断，否则下面回引起死循环
                return -1;
            }
        }

        for (int i = 0; i < test.length; i++) { //整形交换只能交换当前的变量
            while (i != test[i]) {
                if (test[i] == test[test[i]]) {
                    sameNum = test[i];
                    return sameNum;
                } else {
                    int current = test[i];
                    //这里绝对不能用 current代替test[i]进行交换因为是值交换，current 变了但是test[i]没有变，
                    //不能使用另外的exchange函数也是同样的道理
                    int temp;
                    temp = test[i];
                    test[i] = test[temp];
                    test[temp] = temp;
                    //这里一定要用test[temp]而不是test[test[i]]因为此时test[i]已经变了
                    //会引起死循环
                }
            }

        }
        return sameNum;
    }




}
