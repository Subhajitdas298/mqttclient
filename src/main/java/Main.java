public class Main {
    public static void main(String... args){
        SomeEventProducer producer = new SomeEventProducer("c1");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer.sendMessage("topic1", "hello");
        producer.close();
    }
}
