package com.averstad.pitchers;

import com.averstad.pitchers.domain.Pitcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Program to solve
 */
public class PitcherApp {
	private static final Logger log = LoggerFactory.getLogger(PitcherApp.class);
	private static final int MAX_STEPS = 50;

	int run(int pitcher1Volume, int pitcher2Volume, int target) {

		Pitcher pitcher1 = Pitcher.withMaxVolume(pitcher1Volume);
		Pitcher pitcher2 = Pitcher.withMaxVolume(pitcher2Volume);

		log.info("Testing for " + target + " liters in one pitcher.");
		log.info("Pitcher 1 is a " + pitcher1.getMaxVolume() + " liter pitcher.");
		log.info("Pitcher 2 is a " + pitcher2.getMaxVolume() + " liter pitcher.");
		int numberOfSteps = experimentFor(pitcher2, pitcher1, target);
		log.info("Solution found. Took " + numberOfSteps + " steps.\n");

		return numberOfSteps;
	}

	private int experimentFor(Pitcher pitcher1, Pitcher pitcher2, int target) {
		// Determining which pitcher is smaller is necessary for the logic to work.
		Pitcher smallPitcher;
		Pitcher largePitcher;
		if (pitcher1.getMaxVolume() > pitcher2.getMaxVolume()) {
			largePitcher = pitcher1;
			smallPitcher = pitcher2;
		} else {
			largePitcher = pitcher2;
			smallPitcher = pitcher1;
		}

		int steps = 0;
		while (steps < MAX_STEPS) {
			step(largePitcher, smallPitcher);

			log.info("[" + largePitcher.getContents() + "/" + largePitcher.getMaxVolume()
					+ " " + smallPitcher.getContents() + "/" + smallPitcher.getMaxVolume() + "]");

			steps++;

			if (smallPitcher.getContents() == target || largePitcher.getContents() == target) {
				return steps;
			}
		}
		throw new IllegalArgumentException("No solution found in " + MAX_STEPS + " steps.");
	}

	/**
	 * Each step is either filling or emptying a pitcher, or pouring the contents of one into the other
	 * pitcher.
	 */
	private void step(Pitcher largePitcher, Pitcher smallPitcher) {
		if (largePitcher.isEmpty()) {
			largePitcher.fill();
		} else if (!smallPitcher.isFull()) {
			largePitcher.pourInto(smallPitcher);
		} else {
			smallPitcher.empty();
		}
	}

	public static void main(String[] args) {
		try {
			if(args.length == 0) {
				// Run demo application.
				new PitcherApp().run(3, 5, 1);
				new PitcherApp().run(3, 5, 4);
			} else if(args.length == 3 && allIntegers(args)) {
				new PitcherApp().run(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
			} else {
				throw new IllegalArgumentException("Expected three Integers: Pitcher 1 volume, Pitcher 2 volume and Target volume.");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	private static boolean allIntegers(String[] args) {
		try {
			Arrays.stream(args).forEach(Integer::parseInt);
			return true;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Expected three Integers: Pitcher 1 volume, Pitcher 2 volume and Target volume.");
		}
	}
}
