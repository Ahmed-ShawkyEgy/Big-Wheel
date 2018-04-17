import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class Operator extends Thread{
	
	Wheel wheel;
	
	public Operator() throws NumberFormatException, IOException, InterruptedException {
		this.start();
	}	

	synchronized void load_player(Player player) 
	{
		System.out.println("Player "+player.id + " loaded");
		while(wheel.onBoard==5);
		wheel.load_players(player);
		if(wheel.onBoard==5)
			wheel.interrupt();
	}

	
@Override
	public void run() {
	try
	{
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		int maxWaitTime = Integer.parseInt(br.readLine());
		int cnt = Integer.parseInt(br.readLine());
		Player[] players = new Player[cnt];
		this.wheel = new Wheel(maxWaitTime,this);
	
		wheel.start();
		for (int i = 0; i < cnt; i++) 
		{
			StringTokenizer st = new StringTokenizer(br.readLine(), ",");
			int id = Integer.parseInt(st.nextToken());
			int waitingTime = Integer.parseInt(st.nextToken());
			players[i] = new Player(id, waitingTime,this);
			players[i].start();
		}
	
		for (int i = 0; i < players.length; i++) {
//			System.err.println("waiting for "+players[i].id+" to join");
//			players[i].join();
//			System.err.println("Player "+players[i].id+" joined");
		}
		
		
	
	}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException, InterruptedException {
		Operator operator = new Operator();
	}
}
