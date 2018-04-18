import java.io.PrintWriter;


public class Player extends Thread{

	int id;
	long waitingTime;
	boolean rideComplete , onBoard;
	Operator operator;
	PrintWriter out;
	
	public Player(int id,long waitingTime,Operator operator,PrintWriter out) 
	{
		this.id = id;
		this.waitingTime = waitingTime;
		this.operator = operator;
		rideComplete = false;
		onBoard = false;
		this.out = out;
	}
	
	
	public void run() 
	{
		try {
			//out.println("Player :"+id+" sleeps for "+waitingTime + " milliseconds");
			Thread.sleep(waitingTime);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		out.println("Player wakes up:"+id);
		// Call Operator to join wheel
		operator.load_player(this);
		try {
			synchronized (this) {
				this.wait();	
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}
}
