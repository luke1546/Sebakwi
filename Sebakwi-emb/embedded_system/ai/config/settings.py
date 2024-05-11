# ai/config/settings.py
import os

BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))

# 감지에 사용될 모델
DETECT_MODEL = os.path.join(BASE_DIR, "model", "wheel_det.pt")

# 결함 감지에 사용될 모델
DEFECT_MODEL = os.path.join(BASE_DIR, "model", "...")
TEST_MODEL = os.path.join(BASE_DIR, "model", "yolov8s.pt")

# 소스 파일
SOURCE_FILE = os.path.join(BASE_DIR, "output.avi")


# 정상 직경
STANDARD_DIAMETER = 10
# 오차 범위
TOLERANCE = 1