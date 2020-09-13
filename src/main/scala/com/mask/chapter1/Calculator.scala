package com.mask.chapter1
import util.control.Breaks._
/**
 * @author dhy
 * @create 2020-09-01 2:23 下午 
 */
object Calculator {
  def main(args: Array[String]): Unit = {
    val expression = "30+2*6-2"
    val numStack = new ArrayStack2(10)
    val operStack = new ArrayStack2(10)

    var index = 0
    var num1 = 0
    var num2 = 0
    var oper = 0
    var res = 0
    var ch = ' '
    var keepNum = ""
    breakable {
      while (true) {
        //扫描expression
        ch = expression.substring(index, index + 1)(0)
        if (operStack.isOper(ch)) {
          if (!operStack.isEmpty()) {
            //如果符号的优先级小于栈顶的符号的优先级
            if (operStack.priority(ch) <= operStack.priority(operStack.stack(operStack.top))) {
              //开始计算
              num1 = numStack.pop().toString.toInt
              num2 = numStack.pop().toString.toInt
              oper = operStack.pop().toString.toInt
              res = numStack.cal(num1, num2, oper)
              //入栈
              numStack.push(res)
              operStack.push(ch)
            } else {
              //如果优先级大
              operStack.push(ch)
            }
          } else {
            operStack.push(ch)
          }
        } else {
          //处理多位数的逻辑
          keepNum += ch
          //如果ch已经是expression的最后一个字符
          if(index == expression.length - 1){
            numStack.push(keepNum.toInt)
          }else{
            //判断ch的下一个字符是不是数字，如果是数字进行下一次扫描
            if(operStack.isOper(expression.substring(index+1,index+2)(0))){
              numStack.push(keepNum.toInt)
              keepNum = ""//清空
            }
//            numStack.push((ch + "").toInt)
          }
        }

        index += 1
        if (index >= expression.length) {
          break()
        }

      }
    }

    //扫描完毕后，依次从数栈和符号栈取出数据，进行运行，最后在数栈中的数据就是结果
    breakable {
      while (true) {
        if (operStack.isEmpty()) {
          break()
        }
        //运算
        num1 = numStack.pop().toString.toInt
        num2 = numStack.pop().toString.toInt
        oper = operStack.pop().toString.toInt
        res = numStack.cal(num1, num2, oper)
        numStack.push(res)
      }
    }

    printf("表达式 %s = %d",expression,numStack.pop().toString.toInt)
  }
}

class ArrayStack2(size:Int){
  val maxSize = size //栈的大小
  var stack = new Array[Int](maxSize)
  //栈顶，初始化为-1
  var top = -1

  //栈满
  def isFull(): Boolean = {
    top == maxSize - 1
  }
  //栈空
  def isEmpty(): Boolean = {
    top == -1
  }

  //入栈
  def push(value:Int): Unit ={
    if(isFull){
      println("栈满")
      return
    }
    top += 1
    stack(top) = value
  }
  //出栈
  def pop(): Any ={
    if(isEmpty()){
      return new Exception("栈空")
    }
    val value = stack(top)
    top -= 1
    return value
  }
  //遍历栈
  def list(): Unit ={
    if(isEmpty()){
      println("栈空，没有数据")
      return
    }
    for(i <- 0 to top reverse){
      printf("stack[%d]=%d\n",i,stack(i))
    }
  }

  //返回运算符的优先级，程序员自己定，数字越大优先级越高
  // + - => 0, * / => 1
  def priority(oper:Int): Int ={
    if(oper == '*' || oper == '/'){
      return 1
    }else if(oper == '+' || oper == '-'){
      return 0
    }else{
      -1 //不正确
    }
  }

  def isOper(value:Int): Boolean ={
    value == '+' || value == '-' || value == '/' || value == '*'
  }

  def cal(num1:Int,num2:Int,oper:Int): Int ={
    var res = 0
    oper match{
      case '+' => {
        res = num1 + num2
      }
      case '-' => {
        res = num2 - num1
      }
      case '*' => {
        res = num1 * num2
      }
      case '/' => {
        res = num2 / num1
      }
    }
    res
  }


}