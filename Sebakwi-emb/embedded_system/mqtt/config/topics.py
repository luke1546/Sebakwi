# mqtt/config/topics.py
import random

BASE_TOPIC = "checkup"
class MQTT_Topics:
    FOUPSTOCKER = f"{BASE_TOPIC}/foup_stocker"
    ETCHING = f"{BASE_TOPIC}/etching"
    CLEANING = f"{BASE_TOPIC}/cleaning"
    PHOTOS = f"{BASE_TOPIC}/photos"

    @classmethod
    def list_topic(cls):
        topics = [
            MQTT_Topics.FOUPSTOCKER,
            MQTT_Topics.ETCHING,
            MQTT_Topics.CLEANING,
            MQTT_Topics.PHOTOS
        ]

        return topics

    @classmethod
    def random_topic(cls):
        return random.choice(cls.list_topic())