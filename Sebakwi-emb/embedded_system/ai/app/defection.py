# ai/app/defection.py

import cv2
import numpy as np
import time
import json
import random
import datetime
import requests
from elements.my_predict import OBJ_SEGMENTATION
from embedded_system.ai.app.wheel import Wheel
from embedded_system.ai.config import *
import embedded_system.util.config as config
from embedded_system.util.log_config import custom_log_info
from util.create_gif import create_gif
from util.upload_gif import upload_gif


Object_classes = [
    'crack',
    'stmap'
]
Object_colors = list(np.random.rand(80, 3) * 255)

def extract() -> json:

    num = 1000

    random_val = random.randint(1, num) == 1
    source = MIX_VIDEO if random_val == True else NORMAL_VIDEO
    source = MIX_VIDEO
    options = {
        "weights": SEG_MODEL,
        "data": SEG_DATA,
        "source": source,
       #  "source": gstreamer_pipeline()
    }
    results, frame_li = run(**options)

    custom_log_info("결함이 감지되었습니다!") if results else custom_log_info("감지된 결함이 없습니다")
    
    
    # 검진 이미지데이터 업로드
    result = create_gif(frame_li)
    upload_gif(result)

    crack = "crack" in results
    stamp = "stamp" in results
    peeling = "result" in results
    
    crack = False
    stamp = False
    peeling = False

    ohtSerialNumber, wheelSerialNumber, positions = calculate_wheel_info()
    saved_img_name = config.img_name

    result = Wheel(
        ohtSerialNumber,
        wheelSerialNumber,
        positions,
        measure_diameter(frame_li[-1]),
        saved_img_name,
        crack,
        stamp,
        peeling
    )

    return result.to_json()


def run(weights, data, source):
    Object_seg = OBJ_SEGMENTATION(weights, data)

    cap = cv2.VideoCapture(source)
    result_objs = set()
    measure_frame = None
    frame_li = list()
    hit_count = 0
    try :
        if cap.isOpened():
            cv2.namedWindow('Detection Result', cv2.WINDOW_NORMAL)
            cv2.resizeWindow('Detection Result', 640, 640)
            time.sleep(3)
            while True:
                ret, frame = cap.read()
                time.sleep(0.1)
                if not ret:
                    break

                result_frame, objs = Object_seg.run(frame) 
                if objs:
                    frame_li.append(cv2.cvtColor(result_frame, cv2.COLOR_BGR2RGB))
                    hit_count = hit_count + 1

                result_objs.update(objs)

                cv2.imshow("Detection Result", result_frame)
                
                if hit_count == 13:
                    break

                if cv2.waitKey(1) & 0xFF == ord('q'):
                    break

        
        return result_objs, frame_li

    finally:
        cap.release()
        cv2.destroyAllWindows()

def measure_diameter(frame) -> float:
    # frame -> jpg파일로 변환
    _, encoded_image = cv2.imencode('.jpg', frame)

    # 바이트로 변환
    image_bytes = encoded_image.tobytes()

    custom_log_info("휠의 마모도를 체크합니다")  
    url = "http://52.79.241.125:8000/diameter/"
    files = {'file': ('frame.jpg', image_bytes, 'image/jpeg')}
    response = requests.post(url, files=files)

    wear_difference = float(0)
    if response.status_code == 200:
        wear_difference = response.json()[0].get("wear_difference")
        wear_difference = float(wear_difference)
        wear_difference = wear_difference / 100

        custom_log_info(f"wear_diff {wear_difference}")

    return wear_difference

'''
random으로 
oht 정보를 선택
'''
def calculate_wheel_info():
    # 각 oht_serial_number에 대한 wheel_serial_number 및 position 계산
    oht_serial_number = random.randint(1, 30)
    positions = random.randint(1, 4)
    wheel_serial_number = ((oht_serial_number - 1) * 4) + positions

    oht_serial_number = f"VM{oht_serial_number:04d}"
    wheel_serial_number = f"SM{wheel_serial_number:05d}"

    return oht_serial_number, wheel_serial_number, positions
