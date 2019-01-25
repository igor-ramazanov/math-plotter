package edu.nefu.compilerstheory
import edu.nefu.compilerstheory.ast._
import edu.nefu.compilerstheory.tokens._

import scala.util.parsing.combinator.Parsers
import scala.util.parsing.input.{NoPosition, Position, Reader}

/**
  * Created by igorramazanov on 09/05/2017.
  */
object Parser extends Parsers {
  override type Elem = Token

  class MathTokenReader(tokens: List[Token]) extends Reader[Token] {
    override def first: Token = tokens.head

    override def rest: Reader[Token] = new MathTokenReader(tokens.tail)

    override def pos: Position = NoPosition

    override def atEnd: Boolean = tokens.isEmpty
  }

  def apply(tokens: List[Token]): Either[ParserError, AST] = {
    val reader = new MathTokenReader(tokens)
    expression.apply(reader) match {
      case NoSuccess(msg, _)  => Left(ParserError(msg))
      case Success(result, _) => Right(result)
    }
  }

  def expression: Parser[AST] = {
    term ~ rep(ADD() ~ term | SUB() ~ term) ^^ {
      case f ~ list =>
        list.foldLeft(f) {
          case (x, ADD() ~ y) => Add(x, y)
          case (x, SUB() ~ y) => Sub(x, y)
        }
    }
  }

  def term: Parser[AST] = {
    val v = power | factor
    v ~ rep(MUL() ~ v | DIV() ~ v) ^^ {
      case f ~ list =>
        list.foldLeft(f) {
          case (x, MUL() ~ y) => Mul(x, y)
          case (x, DIV() ~ y) => Div(x, y)
        }
    }
  }

  def power: Parser[AST] = {
    factor ~ POW() ~ factor ^^ { case x ~ POW() ~ y => Pow(x, y) }
  }

  def factor: Parser[AST] = {
    x | constant | unaryOperation | LBRACE() ~ expression ~ RBRACE() ^^ {
      case _ ~ e ~ _ => e
    }
  }

  def unaryOperation: Parser[AST] = {
    val _sin = SIN() ~ LBRACE() ~ expression ~ RBRACE() ^^ {
      case SIN() ~ _ ~ e ~ _ => Sin(e)
    }
    val _cos = COS() ~ LBRACE() ~ expression ~ RBRACE() ^^ {
      case COS() ~ _ ~ e ~ _ => Cos(e)
    }
    val _ln = LN() ~ LBRACE() ~ expression ~ RBRACE() ^^ {
      case LN() ~ _ ~ e ~ _ => Ln(e)
    }
    val _tg = TG() ~ LBRACE() ~ expression ~ RBRACE() ^^ {
      case TG() ~ _ ~ e ~ _ => Tg(e)
    }
    val _ctg = CTG() ~ LBRACE() ~ expression ~ RBRACE() ^^ {
      case CTG() ~ _ ~ e ~ _ => Ctg(e)
    }
    val _abs = ABS() ~ LBRACE() ~ expression ~ RBRACE() ^^ {
      case ABS() ~ _ ~ e ~ _ => Abs(e)
    }
    val _arcsin = ARCSIN() ~ LBRACE() ~ expression ~ RBRACE() ^^ {
      case ARCSIN() ~ _ ~ e ~ _ => Arcsin(e)
    }
    val _arccos = ARCCOS() ~ LBRACE() ~ expression ~ RBRACE() ^^ {
      case ARCCOS() ~ _ ~ e ~ _ => Arccos(e)
    }
    val _arctg = ARCTG() ~ LBRACE() ~ expression ~ RBRACE() ^^ {
      case ARCTG() ~ _ ~ e ~ _ => Arctg(e)
    }
    val _arcctg = ARCCTG() ~ LBRACE() ~ expression ~ RBRACE() ^^ {
      case ARCCTG() ~ _ ~ e ~ _ => Arcctg(e)
    }
    val _sqrt = SQRT() ~ LBRACE() ~ expression ~ RBRACE() ^^ {
      case SQRT() ~ _ ~ e ~ _ => Sqrt(e)
    }

    _sin | _cos | _ln | _tg | _ctg | _abs | _arcsin | _arccos | _arctg | _arcctg | _sqrt
  }

  def x: Parser[AST] = accept(VARIABLE()) ^^ (_ => X())

  def constant: Parser[AST] = {
    accept("string constant", {
      case CONSTANT(value) => Constant(value)
      case PI()            => Pi
      case E()             => Exponent
    })
  }
}
