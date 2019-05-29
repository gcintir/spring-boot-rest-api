package springbootrestapi.dto;

public class RecordNotfoundException extends RuntimeException {

	private String message;

	public RecordNotfoundException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
