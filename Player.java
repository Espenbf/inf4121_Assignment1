package telerik;

public class Player implements Comparable<Player>{
	String name;
	int movesCount;
	public Player(String name, int movesCount){
		this.name = name;{
			this.movesCount = movesCount;
		}
	}
	@Override
	public int compareTo(Player player2) {
		Integer player1_move = this.movesCount;
		Integer player2_move = player2.movesCount;
		return player1_move.compareTo(player2_move);
	}
	
}
