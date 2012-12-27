package json;

import java.util.ArrayList;
import java.util.List;

public class Pagelet {

	public String id;
	public String html;
	public List<String> css;
	public List<String> js;
	
	
	public Pagelet(String id) {
		this.id=id;
		this.css=new ArrayList<String>();
		this.js=new ArrayList<String>();
	}
	
	
	
}
