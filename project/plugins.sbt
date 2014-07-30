// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3.2")

// plugin for Play Framework apps which works with a Chrome Extension to auto-refresh your browser
// when changes are made to the web app
addSbtPlugin("com.jamesward" %% "play-auto-refresh" % "0.0.8")
