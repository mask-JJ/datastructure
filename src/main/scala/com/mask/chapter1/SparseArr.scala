package com.mask.chapter1

import java.io.{File, FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream, PrintWriter}

import com.alibaba.fastjson.{JSON, JSONObject}

import scala.collection.mutable.ArrayBuffer

/**
 * @author dhy
 * @create 2020-07-22 12:52 上午 
 */
object SparseArr {
  def main(args: Array[String]): Unit = {
    val rowSize = 11
    val colSize = 11
    //演示稀疏数组的使用
    val chessMap: Array[Array[Int]] = Array.ofDim[Int](rowSize, colSize)
    //初始化地图
    chessMap(1)(2) = 1 //1表示黑子
    chessMap(2)(3) = 2 //2表示白子

    for (item <- chessMap) {
      for (item2 <- item) {
        printf("%d\t",item2)  // %d 十进制数字 , %s 字符串
      }
      println()
    }

    //使用class Node(row,col,value)存放不为0的值及位置
    //放到ArrayBuffer中

    val sparseArr = new ArrayBuffer[Node]()
    val node = new Node(rowSize, colSize, 0)
    sparseArr.append(node)
    for(i <- 0 until chessMap.length){
      for(j <- 0 until chessMap(i).length){
        if(chessMap(i)(j)!=0){
          val node = new Node(i, j, chessMap(i)(j))
          sparseArr.append(node)
        }
      }
    }
    println("---------稀疏数组---------")
    for (node <- sparseArr) {
      printf("%d\t%d\t%d\n",node.row,node.col,node.value)
    }

    //存盘
//
//    val jSONObject: JSONObject = JSON.parseObject()
//    println(jSONObject.toString)
//
//    val oos = new ObjectOutputStream(new FileOutputStream("input/sparsearray.dat"))
//    for (item <- sparseArr) {
//      oos.writeObject(item)
//    }
//
////    //读盘
//    val ois = new ObjectInputStream(new FileInputStream("input/sparsearray.dat"))
//    ois.readObject()

    val newNode: Node = sparseArr(0)
    val rowSize2: Int = newNode.row
    val colSize2: Int = newNode.col

    val chessMap2: Array[Array[Int]] = Array.ofDim[Int](rowSize2, colSize2)

    for(i <- 1 until sparseArr.length){
      val node: Node = sparseArr(i)
      chessMap2(node.row)(node.col) = node.value
    }

    println("-------恢复后--------")

    for (item <- chessMap2) {
      for (item2 <- item) {
        printf("%d\t",item2)
      }
      println()
    }

  }

  case class Node(row:Int,col:Int,value:Int)

}
