# main.py

import mqtt as MQTT
from embedded_system.ai.app import *
from embedded_system.mqtt import MQTT_Topics
from embedded_system.utils.log_config import setup_logging, custom_log_info


def main():
    custom_log_info("프로그램 시작!")
    
    # 로그 관련
    setup_logging()
    
    # MQTT 설정
    client = MQTT.setup_mqtt_client()
    client.loop_start()

    try:

        while True:
            # 실시간 휠 감지
            if detection.extract() == False:
                # 휠이 감지 되지않았다면 계속
                continue
            # 휠 결함 찾기
            json_data = defection.extract()


            # MQTT.publish_message(client, MQTT_Topics.FOUPSTOCKER , json_data)
            MQTT.publish_message(client, MQTT_Topics.random_topic(), json_data)

    except KeyboardInterrupt:
        custom_log_info("Interrupted by user, shutting down.")

    finally:
        client.loop_stop()  # background 네트워크 루프를 멈춥니다.
        client.disconnect()  # 클라이언트 연결을 종료합니다.
        custom_log_info("프로그램을 종료합니다.")

if __name__ == "__main__":
    main()
