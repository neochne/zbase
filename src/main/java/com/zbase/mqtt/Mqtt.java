package com.zbase.mqtt;

public final class Mqtt {

    private static IMqttClient IMqttClient;

    public static void initClient(IMqttClient IMqttClient) {
        Mqtt.IMqttClient = IMqttClient;
    }

    public static boolean isConnected() {
        return IMqttClient.isConnected();
    }

    public static void connect(String user) {
        IMqttClient.connect(user);
    }

    public static void disconnect() {
        IMqttClient.disconnect();
    }

    public static void subscribe(String... topics) {
        IMqttClient.subscribe(topics);
    }

    public static void unsubscribe(String... topics) {
        IMqttClient.unsubscribe(topics);
    }

    public static void publish(String topic, String msg, int qos) {
        IMqttClient.publish(topic,msg,qos);
    }

    public static void setMsgListener(MqttMsgListener listener) {
        IMqttClient.setMsgListener(listener);
    }

}
