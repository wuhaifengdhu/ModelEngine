from __future__ import print_function

"""Model Engine for running python model"""
import sys
from models.pmv import PMV
from lib.data_provider import DataProvider


def get_data_from_db(para_list):
    data_configure = dict()
    for pair in para_list:
        key, value = pair.split(':')
        data_configure[key] = value
    mysql = DataProvider(data_configure)
    return mysql.fetch_data()


def get_data_from_raw(para_list):
    raw_data = []
    for record in para_list:
        raw_data.append([get_digital_value(field) for field in record.split(",")])
    return raw_data


def get_digital_value(field):
    if 'L' in field:
        return float(field[:-1])
    return float(field)


def get_data(data_paras):
    # print(data_paras)
    para_list = data_paras.split(';')
    if para_list[0] == 'MYSQL':
        return get_data_from_db(para_list[1:])
    elif para_list[0] == 'RAW':
        return get_data_from_raw(para_list[1:])


def main(args=()):
    """Command-line entry-point. Parses `args` into model and data."""
    import argparse

    parser = argparse.ArgumentParser(description='Model Engine for python')
    parser.add_argument('-m', '--model', help="Description for model type", required=True)
    parser.add_argument('-d', '--data', help="Description for data source", required=True)

    paras = parser.parse_args(args)
    # print(paras.data, paras.model)
    """
    >>> python main.py -m "pmv" -d "MYSQL;host:localhost;user:root;password:itcm123;db_name:itcm_database;table_name:data_sample;ids:(1)"
    >>> myData myModel
    """
    raw_data = get_data(paras.data)
    # print(raw_data)
    if paras.model.lower() == 'pmv':
        model = PMV().fit()
        result = model.predict(raw_data)
    print(result)


if __name__ == '__main__':
    main(sys.argv[1:])
