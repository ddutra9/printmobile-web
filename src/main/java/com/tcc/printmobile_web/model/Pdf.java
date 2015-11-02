package com.tcc.printmobile_web.model;

public class Pdf extends File {
	private static final long serialVersionUID = 1L;

	private String intervalPage;

	public String getIntervalPage() {
		return intervalPage;
	}

	public void setIntervalPage(String intervalPage) {
		this.intervalPage = intervalPage;
	}

	public Pdf() {

	}

	public Pdf(Boolean colorful, Boolean landscape, Long copies,
			String intervalPage) {
		super(colorful, landscape, copies);
		this.intervalPage = intervalPage;
	}

	@Override
	public String toString() {
		return "Pdf [getIntervalPage()=" + getIntervalPage()
				+ ", getColorful()=" + getColorful() + ", getLandscape()="
				+ getLandscape() + ", getCopies()=" + getCopies() + "]";
	}
}
