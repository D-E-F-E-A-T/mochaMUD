#!/bin/sh
ROOTUID="0"

if [ "$(id -u)" -ne "$ROOTUID" ] ; then
    echo "This script must be executed with root privileges."
    exit 1
fi

echo -n "Would you like to install coffee{MUD}? (Y/N)"
read answer
if echo "$answer" | grep -iq "^y" ;then
    echo "Installing coffee{MUD}..."
	mkdir /opt/coffeeMUD-client
	cp Precompiled/coffeeMUD.jar /opt/coffeeMUD-client
	echo "Copied coffee{MUD} executable to /opt/coffeeMUD-client"
	echo "Creating command runnable as 'coffeemud'"
	touch /usr/bin/coffeemud
	echo "java -jar /opt/coffeeMUD-client/coffeeMUD.jar" > /usr/bin/coffeemud
	chmod 755 /usr/bin/coffeemud
	echo "Installation complete"
    
else
    echo "Cancelled installation."
fi
