import java.io.PrintWriter;
import java.util.ArrayList;


public class Wheel extends Thread{
	final int capacity = 5;
	int maxWaitTime , onBoard;
	boolean finished;
	ArrayList<Player> players;
	Operator operator;
	PrintWriter out;
	
	public Wheel(int maxWaitTime,Operator operator,PrintWriter out) {
		this.maxWaitTime = maxWaitTime;
		this.operator = operator;
		onBoard = 0;
		players = new ArrayList<Player>();
		this.out = out;
	}


	public void run() {
		while(!finished)
		{
			try {
				out.println("Wheel Start Sleep");
				Thread.sleep(maxWaitTime);
				
			} catch (InterruptedException e) {

				
			}
			run_ride();
			end_ride();
			
		}
		
		out.flush();
	
	}
	
	synchronized void load_players(Player player)
	{
		
		players.add(player);
		player.onBoard = true;
		onBoard++;
		out.println("player "+player.id+" on board, capacity: "+this.onBoard);
	}
	
	void run_ride(){
		if(this.players.size()==5)
		out.println("Wheel is full, Lets go for a ride");
		out.println("Thread in this ride are:");
		
	}
	
	void end_ride()
	{
		for(Player p : players)
		{
			p.onBoard = false;
			try{
				synchronized (p) {
					out.print(p.id+", ");
					p.notify();					
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		out.println();
		players = new ArrayList<Player>();
		onBoard = 0;
		out.println("Wheel end");
	}
	
}
