package Graph;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class Phone {
    private int number;

	public Phone(int number) {
		super();
		this.number = number;
	}

	public Phone() {
		super();
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
}
