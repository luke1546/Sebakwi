# 개요


## 프로젝트 구조

- **app/**: 메인 애플리케이션 코드가 포함되어 있습니다.
- **config/**: 설정 파일이 포함되어 있습니다.

## 필수 조건

- Python 3
- 파이썬 패키지 관리를 위한 pip
- MQTT 브로커 (예: Mosquitto, HiveMQ)

## 설치 방법

1. 저장소를 클론합니다:

2. 필요한 파이썬 패키지를 설치합니다:
   ```
   pip install -r requirements.txt
   ```
3. `config/settings.py`에서 MQTT 브로커의 상세 정보에 맞게 설정을 구성합니다.

## 사용 방법

애플리케이션을 실행합니다:
   ```
   python main.py
   ```
