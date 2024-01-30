package testvite

import scala.scalajs.js
import scala.scalajs.js.JSConverters.*
import org.scalajs.dom
import slinky.core._
import slinky.web.ReactDOM
import slinky.web.html._
import typings.react.reactStrings.alert
import org.scalajs.dom.HTMLInputElement
import org.scalajs.dom.Event
import org.scalajs.dom.HTMLButtonElement
import org.scalajs.dom.HTMLDivElement
import slinky.web.SyntheticMouseEvent

object Main {

  def onClickHandlerCapturing(event: SyntheticMouseEvent[HTMLDivElement]): Unit = {
    dom.window.alert("handleClick capturing phase")
  }

  def onClickHandlerTarget(event: SyntheticEvent[HTMLButtonElement, Event]): Unit = {
    dom.window.alert("handleClick target phase")
  }

  def onClickHandlerBubbling(event: SyntheticMouseEvent[HTMLDivElement]): Unit = {
    dom.window.alert("handleClick bubbling phase")
  }

  def main(args: Array[String]): Unit = {
    ReactDOM.render(
      div(className := "App")(
        h1("3 phases of event propagation example"),
        div(onClick := (event => onClickHandlerCapturing(event)))(
          div(onClick := (event => onClickHandlerBubbling(event)))(
            div(onClick := (event => onClickHandlerBubbling(event)))(
              button(onClick := (event => onClickHandlerTarget(event)))("Click Me!")
            )
          )
        ),
        h2("keyboard events"),
        ul(
          li(
            input(onKeyDown := (event => { println("onKeyDown") })),
            input(onKeyPress := (event => { println("onKeyPress") })),
            input(onKeyUp := (event => { println("onKeyUp") }))
          ),
          /// Following doesn't work but it should. ///
          li(
            input(onKeyUpCapture := (event => { println("onKeyUpCapture") })),
            input(onKeyPressCapture := (event => { println("onKeyPressCapture") })),
            input(onKeyDownCapture := (event => { println("onKeyDownCapture") }))
          )
        )
      ),
      dom.document.getElementById("app")
    )
  }
}
