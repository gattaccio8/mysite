#!/bin/bash

java -cp $(echo *.jar | tr ' ' ':') webserver.WebServer
