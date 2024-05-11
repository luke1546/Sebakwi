# ai/app/detection.py

from ultralytics import YOLO
from ultralytics import settings as ultralytics_settings
import cv2
import time
from embedded_system.ai.config import *
from embedded_system.utils.log_config import custom_log_info

def extract()-> bool:
    # Model 로드..
    model = YOLO(TEST_MODEL)
    custom_log_info("Model 로드 완료")

    # 저장될 파일등 설정
    # ultralytics_settings.update({'runs_dir': './result'})
    # ultralytics_settings.update({'wandb': False})

    ## source 파일 설정
    cap = cv2.VideoCapture(0)
    # cap = cv2.VideoCapture(SOURCE_FILE)
    
    while True:
        # 화면 읽어 오기
        ret, frame = cap.read()

        if not ret:
            custom_log_info("화면이 없습니다...")
            continue

        # 읽어 드린 화면 리사이징
        frame = cv2.resize(frame, (800, 600))
        custom_log_info("읽어온 프레임을 resize합니다.. ")
        
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
        is_detected = detect_wheel(results)

        if is_detected == True:
            cap.release()
            cv2.destroyAllWindows()
            custom_log_info("휠이 감지되어 윈도우창이 종료됩니다..")

            return True


def detect_wheel(results) -> bool:
    for result in results:
        boxes = result.boxes

        for box in boxes:
            cls = int(box.cls[0])
            cls_name = result.names[cls]  # 클래스 이름 가져오기
            custom_log_info(f"Class name --> {cls_name}")

            '''
            # bounding box 정보 가져오기
            x1, y1, x2, y2 = map(int, box.xyxy[0])  # 좌표 변환
            # bounding box 그리기
            cv2.rectangle(frame, (x1, y1), (x2, y2), (0, 255, 0), 2)
            # 클래스 이름 그리기
            cv2.putText(frame, cls_name, (x1, y1 - 10), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 255, 0), 2)
            '''

            # wheel 이 감지되었다면 종료
            # if cls_name == 'wheel':
            if cls_name == 'cup':
                return True

