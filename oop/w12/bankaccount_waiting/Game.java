package w12.bankaccount_waiting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Game {
	private Parser parser;
	Room hall, lectureRoom, computerRoom, office, dongBang, cellar;
	Player player;

	/**
	 * Create the game and initialise its internal map.
	 */
	public Game() {
		createRooms();
		parser = new Parser();
	}

	/**
	 * Create all the rooms and link their exits together. 방들을 만들고 방의 출구들을 서로 엮어준다.
	 */
	private void createRooms() {
		

		// create the rooms
		hall = new Room("Hall");
		lectureRoom = new Room("Lecture room");
		dongBang = new Room("Dongari room");
		computerRoom = new Room("Computer room");
		office = new Room("Office");
        cellar = new Room("Cellar");
        computerRoom.addItem(new Item("book", "Old magic book", 10));
        dongBang.addItem(new Item("portion", "Increas strength 5 levels", 5));
        dongBang.addItem(new Item("book", "AI tech book", 5));
        
        
        // initialise room exits
        hall.setExit("east", lectureRoom);
        hall.setExit("south", computerRoom);
        hall.setExit("west", dongBang);
        lectureRoom.setExit("west", hall);
        dongBang.setExit("east", hall);
        computerRoom.setExit("north", hall);
        computerRoom.setExit("east", office);
        computerRoom.setExit("down", cellar);
        office.setExit("west", computerRoom);
        cellar.setExit("up", computerRoom);

        player  = new Player(hall, 20);
        
		
	}

	/**
	 * Main play routine. Loops until end of play.
	 */
	public void play() {
		printWelcome();

		// Enter the main command loop. Here we repeatedly read commands and
		// execute them until the game is over.
		// 명령을 처리하는 주 반복문.
		// 게임이 끝날 때까지 명령을 읽고 명령을 수행하는 일을 반복한다.

		boolean finished = false;
		while (!finished) {
			Command command = parser.getCommand(); // 파싱 후 Command 객체를 반환함에 유의!
			finished = processCommand(command);
		}
		System.out.println("Thank you for playing.  Good bye.");
	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
		System.out.println();
		System.out.println("Welcome to the World of Zuul!");
		System.out.println("World of Zuul is a new, incredibly boring adventure game.");
		System.out.println("Type 'help' if you need help.");
		System.out.println();

		printLocationInfo(player.getCurrentRoom());
	}

	/**
	 * Given a command, process (that is: execute) the command.
	 * 
	 * @param command 처리할 명령.
	 * @return true (게임을 끝내는 명령인 경우), false (그렇지 않은 경우)
	 */
	private boolean processCommand(Command command) {
		boolean wantToQuit = false;

		if (command.isUnknown()) {
			System.out.println("Unknown command...");
			return false;
		}
		
		// Command 객체는 commandWord와 secondWord를 필드로 갖는다.
		// 모든 Command에는 commandWord가 들어 있다(필수).
		// secondWord는 있을 수도 있고 없을 수도 있다(옵션).
		// 없는 경우 null.
		String commandWord = command.getCommandWord();
		if (commandWord.equals("help")) {
			printHelp();
		} else if (commandWord.equals("go")) {
			goRoom(command);
		} else if (commandWord.equals("quit")) {
			wantToQuit = quit(command);
		}else if(commandWord.equals("look")) {
			look();
		}else if(commandWord.equals("eat")) {
			eat();
		}else if(commandWord.equals("back")) {
			back(command);
		}else if(commandWord.equals("take")) {
			take(command);
		}else if(commandWord.equals("drop")) {
			drop(command);
		}else if(commandWord.equals("items")) {
			items();
		}

		return wantToQuit;
	}

	// implementations of user commands:

	/*
	 * Print out some help information. Here we print some stupid, cryptic message
	 * and a list of the command words.
	 */
	private void printHelp() {
		System.out.print("Commands: ");
		System.out.println(parser.getCommandList());
	}

	private void look() {
		System.out.print(player.getCurrentRoom().getLongDescription());
	}
	
	private void eat() {
		System.out.println("Delicious!");
	}
	
	private void items() {
		printItems(player.getItems());
	}
	
	/**
	 * 지정된 List에 있는 모든 아이템들의 상세 내역을 출력한다. 
	 * 아이템들의 총 무게와 이 선수가 들 수 있는 최대 무게도 함께 출력한다. 
	 * @param items
	 */
	private void printItems(List<Item> items) {
	
		int sum = 0;
		
		System.out.println("<Carrying Items>");
		for(Item item : items) {
			System.out.println(item.getLongDescription());
			sum += item.getWeight();
		}
		System.out.println("<Total weight: "+ sum +", max weight: " + player.getMaxWeight() + ">");
		
	}
	
	private void take(Command command) {
		if (!command.hasSecondWord()) {
			System.out.println("잘못된 사용입니다. ");
			return;
		}
		String itemName = command.getSecondWord();
		
		Item item = player.takeItem(itemName);
		
		if(item == null) {
			System.out.println("Cannot take item.");
		}else {
			List<Item> items = player.getItems();
			printItems(items);
		}
		
	}
	private void drop(Command command) {
		if (!command.hasSecondWord()) {
			System.out.println("잘못된 사용입니다. ");
			return;
		}
		
		String itemName = command.getSecondWord();
		
		Item item = player.dropItem(itemName);
		
		if(item == null) {
			System.out.println("You don't have that item.");
			
		}else {
			List<Item> items = player.getItems();
			printItems(items);
		}
		
	}
	
	private void back(Command command) {
		if (command.hasSecondWord()) {
			// Command에 second word가 없는 경우
			System.out.println("You can go back one step only.");
			System.out.println("back command cannot have the second word.");
			return;
		}
		
		player.back();

		printLocationInfo(player.getCurrentRoom());
	}
	
	
	/*
	 * go 명령일 때 이 메소드가 실행된다. "두번째단어"로 north, east, south, west 중 하나가 주어져야 한다. 주어진
	 * 방향으로의 이동을 시도한다. 그 방향으로 방이 연결되어 있지 않은 경우에는 에러 메세지를 출력한다.
	 */
	private void goRoom(Command command) {
		if (!command.hasSecondWord()) {
			// Command에 second word가 없는 경우
			System.out.println("Where to go?");
			return;
		}
		
		String direction = command.getSecondWord();
	
		if(player.moveTo(direction) == -1) {
			System.out.println("No exit in that direction!");
		}else {
			printLocationInfo(player.getCurrentRoom());
		}
	}

	
	/*
	 * "Quit" was entered. Check the rest of the command to see whether we really
	 * quit the game.
	 * 
	 * @return true, if this command quits the game, false otherwise.
	 */
	private boolean quit(Command command) {
		// quit 명령어는 second word를 갖지 말아야 한다.
		if (command.hasSecondWord()) {
			System.out.println("Quit what?");
			return false;
		} else {
			return true; // signal that we want to quit
		}
		
	}

	/**
	 * 현재 위치에 관한 정보를 출력한다.
	 * @param room 정보를 출력할 방
	 */
	private void printLocationInfo(Room room) {
		System.out.print(room.getLongDescription());
		
	}

	public static void main(String[] args) {
		(new Game()).play();
	}
}
class Item {
	private String name;
	private String description;
	private int weight;
	
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public int getWeight() {
		return weight;
	}
	
