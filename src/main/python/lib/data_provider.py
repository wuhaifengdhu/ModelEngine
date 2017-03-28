#!/usr/bin/python
# -*- coding: utf-8 -*-
from __future__ import print_function
from exception.model_exception import DataConfigureException
import MySQLdb


class DataProvider(object):
    def __init__(self, data_configure):
        if not set(["host", "user", "password", "db_name", "table_name"]).issubset(set(data_configure.keys())):
            raise DataConfigureException({"message": "Not all configure key provided!"})
        self.host = data_configure["host"]
        self.user = data_configure["user"]
        self.password = data_configure["password"]
        self.db_name = data_configure["db_name"]
        self.connector = None

    def __get_connect(self):
        if self.connector is not None:
            return self.connector
        else:
            return MySQLdb.connect(self.host, self.user, self.password, self.db_name)

    def run_sql(self, sql):
        self.connector = self.__get_connect()
        db_cursor = self.connector.cursor()
        db_cursor.execute(sql)
        return db_cursor.fetchall()

    def fetch_all(self, table_name, max_num=None):
        if max_num is not None:
            sql = """select * from %s limit %i""" % (table_name, max_num)
        else:
            sql = """select * from %s""" % table_name
        return self.run_sql(sql)


if __name__ == '__main__':
    configure = {"host": "localhost", "user": "root", "password": "itcm123", "db_name": "itcm_database",
                 "table_name": "data_sample"}
    mysql = DataProvider(configure)
    table_data = mysql.fetch_all('data_sample')
    print(len(table_data))
    print(table_data[1])
