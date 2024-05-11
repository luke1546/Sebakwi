# ai/app/defection.py

import json
import datetime
from embedded_system.ai.config import *

def extract() -> json:
    # 직경 측정
    diameter = measure_diameter()

    wheel_id = "OHT_001"
    checked_date = datetime.datetime.now().isoformat()
    wheel_image = "/"
    crack = False
    stamp = False
    peeling = False
    # 반환 json 데이터 만들기
    data = {
        "wheel_id": wheel_id,
        "checked_date": checked_date,
        "wheel_image": wheel_image,
        "diameter": diameter,
        "crack": crack,
        "stamp": stamp,
        "peeling": peeling
    }
    json_data = json.dumps(data)

    return json_data

def measure_diameter(img=None) -> int:
    return STANDARD_DIAMETER - TOLERANCE