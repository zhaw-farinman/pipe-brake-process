package farinman.ba.pipe_brake_process.delegates;

import java.util.List;
import java.util.Optional;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import farinman.ba.pipe_brake_process.entities.Area;
import farinman.ba.pipe_brake_process.entities.Building;
import farinman.ba.pipe_brake_process.entities.Device;
import farinman.ba.pipe_brake_process.entities.Dwelling;
import farinman.ba.pipe_brake_process.entities.FacilityWorker;
import farinman.ba.pipe_brake_process.entities.Person;
import farinman.ba.pipe_brake_process.helpers.SendMailHelper;

@Named("sendSpecialistRequestToTicketSystemAdapter")
public class SendSpecialistRequestToTicketSystemDelegate extends SendMailHelper implements JavaDelegate{


	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Building building = (Building)execution.getVariable("building");
		Area area = building.getArea();
		String subject = "Ticket: Feuchtigkeits-Anomalie - "+building.getStreet()+" "+building.getBuildingNumber();
		Device device = (Device) execution.getVariable("device");
		Dwelling dwelling = (Dwelling) execution.getVariable("dwelling");
		List<Person> personList = dwelling.getPersons();
		Optional<Person> personPrincipalTenantOptional = personList.stream().filter(p -> p.isPrincipalTenant()).findFirst();
		Person person = personPrincipalTenantOptional.get();
		String from = (String) execution.getVariable("sentFrom");
		String to = (String) execution.getVariable("ticketSystemMail");
		String text = "";
		
		text += "Hallo ";
		text += "Es wurde eine Feuchtugkeits-Anomalie wahrgenommen.\n\n";
		text += "----------Gebäude----------\n\n";
		text += "Strasse: "+building.getStreet()+" "+building.getBuildingNumber()+"\n";
		text += "Ort / PLZ: "+building.getPlace()+" "+building.getPostCode()+"\n\n";
		text += "Land: "+building.getCountry()+"\n";
		text += "Zone: "+area.getName()+"\n\n";
		text += "----------Wohnung----------\n\n";
		text += "Stockwerk: "+dwelling.getFloor()+"\n";
		text += "Türe: "+dwelling.getDoor()+" \n";
		text += "Standort der Anomalie: "+device.getLocationInDwelling()+"\n\n";
		text += "-------Kontaktperson-------\n\n";
		text += "Name: "+person.getName()+" \n";
		text += "Nachname: "+person.getLastname()+" \n";

		sendSimpleMessage(to, subject, text, from);

	}
}
