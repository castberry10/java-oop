package w14.zuul9;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game. It is connected
 * to other rooms via exits. The exits are labelled north, east, south, west.
 * For each direction, the room stores a reference to the neighboring room, or
 * null if there is no exit in that direction.
 * <p>
 * Room은 게임 내 세상에서 어느 한 지점을 나타낸다. Room은 exit(출구)를 통해 다른 Room과 연결된다. Exit은 north,
 * east, south, west 중 어느 하나의 방향을 갖는다.
 * <p>
 * Rooom은 동서남북 각 방향마다 그 방향으로 이웃한 방을 가리키는 참조변수를 갖는다. 만약 어느 방향으로 방이 이웃해 있지 않다면 그
 * 방향의 참조변수는 null을 저장한다.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class Room {
	public String description; // 이 Room에 대한 설명.
	private Map<String, Room> exits;
	private Item item;
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

	public void setItem(Item itema) {
		item = itema;
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
		
		
		if(item != null) {
			sb.append("<Item>\n");
			sb.append(item.getLongDescription());
			sb.append("\n");
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
