package co.edu.todo;

public class ToDo {
	
	String cont;
	String checked;
	
	public String getCont() {
		return cont;
	}
	
	public void setCont(String cont) {
		this.cont = cont;
	}
	
	public String getChecked() {
		return checked;
	}
	
	public void setChecked(String checked) {
		this.checked = checked;
	}
	
	public ToDo(String cont, String checked) {
		super();
		this.cont = cont;
		this.checked = checked;
	}

}
