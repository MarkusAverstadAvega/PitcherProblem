package com.averstad.pitchers;

import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.fail;

public class PitcherAppTest {
	@Test
	public void shouldSucceedForThreeAndFiveLitersTargetOneLiterTest() {
		int pitcher1Volume = 3;
		int pitcher2Volume = 5;
		int target = 1;

		int numberOfSteps = new PitcherApp().run(pitcher1Volume, pitcher2Volume, target);

		assertThat(numberOfSteps).isEqualTo(8);
	}

	@Test
	public void shouldSucceedForThreeAndFiveLitersTargetFourLitersTest() {
		int pitcher1Volume = 3;
		int pitcher2Volume = 5;
		int target = 4;

		int numberOfSteps = new PitcherApp().run(pitcher1Volume, pitcher2Volume, target);

		assertThat(numberOfSteps).isEqualTo(6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldFailForEqualEvenVolumePitchersTest() {
		int pitcher1Volume = 4;
		int pitcher2Volume = 4;
		int target = 2;

		new PitcherApp().run(pitcher1Volume, pitcher2Volume, target);

		fail("Expected IllegalArgumentException.");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldFailForEqualUnevenVolumePitchersTest() {
		int pitcher1Volume = 5;
		int pitcher2Volume = 5;
		int target = 3;

		new PitcherApp().run(pitcher1Volume, pitcher2Volume, target);

		fail("Expected IllegalArgumentException.");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldFailForImpossibleOperationTest() {
		int pitcher1Volume = 4;
		int pitcher2Volume = 6;
		int target = 1;

		new PitcherApp().run(pitcher1Volume, pitcher2Volume, target);

		fail("Expected IllegalArgumentException.");
	}
}
