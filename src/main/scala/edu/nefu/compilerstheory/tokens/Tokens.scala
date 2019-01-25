package edu.nefu.compilerstheory.tokens

/**
  * Created by igorramazanov on 09/05/2017.
  */
sealed trait Token

case class CONSTANT(number: Double) extends Token
case class VARIABLE() extends Token

case class SIN() extends Token
case class COS() extends Token
case class TG() extends Token
case class CTG() extends Token
case class LN() extends Token
case class SQRT() extends Token
case class ARCCOS() extends Token
case class ARCSIN() extends Token
case class ARCTG() extends Token
case class ARCCTG() extends Token

case class ADD() extends Token
case class MUL() extends Token
case class SUB() extends Token
case class DIV() extends Token
case class POW() extends Token
case class ABS() extends Token

case class LBRACE() extends Token
case class RBRACE() extends Token
case class COMMA() extends Token
case class PI() extends Token
case class E() extends Token
