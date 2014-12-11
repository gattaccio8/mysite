#!/bin/bash



function deploy() {
    ./sbt.sh clean dist
    local approot="cd /root/app/"
    local stop="./mysite.sh stop"
    local root="cd /root"
    local removeOld="rm -rf app"
    local makeAppRoot="mkdir /root/app"
    local killoldversion="${approot} && ${root} && ${removeOld} && ${makeAppRoot}"

    ssh root@ipaddress ${killoldversion}

    scp target/dist.zip root@ipaddress:/root/app


    local unpack="unzip dist.zip"
    local makeexecutable="chmod a+x mysite.sh"
    local script="./mysite.sh start"
    local torun="${approot} && ${unpack} && ${makeexecutable} && ${script}"
    ssh root@ipaddress ${torun}
}

deploy

