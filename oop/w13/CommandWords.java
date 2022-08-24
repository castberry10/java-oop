package w13;

/**
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game. It is
 * used to recognise commands as they are typed in.
 * 
 * 이 클래스는 게임에 사용될 수 있는 명령어들을 가지고 있으며 사용자 입력이 유효한 명령어인지 판별한다.
 *
 * @author Michael Klling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords {
	// 유효한 명령어들을 가지고 있는 상수 배열.
	private static final String[] validCommands = { "go", "quit", "help" };

	/**
	 * Constructor - initialise the command words.
	 */
	public CommandWords() {
		// nothing to do at the moment...
	}

	/**
	 * Check whether a given String is a valid command word. 주어진 문자열이 유효한 명령어인지
	 * 판별한다.
	 * 
	 * @param aString 주어진 문자열.
	 * @return true, if a given string is a valid command, false, if it isn't.
	 */
	public boolean isCommand(String aString) {
		for (int i = 0; i < validCommands.length; i++) {
			if (validCommands[i].equals(aString))
				return true;
		}
		// 이곳에 도달했다면 validCommands에서 sString을 찾지 못한 것이다.
		return false;
	}
}
