import imageio
import cv2
from io import BytesIO
import time

def create_gif(frames, fps=2):
    """
    프레임 리스트로부터 GIF를 생성하여 반환합니다.

    :param frames: GIF에 포함될 프레임(이미지) 리스트.
    :param fps: GIF의 초당 프레임 수.
    :return: GIF 바이너리 데이터를 포함한 BytesIO 객체.
    """

    # 프레임을 올바른 형식으로 변환 (BGR -> RGB)
    rgb_frames = [cv2.cvtColor(frame, cv2.COLOR_BGR2RGB) for frame in frames]
    
    # GIF를 저장할 BytesIO 객체 생성
    gif_buffer = BytesIO()
    
    # 프레임을 GIF로 저장
    imageio.mimsave(gif_buffer, rgb_frames, format='GIF', fps=fps)
    
    # 버퍼의 초기회
    gif_buffer.seek(0)
    
    return gif_buffer