	public Item(String name, String description, int weight) {
		this.name = name;
		this.description = description;
		this.weight = weight;
	}
	public String getLongDescription() {
		return name + " (" + weight +"Kg, " + description + ")";
	}
}
class Player {
	private Room currentRoom;
	private Stack<Room> pastRooms;
	private int maxWeight;
	private ArrayList<Item> items;
	
	public Player(Room startRoom, int maxWeight) {
		currentRoom = startRoom;
		pastRooms = new Stack<Room>();
		this.maxWeight = maxWeight;
		items = new ArrayList<Item>();
	}

	/**
	 * 주어진 방향으로 옮겨간다. 그 방향으로 출구가 없으면 현재위치에 머문다.
	 * 
	 * @param direction 옮겨갈 방향
	 * @return 성공했으면 0, 실패했으면 -1.
	 */
	public int moveTo(String direction) {
		// Try to leave current room.
		Room nextRoom = null;

		nextRoom = currentRoom.getExit(direction);

		if (nextRoom == null) {
//			System.out.println("No exit in that direction!");
			return -1;
		} else {
			pastRooms.push(currentRoom);
			currentRoom = nextRoom; // 방을 변경
			return 0;
		}
		

	}

	/**
	 * 이전방으로 돌아간다.
	 */
	public void back() {
//		currentRoom = returnRoom;
		if(!pastRooms.isEmpty()) {
			currentRoom = pastRooms.pop();
		}
	}

	/**
	 * 선수가 현재있는 방을 반환한다.
	 * 
	 * @return
	 */
	public Room getCurrentRoom() {
		return currentRoom;

	}
	
	/**
	 * 아이템을 집는다. 
	 * @param name 집어들 아이템의 이름 
	 * @return 집어든 아이템(성공경우), null(실패한 경우) 
	 */
	public Item takeItem(String name) {
		Item item = currentRoom.removeItem(name);
		
		if(!(item==null)) {
			if(pickable(item)) {
				items.add(item);
				return item;
			}else {
				currentRoom.addItem(item);
				return null;
			}
		}
		return null;
	}
	
	/**
	 * 가지고있는 아이템 중 하나를 내려 놓는다. 
	 * @param name 내려놓울 아이템의 이름. 
	 * @return 내려놓은 아이템(성공한 경우), null(실패한 경우)
	 */
	public Item dropItem(String name) {
		Iterator<Item> it = items.iterator();
		
		while(it.hasNext()) {
			Item i2 = it.next();
			if(i2.getName().equals(name)) {
				items.remove(i2);
				return i2;
			}
		}
		return null;
	}
	
