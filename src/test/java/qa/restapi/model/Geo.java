package qa.restapi.model;

import java.util.Objects;

public class Geo {
	public String lat;
	public String lng;

	@Override
	public int hashCode() {
		return Objects.hash(lat, lng);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Geo other = (Geo) obj;
		return Objects.equals(lat, other.lat) && Objects.equals(lng, other.lng);
	}
}
