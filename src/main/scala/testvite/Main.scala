package testvite

import scala.scalajs.js
import scala.scalajs.js.JSConverters.*
import org.scalajs.dom
import slinky.core._
import slinky.web.ReactDOM
import slinky.web.html._

object Main {
  def main(args: Array[String]): Unit = {
    ReactDOM.render(
      h1("Hello, world!"),
      dom.document.getElementById("app")
    )
  }
}
