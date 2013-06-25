package haw.ai.hapsar_payment;

import redis.clients.jedis.Jedis;

public class HapsarPayment {
	private static final String REDIS_HOST = "localhost";
	private static final Integer REDIS_PORT = 6379;
	private static final String HAPSAR_QUEUE_NAME = "hapsar_queue";

	public static void main(String[] args) {
		Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);
		jedis.rpush(HAPSAR_QUEUE_NAME, argumentsToHapsarQueueFormat(args[0], args[1]));
		jedis.quit();
	}
	
	public static String argumentsToHapsarQueueFormat(String rechnungsNr, String betrag) {
		return rechnungsNr + " " + betrag;
	}
}
