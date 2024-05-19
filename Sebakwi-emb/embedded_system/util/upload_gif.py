import requests
import uuid
from io import BytesIO
import embedded_system.util.config as config
from embedded_system.util.log_config import custom_log_info

base_url = "http://k10s108.p.ssafy.io/api/upload/checkup"
# base_url = "http://192.168.219.100:8080/api/upload/checkup"
def upload_gif(gif_buffer: BytesIO) -> bool:
    """
    GIF 바이너리 데이터를 주어진 URL로 업로드합니다.

    :param gif_buffer: GIF 바이너리 데이터를 포함한 BytesIO 객체.
    :param url: GIF를 업로드할 URL.
    :return: 업로드 성공 여부를 나타내는 bool 값.
    """

    config.img_name = str(uuid.uuid4()) + ".gif"
    upload_url = f"{base_url}/{config.img_name}"
    files = {'file': (config.img_name, gif_buffer, 'image/gif')}

    gif_size_bytes = gif_buffer.getbuffer().nbytes
    gif_size_mb = gif_size_bytes / (1024 * 1024)  # MB 단위로 변환
    custom_log_info(f"{config.img_name}: {gif_size_mb:.2f} MB")

    response = requests.post(upload_url, files=files)

    if response.status_code == 200:
        print("검진 이미지 데이터  업로드 성공")
        return True
    else:
        print(f"GIF 업로드 실패: {response.status_code}")
        return False
