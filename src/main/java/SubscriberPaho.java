import org.eclipse.paho.client.mqttv3.*;

public class SubscriberPaho {

    public static final String BROKER_URL = "tcp://localhost:1883";
    //public static final String BROKER_URL = "tcp://test.mosquitto.org:1883";

    //We have to generate a unique Client id.
    String clientId = "c-sub1";
    private MqttClient mqttClient;

    public SubscriberPaho() {

        try {
            mqttClient = new MqttClient(BROKER_URL, clientId);


        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void start() {
        try {

            mqttClient.setCallback(new MqttCallback() {
                public void connectionLost(Throwable throwable) {
                    System.out.println("Connection lost");
                }

                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    String message = new String(mqttMessage.getPayload());
                    System.out.println("Topic : " + s + ", Message : " + message);

                    if(message.equals("exit")){
                        mqttClient.unsubscribe("topic1/#");
                        mqttClient.disconnect();
                    }
                }

                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    System.out.println("Delivered with token : " + iMqttDeliveryToken);
                }
            });
            mqttClient.connect();

            //Subscribe to all subtopics of homeautomation
            mqttClient.subscribe("topic1/#");

        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String... args) {
        final SubscriberPaho subscriber = new SubscriberPaho();
        subscriber.start();
    }

}