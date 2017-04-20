#!/usr/bin/python
# -*- coding: utf-8 -*-

from flask import Flask
from flask import request
from models.pmv import PMV
import json
app = Flask(__name__)


@app.route('/api/v1/model/pmv', methods=['POST'])
def model_score():
    model = PMV().fit()
    print "request.data : " + request.data
    raw_data = json.loads(request.data)
    print "raw_data: " + str(raw_data)
    result = model.predict(raw_data)
    return json.dumps(result)
