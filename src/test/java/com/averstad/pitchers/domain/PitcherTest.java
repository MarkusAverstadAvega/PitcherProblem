package com.averstad.pitchers.domain;

import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class PitcherTest {
	@Test
	public void pitcherShouldInstantiateAsEmptyTest() {
		Pitcher pitcher = Pitcher.withMaxVolume(5);

		assertTrue(pitcher.isEmpty());
		assertThat(pitcher.getMaxVolume()).isEqualTo(5);
	}

	@Test
	public void filledPitcherShouldBeFullTest() {
		Pitcher pitcher = Pitcher.withMaxVolume(5);

		pitcher.fill();

		assertFalse(pitcher.isEmpty());
		assertTrue(pitcher.isFull());
		assertThat(pitcher.getContents()).isEqualTo(5);
		assertThat(pitcher.getMaxVolume()).isEqualTo(5);
	}

	@Test
	public void emptiedPitcherShouldBeEmptyTest() {
		Pitcher pitcher = Pitcher.withMaxVolume(5);

		pitcher.fill();

		assertThat(pitcher.getContents()).isEqualTo(5);

		pitcher.empty();

		assertTrue(pitcher.isEmpty());
		assertThat(pitcher.getMaxVolume()).isEqualTo(5);
	}

	@Test
	public void pourFullContentsOfPitcherTest() {
		Pitcher tenLiterPitcher = Pitcher.withMaxVolume(10);
		Pitcher fiveLiterPitcher = Pitcher.withMaxVolume(5);

		tenLiterPitcher.fill();
		tenLiterPitcher.pourInto(fiveLiterPitcher);

		assertThat(fiveLiterPitcher.getContents()).isEqualTo(5);
		assertThat(tenLiterPitcher.getContents()).isEqualTo(5);
	}

	@Test
	public void overflowShouldRemainInPitcherWhenPouredIntoSmallerPitcherTest() {
		Pitcher fifteenLiterPitcher = Pitcher.withMaxVolume(15);
		Pitcher fiveLiterPitcher = Pitcher.withMaxVolume(5);

		fifteenLiterPitcher.fill();
		fifteenLiterPitcher.pourInto(fiveLiterPitcher);

		assertThat(fiveLiterPitcher.getContents()).isEqualTo(5);
		assertThat(fifteenLiterPitcher.getContents()).isEqualTo(10);
	}

	@Test
	public void pourUntilEmptyTest() {
		Pitcher fifteenLiterPitcher = Pitcher.withMaxVolume(15);
		Pitcher fiveLiterPitcher = Pitcher.withMaxVolume(5);

		fiveLiterPitcher.fill();
		fiveLiterPitcher.pourInto(fifteenLiterPitcher);

		assertThat(fifteenLiterPitcher.getContents()).isEqualTo(5);
		assertThat(fiveLiterPitcher.isEmpty()).isTrue();
	}

	@Test
	public void threeLitersIntoPartiallyFullTest() {
		TestPitcher threeLiterPitcher = new TestPitcher(3);
		TestPitcher fiveLiterPitcher = new TestPitcher(5);

		threeLiterPitcher.setContents(3);
		fiveLiterPitcher.setContents(3);
		threeLiterPitcher.pourInto(fiveLiterPitcher);

		assertThat(threeLiterPitcher.getContents()).isEqualTo(1);
		assertThat(fiveLiterPitcher.getContents()).isEqualTo(5);
	}

	/**
	 * Test object for Pitcher, exposes setter for Pitcher.contents to this test
	 * class only since we want to keep Pitcher as immutable as possible.
	 */
	private class TestPitcher extends Pitcher {
		private TestPitcher(int maxVolume) {
			super(maxVolume);
		}

		void setContents(int contents) {
			add(contents);
		}
	}
}
