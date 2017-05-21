package com.nefu.compilerstheory.error

/**
  * Created by igorramazanov on 09/05/2017.
  */
sealed trait CompilationError
case class LexerError(msg: String) extends CompilationError
case class ParserError(msg: String) extends CompilationError