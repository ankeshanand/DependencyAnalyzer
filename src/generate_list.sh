#!/usr/bin/env bash
javac $1 -verbose >>javac_out.txt 2>&1
if [ -f "./a.out" ]; then
        echo ""
    else
        echo "./a.out is not present.Compiling now..";
        g++ class_dependencies.cpp
fi
./a.out 
rm -rf javac_out.txt


