#!/bin/bash

case "$1" in 
    start)
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
        ;;
    stop)
        kill `pgrep -f AptPanel`
        kill `pgrep -f SkyTower`
        kill `pgrep -f ShadyFloodLight`
        kill `pgrep -f TowerBlinkingLights`
        kill `pgrep -f ShadySecurityDoor`
        kill `pgrep -f ShortBuildingFloodLight`
        kill `pgrep -f ShortBuildingSecurityLight`
        kill `pgrep -f ShortBuildingTankLight`
        kill `pgrep -f GoGoClub`
        kill `pgrep -f PoliceStation`
        ;;
    *)
        echo "Usage: $0 {start|stop}"
esac

exit 0 
