#!/bin/bash

java -cp .:/opt/pi4j/lib/'*':terrain_leds.jar  com/clandaith/terrain_leds/AptPanel &
java -cp .:/opt/pi4j/lib/'*':terrain_leds.jar  com/clandaith/terrain_leds/SkyTower   &
java -cp .:/opt/pi4j/lib/'*':terrain_leds.jar  com/clandaith/terrain_leds/ShadyBuilding &
java -cp .:/opt/pi4j/lib/'*':terrain_leds.jar  com/clandaith/terrain_leds/PowerStation &
java -cp .:/opt/pi4j/lib/'*':terrain_leds.jar  com/clandaith/terrain_leds/PoliceStation &
java -cp .:/opt/pi4j/lib/'*':terrain_leds.jar  com/clandaith/terrain_leds/GoGoClub &
