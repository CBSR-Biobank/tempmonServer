#!/usr/bin/python
import socket

UDP_IP = "255.255.255.255"
UDP_PORT = 5005

CLIENT_IP = ""

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.bind((UDP_IP, UDP_PORT))


while True:
    data = ""
    
    while data != "REQUEST_SERVER_IP":
        data, CLIENT_IP = sock.recvfrom(1024)
        print "message received: ", data
        print "from: ", CLIENT_IP
        
        sock.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)
        sock.sendto("SERVER_RESPONSE", (CLIENT_IP[0], CLIENT_IP[1]))
        print("Responding with server IP...")
        
