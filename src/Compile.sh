#!/bin/bash 

make ()
{
    javac *.java
    echo Built successfully.
}

clean ()
{
    rm *.class
    echo Cleaned successfully.   
}

echo clean or make

read input

if [ "$input" = "clean" ]
then
    clean
elif [ "$input" = "make" ]
then
    make
fi