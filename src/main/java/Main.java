/**
 * Created by Filip Gdovin on 30. 4. 2015.
 */
public class Main {

    public static void main(String[] args) {
        RabbitMQReceiver myReceiver = new RabbitMQReceiver();
        try {
//            "147.251.43.204", "esperOutputQueue", "date_last_seen", 0
            myReceiver.listen(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
