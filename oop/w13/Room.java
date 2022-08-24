package w13;

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
 * 
 * Room은 게임 내 세상에서 어느 한 지점을 나타낸다. Room은 exit(출구)를 통해 다른 Room과 연결된다. Exit은 north,
 * east, south, west 중 어느 하나의 방향을 갖는다.
 * 
 * Rooom은 동서남북 각 방향마다 그 방향으로 이웃한 방을 가리키는 참조변수를 갖는다. 만약 어느 방향으로 방이 이웃해 있지 않다면 그
 * 방향의 참조변수는 null을 저장한다.
 * 
 * @author Michael Klling and David J. Barnes
 * @version 2011.07.31
 */
public class Room {
	public String description; // 이 Room에 대한 설명.
	public Room northExit; // 북쪽 방향으로 이웃한 Room을 가리키는 참조변수.
	public Room southExit;
	public Room eastExit;
	public Room westExit;

	/**
	 * "description" 설명에 해당하는 Room을 구성한다. 초기에는 exit을 갖지 않는다. "description"은 가령
	 * "과사무실", "실습실", "동아리방" 같은 것이다.
	 * 
	 * @param description 이 방에 관한 설명.
	 */
	public Room(String description) {
		this.description = description;
	}

	/**
	 * 이 방의 출구들을 설정해 준다. 각 방향은 다른 방일 수도 있고 null일 수도 있다(다른 방으로 연결되지 않는 경우).
	 * 
	 * @param north The north exit.
	 * @param east  The east east.
	 * @param south The south exit.
	 * @param west  The west exit.
	 */
	public void setExits(Room north, Room east, Room south, Room west) {
		if (north != null)
			northExit = north;
		if (east != null)
			eastExit = east;
		if (south != null)
			southExit = south;
		if (west != null)
			westExit = west;
	}

	/**
	 * @return The description of the room.
	 */
	public String getDescription() {
		return description;
	}

}
