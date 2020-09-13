package com.mask.chapter1
import util.control.Breaks._
/**
 * @author dhy
 * @create 2020-08-30 9:15 下午 
 */
object SingleLinkedListDemo {
  def main(args: Array[String]): Unit = {
    val hero1 = new HeroNode(1, "宋江", "及时雨")
    val hero3 = new HeroNode(3, "宋江3", "及时雨3")
    val hero4 = new HeroNode(4, "宋江4", "及时雨4")
    val hero2 = new HeroNode(2, "宋江2", "及时雨2")
    val hero5 = new HeroNode(2, "吴用", "智多星")

    val singleLinkedList = new SingleLinkedList
//    singleLinkedList.add(hero1)
//    singleLinkedList.add(hero3)
//    singleLinkedList.add(hero4)
//    singleLinkedList.add(hero2)

    singleLinkedList.add2(hero1)
    singleLinkedList.add2(hero3)
    singleLinkedList.add2(hero4)
    singleLinkedList.add2(hero2)

    singleLinkedList.list()

    println("========================")
    singleLinkedList.update(hero5)
    singleLinkedList.list()

    println("------------------------")
    singleLinkedList.del(1)
    singleLinkedList.list()

  }

}
class SingleLinkedList{
  //先初始化一个头节点，头节点一般不会变动
  val head = new HeroNode(0,"","")

  //添加
  //第一种方法，在添加英雄时，直接添加到链表的尾部
  def add(heroNode: HeroNode): Unit ={
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
  }

  //第二种添加方法，有序添加，这里默认从大到小
  def add2(heroNode: HeroNode): Unit ={
    var temp = head
    var flag = false
    breakable{
      while(true){
        if(temp.next == null){
          break()
        }
        if(temp.next.no > heroNode.no){
          break()
        }else if(temp.next.no == heroNode.no){
          flag = true
          break()
        }
        temp = temp.next
      }
    }
    if(!flag){
      heroNode.next = temp.next
      temp.next = heroNode
    }else{
      println("hero已经存在")
    }
  }

  //修改
  def update(newHeroNode:HeroNode): Unit ={
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
  def del(no:Int): Unit ={
    var temp = head
    var flag = false
    breakable{
      while(true){
        if(temp.next == null){
          break()
        }
        if(temp.next.no == no){
          flag = true
          break()
        }
        temp = temp.next
      }
    }

    if(flag){
      temp.next = temp.next.next
    }else{
      printf("要删除的no=%d 不存在\n",no)
    }
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
}
//先创建HeroNode
class HeroNode(hNo:Int,hName:String,hNickname:String){
  var no: Int = hNo
  var name: String = hName
  var nickname: String = hNickname
  var next: HeroNode = null
}
