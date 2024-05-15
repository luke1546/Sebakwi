import imageio
import numpy as np
import cv2
from elements.yolo import OBJ_DETECTION
from util.create_gif import create_gif
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
    }

    results = wheel_detecion(**options)

    for i in results:
        print(i)

    return True


def wheel_detecion(weights):
    Object_detector = OBJ_DETECTION(weights, Object_classes)
    # cap = cv2.VideoCapture(
    #     gstreamer_pipeline(
    #         capture_width=1920,
    #         capture_height=1080,
    #         framerate=1,
    #         flip_method=0
    #     ),
    #     cv2.CAP_GSTREAMER
    # )
    cap = cv2.VideoCapture(TEST_720p_VIDEO)
    # cv2.VideoCapture().set(cv2.CAP_PROP_BUFFERSIZE, 1)

    frames = []
    detect_obj = set()
    hit_count = 0
    while cap.isOpened():
        ret, frame = cap.read()

        # detection process
        if not ret:
            custom_log_info("화면이 없습니다...")
            break

        objs = Object_detector.detect(frame)

        # plotting
        for obj in objs:
            # print(obj)
            label = obj['label']
            score = obj['score']
            [(xmin, ymin), (xmax, ymax)] = obj['bbox']
            color = Object_colors[Object_classes.index(label)]
            frame = cv2.rectangle(frame, (xmin, ymin), (xmax, ymax), color, 2)
            frame = cv2.putText(frame, f'{label} ({str(score)})', (xmin, ymin), cv2.FONT_HERSHEY_SIMPLEX, 0.75,
                                color, 1, cv2.LINE_AA)

            detect_obj.add(obj['label'])
            if obj['label'] == 'wheel' and float(score) > 0.8:
                hit_count = hit_count + 1
                frames.append(cv2.cvtColor(frame, cv2.COLOR_BGR2RGB))

        if hit_count >= 5:
            break

        # display the frame
        cv2.imshow("CSI Camera", frame)
        if cv2.waitKey(25) & 0xFF == ord('q'):
            break

    # create gif
    # create_gif(frames)

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