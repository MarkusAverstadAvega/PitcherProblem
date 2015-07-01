package com.averstad.pitchers.domain;

public class Pitcher {
	private final int maxVolume;
	private int contents;

	protected Pitcher(int maxVolume) {
		this.maxVolume = maxVolume;
	}

	protected int add(int volume) {
		if(contents + volume >= maxVolume) {
			int overflow = volume - (maxVolume - contents);

			this.contents = this.contents + (volume - overflow);

			return overflow;
		} else {
			this.contents = volume;
			return 0;
		}
	}

	public int getMaxVolume() {
		return maxVolume;
	}

	public int getContents() {
		return contents;
	}

	public void fill() {
		contents = maxVolume;
	}

	public void empty() {
		contents = 0;
	}

	public void pourInto(Pitcher otherPitcher) {
		this.contents = otherPitcher.add(this.contents);
	}

	public boolean isEmpty() {
		return contents == 0;
	}

	public boolean isFull() {
		return contents == maxVolume;
	}

	public static Pitcher withMaxVolume(int maxVolume) {
		return new Pitcher(maxVolume);
	}

	@Override
	public String toString() {
		return "[" + contents + "] ";
	}
}
