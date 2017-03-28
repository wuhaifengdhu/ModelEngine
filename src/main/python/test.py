#!/usr/bin/python
# -*- coding: utf-8 -*-
from sklearn import datasets, tree
from sklearn_pandas import DataFrameMapper
from sklearn2pmml import sklearn2pmml

iris = datasets.load_iris()
clf = tree.DecisionTreeClassifier()
clf = clf.fit(iris.data, iris.target)

default_mapper = DataFrameMapper([(i, None) for i in iris.feature_names + ['Species']])

sklearn2pmml(estimator=clf,
             mapper=default_mapper,
             pmml="D:/workspace/IrisClassificationTree.pmml")