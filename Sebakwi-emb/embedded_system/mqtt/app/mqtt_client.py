# mqtt/app/mqtt_client.py

import paho.mqtt.client as mqtt
from embedded_system.mqtt.config import *
from embedded_system.util.log_config import custom_log_info
from embedded_system.util import config
# from embedded_system.mqtt import MQTT_Topics

def on_connect(client, userdata, flags, rc):
    custom_log_info("Connected with result code " + str(rc))
    client.subscribe(MQTT_Topics.SWITCH)
    custom_log_info(f"Subscribed to topic: {MQTT_Topics.SWITCH}")

def on_publish(client, userdata, mid):
    custom_log_info("Message Published.")

def on_message(client, userdata, message):
    payload = message.payload.decode()
    config.switch_status = True if payload == "true" else False
    custom_log_info(f"스위치 상태 {config.switch_status} {'OHT 검사를 시작합니다!' if config.switch_status else 'OHT 검사를 중지합니다!'}")    

def setup_mqtt_client():
    client = mqtt.Client()
    client.on_connect = on_connect
    client.on_publish = on_publish
    client.on_message = on_message

    custom_log_info("Connecting to MQTT broker...")
    client.connect(MQTT_BROKER_URL, MQTT_PORT, 60)
    client.loop_start()  # 네트워크 루프를 시작합니다.

    return client

def publish_message(client, topic, message):
    client.publish(topic, message, qos=2)

