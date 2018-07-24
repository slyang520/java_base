package com.slyang.test.mq;

import com.rabbitmq.client.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


// doc https://www.cnblogs.com/piaolingzxh/p/5448927.html
public class RabbitmqTestCase {

	Logger logger = LoggerFactory.getLogger(RabbitmqTestCase.class);
	static final String QUEUE_NAME = "hello";

	@Test
	public void testSendMq() throws IOException, TimeoutException, InterruptedException {


		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");

		Connection connection = factory.newConnection();

		//         //创建一个频道
		Channel channel = connection.createChannel();


		// 1 durable = true  该队列机器重启后 对立会自动恢复
		boolean durable = true;


		//         //指定一个队列
		channel.queueDeclare(QUEUE_NAME, durable, false, false, null);

		String message = "Hello World!";

		//往队列中发出一条消息

		for (int i = 0; i < 500; i++) {
			channel.basicPublish("", QUEUE_NAME, null, (message + i).getBytes("UTF-8"));
		}

	}


	@Test
	public void testRecvMq() throws IOException, TimeoutException, InterruptedException {


		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");

		Connection connection = factory.newConnection();

		//创建一个频道
		Channel channel = connection.createChannel();

		//指定一个队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		// 创建消费者
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
									   byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + message + "'");

				try {
					Thread.sleep(1000);        // 模拟耗时
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 1.0  对处理好的消息进行应答
				/**
				 * 第二个参数multiple：一般设置为false
				 * 如果值为true，则执行批量确认，此deliveryTag之前收到的消息全部进行确认;
				 * 如果值为false，则只对当前收到的消息进行确认
				 */
				channel.basicAck(envelope.getDeliveryTag(), false);

				// 2.0  拒绝处理消息，消息重新回到队列中
				/**
				 *
				 * requeue = true ，则重新放入RabbitMQ的发送队列，
				 * requeue = false  通知RabbitMQ销毁这条消息
				 */

				// channel.basicReject(envelope.getDeliveryTag(), true);


				/**
				 * channel.basicReject 的区别在于basicNack可以拒绝多条消息，而basicReject一次只能拒绝一条消息
				 */

				/**
				 *
				 * deliveryTag:该消息的index
				 *  multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
				 *  requeue：被拒绝的是否重新入队列
				 *
				 */
				//channel.basicNack();

			}
		};
		boolean autoAck = false;  // 开启ACT机制  只有应答了 才从消息队列中移除
		channel.basicConsume(QUEUE_NAME, autoAck, consumer);


		Thread.sleep(8000);        // 模拟耗时

	}


}
