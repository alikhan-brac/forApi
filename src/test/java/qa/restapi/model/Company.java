package qa.restapi.model;

import java.util.Objects;

public class Company {
	public String name;
	public String catchPhrase;
	public String bs;

	@Override
	public int hashCode() {
		return Objects.hash(bs, catchPhrase, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		return Objects.equals(bs, other.bs) && Objects.equals(catchPhrase, other.catchPhrase)
				&& Objects.equals(name, other.name);
	}
}
