package edu.nefu.compilerstheory
import edu.nefu.compilerstheory.tokens._

import scala.util.matching.Regex
import scala.util.parsing.combinator.RegexParsers

/**
  * Created by igorramazanov on 09/05/2017.
  */
object Lexer extends RegexParsers {
  override def skipWhitespace = true

  override val whiteSpace: Regex = "[ \t\r\f]+".r

  def apply(mathExpression: String): Either[CompilationError, List[Token]] = {
    parse(tokens, mathExpression) match {
      case NoSuccess(msg, _) => Left(LexerError(msg))
      case Success(result, _) => Right(result)
    }
  }

  def tokens: Parser[List[Token]] = {
    phrase(rep(lBrace | rBrace | sqrt | sin | cos | tg | ctg | arctg | arcctg | arcsin | arccos | ln | abs | pi | e | constant | pow | mul | add | sub | div | x))
  }

  def constant: Parser[CONSTANT] = "[0-9]+(\\.[0-9]+)?".r ^^ { str => CONSTANT(str.toDouble) }

  def x: Parser[VARIABLE] = "x" ^^ (_ => VARIABLE())

  def add: Parser[ADD] = "+" ^^ (_ => ADD())

  def sub: Parser[SUB] = "-" ^^ (_ => SUB())

  def mul: Parser[MUL] = "*" ^^ (_ => MUL())

  def div: Parser[DIV] = "/" ^^ (_ => DIV())

  def pow: Parser[POW] = "^" ^^ (_ => POW())

  def sin: Parser[SIN] = "sin" ^^ (_ => SIN())

  def cos: Parser[COS] = "cos" ^^ (_ => COS())

  def tg: Parser[TG] = "tg" ^^ (_ => TG())

  def ctg: Parser[CTG] = "ctg" ^^ (_ => CTG())

  def abs: Parser[ABS] = "abs" ^^ (_ => ABS())

  def sqrt: Parser[SQRT] = "sqrt" ^^ (_ => SQRT())

  def arctg: Parser[ARCTG] = "arctg" ^^ (_ => ARCTG())

  def arcctg: Parser[ARCCTG] = "arcctg" ^^ (_ => ARCCTG())

  def arcsin: Parser[ARCSIN] = "arcsin" ^^ (_ => ARCSIN())

  def arccos: Parser[ARCCOS] = "arccos" ^^ (_ => ARCCOS())

  def ln: Parser[LN] = "ln" ^^ (_ => LN())

  def pi: Parser[PI] = "pi" ^^ (_ => PI())

  def e: Parser[E] = "e" ^^ (_ => E())

  def lBrace: Parser[LBRACE] = "(" ^^ (_ => LBRACE())

  def rBrace: Parser[RBRACE] = ")" ^^ (_ => RBRACE())
}
