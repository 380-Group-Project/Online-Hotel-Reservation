# -*- coding: utf-8 -*-
import sqlite3

connection = sqlite3.connect("database/reservations.db")

crsr = connection.cursor()

#set up the tables all nice and stuff 
sql_command = """CREATE TABLE room(
room_number INTEGER PRIMARY KEY,
room_type VARCHAR(10),
bednum INTEGER,
price INTEGER,
reserved BOOLEAN)
"""

crsr.execute(sql_command)

# set up reservation table, dont add anything though, that's handled by main software
sql_command = """CREATE TABLE reservations(
res_num INTEGER PRIMARY KEY,
room_num INTEGER,
guests INTEGER,
check_in TEXT,
check_out TEXT)
"""

crsr.execute(sql_command)

#add the 9 rooms
sql_command = """INSERT INTO room VALUES(101, "basic", 1, 50, FALSE)"""
crsr.execute(sql_command)
sql_command = """INSERT INTO room VALUES(102, "basic", 1, 50, FALSE)"""
crsr.execute(sql_command)
sql_command = """INSERT INTO room VALUES(103, "basic", 2, 75, FALSE)"""
crsr.execute(sql_command)
sql_command = """INSERT INTO room VALUES(201, "family", 2, 100, FALSE)"""
crsr.execute(sql_command)
sql_command = """INSERT INTO room VALUES(202, "family", 2, 100, FALSE)"""
crsr.execute(sql_command)
sql_command = """INSERT INTO room VALUES(203, "family", 3, 125, FALSE)"""
crsr.execute(sql_command)
sql_command = """INSERT INTO room VALUES(301, "suite", 2, 200, FALSE)"""
crsr.execute(sql_command)
sql_command = """INSERT INTO room VALUES(302, "suite", 2, 200, FALSE)"""
crsr.execute(sql_command)
sql_command = """INSERT INTO room VALUES(303, "suite", 2, 200, FALSE)"""
crsr.execute(sql_command)



connection.commit()

connection.close()