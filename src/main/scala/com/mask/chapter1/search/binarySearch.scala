package com.mask.chapter1.search

import scala.collection.mutable.ArrayBuffer
import util.control.Breaks._
/**
 * @author dhy
 * @create 2020-09-09 4:05 下午 
 */
object binarySearch {
  def main(args: Array[String]): Unit = {
    val arr: Array[Int] = Array(1, 8, 10, 89, 1000,1000, 1234)
    val resArr = binarySearch(arr,0,arr.length - 1 ,1000)
    if(resArr.length != -1){
      for(index <- resArr){
        println("找到了,位置为:" + index)
      }

    }else{
      println("没有找到")
    }
  }
  /*
  中间值 = 查找值 找到了
  中间值 > 查找值 向左边继续找
  中间值 < 查找值 向右边继续找

  找不到的情况 l > r
  找不到就返回 -1
   */

  def binarySearch(arr:Array[Int], l:Int, r:Int, findValue:Int): ArrayBuffer[Int] = {
    if(l > r){
      return ArrayBuffer[Int]()
    }

    val midIndex: Int = (l + r) / 2
    val midValue: Int = arr(midIndex)

    if(midValue > findValue){
      binarySearch(arr,l,midIndex - 1,findValue)
    }else if(midValue < findValue){
      binarySearch(arr,midIndex + 1,r,findValue)
    }else {
      //定义一个可变数组
      val resArr = ArrayBuffer[Int]()
      //向左扫描
      var temp = midIndex - 1
      breakable {
        while (true) {
          if (temp < 0 || arr(temp) != findValue) {
            break()
          }
          if (arr(temp) == findValue) {
            resArr.append(temp)
          }
          temp -= 1
        }
      }
      //将midIndex加入resArr
      resArr.append(midIndex)

      //向右扫描
      temp = midIndex + 1
      breakable {
        while (true) {
          if (temp > arr.length - 1 || arr(temp) != findValue) {
            break()
          }
          if (arr(temp) == findValue) {
            resArr.append(temp)
          }
          temp += 1
        }
      }
      resArr
    }
  }

}
