package app.java.com.model.entities.user;

import app.java.com.model.entities.Type;

public class UserTypeFinder {

	public static boolean isTeqStaff(User user) {
		if (user.getUserType().compareTo(Type.TEQ.getType()) == 0) {
			return true;
		} else {
			return false;
		}
	}
}
