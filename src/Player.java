
public class Player extends Thread{

	int id;
	long waitingTime;
	boolean rideComplete , onBoard;
	Operator operator;
	
	public Player(int id,long waitingTime,Operator operator) 
	{
		this.id = id;
		this.waitingTime = waitingTime;
		this.operator = operator;
		rideComplete = false;
		onBoard = false;
	}
	
	
	public void run() 
	{
		try {
			System.out.println("Player :"+id+" sleeps for "+waitingTime + " milliseconds");
			Thread.sleep(waitingTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Player :"+id+" wakes up");
		// Call Operator to join wheel
		operator.load_player(this);
		try {
			synchronized (this) {
				this.wait();
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.err.println(id+" woke up");
	}
}
