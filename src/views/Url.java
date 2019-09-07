package views;

public class Url {
int id;
String url;

public Url(int id, String url) {
	super();
	this.id = id;
	this.url = url;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}

@Override
public String toString() {
	return "Url [id=" + id + ", url=" + url + "]";
}






}
