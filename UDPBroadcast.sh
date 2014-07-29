#!/bin/sh

# to install: 
# : mv /path/to/UDPBroadcast.sh /etc/init.d/
# : sudo chmod 755 /etc/init.d/UDPBroadcast.sh
# : mv /path/to/UDPReceive.py /usr/local/lib
# : sudo chmod 755 /usr/local/lib/UDPReceive.py
# : sudo update-rc.d UDPBroadcast.sh defaults
 
### BEGIN INIT INFO
# Provides:          UDPBroadcast
# Required-Start:    $remote_fs $syslog
# Required-Stop:     $remote_fs $syslog
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# Short-Description: Broadcasts this computer's location
# Description:       Broadcasts this computer's location
### END INIT INFO
 
# Change the next 3 lines to suit where you install your script and what you want to call it
DIR=/usr/local/bin
DAEMON=$DIR/UDPReceive.py
DAEMON_NAME=UDPBroadcast
 
# This next line determines what user the script runs as.
# Root generally not recommended but necessary if you are using the Raspberry Pi GPIO from Python.
DAEMON_USER=root
 
# The process ID of the script when it runs is stored here:
PIDFILE=/var/run/$DAEMON_NAME.pid
 
. /lib/lsb/init-functions
 
do_start () {
    log_daemon_msg "Starting system $DAEMON_NAME daemon"
    start-stop-daemon --start --background --pidfile $PIDFILE --make-pidfile --user $DAEMON_USER --chuid $DAEMON_USER --startas $DAEMON
    log_end_msg $?
}
do_stop () {
    log_daemon_msg "Stopping system $DAEMON_NAME daemon"
    start-stop-daemon --stop --pidfile $PIDFILE --retry 10
    log_end_msg $?
}
 
case "$1" in
    start|stop)
        do_${1}
        ;;
 
    restart|reload|force-reload)
        do_stop
        do_start
        ;;
 
    status)
        status_of_proc "$DAEMON_NAME" "$DAEMON" && exit 0 || exit $?
        ;;
    *)
        echo "Usage: /etc/init.d/$DAEMON_NAME {start|stop|restart|status}"
        exit 1
        ;;
 
esac
exit 0
