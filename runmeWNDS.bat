@echo off
javac slots/rungame/*.java
javac slots/guigame/*.java
javac slots/gamelogic/*.java
javac slots/exception/*.java
javac slots/test/*.java
cls
java slots.rungame.Play
