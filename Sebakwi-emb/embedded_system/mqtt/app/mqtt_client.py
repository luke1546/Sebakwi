# mqtt/app/mqtt_client.py

import paho.mqtt.client as mqtt
from embedded_system.mqtt.config import *
from embedded_system.utils.log_config import custom_log_info

def on_connect(client, userdata, flags, rc):
    custom_log_info("Connected with result code "+str(rc))

def on_publish(client, userdata, mid):
    custom_log_info("Message Published.")

def setup_mqtt_client():
    client = mqtt.Client(mqtt.CallbackAPIVersion.VERSION1)

    client.on_connect = on_connect
    client.on_publish = on_publish
    client.connect(MQTT_BROKER_URL, MQTT_PORT, 60)

    client.loop_start()  # 네트워크 루프를 시작합니다.

    return client

def publish_message(client, topic, message):
    client.publish(topic, message, qos=2)
