package ie.sessionfinder;

import java.util.ArrayList;

public interface SessionListHandler {
	public void onSessionListReceived(ArrayList<Session> matches);
}
