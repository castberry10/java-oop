package w14.zuul11;

public class Player {
	private Room currentRoom;
	private Room returnRoom;

	public Player(Room startRoom) {

		currentRoom = startRoom;
		returnRoom = startRoom;
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
			returnRoom = currentRoom;
			currentRoom = nextRoom; // 방을 변경
			return 0;
		}
		

	}

	/**
	 * 이전방으로 돌아간다.
	 */
	public void back() {
		currentRoom = returnRoom;
	}

	/**
	 * 선수가 현재있는 방을 반환한다.
	 * 
	 * @return
	 */
	public Room getCurrentRoom() {
		return currentRoom;

	}
}
