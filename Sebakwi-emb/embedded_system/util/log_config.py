# ai/log_config.py

import logging
import sys

def setup_logging():
    logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

def shorten_path(path):
    # 경로에서 특정 부분만 추출 (프로젝트 디렉토리 이름 이후)
    project_folder = 'Sebakwi-emb'
    if project_folder in path:
        path = path.split(project_folder)[-1]
    return path.strip('\\')

def custom_log_info(message):
    caller = sys._getframe(1)  # 상위 프레임 정보 가져오기

    logger = logging.getLogger(caller.f_globals['__name__'])
    filename = shorten_path(caller.f_code.co_filename)
    lineno = caller.f_lineno
    func_name = caller.f_code.co_name  # 현재 실행 중인 함수 이름

    logger.info(f"{filename}:{lineno} - {func_name}() - {message}")