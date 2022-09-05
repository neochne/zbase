package com.zbase.mqtt;

public interface IMqttClient {

    boolean isConnected();

    void connect(String user);

    void disconnect();

    void subscribe(String... topics);

    void unsubscribe(String... topics);

    default void publish(String topic,String msg) {
        publish(topic,msg,2);
    }

    /**
     * @param topic topic
     * @param msg   message content
     * @param qos   0：“至多一次”，消息发布完全依赖底层 TCP/IP 网络。会发生消息丢失或重复。这一级别可用于如下情况，
     *              环境传感器数据，丢失一次读记录无所谓，因为不久后还会有第二次发送
     *              1：“至少一次”，确保消息到达，但消息重复可能会发生。这一级别可用于如下情况，
     *              你需要获得每一条消息，并且消息重复发送对你的使用场景无影响
     *              2：“只有一次”，确保消息到达一次。这一级别可用于如下情况，在计费系统中，消息重复或丢失会导致不正确的结果
     */
    void publish(String topic,String msg,int qos);

    void setMsgListener(MqttMsgListener listener);

}
