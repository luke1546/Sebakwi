import cv2
import torch
import numpy as np
from models.common import DetectMultiBackend
from utils.general import check_img_size, non_max_suppression, scale_boxes
from utils.torch_utils import select_device
from utils.plots import Annotator, colors
from utils.segment.general import process_mask

device = torch.device("cuda" if torch.cuda.is_available() else "cpu")

class OBJ_SEGMENTATION():
    def __init__(self, model_path, data):
        #`self.yolo_model = attempt_load(weights=model_path, map_location=device)
        # self.yolo_model = attempt_load(weights=model_path)
        # self.yolo_model = attempt_load(weights=model_path).cuda()

        # 로컬 설정하기위해 잠시변경
        # self.yolo_model = attempt_load(weights=model_path).to(0)
        self.yolo_model = DetectMultiBackend(model_path, device=device, dnn=False, data=data)

        self.input_width = 640

    def run(self, frame):

        stride, names, pt = self.yolo_model.stride, self.yolo_model.names, self.yolo_model.pt
        imgsz = check_img_size((640, 640), s=stride)  # 이미지 크기 확인

        # 프레임 전처리
        im0 = cv2.resize(frame, (640, 640))  # YOLOv5 모델에 맞는 크기로 리사이즈
        im = im0[:, :, ::-1].transpose(2, 0, 1)  # BGR to RGB, to (3, 640, 640)
        im = np.ascontiguousarray(im)  # 성능 최적화를 위한 메모리 연속 배열
        im = torch.from_numpy(im).to(device)
        im = im.half() if self.yolo_model.fp16 else im.float()  # uint8 to fp16/32
        im /= 255  # 0 - 255 to 0.0 - 1.0
        im = im.unsqueeze(0)  # (3, 640, 640) to (1, 3, 640, 640)

        # 추론 수행
        pred, proto = self.yolo_model(im, augment=False, visualize=False)[:2]

        # NMS (비최대 억제) 적용
        pred = non_max_suppression(pred, 0.35, 0.45, None, False, max_det=1000, nm=32)

        objs = set()
        # 결과 처리 및 마스킹
        for i, det in enumerate(pred):  # per image
            annotator = Annotator(im0, line_width=3, example=str(names))
            if len(det):
                det[:, :4] = scale_boxes(im.shape[2:], det[i:, :4],
                                         im0.shape).round()  # rescale boxes to im0 size

                # 마스크를 640x640 크기로 그대로 유지
                masks = process_mask(proto[i], det[:, 6:], det[:, :4], im.shape[2:], upsample=True)  # HWC

                for c in det[:, 5].unique():
                    n = (det[:, 5] == c).sum()  # detections per class
                    objs.add(names[int(c)])

                # Mask plotting
                annotator.masks(
                    masks,
                    colors=[colors(x, True) for x in det[:, 5]],
                    im_gpu=None,  # Use None to directly draw on CPU numpy array
                )

                # Bounding box 및 레이블 그리기
                for j, (*xyxy, conf, cls) in enumerate(reversed(det[:, :6])):
                    label = f"{names[int(cls)]} {conf:.2f}"
                    print(f"Class: {names[int(cls)]}, Confidence: {conf:.2f}")  # 신뢰도 값을 출력
                    annotator.box_label(xyxy, label, color=colors(int(cls), True))

            # 결과 프레임 표시 (1280x800 크기로 리사이즈)
            #im0_resized = cv2.resize(im0, (800, 800))

            return im0, objs