	/**
	 * 이 Player가 들 수 있는 아이템의 최대 무게.
	 * @return
	 */
	public int getMaxWeight() {
		return maxWeight;
	}
	/**
	 * 반환되는 리스트는 수정 불가 
	 * @return
	 */
	public List<Item> getItems() {
		return Collections.unmodifiableList(items);
	}
	/**
	 * 지정된 아이템이 집어들 수 있는 무게인지 판별한다. 
	 * @param item
	 * @return
	 */
	private boolean pickable(Item item) {
		if(item.getWeight() + totalWeight() > maxWeight) {
			return false;
		}else {
			return true;
		}
	}
	private int totalWeight() {
		
		Iterator<Item> it = items.iterator();
		int sum = 0 ;
		while(it.hasNext()) {
			sum += it.next().getWeight();
		}
		
		return sum;
		
	}
}
class Room {
	public String description; // 이 Room에 대한 설명.
	private Map<String, Room> exits;
	private ArrayList<Item> item = new ArrayList<Item>();
	
	/**
	 * "description" 설명에 해당하는 Room을 구성한다. 초기에는 exit을 갖지 않는다. "description"은 가령
	 * "Office", "Computer room", "Dognari room" 같은 것이다.
	 * 
	 * @param description 이 방에 관한 설명.
	 */
	public Room(String description) {
		this.description = description;
		exits = new HashMap<String, Room>();
	}

	/**
	 * 이 Room의 출구들 중 하나를 정해 준다.
	 * 
	 * @param direction 출구 방향.
	 * @param neighbor  지정된 방향의 출구에 연결된 Room.
	 */
	public void setExit(String direction, Room neighbor) {
		// neighbor이 null일 때는 map에 넣지 않고 무시한다.
		if (neighbor != null)
			exits.put(direction, neighbor);
		
	}

	public void addItem(Item itema) {
		item.add(itema);
	}
	
	/**
	 * 이 Room에서 아이템을 제거한다.
	 * @param name 이 Room에서 제거할 아이템이 이름.
	 * @return 	제거된 아이템 (제거 성공)
	 * 			null (제거에 실패한 경우)
	 */
	public Item removeItem(String name) {
		Iterator<Item> it = item.iterator();
		
		while(it.hasNext()) {
			Item i2 = it.next();
			if(i2.getName().equals(name)) {
				item.remove(i2);
				return i2;
			}
		}
		return null;
		
	}
	
	/**
	 * 
	 * @return Room의 상세한 정보
	 */
	public String getLongDescription(){
		StringBuilder sb = new StringBuilder();

		sb.append("Location: ");
		sb.append(description);
		sb.append(".\n");
		sb.append(getExitString());
		sb.append("\n");
		
		
		if(!item.isEmpty()) {
			sb.append("<Item>\n");
			for(Iterator<Item> it = item.iterator(); it.hasNext();) {
				sb.append(it.next().getLongDescription());
				sb.append("\n");
			
			}
//			sb.append(item.);
//			sb.append("\n");
		}
		
		return sb.toString();
	}
	/**
	 * 지정된 방향으로 나가려고 할 때 연결되는 Room을 알려준다.
	 * 
	 * @param direction 나가려고 하는 방향 "north", "east", "south", "west"
	 * @return 나가려고 하는 방향으로 연결된 Room, 그 방향으로 출구가 없으면 null.
	 */
	public Room getExit(String direction) {
		return exits.get(direction);
	}

	/**
	 * @return The description of the room.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 방의 출구들을 알려주는 문자열을 반환한다. 문자열 예: "Exits: north west".
	 * 
	 * @return 출구가 있는 방향들을 알려주는 문자열
	 */
	public String getExitString() {

		StringBuilder s = new StringBuilder("Exits: ");

		// Map에 있는 key들을 모두 읽어냄.
		Set<String> keys = exits.keySet();

		// Set에 들어 있는 문자열들을 읽어냄.
		Iterator<String> it = keys.iterator();
		while (it.hasNext())
			s.append(it.next() + " ");

		return s.toString();
	}

}
class Command {
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
class CommandWords {
	// 유효한 명령어들을 가지고 있는 상수 배열.
	private static final String[] validCommands = {"go", "quit", "help", "look", "eat", "back", "take", "drop", "items"};

	/**
	 * Constructor - initialise the command words.
	 */
	public CommandWords() {
		// nothing to do at the moment...
	}
	
	/**
	 * 명령어들을 화면에 출력한다.
	 */
	public String getCommandList() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < validCommands.length; i++) {
//			System.out.print(validCommands[i] + " ");
			sb.append(validCommands[i] + " ");
		}
		return sb.toString();
	}
	
	/**
	 * Check whether a given String is a valid command word. 주어진 문자열이 유효한 명령어인지
	 * 판별한다.
	 * 
	 * @param aString 명령어.
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
class Parser {
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
	 * 모든 명령어들을 화면에 출력한다. 
	 */
	public String getCommandList() {
		return commands.getCommandList();
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

