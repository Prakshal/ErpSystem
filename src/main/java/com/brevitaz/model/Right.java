package com.brevitaz.model;

public class Right
{
	private String rightId;
	private String rightName;
	public String getRightId() {
		return rightId;
	}
	public void setRightId(String rightId) {
		this.rightId = rightId;
	}
	public String getRightName() {
		return rightName;
	}
	@Override
	public String toString() {
		return "Right [rightId=" + rightId + ", rightName=" + rightName + "]";
	}
	public void setRightName(String rightName) {
		this.rightName = rightName;
	}
}
