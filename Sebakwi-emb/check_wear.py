import cv2
import numpy as np

# 이미지 불러오기
src = cv2.imread('4.jpeg')  # 이미지 경로를 적절히 변경하세요
image = cv2.resize(src, dsize=(480, 640), fx=0.3, fy=0.3)
gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
gray = cv2.GaussianBlur(gray, (5, 5), 0)  # 가우시안 블러로 노이즈 제거

# 캐니 엣지를 사용하여 이미지 전처리
edges = cv2.Canny(gray, 50, 150)

# 컨투어 찾기
contours, _ = cv2.findContours(edges, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

# 컨투어 그리기
contour_image = np.zeros_like(image)
cv2.drawContours(contour_image, contours, -1, (255, 255, 255), 2)

# 컨투어를 감싸는 최소 외접원과 중심 찾기 함수
def find_min_enclosing_circle(contour):
    (x, y), radius = cv2.minEnclosingCircle(contour)
    center = (int(x), int(y))
    radius = int(radius)
    return center, radius

# 컨투어를 감싸는 최소 외접원 찾기
min_circle_centers = []
min_circle_radii = []
for contour in contours:
    center, radius = find_min_enclosing_circle(contour)
    if center is not None:
        min_circle_centers.append(center)
        min_circle_radii.append(radius)

for contour in contours:
    min_center, min_radius = find_min_enclosing_circle(contour)
    if min_center is not None:
        min_distance = np.inf
        for point in contour:
            point = tuple(point[0])
            distance = np.linalg.norm(np.array(point) - np.array(min_center))
            if distance < min_distance:
                min_distance = distance
        # 거리를 반지름으로 하는 원 그리기
        cv2.circle(image, min_center, int(min_distance), (0, 0, 255), 1)  
      
for center, radius in zip(min_circle_centers, min_circle_radii):
    cv2.circle(image, center, radius, (0, 255, 0), 2)  # 최소 외접원 그리기
    cv2.circle(image, center, 3, (0, 0, 255), -1)  # 중심 그리기
    min_distance = np.inf
    for contour in contours:
        for point in contour:
            point = tuple(point[0])
            distance = np.linalg.norm(np.array(point) - np.array(center))
            if distance < min_distance:
                min_distance = distance
    # 최소 외접원과 거리를 반지름으로 하는 원의 반지름 차이
    diff_radius = abs(radius - min_distance)
    # 텍스트를 우측하단에 표시
    text_pos = (240, 600)
    cv2.putText(image, f'Wear : {diff_radius:.2f}', text_pos, cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 0), 2, cv2.LINE_AA)

# 결과 이미지에 컨투어 그리기
result_image = cv2.addWeighted(image, 0.7, contour_image, 0.3, 0)

# 결과 이미지 출력
cv2.imshow('Result Image', result_image)
cv2.waitKey(0)
cv2.destroyAllWindows()
