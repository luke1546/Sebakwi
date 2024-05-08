import time
from datetime import datetime
import mqtt as MQTT
from ai.app import *


def main():
    count = 0

    try:
        # MQTT 클라이언트를 시작합니다. 모든 메시지 처리는 background thread에서 수행됩니다.
        client = MQTT.setup_mqtt_client()
        client.loop_start()

        while True:
            print("-----------------")
            print(detection.extract())
            print(defection.extract())
            print("-----------------")

            message = "Hello, MQTT! " + datetime.now().isoformat()

            MQTT.publish_message(client, MQTT.HELLO_MQTT_TOPIC, message)
            count += 1
            time.sleep(2)

    except KeyboardInterrupt:
        print("Interrupted by user, shutting down.")

    finally:
        client.loop_stop()  # background 네트워크 루프를 멈춥니다.
        client.disconnect()  # 클라이언트 연결을 종료합니다.
        print("MQTT client disconnected.")

if __name__ == "__main__":
    main()
