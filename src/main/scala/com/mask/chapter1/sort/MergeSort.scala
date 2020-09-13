package com.mask.chapter1.sort

import java.text.SimpleDateFormat
import java.util.Date

/**
 * @author dhy
 * @create 2020-09-09 12:07 下午 
 */
object MergeSort {
  def main(args: Array[String]): Unit = {
//    val arr: Array[Int] = Array(-9, 78, 0, 23, -567, 70)

    val random = new util.Random()
    val arr = new Array[Int](80000000)
    for (i <- 0 until 80000000) { //循环的生成随机数
      arr(i) = random.nextInt(8000000)
    }
    println("排序前")
    val now: Date = new Date()
    val dateFormat: SimpleDateFormat =
      new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date = dateFormat.format(now)

    val temp = new Array[Int](arr.length) //临时的数组

    println("排序前时间=" + date) //输出时间

    mergeSort(arr, 0, arr.length - 1, temp) //调用归并排序法

    println("归并排序后")

    val now2: Date = new Date()
    val date2 = dateFormat.format(now2)
    println("排序后时间=" + date2) //输出时间

  }

  /*
  arr:带排序的数组
  left:最初的左下标 0
  right:最初的右下标 length-1
  temp:临时数组，事先就开辟好的，大小和arr一样
   */
  def mergeSort(arr: Array[Int], left: Int, right: Int, temp: Array[Int]): Unit = {
    if (left < right) { //如果left < right 就可以继续分
      val mid: Int = (left + right) / 2
      mergeSort(arr, left, mid, temp)
      mergeSort(arr, mid + 1, right, temp)

      merge(arr,left,mid,right,temp)

    }
  }

  def merge(arr: Array[Int], left: Int, mid: Int, right: Int, temp: Array[Int]): Unit = {
    var i = left //i就是左边的指针
    var j = mid + 1 //j就是右边的指针
    var t = 0 //t就是temp数组的索引
    while (i <= mid && j <= right) {
      //如果当前左边的有序列表的值小于当前右边的有序列表的值，就把这个值拷贝到temp数组
      if (arr(i) <= arr(j)) {
        temp(t) = arr(i)
        t += 1
        i += 1
      } else {
        //如果当前右边的有序列表的值小于当前左边的有序列表的值，就把这个值拷贝到temp数组
        temp(t) = arr(j)
        t += 1
        j += 1
      }
    }
    while (i <= mid) {
      //如果左边有序列表还有剩余数据，就依次拷贝到temp数组
      temp(t) = arr(i)
      t += 1
      i += 1
    }
    while (j <= right) {
      //如果左边有序列表还有剩余数据，就依次拷贝到temp数组
      temp(t) = arr(j)
      t += 1
      j += 1

    }

    //将temp中的数据拷贝到arr中
    t = 0
    var tempLeft = left
    while(tempLeft <= right){
      arr(tempLeft) = temp(t)
      t += 1
      tempLeft += 1
    }

  }
}
