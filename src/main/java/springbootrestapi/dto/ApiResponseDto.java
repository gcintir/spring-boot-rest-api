package springbootrestapi.dto;

import java.io.Serializable;

import springbootrestapi.dto.ResponseType.Response;

public class ApiResponseDto implements Serializable {

	private Integer code;
	private String message;
	private Object data;

	public ApiResponseDto(Response response) {
		super();
		this.code = response.getCode();
		this.message = response.getText();
	}

	public ApiResponseDto(Response response, Object responseData) {
		super();
		this.code = response.getCode();
		this.message = response.getText();
		this.data = responseData;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
