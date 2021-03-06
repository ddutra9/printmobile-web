package com.tcc.printmobile_web.model;

import java.io.Serializable;

public class File implements Serializable {
	private static final long serialVersionUID = 1L;

	private Boolean colorful;
	private Boolean landscape;
	private byte[] byteOfObj;
	private String byteBase64;
	private Long copies;

	public File() {
		super();
	}

	public File(Boolean colorful, Boolean landscape, Long copies) {
		this();
		this.colorful = colorful;
		this.landscape = landscape;
		this.copies = copies;
	}

	public Boolean getColorful() {
		return colorful;
	}

	public void setColorful(Boolean colorful) {
		this.colorful = colorful;
	}

	public Boolean getLandscape() {
		return landscape;
	}

	public void setLandscape(Boolean landscape) {
		this.landscape = landscape;
	}

	public Long getCopies() {
		return copies;
	}

	public void setCopies(Long copies) {
		this.copies = copies;
	}

	public byte[] getByteOfObj() {
		return byteOfObj;
	}

	public void setByteOfObj(byte[] byteOfObj) {
		this.byteOfObj = byteOfObj;
	}

	@Override
	public String toString() {
		return "colorful: '" + getColorful() + "', landscape: '"
				+ getLandscape() + "', copies: '" + getCopies() + "'";
	}

	public String getByteBase64() {
		return byteBase64;
	}

	public void setByteBase64(String byteBase64) {
		this.byteBase64 = byteBase64;
	}
}
