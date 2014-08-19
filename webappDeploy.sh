#!/bin/bash

java -cp $(echo dist/*.jar | tr ' ' ':') webserver.WebServer
