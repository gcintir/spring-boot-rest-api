package springbootrestapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class UserSaveDto implements Serializable {

	private Long id;
	
	@NotNull(message = "Please provide name field")
	private String name;

	public UserSaveDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
