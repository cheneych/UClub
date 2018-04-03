package raymond.TestHomePage;

public class Order {
	private String startTime;
	private String endTime;
	private String des;
	private String room;
	private OrderStatus status;
	
	public String getStartTime(){
		return startTime;
	}
	
	public void setStartTime(String startTime){
		this.startTime=startTime;
	}
	
	public String getEndTime(){
		return endTime;
	}
	
	public void setEndTime(String endTime){
		this.endTime=endTime;
	}
	
	public String getDes(){
		return des;
	}
	
	public void setDes(String des){
		this.des=des;
	}
	
	public String getRoom(){
		return room;
	}
	
	public void setRoom(String room){
		this.room=room;
	}
	
	public OrderStatus getStatus(){
		return status;
	}
	
	public void setStatus(OrderStatus status){
		this.status=status;
	}
}


