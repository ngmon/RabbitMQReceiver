package main;

import com.rabbitmq.client.*;

/**
 * Created by Filip Gdovin on 19. 2. 2015.
 */

public class RabbitMQReceiver {

    private static final String EXCHANGE_NAME = "sortedLogs";
    private final static String QUEUE_NAME = "testQueue";

    public static void main(String[] argc) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());

            System.out.println("Received '" + message + "'");
        }
    }
}
