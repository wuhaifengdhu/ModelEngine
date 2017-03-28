from lib.pmv_record import PmvBrain


class PMV(object):
    def __init__(self):
        pass

    def fit(self, feature_list=None, label_list=None):
        # This model do not need trained, it's a configured model with discovered logical
        return self

    def predict(self, feature_list):
        return [self.__predict_record(x) for x in feature_list]

    @staticmethod
    def __predict_record(x):
        """Assume x is record with field[]"""
        pmv_cell = PmvBrain(x)
        return pmv_cell.get_predict()
