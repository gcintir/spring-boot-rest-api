package springbootrestapi.dto;

import java.io.Serializable;

public class AddressDto implements Serializable {

	private Long addressId;
	private String country;
	private String city;

	public AddressDto() {
		super();
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
