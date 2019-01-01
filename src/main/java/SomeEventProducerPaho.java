import java.util.Properties;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class SomeEventProducerPaho {

    private MqttClient mqtt = null;

    public SomeEventProducerPaho() {
        try {
            mqtt = new MqttClient("tcp://localhost:1883", "client1");
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName("user1");
            options.setPassword("abcd1234".toCharArray());
            if( mqtt.isConnected())
                mqtt.disconnect();
            mqtt.connect(options);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage( String topicName, String data ) {
        try {
            mqtt.getTopic(topicName).publish(data.getBytes(), 1, false);
            mqtt.disconnect();
            System.exit(0);
        }
        catch( Exception e ) {
            System.out.println("Exception occurred:  " + e.getMessage());
        }
    }
}