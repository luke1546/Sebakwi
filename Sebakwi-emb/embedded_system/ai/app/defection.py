# ai/app/defection.py

# from ultralytics import YOLO
# from ultralytics import settings as ultralytics_settings
import cv2
import time
import json
import random
import datetime
from embedded_system.ai.app.wheel import Wheel
from embedded_system.ai.config import *




def extract() -> json:
    ohtSerialNumber, wheelSerialNumber, positions = calculate_wheel_info()

    result = Wheel(
        ohtSerialNumber,
        wheelSerialNumber,
        positions,
        measure_diameter(),
        "image.jpg",
        True,
        True,
        True
    )

    return result.to_json()

def measure_diameter(img=None) -> int:
    return STANDARD_DIAMETER - TOLERANCE

def calculate_wheel_info():
    # 각 oht_serial_number에 대한 wheel_serial_number 및 position 계산
    oht_serial_number = random.randint(1, 30)
    positions = random.randint(1, 4)
    wheel_serial_number = ((oht_serial_number - 1) * 4) + positions

    oht_serial_number = f"VM{oht_serial_number:04d}"
    wheel_serial_number = f"SM{wheel_serial_number:05d}"

    return oht_serial_number, wheel_serial_number, positions