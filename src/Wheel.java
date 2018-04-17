import java.util.ArrayList;


public class Wheel extends Thread{
	final int capacity = 5;
	int maxWaitTime , onBoard;
	ArrayList<Player> players;
	Operator operator;
	
	public Wheel(int maxWaitTime,Operator operator) {
		this.maxWaitTime = maxWaitTime;
		this.operator = operator;
		onBoard = 0;
		players = new ArrayList<Player>();
		
	}


	public void run() {
		while(true)
		{
			try {
				System.out.println("Wheel Sleep");
				Thread.sleep(maxWaitTime);
				
			} catch (InterruptedException e) {
//				e.printStackTrace();
				System.err.println("Exception");
			}
			System.out.println("Wheel Wakes up");
			run_ride();
			end_ride();
			
		}
	
	}
	
	synchronized void load_players(Player player)
	{
		System.out.println("load wheel method is called by :"+player.id);
		players.add(player);
		player.onBoard = true;
		onBoard++;
//		try {
//			player.wait();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	void run_ride(){
		System.out.println("Wheel run_ride()");
		
	}
	
	void end_ride()
	{
		for(Player p : players)
		{
			p.onBoard = false;
			try{
				synchronized (p) {
					p.notify();					
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		players = new ArrayList<Player>();
		onBoard = 0;
	}
	
}
