package it.unicam.ing.DTO;

public class ClienteDTO {

	private String id;
	private int timeLeft;
	
	public ClienteDTO(String id, int timeLeft) {
		super();
		this.id = id;
		this.timeLeft = 21;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}
	
	
	
}
