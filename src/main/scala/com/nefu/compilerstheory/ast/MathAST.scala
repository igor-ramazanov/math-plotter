package com.nefu.compilerstheory.ast

import scala.util.parsing.input.Positional

/**
  * Created by igorramazanov on 09/05/2017.
  */
sealed trait MathAST
sealed trait SingleToken extends MathAST{
  val a: MathAST
}
sealed trait DoubleToken extends MathAST{
  val a: MathAST
  val b: MathAST
}
sealed trait LeafToken extends MathAST

case object Pi extends LeafToken
case object Exponent extends LeafToken

case class Sin(override val a: MathAST) extends SingleToken
case class Cos(override val a: MathAST) extends SingleToken
case class Tg(override val a: MathAST) extends SingleToken
case class Ctg(override val a: MathAST) extends SingleToken

case class Ln(override val a: MathAST) extends SingleToken

case class Abs(override val a: MathAST) extends SingleToken

case class Arcsin(override val a: MathAST) extends SingleToken
case class Arccos(override val a: MathAST) extends SingleToken
case class Arctg(override val a: MathAST) extends SingleToken
case class Arcctg(override val a: MathAST) extends SingleToken

case class Sqrt(override val a: MathAST) extends SingleToken

case class Mul(override val a: MathAST, override val b: MathAST) extends DoubleToken
case class Add(override val a: MathAST, override val b: MathAST) extends DoubleToken
case class Sub(override val a: MathAST, override val b: MathAST) extends DoubleToken
case class Div(override val a: MathAST, override val b: MathAST) extends DoubleToken
case class Pow(override val a: MathAST, override val b: MathAST) extends DoubleToken

case class X() extends LeafToken
case class Constant(value: Double) extends LeafToken