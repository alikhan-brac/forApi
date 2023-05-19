package qa.restapi.model;

import java.util.Objects;

public class Address {
	public String street;
	public String suite;
	public String city;
	public String zipcode;
	public Geo geo;

	@Override
	public int hashCode() {
		return Objects.hash(city, geo, street, suite, zipcode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(city, other.city) && Objects.equals(geo, other.geo)
				&& Objects.equals(street, other.street) && Objects.equals(suite, other.suite)
				&& Objects.equals(zipcode, other.zipcode);
	}
}
