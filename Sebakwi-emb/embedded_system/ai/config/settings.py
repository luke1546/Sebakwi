# ai/config/settings.py
import os

BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))

# 감지에 사용될 모델
# DETECT_MODEL = os.path.join(BASE_DIR, "model", "detect.pt")
# DETECT_MODEL = os.path.join(BASE_DIR, "model", "detect_ep100_5n.pt")
DETECT_MODEL = os.path.join(BASE_DIR, "model", "detect_ep100_5s.pt")
# DETECT_MODEL = os.path.join(BASE_DIR, "model", "detect_ep100_5m.pt")

# DETECT_MODEL = os.path.join(BASE_DIR, "model", "detect_ep100_5x.pt")

# 결함 감지에 사용될 모델
DEFECT_MODEL = os.path.join(BASE_DIR, "model", "...")
TEST_MODEL = os.path.join(BASE_DIR, "model", "yolov8s.pt")
TEST_720p_MODEL = os.path.join(BASE_DIR, "model", "720p_10ep.pt")
TEST_fhd_MODEL = os.path.join(BASE_DIR, "model", "fhd_10ep.pt")
V5X_MODEL = os.path.join(BASE_DIR, "model", "server_v5x._ep30.pt")
SEG_MODEL = os.path.join(BASE_DIR, "model", "crack_v5s_seg.pt")
# SEG_MODEL = os.path.join(BASE_DIR, "model", "seg_ep100_5s.pt")
SEG_MODEL = os.path.join(BASE_DIR, "model", "seg_ep200_v5n.pt")
# SEG_MODEL = os.path.join(BASE_DIR, "model", "seg_ep200_v5s.pt")



# 데이터셋 yaml
SEG_DATA = os.path.join(BASE_DIR, "model", "crack_data.yaml")



# 소스 파일
#TEST_720p_VIDEO = os.path.join(BASE_DIR, "source", "video_720p.avi")
TEST_720p_VIDEO = os.path.join(BASE_DIR, "source", "video_720p_1.avi")
TEST_fhd_VIDEO = os.path.join(BASE_DIR, "source", "video_fhd.avi")
TEST1_VIDEO = os.path.join(BASE_DIR, "source", "output1.avi")
TEST2_VIDEO = os.path.join(BASE_DIR, "source", "output2.avi")
NORMAL_VIDEO = os.path.join(BASE_DIR, "source", "normal_wheel.avi")
MIX_VIDEO = os.path.join(BASE_DIR, "source", "normal_defect_wheel.avi")
DEFECT_VIDEO = os.path.join(BASE_DIR, "source", "defect_wheel_f21.avi")
MIX_VIDEO = os.path.join(BASE_DIR, "source", "normal_defect_wheel_original.avi")


# 정상 직경
STANDARD_DIAMETER = 0
# 오차 범위
TOLERANCE = 0


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
