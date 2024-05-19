import json

class Wheel:
    def __init__(
            self, ohtSerialNumber, wheelSerialNumber, position,  diameter,
            wheelImage="", crack=False, stamp=False, peeling=False
    ):
        self.ohtSerialNumber = ohtSerialNumber
        self.wheelSerialNumber =  wheelSerialNumber
        self.position = position
        self.wheelImage = "http://k10s108.p.ssafy.io:3333/files/" + wheelImage
        self.crack = crack
        self.stamp = stamp
        self.peeling = peeling
        self.diameter = diameter


    def to_json(self):
        # JSON 포맷으로 직렬화합니다.
        return json.dumps(self, default=lambda o: o.__dict__, indent=4)
