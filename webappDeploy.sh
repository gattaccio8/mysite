#!/bin/bash

nohup java -cp $(echo *.jar | tr ' ' ':') webserver.WebServer > app.log 2>&1 &
