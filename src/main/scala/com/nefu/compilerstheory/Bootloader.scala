package com.nefu.compilerstheory

import java.io.IOException
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafxml.core.{NoDependencyResolver, FXMLView}

object Bootloader extends JFXApp {
  val resource = getClass.getResource("view/view.fxml")
  if (resource == null) {
    throw new IOException("Cannot load resource: view.fxml")
  }

  val root = FXMLView(resource, NoDependencyResolver)

  stage = new PrimaryStage() {
    title = "Мат. выражения, ИВТ-14 Рамазанов Игорь"
    scene = new Scene(root)
  }
}