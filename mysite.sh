#!/bin/bash

DEBUG="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=9005"

function start() {
    nohup java -cp $(echo *.jar | tr ' ' ':') webserver.WebServer | awk '{ print strftime(), $0; fflush() }' >> stdout.log 2>&1 &
}

function stop() {
    PID=`currentpid`
    echo "Killing PID ${PID}...."
    kill $PID
}

function status() {
    PID=`currentpid`
    if [[ -n $PID ]]; then
        echo "WebServer is running..."
    else
        echo "WebServer is NOT running..."
    fi
}


function currentpid() {
    ps -ef | awk '/[w]ebserver/{print $2}'
}

case "$1" in
    'start')
    echo "Starting application"
    start
;;
    'stop')
    echo "Stopping application"
    stop
;;
    'status')
    status
;;
esac

