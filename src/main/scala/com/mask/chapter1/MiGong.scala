package com.mask.chapter1

/**
 * @author dhy
 * @create 2020-09-03 7:16 上午 
 */
object MiGong {
  def main(args: Array[String]): Unit = {
    //地图
    val map: Array[Array[Int]] = Array.ofDim[Int](8, 7)
    //上下全部置1
    for(i <- 0 until 7){
      map(0)(i) = 1
      map(7)(i) = 1
    }
    // 左右全部置1
    for(j <- 0 until 8){
      map(j)(0) = 1
      map(j)(6) = 1
    }

    //设置挡板
    map(3)(1) = 1
    map(3)(2) = 1
    //打印地图
    for(i <- 0 until 8){
      for(j <- 0 until 7){
        print(map(i)(j) + " ")
      }
      println()
    }



    //使用递归回溯来找路
    //map 地图
    //i j 地图从哪个点开始出发
    //约定元素的值，0：可以走还没有走，1：墙，2：表示可以走，3：表示已经走过，但是是死路
    //确定一个策略：下 -->,右 -->,上 -->,左
    def setWay(map:Array[Array[Int]],i:Int,j:Int): Boolean ={
      if(map(6)(5) == 2){ //表示路已经找到了
        true
      }else{
        if(map(i)(j) == 0){ //0可以走还没有走
          //这里开始递归回溯
          map(i)(j) = 2
          if(setWay(map,i+1,j)){ //下
            true
          }else if(setWay(map,i,j+1)){//右
            true
          }else if(setWay(map,i-1,j)){//上
            true
          }else if(setWay(map,i,j-1)){//左
            true
          }else{
            map(i)(j) = 3
            false
          }
        }else{ //map(i)(j) != 0 值只能是1，2，3
          false
        }
      }
    }

  }
}
