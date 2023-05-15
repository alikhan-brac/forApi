package qa.swapi.model;

public class FilmInfo {
	public String filmName;

	@Override
	public String toString() {
		return "FilmInfo [filmName=" + filmName + "]";
	}

	public FilmInfo() {
	}

	public FilmInfo(String filmName) {
		this.filmName = filmName;
	}
}
