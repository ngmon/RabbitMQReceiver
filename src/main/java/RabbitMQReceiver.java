import com.rabbitmq.client.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Filip Gdovin
 * @version 19. 2. 2015
 */

public class RabbitMQReceiver {

    private static final Logger logger = LogManager.getLogger(RabbitMQReceiver.class);

    //queueName will NOT be created if non-existing, purpose is to create only in app!!
    //set numOfMessages to 0 for endless message waiting
    public void listen(String[] args) throws Exception {

        String hostName = args[0];
        String queueName = args[1];
        int numOfMessages = Integer.parseInt(args[2]);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(hostName);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        logger.info(" [*] Waiting for messages. To exit press CTRL+C");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);

        int messageCount = 0;
        while (messageCount <= numOfMessages) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());

            logger.info(message);
        }
    }
}
