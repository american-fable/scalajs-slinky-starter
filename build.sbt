import org.scalajs.linker.interface.ModuleSplitStyle

lazy val `test-vite` = project.in(file("."))
  .enablePlugins(ScalaJSPlugin) // Enable the Scala.js plugin in this project
  .enablePlugins(ScalablyTypedConverterExternalNpmPlugin)
  .settings(
    scalaVersion := "3.3.1",
    scalacOptions ++= Seq("-encoding", "utf-8", "-deprecation", "-feature"),

    // Tell Scala.js that this is an application with a main method
    scalaJSUseMainModuleInitializer := true,

    /* Configure Scala.js to emit modules in the optimal way to
     * connect to Vite's incremental reload.
     * - emit ECMAScript modules
     * - emit as many small modules as possible for classes in the "testvite" package
     * - emit as few (large) modules as possible for all other classes
     *   (in particular, for the standard library)
     */
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
        .withModuleSplitStyle(ModuleSplitStyle.SmallModulesFor(List("testvite")))
    },

    // Depend on Slinky
    libraryDependencies += "me.shadaj" %%% "slinky-core" % "0.7.3", // core React functionality, no React DOM
    libraryDependencies += "me.shadaj" %%% "slinky-web" % "0.7.3", // React DOM, HTML and SVG tags

    // Tell ScalablyTyped that we manage `npm install` ourselves
    externalNpm := baseDirectory.value,
  )
