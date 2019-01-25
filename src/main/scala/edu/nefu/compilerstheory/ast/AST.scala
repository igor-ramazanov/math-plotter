package edu.nefu.compilerstheory.ast

/**
  * Created by igorramazanov on 09/05/2017.
  */
sealed trait AST
sealed trait Unary extends AST{
  val a: AST
}
sealed trait Binary extends AST{
  val a: AST
  val b: AST
}
sealed trait Leaf extends AST

case object Pi extends Leaf
case object Exponent extends Leaf

case class Sin(override val a: AST) extends Unary
case class Cos(override val a: AST) extends Unary
case class Tg(override val a: AST) extends Unary
case class Ctg(override val a: AST) extends Unary

case class Ln(override val a: AST) extends Unary

case class Abs(override val a: AST) extends Unary

case class Arcsin(override val a: AST) extends Unary
case class Arccos(override val a: AST) extends Unary
case class Arctg(override val a: AST) extends Unary
case class Arcctg(override val a: AST) extends Unary

case class Sqrt(override val a: AST) extends Unary

case class Mul(override val a: AST, override val b: AST) extends Binary
case class Add(override val a: AST, override val b: AST) extends Binary
case class Sub(override val a: AST, override val b: AST) extends Binary
case class Div(override val a: AST, override val b: AST) extends Binary
case class Pow(override val a: AST, override val b: AST) extends Binary

case class X() extends Leaf
case class Constant(value: Double) extends Leaf