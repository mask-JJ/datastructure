package com.mask.chapter1
import util.control.Breaks._
/**
 * @author dhy
 * @create 2020-08-31 4:59 下午 
 */
object Josephu {
  def main(args: Array[String]): Unit = {
    val boyGame = new BoyGame
    boyGame.addBoy(7)
    boyGame.showBoy()

    boyGame.countBoy(4,3,7)
  }
}
//定义Boy类
class Boy(bNo:Int){
  val no:Int = bNo
  var next:Boy = null
}

class BoyGame{
  //定义一个初始的头节点
  var first:Boy = new Boy(-1)
  //添加小孩,形成一个单向环形的链表
  def addBoy(nums:Int): Unit ={
    if(nums < 1){
      println("nums的值不正确")
      return
    }
    //形成环形链表时需要一个辅助指针
    var curBoy: Boy = null
    for(no <- 1 to nums){
      val boy = new Boy(no)
      if(no == 1){
        first = boy
        first.next = first
        curBoy = first
      }else{
        curBoy.next = boy
        boy.next = first
        curBoy = boy
      }
    }
  }

  //遍历单向环形链表
  def showBoy(): Unit ={
    if(first.next == null){
      println("没有任何小孩")
      return
    }
    //因为first不能动,还是借助一个辅助指针完成遍历
    var curBoy = first

    breakable{
      while(true){
        printf("小孩编号 %d\n",curBoy.no)
        if(curBoy.next == first){
          break()
        }
        curBoy = curBoy.next
      }
    }
  }

  //编写方法countBoy，完成游戏
  //startNo 从第几个人开始数
  //countNum 数几下
  //nums 一共多少人
  def countBoy(startNo:Int,countNum:Int,nums:Int): Unit ={
    if(first.next == null || startNo < 1 || startNo > nums){
      println("参数有误")
      return
    }

    //完成游戏的思路
    /*
    1）在first前面设计一个辅助指针helper
    2）将first指针移动到startNo这个小孩
    3）开始数countNum个数
    4）删除first指向的这个小孩
     */
    var helper = first
    //将helper指针定位到first前面
    breakable {
      while (true) {
        if (helper.next == first) {
          break()
        }
        helper = helper.next
      }
    }

    //将first指针移动到startNo这个小孩
    for(i <- 0 until startNo - 1){
      first = first.next
      helper = helper.next
    }

    //开始数数
    breakable {
      while (true) {
        if (helper == first) {
          break()
        }
        //开始数countNum个数
        for (i <- 0 until countNum - 1) {
          first = first.next
          helper = helper.next
        }
        //输出出圈的人的信息
        printf("小孩%d出圈\n", first.no)

        //将first指向的节点删除
        first = first.next
        helper.next = first

      }
    }

    printf("最后留在圈里的小孩%d\n",first.no)



  }
}
