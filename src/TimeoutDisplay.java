//sleeps 4 seconds and then displays a message on the vending machines display
public class TimeoutDisplay implements Runnable {
	Vending vend;
	String msg;
	TimeoutDisplay(Vending vend, String msg){
		this.vend = vend;
		this.msg = msg;
		
	}

    public void run() {
    	try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vend.display = msg;
   }

}