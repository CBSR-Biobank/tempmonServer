#!/bin/sh

cd ~/lib/scalaserv-0.1
rm -rf RUNNING_PID
nohup bin/scalaserv -J-server -Dconfig.resource=application.conf -Dhttp.port=5005 &
