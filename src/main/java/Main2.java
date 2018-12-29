public class Main2 {
    public static void main(String... args){
        SomeEventProducerPaho producer = new SomeEventProducerPaho();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer.sendMessage("topic1", "hello");
        //producer.close();
    }
}
