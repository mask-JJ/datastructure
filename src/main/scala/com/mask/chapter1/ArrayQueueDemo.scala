package com.mask.chapter1

import scala.io.StdIn

/**
 * @author dhy
 * @create 2020-07-22 7:23 下午 
 */
object ArrayQueueDemo {
  def main(args: Array[String]): Unit = {
    //初始化一个队列
    val queue = new ArrayQueue(3)
    var key = ""
    while(true){
      println("show:表示显示队列")
      println("add:表示添加元素")
      println("get:表示取出元素")
      println("head:表示取出队列头")
      println("exit:表示退出程序")

      key = StdIn.readLine()
      key match {
        case "show" => queue.showQueue()
        case "exit" => System.exit(0)
        case "add" => {
          println("请输入一个数")
          val value = StdIn.readInt()
          queue.addQueue(value)
        }
        case "get" => {
          val res: Any = queue.getQueue()
          if(res.isInstanceOf[Exception]){
            println(res.asInstanceOf[Exception])
          }else{
            println(s"取出的数据是 $res")
          }
        }
        case "head" => {
          val res: Any = queue.headQueue()
          if(res.isInstanceOf[Exception]){
            println(res.asInstanceOf[Exception])
          }else{
            println(s"队列头是 $res")
          }
        }
      }
    }
  }

  class ArrayQueue(arrMaxSize:Int){
    val maxSize = arrMaxSize
    val arr = new Array[Int](maxSize)

    var front = -1 //指向队列的头部,front指向队列数据的前一个位置
    var rear = -1 //指向队列的尾部

    //判断队列是否满
    def isFull():Boolean = {
      rear == maxSize - 1
    }

    //判断队列是否空
    def isEmpty():Boolean = {
      rear == front
    }

    //显示队列的所有数据
    def showQueue(): Unit ={
      if(isEmpty()){
        println("队列空")
      }

      for(i <- front + 1 to rear){
        printf("arr(%d)=%d",i,arr(i))
        println()
      }

    }

    //入队列
    def addQueue(n:Int): Unit ={
      //判断是否满
      if(isFull()){
        println("队列满")
      }else{
        rear += 1
        arr(rear) = n
      }
    }

    //出队列
    def getQueue(): Any ={
      //判断是否为空
      if(isEmpty()){
        return new Exception("队列为空")
      }
      front += 1
      return arr(front)
    }

    //查看队列的头元素
    def headQueue(): Any ={
      if(isEmpty()){
        return new Exception("队列空")
      }
      return arr(front + 1)
    }

  }
}
