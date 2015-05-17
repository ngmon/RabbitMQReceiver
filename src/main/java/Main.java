/**
 * Created by Filip Gdovin on 30. 4. 2015.
 */
public class Main {

    public static void main(String[] args) {
        RabbitMQReceiver myReceiver = new RabbitMQReceiver();
        try {
            myReceiver.listen(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
