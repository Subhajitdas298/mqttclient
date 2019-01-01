import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;

public class SomeEventProducer {

    MQTT mqtt = null;
    BlockingConnection connection = null;

    public SomeEventProducer(String clientId) {
        try {
            mqtt = new MQTT();
            mqtt.setHost("tcp://localhost:1883");
            mqtt.setUserName("abc");
            mqtt.setPassword("pass123");
            mqtt.setClientId(clientId);
            connection = mqtt.blockingConnection();
            connection.connect();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void sendMessage(String topicName, String data) {
        try {
            connection.publish("topic1", "Hello".getBytes(), QoS.AT_LEAST_ONCE, false);
        } catch (Exception e) {
            System.out.println("Exception occurred:  " + e.getMessage());
            close();
        }
    }

    public void close(){
        try {
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(1);
    }
}