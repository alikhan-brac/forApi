package qa.restapi.util;

import java.util.Random;

public class LipsumGenerator {
	private static String[] words = new String[] { "Lorem", "ipsum", "dolor", "sit", "amet", "consetetur", "sadipscing",
			"elitr", "sed", "diam", "nonumy", "eirmod", "tempor", "invidunt", "ut", "labore", "et", "dolore", "magna",
			"aliquyam", "erat", "sed", "diam", "voluptua", "At", "vero", "eos", "et", "accusam", "et", "justo", "duo",
			"dolores", "et", "ea", "rebum", "Stet", "clita", "kasd", "gubergren", "no", "sea", "takimata", "sanctus",
			"est" };
	private static Random random = new Random();

	public static String getRandomWord() {
		return words[random.nextInt(words.length)];
	}

	public static String getRandomSentence(int wordNumber) {
		StringBuffer buffer = new StringBuffer(wordNumber * 12);

		int j = 0;
		while (j < wordNumber) {
			buffer.append(getRandomWord());
			buffer.append(" ");
			j++;
		}
		return buffer.toString();
	}
}
