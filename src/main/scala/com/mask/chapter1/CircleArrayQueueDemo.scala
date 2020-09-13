package com.mask.chapter1

import com.mask.chapter1.ArrayQueueDemo.ArrayQueue

import scala.io.StdIn

/**
 * @author dhy
 * @create 2020-08-28 1:40 下午 
 */
object CircleArrayQueueDemo {
  def main(args: Array[String]): Unit = {
    //初始化一个队列
    val queue = new CircleArrayQueue(4)
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

}
class CircleArrayQueue(arrMaxSize:Int){
  val maxSize = arrMaxSize
  val arr = new Array[Int](maxSize) //存放数据，模拟队列
  var front = 0 //指向队列的头部
  var rear = 0 //指向队列的尾部

  //判断队列满的方法
  def isFull(): Boolean = {
    (rear + 1) % maxSize == front
  }

  //判断队列为空的条件
  def isEmpty():Boolean = {
    rear == front
  }

  //添加数据到队列
  def addQueue(n:Int): Unit ={
    if(isFull()){
      println("队列满，无法加入")
      return
    }
    //将数据加入
    arr(rear) = n
    //然后将rear后移
    rear = (rear + 1) % maxSize
  }

  //取出数据
  def getQueue():Any = {
    if(isEmpty()){
      return new Exception("队列空")
    }
    val value = arr(front)
    front = (front + 1) % maxSize
    return value
  }

  //显示队列
  def showQueue(): Unit = {
    if(isEmpty()){
      println("队列空，没有数据")
      return
    }
    for(i <- front until front + size()){
      printf("arr[%d]=%d\n",i % maxSize,arr(i % maxSize))
    }
  }

  def size(): Int = {
    (rear + maxSize - front) % maxSize //当前环形队列有几个元素
  }

  def headQueue(): Any ={
    if(isEmpty()){
      return new Exception("队列空")
    }
    return arr(front)
  }
}
