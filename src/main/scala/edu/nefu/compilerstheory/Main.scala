package edu.nefu.compilerstheory
import java.io.IOException
import java.net.URL

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafxml.core.{FXMLView, NoDependencyResolver}

object Main extends JFXApp {
  val resource: URL = getClass.getResource("/view/view.fxml")
  if (resource == null) {
    throw new IOException("Cannot load resource: view.fxml")
  }

  val root = FXMLView(resource, NoDependencyResolver)

  stage = new PrimaryStage() {
    title = "Math expressions plotter"
    scene = new Scene(root)
  }
}
