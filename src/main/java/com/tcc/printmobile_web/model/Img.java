package com.tcc.printmobile_web.model;

public class Img extends File {
	private static final long serialVersionUID = 1L;

	public Img() {

	}

	public Img(Boolean colorful, Boolean landscape, Long copies) {
		super(colorful, landscape, copies);
	}
}
