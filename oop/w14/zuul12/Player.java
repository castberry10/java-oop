package w14.zuul12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class Player {
	private Room currentRoom;
	private Room returnRoom;
	private int maxWeight;
	private ArrayList<Item> items = new ArrayList<Item>();
	
	public Player(Room startRoom, int maxWeight) {
		currentRoom = startRoom;
		returnRoom = startRoom;
		this.maxWeight = maxWeight;
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
