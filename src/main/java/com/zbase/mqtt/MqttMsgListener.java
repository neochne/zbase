package com.zbase.mqtt;

public interface MqttMsgListener {

    void onMessageArrive(String topic, String msg);

    void onPublishFinish();

    void onConnectComplete(boolean reconnect, String broker);

    void onConnectionLost(String cause);

}
