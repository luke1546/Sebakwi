import random
import json

class Wheel:
    def __init__(self, wheel_image="", crack=False, stamp=False, peeling=False):
        self.oht_serial_number, self.wheel_serial_numbers, self.position = self.calculate_wheel_info()
        self.wheel_image = wheel_image
        self.crack = crack
        self.stamp = stamp
        self.peeling = peeling
        self.diameter = self.calculate_diameter()

    def calculate_wheel_info(self):
        # 각 oht_serial_number에 대한 wheel_serial_number 및 position 계산
        oht_serial_number = random.randint(1, 30)
        positions = random.randint(1, 4)
        wheel_serial_numbers = ((oht_serial_number - 1) * 4) + positions
        return oht_serial_number, wheel_serial_numbers, positions


    def calculate_diameter(self):
        return 0.5

    def to_json(self):
        # 클래스 인스턴스를 딕셔너리로 변환하고, 이를 JSON 포맷으로 직렬화합니다.
        return json.dumps(self, default=lambda o: o.__dict__, indent=4)
