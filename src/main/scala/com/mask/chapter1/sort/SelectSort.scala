package com.mask.chapter1.sort

import java.text.SimpleDateFormat
import java.util.Date

/**
 * @author dhy
 * @create 2020-09-06 5:11 下午 
 */
object SelectSort {
  def main(args: Array[String]): Unit = {
//    var arr = Array(101,34,119,1)
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

    selectSort(arr)

    println("排序后")
    val now2 = new Date()
    val date2: String = sdf.format(now2)
    println("排序前时间=" + date2)


    //第一轮 (101,34,119,1) => (1,34,119,101)
//    var min = arr(0)
//    var minIndex = 0
//    for(j <- 1 until arr.length){
//      if(min > arr(j)){
//        min = arr(j)
//        minIndex = j
//      }
//    }
//
//    if(minIndex != 0){
//      arr(minIndex) = arr(0)
//      arr(0) = min
//    }
//
//    println(arr.mkString(" "))

    //第二轮 (1,34,119,101) => (1,34,119,101)
//    min = arr(1)
//    minIndex = 1
//    for(j <- 1 + 1 until arr.length){
//      if(min > arr(j)){
//        min = arr(j)
//        minIndex = j
//      }
//    }
//
//    if(minIndex != 1){
//      arr(minIndex) = arr(1)
//      arr(1) = min
//    }

//    println(arr.mkString(" "))
  }

  /*
    选择排序
     */
  def selectSort(arr:Array[Int]): Unit ={

    for(i <- 0 until arr.length - 1){
      var min = arr(i)
      var minIndex = i
      for(j <- i + 1 until arr.length){
        if(min > arr(j)){
          min = arr(j)
          minIndex = j
        }
      }

      if(minIndex != i){
        arr(minIndex) = arr(i)
        arr(i) = min
      }
    }
  }
}
