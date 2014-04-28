package com.interactive.data.model;

//The service handler for the game logic

public interface IGameLogicService {
	public GameStatusVO handleLogic(GameStatusVO previousAttempt) throws Exception;
	public GameStatusVO initGame() throws Exception;
}
