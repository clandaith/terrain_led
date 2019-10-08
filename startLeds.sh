#!/bin/bash


java -cp .:/opt/pi4j/lib/'*':terrain_leds.jar  com/clandaith/terrain_leds/AptPanel &
java -cp .:/opt/pi4j/lib/'*':terrain_leds.jar  com/clandaith/terrain_leds/SkyTower   &
java -cp .:/opt/pi4j/lib/'*':terrain_leds.jar  com/clandaith/terrain_leds/ShadyFloodLight &
java -cp .:/opt/pi4j/lib/'*':terrain_leds.jar  com/clandaith/terrain_leds/TowerBlinkingLights &
java -cp .:/opt/pi4j/lib/'*':terrain_leds.jar  com/clandaith/terrain_leds/ShadySecurityDoor &
java -cp .:/opt/pi4j/lib/'*':terrain_leds.jar  com/clandaith/terrain_leds/ShortBuildingFloodLight &
java -cp .:/opt/pi4j/lib/'*':terrain_leds.jar  com/clandaith/terrain_leds/ShortBuildingSecurityLight &
java -cp .:/opt/pi4j/lib/'*':terrain_leds.jar  com/clandaith/terrain_leds/ShortBuildingTankLight &
java -cp .:/opt/pi4j/lib/'*':terrain_leds.jar  com/clandaith/terrain_leds/PoliceStation &
java -cp .:/opt/pi4j/lib/'*':terrain_leds.jar  com/clandaith/terrain_leds/GoGoClub &

