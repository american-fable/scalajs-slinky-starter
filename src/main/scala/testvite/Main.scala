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
import slinky.web.SyntheticKeyboardEvent
import slinky.web.SyntheticInputEvent

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

  def onKeyDownCaptureHandler(event: SyntheticKeyboardEvent[HTMLDivElement]): Unit = { dom.window.alert("onKeyDownCapture") }
  def onKeyDownHandler(event: SyntheticKeyboardEvent[HTMLDivElement]): Unit = { dom.window.alert("onKeyDown") }
  def onKeyPressCaptureHandler(event: SyntheticKeyboardEvent[HTMLDivElement]): Unit = { dom.window.alert("onKeyPressCapture") }
  def onKeyPressHandler(event: SyntheticKeyboardEvent[HTMLDivElement]): Unit = { dom.window.alert("onKeyPress") }
  def onKeyUpCaptureHandler(event: SyntheticKeyboardEvent[HTMLDivElement]): Unit = { dom.window.alert("onKeyUpCapture") }
  def onKeyUpHandler(event: SyntheticKeyboardEvent[HTMLDivElement]): Unit = { dom.window.alert("onKeyUp") }


  def main(args: Array[String]): Unit = {
    ReactDOM.render(
      div(className := "App")(

        h2("3 phases of event propagation example"),
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
            input(placeholder := "onKeyDown", onKeyDown := (event => { println("onKeyDown") })),
            input(placeholder := "onKeyPress", onKeyPress := (event => { println("onKeyPress") })),
            input(placeholder := "onKeyUp", onKeyUp := (event => { println("onKeyUp") }))
          ),
          // doesn't work in Slinky v0.7.4
          li(
             input(placeholder := "onKeyUpCapture", onKeyUpCapture := (event => { println("onKeyUpCapture") })),
             input(placeholder := "onKeyPressCapture", onKeyPressCapture := (event => { println("onKeyPressCapture") })),
             input(placeholder := "onKeyDownCapture", onKeyDownCapture := (event => { println("onKeyDownCapture") }))
          )
        ),

        // doesn't work in Slinky v0.7.4
        h2("keyboard events propagation test"),
        /**
          *  This test required to click area and press key button
          *  but UP events only works if cursor placed outside area where
          *  browser popup window appears.
          */
        div(onKeyDownCapture := (event => onKeyDownCaptureHandler(event)),
            onKeyUpCapture := (event => onKeyUpCaptureHandler(event)),
            onKeyUp := (event => onKeyUpHandler(event))) {
          div(onKeyDown := (event => onKeyDownHandler(event))) {
            div(onKeyPressCapture := (event => onKeyPressCaptureHandler(event))) {
              div(className := "ClickArea",
                // may not work without 'tabIndex',
                // see: https://stackoverflow.com/questions/43503964/onkeydown-event-not-working-on-divs-in-react
                tabIndex := -1,
                onKeyPress := (event => onKeyPressHandler(event))) { "Click here and then press any key" }
            }
          }
        },

        // doesn't work in Slinky v0.7.4
        h2("onBeforeInput test"),
        div() {
          input(placeholder := "onBeforeInput",
            onKeyDown := (event => { println("onKeyDown") }),
            onKeyUpCapture := (event => { println(s"onKeyUp, key code:${event.keyCode}") }),
            onBeforeInputCapture := (event => { println(s"onBeforeInputCapture, data: ${event.data}") }),
            onInput := (event => { println(s"onInput") }),
            onBeforeInput := (event => { println(s"onBeforeInput, data: ${event.data}") }))
        }

      ),
      dom.document.getElementById("app")
    )
  }
}
