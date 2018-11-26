package app.java.com.model.entities;

public enum Type {

	AGENCY("A"), TEQ("T");

	private String type;

	Type(String name) {
		type = name;
	}

	public String getType() {
		return this.type;
	}
}

