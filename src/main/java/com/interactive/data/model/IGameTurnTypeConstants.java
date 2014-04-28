package com.interactive.data.model;

public interface IGameTurnTypeConstants {
	public final static int NOT_LAST_ATTEMPT_RIGHT_GUESS = 1;
	public final static int NOT_LAST_ATTEMPT_WRONG_GUESS = 2;
	public final static int LAST_ATTEMPT_RIGHT_GUESS = 3;
	public final static int LAST_ATTEMPT_WRONG_GUESS = 4;
	public final static int PAST_LAST_ATTEMPT = 5;
}
