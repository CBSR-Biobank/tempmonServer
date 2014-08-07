# Architecture 

There are two components to the tempmonServer software: a simple UDP broadcast Python script component and a web interface component.

The UDP broadcast Python script component continuously broadcasts the server's IP and the port the web interface is operating on across the server's local area network. From this broadcast any listening client processes listening can ascertain the location to acquire runtime specifications and perform uploads. The Python script is run as a daemon service on server startup and run continuously until server shutdown.

The web interface component of the tempmon application conforms to the [MVC](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) framework required by the Play! framework. 