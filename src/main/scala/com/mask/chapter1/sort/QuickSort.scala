package com.mask.chapter1.sort
import util.control.Breaks._
/**
 * @author dhy
 * @create 2020-09-08 3:47 下午 
 */
object QuickSort {
  def main(args: Array[String]): Unit = {
    val arr = Array(2,10,8,22,34,5,12,28,21,11)
    quickSort(0,9,arr)
    println(arr.mkString(" "))
  }
  def quickSort(left: Int, right: Int, arr: Array[Int]): Unit = {
    var l = left
    var r = right
    var pivot = arr((left + right) / 2)
    var temp = 0
    breakable {
      while (l < r) {
        while (arr(l) < pivot) {
          l += 1 }
        while (arr(r) > pivot) {
          r -= 1}
        if (l >= r) {
          break()
        }
        var temp = arr(l)
        arr(l) = arr(r)
        arr(r) = temp
        if (arr(l) == pivot) {
          r -= 1
        }
        if (arr(r) == pivot) {
          l += 1
        }}}
    if (l == r) {
      l += 1
      r -= 1
    }
    if (left < r) {
      quickSort(left, r, arr)
    }
    if (right > l) {
      quickSort(l, right, arr)
    }
  }

}
