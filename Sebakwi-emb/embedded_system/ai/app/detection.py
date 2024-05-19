# ai/app/detection.py

import imageio
import numpy as np
import cv2
import torch
from elements.yolo import OBJ_DETECTION
from util.create_gif import create_gif
from util.upload_gif import upload_gif
from embedded_system.ai.config import *
from embedded_system.util.log_config import custom_log_info

Object_classes = [
    'frame',
    'wheel'
]

Object_colors = list(np.random.rand(80, 3) * 255)


def extract() -> bool:
    options = {
        "weights": DETECT_MODEL

        # CSI Camera on
        #, "source" = gstreamer_pipeline()
    }

    results = wheel_detection(**options)
    custom_log_info("wheel이 감지되었습니다") if 'wheel' in results else None
    return True


def wheel_detection(weights, source = TEST_720p_VIDEO):
    Object_detector = OBJ_DETECTION(weights, Object_classes)
    '''
    cap = cv2.VideoCapture(
        gstreamer_pipeline(
            capture_width=1280,
            capture_height=720,
            framerate=120,
            flip_method=0
         ),
        cv2.CAP_GSTREAMER
    )
    '''
    cap = cv2.VideoCapture(source)

    frames = []
    detect_obj = set()
    hit_count = 0

    try:
        while cap.isOpened():
            ret, frame = cap.read()

            # cap.set(cv2.CAP_PROP_BUFFERSIZE, 1)
            # detection process
            if not ret:
                custom_log_info("화면이 없습니다...")
                break

            objs = Object_detector.detect(frame)

            # plotting
            for obj in objs:
                label = obj['label']
                score = obj['score']
                [(xmin, ymin), (xmax, ymax)] = obj['bbox']
                color = Object_colors[Object_classes.index(label)]
                frame = cv2.rectangle(frame, (xmin, ymin), (xmax, ymax), color, 2)
                frame = cv2.putText(frame, f'{label} ({str(score)})', (xmin, ymin), cv2.FONT_HERSHEY_SIMPLEX, 1.5,
                                    color, 1, cv2.LINE_AA)

                detect_obj.add(obj['label'])

                # frame = func(obj, frame)
                if obj['label'] == 'wheel' and float(score) > 0.6:
                    hit_count = hit_count + 1
                    frames.append(cv2.cvtColor(frame, cv2.COLOR_BGR2RGB))

            if hit_count >= 25:
                break

            # display the frame
            display_frame = cv2.resize(frame, (640, 640))
            cv2.imshow("CSI Camera", display_frame)
            if cv2.waitKey(25) & 0xFF == ord('q'):
                break

    finally:
        custom_log_info("wheel 감지 시스템을 종료합니다")
        cap.release()
        cv2.destroyAllWindows()

    return detect_obj

def gstreamer_pipeline(
    capture_width=1280,
    capture_height=720,
    display_width=1280,
    display_height=720,
    framerate=60,
    flip_method=0,
):
    return (
        "nvarguscamerasrc ! "
        "video/x-raw(memory:NVMM), "
        "width=(int){}, height=(int){}, "
        "format=(string)NV12, framerate=(fraction){}/1 ! "
        "nvvidconv flip-method={} ! "
        "video/x-raw, width=(int){}, height=(int){}, format=(string)BGRx ! "
        "videoconvert ! "
        "video/x-raw, format=(string)BGR ! appsink".format(
            capture_width,
            capture_height,
            framerate,
            flip_method,
            display_width,
            display_height,
        )
    )

def func(obj, frame):
    label = obj['label']
    score = obj['score']
    box = obj['bbox']

    (x1, y1), (x2, y2) = box  # bbox 좌표를 추출
    x1, y1, x2, y2 = int(x1), int(y1), int(x2), int(y2)  # 정수로 변환
    alpha = 0.05
    if label == 1 or score > 0.5 or True:  # 예시로 클래스 1 (크랙)과 점수 0.5 이상만 선택
        overlay = frame.copy()  # 원본 이미지 복사
        cv2.rectangle(overlay, (x1, y1), (x2, y2), (0, 0, 255), -1)  # 빨간색으로 채운 사각형 그리기 (BGR 형식)
        cv2.addWeighted(overlay, alpha, frame, 1 - alpha, 0, frame)  # 이미지 혼합

    return frame
