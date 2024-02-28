# -*- coding: utf-8 -*-
import sqlite3
import random


#must match path from startup.py
path = 'hotel.db'

#arguments are all parts of the reservation except the primary key
def reserve(room, name, guests, indate, outdate):
    connection = sqlite3.connect(path)
    
    crsr = connection.cursor()
    collision = True
    res_id = random.randint(100000,999999)
    crsr.execute('SELECT EXISTS (SELECT 1 FROM reservations WHERE res_num = ?)',(res_id,))
    collision = crsr.fetchone()
    # print(res_id)
    # print(collision)
    
    #generate reservation IDs until a unique one is generated
    while collision == (1,):
        res_id = random.randint(100000,999999)
        # print(res_id)
        crsr.execute('SELECT EXISTS (SELECT 1 FROM reservations WHERE res_num = ?)',(res_id,))
        collision = crsr.fetchone()
        # print(collision)

        
    # crsr.execute('SELECT EXISTS (SELECT 1 FROM reservations WHERE res_num = 686963)')
    # print(crsr.fetchone())
    crsr.execute('INSERT INTO reservations VALUES(?,?,?,?,?,?)', (res_id, room, name, guests, indate, outdate))
    
    connection.commit()
    
    connection.close()
    
    return res_id
    
#function to cancel a reservation based on its ID 
def cancel(res_id):
    connection = sqlite3.connect(path)
    
    crsr = connection.cursor()
    #simply deletes the row containing the associated ID 
    crsr.execute('DELETE FROM reservations WHERE res_id = ?',(res_id,))
    
#reserve(101, "Jane Doe", 1, "2024-01-01", "2024-01-02")