package qa.swapi.util;

import java.util.Random;

public class RandomSelection {
	public static String randomPeopleSelection() {
		Random rn = new Random();
		int answer = rn.nextInt(83 - 1 + 1) + 1;
		String rndNum = Integer.toString(answer);
		return rndNum;
	}
}
