package w13.zuul3;

/**
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 *
 * This class holds information about a command that was issued by the user. A
 * command currently consists of two strings: a command word and a second word
 * (for example, if the command was "take map", then the two strings obviously
 * are "take" and "map").
 * 
 * The way this is used is: Commands are already checked for being valid command
 * words. If the user entered an invalid command (a word that is not known) then
 * the command word is null.
 *
 * If the command had only one word, then the second word is null.
 * 
 * 이 클래스는 사용자가 입력한 명령의 내용을 간직한다. 명령은 "명령어"와 "두번째단어" 등 두 개의 문자열로 구성된다. 예를 들어,
 * "take map" 명령이 입력됐다면 명령어는 "take"이고 두번째단어는 "map"이다.
 * 
 * Command 객체는 "명령어"와 "두번째단어"를 간직한다. 만약 사용자가 유효하지 않은 명령어를 입력했다면 명령어가 null로 기록된다.
 * 명령어는 유효하지만 두번째단어를 입력하지 않았다면 "두번째단어"에 null이 기록된다.
 * 
 * @author Michael Klling and David J. Barnes
 * @version 2011.07.31
 */

public class Command {
	private String commandWord;
	private String secondWord;

	/**
	 * Command 객체를 구성한다. 첫 단어와 두 번째 단어가 제공되어야 한다. 두 단어 중 어느 하나가 null일 수 있으며 두 단어 모두가
	 * null일 수도 있다.
	 * 
	 * @param firstWord  명령의 첫 단어. 입력된 명령어가 유효하지 않은 경우에는 null.
	 * @param secondWord 명령의 두 번째 단어.
	 */
	public Command(String firstWord, String secondWord) {
		commandWord = firstWord;
		this.secondWord = secondWord;
	}

	/**
	 * Return the command word (the first word) of this command. If the command was
	 * not understood, the result is null.
	 * 
	 * @return The command word.
	 */
	public String getCommandWord() {
		return commandWord;
	}

	/**
	 * @return The second word of this command. Returns null if there was no second
	 *         word.
	 */
	public String getSecondWord() {
		return secondWord;
	}

	/**
	 * 올바르지(유효하지) 않은 명령어인가?
	 * 
	 * @return true if this command was not understood.
	 */
	public boolean isUnknown() {
		return (commandWord == null);
	}

	/**
	 * 이 Command가 두번째단어를 가지고 있는가?
	 * 
	 * @return true if the command has a second word.
	 */
	public boolean hasSecondWord() {
		return (secondWord != null);
	}
}
