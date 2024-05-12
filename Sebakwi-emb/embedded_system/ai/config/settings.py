# ai/config/settings.py
import os

BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))

# 감지에 사용될 모델
DETECT_MODEL = os.path.join(BASE_DIR, "model", "wheel_det.pt")

# 결함 감지에 사용될 모델
DEFECT_MODEL = os.path.join(BASE_DIR, "model", "...")
TEST_MODEL = os.path.join(BASE_DIR, "model", "yolov8s.pt")
TEST_720p_MODEL = os.path.join(BASE_DIR, "model", "720p_10ep.pt")
TEST_fhd_MODEL = os.path.join(BASE_DIR, "model", "fhd_10ep.pt")

# 소스 파일
TEST_720p_VIDEO = os.path.join(BASE_DIR, "source", "video_720p.avi")
TEST_fhd_VIDEO = os.path.join(BASE_DIR, "source", "video_fhd.avi")


# 정상 직경
STANDARD_DIAMETER = 10
# 오차 범위
TOLERANCE = 1


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