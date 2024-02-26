# -*- coding: utf-8 -*-
import sqlite3
import os
import time
import random


#must match path from startup.py
path = 'hotel.db'

#arguments are all parts of the reservation except the primary key
def reserve(room, name, guests, indate, outdate):
    connection = sqlite3.connect(path)
    
    crsr = connection.cursor()
    collision = True
    res_id = random.randint(100000,999999)
    #generate reservation IDs until a unique one is generated
    # while collision == True:
    #     res_id = random.randint(1,65536)
    #     print(res_id)
    #     collision = False
    #     crsr.execute('SELECT EXISTS (SELECT 1 FROM reservations WHERE res_num = 1)')
    #     if crsr.fetchone():
    #         collision = True
        
    
    crsr.execute('INSERT INTO reservations VALUES(?,?,?,?,?,?)', (res_id, room, name, guests, indate, outdate))
    
    connection.commit()
    
    connection.close()
    
reserve(101, "Jane Doe", 1, "2024-01-01", "2024-01-02")