import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class Operator extends Thread{
	
	Wheel wheel;
	int loaded;
	int total;
	PrintWriter out;
	
	public Operator() throws NumberFormatException, IOException, InterruptedException {
		loaded = 0;
		this.start();
	}	

	synchronized void load_player(Player player) 
	{
		
		while(wheel.onBoard==5);
		out.println("Player wakes up:"+(player.id));
		out.println("passing player to operator:"+(player.id));
		wheel.load_players(player);
		if(wheel.onBoard==5)
		{
			wheel.interrupt();
		}
		
		loaded++;
		if(loaded==total)
		{
			wheel.finished = true;
		}
	}



	public void run() {
	try
	{
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("out.txt");
		int maxWaitTime = Integer.parseInt(br.readLine());
		total= Integer.parseInt(br.readLine());
		Player[] players = new Player[total];
		this.wheel = new Wheel(maxWaitTime,this,out);
	
		wheel.start();
		for (int i = 0; i < total; i++) 
		{
			StringTokenizer st = new StringTokenizer(br.readLine(), ",");
			int id = Integer.parseInt(st.nextToken());
			int waitingTime = Integer.parseInt(st.nextToken());
			players[i] = new Player(id, waitingTime,this,out);
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
