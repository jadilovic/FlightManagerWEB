package dto;

public class Airline {
	
	private String name;
	
	public Airline(){
		
	}
	
	public Airline(String name){
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Airline [name=" + name + "]";
	}
	
	

}
