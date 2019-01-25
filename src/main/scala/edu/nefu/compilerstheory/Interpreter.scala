package edu.nefu.compilerstheory
import edu.nefu.compilerstheory.ast._

/**
  * Created by igorramazanov on 09/05/2017.
  */

case class Point(x: Double, y: Double)

object Interpreter {
  private def interpret(x: Double, ast: AST): Double = {
    ast match {
      case X() => x
      case Pi => math.Pi
      case Exponent => math.E
      case Constant(c) => c
      case Mul(a, b) => interpret(x, a) * interpret(x, b)
      case Div(a, b) => interpret(x, a) / interpret(x, b)
      case Add(a, b) => interpret(x, a) + interpret(x, b)
      case Sub(a, b) => interpret(x, a) - interpret(x, b)
      case Pow(a, b) => math.pow(interpret(x, a), interpret(x, b))
      case Sqrt(a) => math.sqrt(interpret(x, a))
      case Arcsin(a) => math.asin(interpret(x, a))
      case Arccos(a) => math.acos(interpret(x, a))
      case Arctg(a) => math.atan(interpret(x, a))
      case Arcctg(a) => math.atan(1 / interpret(x, a))
      case Cos(a) => math.cos(interpret(x, a))
      case Sin(a) => math.sin(interpret(x, a))
      case Tg(a) => math.tan(interpret(x, a))
      case Ctg(a) => 1 / math.tan(interpret(x, a))
      case Abs(a) => math.abs(interpret(x, a))
      case Ln(a) => math.log(interpret(x, a))
    }
  }

  def interpret(start: Double, stop: Double, step: Double, ast: AST): Iterator[Point] = {
    val iterator = Iterator.iterate(start)(_ + step).takeWhile(_ <= stop)
    iterator.map(n => Point(n, interpret(n, ast)))
  }
}
