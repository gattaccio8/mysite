#!/bin/sh


echo removing old libs

rm -r .idea/*
rm -rf .idea_modules/*

echo Updating dependencies

./sbt.sh clean update gen-idea

echo "intelliJ" needs help with the !?*.props files in sdk settings
