package com.mask.chapter1.sort

import java.text.SimpleDateFormat
import java.util.Date

import com.mask.chapter1.sort.SelectSort.selectSort

/**
 * @author dhy
 * @create 2020-09-07 10:26 上午 
 */
object InsertSort {
  def main(args: Array[String]): Unit = {
    println("hotfix")
    var random = new util.Random()
    val arr = new Array[Int](80000)
    for(i <- 0 until 80000){
      arr(i) = random.nextInt(8000000)
    }
    println("排序前")
    val now = new Date()
    val sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date: String = sdf.format(now)
    println("排序前时间=" + date)

    insertSort(arr)

    println("排序后")
    val now2 = new Date()
    val date2: String = sdf.format(now2)
    println("排序前时间=" + date2)

    //    var arr = Array(101,34,119,1)
    //第一轮 (101),34,119,1 => (34,101),119,1
//    var insertValue = arr(1)
//    var insertIndex = 1 - 1
//    while (insertIndex >= 0 && insertValue < arr(insertIndex)){
//      arr(insertIndex + 1) = arr(insertIndex)
//      insertIndex -= 1
//    }
//
//    arr(insertIndex + 1) = insertValue
//    println(arr.mkString(" "))


    //第二轮 (34,101),119,1 => (34,101,119),1
//    insertValue = arr(2)
//    insertIndex = 2 - 1
//    while (insertIndex >= 0 && insertValue < arr(insertIndex)){
//      arr(insertIndex + 1) = arr(insertIndex)
//      insertIndex -= 1
//    }
//
//    arr(insertIndex + 1) = insertValue
//    println(arr.mkString(" "))
  }
  def insertSort(arr:Array[Int]): Unit ={
    for(i <- 1 until arr.length){
      var insertValue = arr(i)
      var insertIndex = i - 1
      while (insertIndex >= 0 && insertValue < arr(insertIndex)){
        arr(insertIndex + 1) = arr(insertIndex)
        insertIndex -= 1
      }

      arr(insertIndex + 1) = insertValue
//      println(s"第${i}轮结果")
//      println(arr.mkString(" "))
    }
  }
}
