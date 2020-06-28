package com.example.firstdemo.test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * 快速排序
 */
public class TestFastSort {
    //  static   CompositeDisposable comDisposable = new CompositeDisposable();
    public static void main(String[] args) throws Exception {
       int[] arr= new int[]{5,9,1,2,7,8,3,6,4};
        QuickSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]+",");
        }

    }

    public static void QuickSort(int[] arr) {
        // 定义两个哨兵  分别为 数组的最小下边  和数组的最大下标
        sort(arr, 0, arr.length - 1);

    }

    public static void sort(int[] arr, int left, int right) {
        if (left < right) {
            // 通过基准数字将数组左右分为两个子数组
            // 数组中小于基准的数放在左边  大于基准的数字调换到右边
            // 递归子数组
            int pvtion = partion(arr, left, right);
            sort(arr, left, pvtion - 1);
            sort(arr, pvtion + 1, right);
        }
    }

    public static int partion(int[] arr, int left, int right) {
        int pvtion = arr[left]; //依数组中最左边的数字作为基准

        while (left < right) {  // 左边数组下标 小于右边数组下标

            while (arr[right] > pvtion && left < right)
                right--; //当右边的哨兵位置上的数字大于基准数字  哨兵左移  直达哨兵到达位于从右边开始第一个小于基准的数字

            if (left < right) {
                // 把最右边第一个小于基准的数字换到最左边位置
                // 因为我们以最左边的哨兵为基准 ,所以直接赋值到该哨兵位置
                arr[left++] = arr[right]; //类似于 arr[left] = arr[right];left++;
            }

            while (arr[left] <= pvtion && left < right)
                left++;//当左边的哨兵位置上的数字小于基准数字  哨兵右移  直达哨兵到达位于从左边开始第一个大于基准的数字
            if (left < right) {
                arr[right--] = arr[left]; //把该大于基准的数字的换到右边
            }
        }
        arr[left] = pvtion;  //把基准数字放在中间位置
        return left; // 返回中间位置的下标

    }

}
