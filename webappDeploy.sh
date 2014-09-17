#!/bin/bash

nohup java -cp $(echo *.jar | tr ' ' ':') webserver.WebServer
