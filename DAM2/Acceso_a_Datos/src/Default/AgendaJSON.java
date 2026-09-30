package Default;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AgendaJSON {

	@SerializedName("agenda")
	
	private List<ContactoJSON> contactos;
	
	public List<ContactoJSON> getContactos(){
		return contactos;
	}
	

}
