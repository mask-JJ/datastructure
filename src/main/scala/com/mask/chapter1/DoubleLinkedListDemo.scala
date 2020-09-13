package com.mask.chapter1

import scala.util.control.Breaks.{break, breakable}

/**
 * @author dhy
 * @create 2020-08-31 3:17 下午 
 */
object DoubleLinkedListDemo {
  def main(args: Array[String]): Unit = {
    val hero1 = new HeroNode2(1, "宋江", "及时雨")
    val hero3 = new HeroNode2(3, "宋江3", "及时雨3")
    val hero4 = new HeroNode2(4, "宋江4", "及时雨4")
    val hero2 = new HeroNode2(2, "宋江2", "及时雨2")
    val hero5 = new HeroNode2(2, "吴用", "智多星")

    val doubleLinkedList = new DoubleLinkedList
    doubleLinkedList.add(hero1)
    doubleLinkedList.add(hero3)
    doubleLinkedList.add(hero4)
    doubleLinkedList.add(hero2)

    doubleLinkedList.list()
  }
}

class DoubleLinkedList{
  //先初始化一个头节点，头节点一般不会动
  val head = new HeroNode2(0,"","")

  //添加-遍历-修改-删除
  //添加
  //第一种方法，在添加英雄时，直接添加到链表的尾部
  def add(heroNode: HeroNode2): Unit ={
    var temp = head
    //找到链表的最后
    breakable {
      while (true) {
        if (temp.next == null) {
          break()
        }
        //如果没有找到最后
        temp = temp.next
      }
    }

    //当退出while循环时，temp就是链表的最后
    temp.next = heroNode
    heroNode.pre = temp
  }

  //遍历单向链表
  def list(): Unit ={
    //判断当前链表是否为空
    if(head.next == null){
      println("链表为空")
      return
    }

    //因为头节点不能动，因此我们需要有一个临时节点，作为辅助
    var temp = head.next
    breakable {
      while (true) {
        if (temp == null) {
          break()
        }

        printf("节点信息 no=%d name=%s nickname=%s\n",
          temp.no, temp.name, temp.nickname)

        temp = temp.next
      }
    }
  }

  //修改
  def update(newHeroNode:HeroNode2): Unit ={
    if(head.next == null){
      println("链表为空")
      return
    }
    var temp = head.next
    var flag = false
    breakable{
      while(true){
        if(temp == null){
          break()
        }
        if(temp.no == newHeroNode.no){
          flag = true
          break()
        }
        temp = temp.next
      }
    }
    if(flag){
      temp.name = newHeroNode.name
      temp.nickname = newHeroNode.nickname
    }else{
      printf("没有找到编号为%d节点，不能修改\n",newHeroNode.no)
    }
  }
  //删除
  //双向链表可以实现自我删除
  def del(no:Int): Unit ={
    if(head.next == null){
      println("链表为空")
      return
    }
    var temp = head.next
    var flag = false
    breakable{
      while(true){
        if(temp == null){
          break()
        }
        if(temp.no == no){
          flag = true
          break()
        }
        temp = temp.next
      }
    }

    if(flag){
      temp.pre.next = temp.next
      if(temp.next != null){
        temp.next.pre = temp.pre
      }

    }else{
      printf("要删除的no=%d 不存在\n",no)
    }
  }

}
class HeroNode2(hNo:Int,hName:String,hNickname:String){
  var no: Int = hNo
  var name: String = hName
  var nickname: String = hNickname
  var pre: HeroNode2 = null
  var next: HeroNode2 = null
}
