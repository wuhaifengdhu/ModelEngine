#!/usr/bin/python
# -*- coding: utf-8 -*-
import math


class PmvBrain(object):
    def __init__(self, record):
        self.icl = record[4]                # clothing insulation [clo] (directly use value in excel)
        self.M = 58.15 * record[3]          # metabolic rate [w/m2]  (58.15 * value in excel)
        self.V = record[9]                  # air velocity
        self.rh = record[10] / 100.0        # humidity / 100
        self.ta = record[7]                 # air temperature
        self.tr = record[8]                 # mean radiant temperature [C] (equal to air temperature)
        self.W = 0                          # external work, assume 0

    def get_predict_pmv_pdd(self):
        rmw = self.M - self.W
        pa = self.rh * 10 * math.exp(16.6536 - 4030.183 / (self.ta + 235))
        tolerance = 0.00015
        fcl = 1.05 + 0.1 * self.icl
        if self.icl < 0.5:
            fcl = 1.0 + 0.2 * self.icl

        taa = self.ta + 273
        tra = self.tr + 273
        tcla = taa + (35.3 - self.ta) / (3.5 * (self.icl + 0.1))
        xn = tcla / 100
        xf = xn

        fcic = self.icl * 0.155 * fcl
        p1 = fcic * taa
        p2 = fcic * 3.96
        p3 = fcic * 100
        p4 = 308.7 - 0.028 * rmw + p2 * (tra / 100) ** 4

        n_iterations = 0
        xf = xn

        while n_iterations < 150:
            xf = (xf + xn) / 2
            hcf = 12.1 * self.V ** 0.5
            hcn = 2.38 * abs(100 * xf - taa) ** 0.25
            hc = max(hcf, hcn)
            xn = (p4 + p1 * hc - p2 * xf ** 4) / (100 + p3 * hc)
            n_iterations += 1
            if n_iterations > 1 and abs(xn - xf) < tolerance:
                break

        if n_iterations < 150:
            tcl = 100 * xn - 273
            pm1 = 3.96 * fcl * (xn ** 4 - (tra / 100) ** 4)
            pm2 = fcl * hc * (tcl - self.ta)
            pm3 = 0.303 * math.exp(-0.036 * self.M) + 0.028
            pm4 = 0.0 if rmw <= 58.15 else 0.42 * (rmw - 58.15)
            bmv = rmw - 3.05 * 0.001 * (5733 - 6.99 * rmw - pa)
            cmv = -pm4 - 1.7 * 0.00001 * self.M * (5867 - pa) - 0.0014 * self.M * (34 - self.ta) - pm1 - pm2
            pmv = pm3 * (bmv + cmv)
            ppd = 100 - 95 * math.exp(-0.03353 * pmv ** 4 - 0.2179 * pmv ** 2)
        else:
            pmv = 999
            ppd = 100
        return pmv, ppd

    def get_predict(self):
        return self.get_predict_pmv_pdd()[0]


if __name__ == "__main__":
    raw_data = ((1L, 0L, 2L, 0.9898, 0.467, 0.15, 0.617, 21.8, 22.3, 0.01, 57.4, -1.08, 0.3, 0L),
                (2L, 1L, 2L, 0.9898, 0.307, 0.15, 0.457, 24.0, 24.5, 0.15, 39.7, -1.07, 0.29, 0L))
    score_list = list()
    for record in raw_data:
        compute = PmvBrain(record)
        score_list.append(compute.get_predict())
    print score_list


