package edu.nefu.compilerstheory

import scalafx.scene.chart.{LineChart, NumberAxis, XYChart}
import scalafx.scene.control.{Slider, TextField}
import scalafxml.core.macros.sfxml

/**
  * Created by igorramazanov on 12/05/2017.
  */
@sfxml
class ViewController(private val chart: LineChart[Double, Double],
                     private val input: TextField,
                     private val minXmin: TextField,
                     private val minXmax: TextField,
                     private val maxXmin: TextField,
                     private val maxXmax: TextField,
                     private val stepMin: TextField,
                     private val stepMax: TextField,
                     private val minX: Slider,
                     private val maxX: Slider,
                     private val step: Slider,
                     private val minY: TextField,
                     private val maxY: TextField,
                     private val yAxisSlider: Slider,
                     private val xAxis: NumberAxis,
                     private val yAxis: NumberAxis
                ) {

  input.text.onChange(draw())
  yAxis.setAutoRanging(false)
  yAxis.setLabel("y")
  xAxis.setAutoRanging(false)
  xAxis.setLabel("x")

  def draw(): Unit = {
    draw(input.text.value, minX.value.value, maxX.value.value, step.value.value)
  }

  def draw(mathExpression: String, minX: Double, maxX: Double, step: Double): Unit = {
    if (mathExpression.nonEmpty) {
      for {
      tokens <- Lexer(mathExpression)
      ast <- Parser(tokens)
      points = Interpreter.interpret(minX, maxX, step, ast)
    } {
      val series = new XYChart.Series[Double, Double] {
        name = "f(x)"
        data_=(points.map(p => XYChart.Data(p.x, p.y)).toSeq)
      }

      xAxis.setLowerBound(minX)
      xAxis.setUpperBound(maxX)
      xAxis.setTickUnit((maxX - minX) / 20)

      updateYAxis()

      chart.data_=(series)
    }
    }
  }

  private def updateYAxis(): Unit = {
    yAxis.setLowerBound(-yAxisSlider.value.value)
    yAxis.setUpperBound(yAxisSlider.value.value)
    yAxis.setTickUnit(yAxisSlider.value.value / 20)
  }

  minXmin.text.onChange {
    minX.min_=(minXmin.text.value.toDouble)
    updateSliderPrefs(minX)
  }

  def updateSliderPrefs(slider: Slider): Unit = {
    slider.blockIncrement_=((slider.max.value - slider.min.value) / 50)
  }

  minXmax.text.onChange {
    minX.max_=(minXmax.text.value.toDouble)
    updateSliderPrefs(minX)
  }

  maxXmin.text.onChange {
    maxX.min_=(maxXmin.text.value.toDouble)
    updateSliderPrefs(maxX)
  }

  maxXmax.text.onChange {
    maxX.max_=(maxXmax.text.value.toDouble)
    updateSliderPrefs(maxX)
  }

  stepMin.text.onChange {
    step.min_=(stepMin.text.value.toDouble)
    updateSliderPrefs(step)
  }

  stepMax.text.onChange {
    step.max_=(stepMax.text.value.toDouble)
    updateSliderPrefs(step)
  }

  minX.value.onChange(draw())

  maxX.value.onChange(draw())

  step.value.onChange(draw())

  minY.text.onChange {
    yAxisSlider.min_=(minY.text.value.toDouble)
    updateSliderPrefs(yAxisSlider)
  }

  maxY.text.onChange {
    yAxisSlider.max_=(maxY.text.value.toDouble)
    updateSliderPrefs(yAxisSlider)
  }

  yAxisSlider.value.onChange(updateYAxis())

  minY.text.set("1")
  maxY.text.set("20")

  minXmin.text.set("-10")
  minXmax.text.set("-5")
  maxXmin.text.set("5")
  maxXmax.text.set("10")
  stepMin.text.set("0.1")
  stepMax.text.set("1")
}
