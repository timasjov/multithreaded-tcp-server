#!/usr/bin/env python

import socket
import threading
from random import randrange

# Do a request
def execute_command():
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect(('127.0.0.1', 6789))
    s.sendall(commands[randrange(len(commands))] + '\n')
    data = s.recv(1024)
    print 'Output (received): ' + data
    s.close()

jobs = []
threads = 10
commands = ['ls', 'ps -ef | grep java', 'man java']
# Run threads in parallel
for i in range(0, threads):
    out_list = list()
    thread = threading.Thread(target=execute_command)
    jobs.append(thread)
    
# Start the threads
for j in jobs:
    j.start()

# Ensure all of the threads have finished
for j in jobs:
    j.join()

print "Threads finished...exiting"