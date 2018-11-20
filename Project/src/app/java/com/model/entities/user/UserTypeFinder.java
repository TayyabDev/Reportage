package app.java.com.model.entities.user;

import app.java.com.model.entities.Type;

public class UserTypeFinder {

	public static boolean isTeqStaff(User user) {
		return (user.getUserType().compareTo(Type.TEQ.getType()) == 0);
	}
}
