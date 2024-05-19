# main.py

import mqtt as MQTT
from embedded_system.ai.app import *
from embedded_system.mqtt import MQTT_Topics
from embedded_system.util.log_config import setup_logging, custom_log_info
import embedded_system.util.config as config
import time

def main():
    custom_log_info("프로그램 시작!")
    
    # 로그 관련
    setup_logging()
    
    # MQTT 설정
    client = MQTT.setup_mqtt_client()
    client.loop_start()

    global img_name
    global switch_status

    try:

        while True:
            switch_status = config.switch_status
            custom_log_info(f"current Switch Status : {switch_status}")
            if switch_status == False :
                custom_log_info("System stop")
                time.sleep(5)
                continue
            
            
            # 실시간 휠 감지
            if detection.extract() == False:
                # 휠이 감지 되지않았다면 계속
                continue

            # 휠 결함 찾기d
            json_data = defection.extract()
            
            custom_log_info("검진 데이터를 전송합니다.")
            MQTT.publish_message(client, MQTT_Topics.FOUPSTOCKER , json_data)
            # MQTT.publish_message(client, MQTT_Topics.random_topic(), json_data)

    except KeyboardInterrupt:
        custom_log_info("Interrupted by user, shutting down.")

    finally:
        client.loop_stop()  # background 네트워크 루프를 멈춥니다.
        client.disconnect()  # 클라이언트 연결을 종료합니다.
        custom_log_info("프로그램을 종료합니다.")

if __name__ == "__main__":
    main()
