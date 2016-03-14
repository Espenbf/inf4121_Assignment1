package telerik;

import java.util.LinkedList;


public class HighScoreBoard {
	LinkedList<Player> list;
	public final int boardSize = 5;
	public HighScoreBoard(){
		list = new LinkedList<Player>();
	}
	
	public boolean addPlayerToChart(Player player){
		list.add(player);
		list.sort(null);
		if (list.size() > 5){
			list.removeLast();
		}
		if (list.contains(player)){
			return true;
		}
		else {
			return false;
		}
	}
	void printBoard(LinkedList<Player> list){
		System.out.println("Score :");
		for(int i=0;i<list.size();i++){
			Player p = (Player) list.get(i);
			System.out.print((i+1)+". Name - "+p.name+" ");
			System.out.print("Escaped in "+p.movesCount+" moves");
			System.out.println();
		}
	}
}
