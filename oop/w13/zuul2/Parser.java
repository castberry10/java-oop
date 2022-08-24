package w13.zuul2;

import java.util.Scanner;

/**
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and tries
 * to interpret the line as a two word command. It returns the command as an
 * object of class Command.
 *
 * The parser has a set of known command words. It checks user input against the
 * known commands, and if the input is not one of the known commands, it returns
 * a command object that is marked as an unknown command.
 * 
 * 파서는 사용자 입력을 읽어 그것이 어떤 명령인지 분석한다. 사용자에 의해 입력된 줄을 두 단어로 된 명령으로 해석하려고 시도한다. 그리고
 * 명령의 내용을 담고 있는 Command 객체를 반환한다.
 * 
 * Parser는 명령어 집합을 가지고 있다. 사용자 입력이 명령어 집합 중 어느 하나에 해당되는지 검사한다. 만약, 올바른 명령이 아니라면
 * "모르는 명령"에 해당하는 Command 객체를 반환한다.
 * 
 * @author Michael Klling and David J. Barnes
 * @version 2011.07.31
 */
public class Parser {
	private CommandWords commands; // 유효한 명령어들을 보관하고 있는 객체
	private Scanner reader; // 명령이 입력되는 곳

	/**
	 * Create a parser to read from the terminal window.
	 */
	public Parser() {
		commands = new CommandWords();
		reader = new Scanner(System.in);
	}

	/**
	 * @return The next command from the user.
	 */
	public Command getCommand() {
		String inputLine; // will hold the full input line
		String word1 = null;
		String word2 = null;

		System.out.print("> "); // print prompt

		inputLine = reader.nextLine();

		// Find up to two words on the line.
		Scanner tokenizer = new Scanner(inputLine);
		if (tokenizer.hasNext()) {
			word1 = tokenizer.next(); // get first word
			if (tokenizer.hasNext()) {
				word2 = tokenizer.next(); // get second word
				// note: we just ignore the rest of the input line.
			}
		}
		tokenizer.close();

		// Now check whether this word is known. If so, create a command
		// with it. If not, create a "null" command (for unknown command).
		if (commands.isCommand(word1)) {
			return new Command(word1, word2);
		} else {
			return new Command(null, word2);
		}

	}
}
