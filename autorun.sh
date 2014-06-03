#!/bin/sh

cd ~/lib/scalaserv-0.1
nohup bin/scalaserv -J-server -Dconfig.resource=application.conf -Dhttp.port=5005 &
