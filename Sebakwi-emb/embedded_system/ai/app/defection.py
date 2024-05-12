# ai/app/defection.py

from ultralytics import YOLO
from ultralytics import settings as ultralytics_settings
import cv2
import time
import json
import datetime
from embedded_system.ai.app.wheel import Wheel
from embedded_system.ai.config import *
from embedded_system.utils.log_config import custom_log_info


def extract() -> json:
    # 직경 측정

    model = YOLO(TEST_fhd_MODEL)
    custom_log_info("Model 로드 완료")

    # 저장될 파일등 설정
    # ultralytics_settings.update({'runs_dir': './result'})
    # ultralytics_settings.update({'wandb': False})

    try:
        ## source 파일 설정
        # cap = cv2.VideoCapture(0)
        # cap = cv2.VideoCapture(TEST_720p_VIDEO)
        cv2.VideoCapture().set(cv2.CAP_PROP_BUFFERSIZE, 1)
        cap = cv2.VideoCapture(
            gstreamer_pipeline(
                capture_width=1920,
                capture_height= 1080,
                framerate=1,
                flip_method=0
            ),
            cv2.CAP_GSTREAMER
        )
        while True:
            # setting Buffer Size
            cap.set(cv2.CAP_PROP_BUFFERSIZE, 0)

            # 화면 읽어 오기
            ret, frame = cap.read()

            if not ret:
                custom_log_info("화면이 없습니다...")
                continue

            # 읽어 드린 화면 리사이징
            # frame = cv2.resize(frame, (800, 600))
            # custom_log_info("읽어온 프레임을 resize합니다.. ")

            # 예측에 사용돌 옵션 설정
            results = model.predict(
                source=frame,
                stream=True,
                show=True,
                # max_det=2,
                conf=0.70
            )
            custom_log_info("frame을 예측합니다")

            # 휠 탐지결과
            is_detected = defect_wheel(results)
            # is_detected = True
            if is_detected == True:
                cap.release()
                cv2.destroyAllWindows()
                custom_log_info("defect 감지되어 윈도우창이 종료됩니다..")

                return True

    finally:
        cap.release()
        cv2.destroyAllWindows()
        custom_log_info("종료됩니다")

    detect_wheel = Wheel(
        "image.jpg",
        True,
        True,
        True
    )


    return detect_wheel.to_json()


def defect_wheel(results) -> bool:
    crack = False
    stamp = False
    peeling = False

    for result in results:
        boxes = result.boxes

        for box in boxes:
            cls = int(box.cls[0])
            cls_name = result.names[cls]  # 클래스 이름 가져오기
            custom_log_info(f"Class name --> {cls_name}")

            # wheel 이 감지되었다면 종료
            if cls_name == 'crack':
                crack = True
            elif cls_name == 'stamp':
                stamp = True
            elif cls_name == 'peeling':
                peeling = True

    return crack, stamp, peeling

def measure_diameter(img=None) -> int:
    return STANDARD_DIAMETER - TOLERANCE