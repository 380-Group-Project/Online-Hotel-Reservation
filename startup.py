# -*- coding: utf-8 -*-
import sqlite3

connection = sqlite3.connect("database/reservations.db")

crsr = connection.cursor()

connection.close()