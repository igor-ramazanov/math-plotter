package com.nefu.compilerstheory.calculator

import com.nefu.compilerstheory.ast._

/**
  * Created by igorramazanov on 09/05/2017.
  */

case class Point(x: Double, y: Double)

object Calculator {
  def calculate(x: Double, mathAST: MathAST): Double = {
    mathAST match {
      case X() => x
      case Pi => math.Pi
      case Exponent => math.E
      case Constant(c) => c
      case Mul(a, b) => calculate(x, a) * calculate(x, b)
      case Div(a, b) => calculate(x, a) / calculate(x, b)
      case Add(a, b) => calculate(x, a) + calculate(x, b)
      case Sub(a, b) => calculate(x, a) - calculate(x, b)
      case Pow(a, b) => math.pow(calculate(x, a), calculate(x, b))
      case Sqrt(a) => math.sqrt(calculate(x, a))
      case Arcsin(a) => math.asin(calculate(x, a))
      case Arccos(a) => math.acos(calculate(x, a))
      case Arctg(a) => math.atan(calculate(x, a))
      case Arcctg(a) => math.atan(1 / calculate(x, a))
      case Cos(a) => math.cos(calculate(x, a))
      case Sin(a) => math.sin(calculate(x, a))
      case Tg(a) => math.tan(calculate(x, a))
      case Ctg(a) => 1 / math.tan(calculate(x, a))
      case Abs(a) => math.abs(calculate(x, a))
      case Ln(a) => math.log(calculate(x, a))
    }
  }

  def calculate(start: Double, stop: Double, step: Double, mathAST: MathAST): Iterator[Point] = {
    val iterator = Iterator.iterate(start)(_ + step).takeWhile(_ <= stop)
    iterator.map(n => Point(n, calculate(n, mathAST)))
  }
}
