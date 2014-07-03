#!/usr/bin/python
import socket
import time 

MCAST_GRP = '224.1.1.1'
MCAST_PORT = 10010

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM, socket.IPPROTO_UDP)
sock.setsockopt(socket.IPPROTO_IP, socket.IP_MULTICAST_TTL, 2)

while True:
    time.sleep(30)
    sock.sendto("TEMPMON_SERVER_IP", (MCAST_GRP, MCAST_PORT))

