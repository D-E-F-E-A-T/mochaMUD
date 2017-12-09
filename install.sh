#!/bin/sh
if [[ $EUID -ne 0 ]]; then
   echo "Please run the coffee{MUD} installer as root." 
   exit 1
fi
echo "Would you like to install coffee{MUD}?"
select yn in "Yes" "No"; do
    case $yn in
        Yes ) break;;
        No ) exit;;
    esac
done
echo "Installing coffee{MUD}..."
mkdir /opt/coffeeMUD-client
cp Precompiled/coffeeMUD.jar /opt/coffeeMUD-client
echo "Copied coffee{MUD} executable to /opt/coffeeMUD-client"
echo "Creating command runnable as 'coffeemud'"
touch /usr/bin/coffeemud
echo "java -jar /opt/coffeeMUD-client/coffeeMUD.jar" > /usr/bin/coffeemud
chmod 755 /usr/bin/coffeemud
echo "Installation complete"
