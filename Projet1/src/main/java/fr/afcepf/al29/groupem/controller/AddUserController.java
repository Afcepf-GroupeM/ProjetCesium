package fr.afcepf.al29.groupem.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.ItemBusApi;
import fr.afcepf.al29.groupem.business.api.OrderBusApi;
import fr.afcepf.al29.groupem.business.api.UserBusApi;
import fr.afcepf.al29.groupem.dao.api.OrderLineDaoApi;
import fr.afcepf.al29.groupem.entities.Civilite;
import fr.afcepf.al29.groupem.entities.Item;
import fr.afcepf.al29.groupem.entities.Order;
import fr.afcepf.al29.groupem.entities.OrderLine;
import fr.afcepf.al29.groupem.entities.User;

@Component
@ManagedBean
public class AddUserController {
	
	private int id;
	
	private Civilite civilite;
	private Civilite[] listeCivilite; 
	
	private String lastName;
	private String firstName;
	private String birthDay;
	private String birthMonth;
	private String birthYear;
	private String email;
	private String phone;
	private String password1;
	private String password2;
	private String message;
	private List<String> dayList;
	private List<String> monthList;
	private List<String> yearList;
	
	private DataGeneration dataGen = new DataGeneration();
	private int nbUsersToGenerate;
	private String messageUsersGenerate;
	private List<User> listUsersGenerated;
	
	private int nbOrdersToGenerate;
	private String messageOrdersGenerate;
	private List<Order> listOrdersGenerated;
	
	

	@Autowired
	private ItemBusApi itemBus;
	
	@Autowired
	private UserBusApi userBus;
	
	@Autowired
	private OrderBusApi orderBus;
	
	@Autowired
	private OrderLineDaoApi orderLineBus;
	
	@PostConstruct
	public void init(){
		message ="";
		listeCivilite = Civilite.class.getEnumConstants();
		dayList = new ArrayList<>();
		monthList = new ArrayList<>();
		yearList = new ArrayList<>();
		dayList.addAll(Arrays.asList("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"));
		monthList.addAll(Arrays.asList("01","02","03","04","05","06","07","08","09","10","11","12"));
		yearList.addAll(Arrays.asList("2016","2015","2014","2013","2012","2011","2010","2009","2008","2007","2006","2005","2004","2003","2002","2001","2000","1999",
									  "1998","1997","1996","1995","1994","1993","1992","1991","1990","1989","1988","1987","1986","1985","1984","1983","1982","1981",
									  "1980","1979","1978","1977","1976","1975","1974","1973","1972","1971","1970","1969","1968","1967","1966","1965","1964","1963",
									  "1962","1961","1960","1959","1958","1957","1956","1955","1954","1953","1952","1951","1950","1949","1948","1947","1946","1945",
									  "1944","1943","1942","1941","1940","1939","1938","1937","1936","1935","1934","1933","1932","1931","1930","1929","1928","1927",
									  "1926","1925","1924","1923","1922","1921","1920","1919","1918","1917","1916","1915","1914","1913","1912","1911","1910","1909",
									  "1908","1907","1906","1905"));
	}
	
	public String action(){
		String returnAddress = null;
		message ="";
		EmailValidator emailValidator = EmailValidator.getInstance();
		RegexValidator nameValidator = new RegexValidator("^[a-zA-Z \\-\\.\\']*$",false);
		RegexValidator phoneValidator = new RegexValidator("^0[1-6]{1}(([0-9]{2}){4})|((\\s[0-9]{2}){4})|((-[0-9]{2}){4})$",false);
		
		DateValidator dateValidator = DateValidator.getInstance();
		String birthDate = birthDay+birthMonth+birthYear;
		
		boolean civiliteValid =false;
			for (Civilite civi : listeCivilite) {
				if(civi.equals(civilite)){
					civiliteValid = true;
				}
			}	
			
		boolean lastNameValid = nameValidator.isValid(lastName) && (!lastName.isEmpty());
		boolean firstNameValid = nameValidator.isValid(firstName) && (!firstName.isEmpty());
		boolean passwordValid = password1.equals(password2) && (!password1.isEmpty());
		boolean emailValid = emailValidator.isValid(email);
		boolean emailNotAlreadySubcribed = true;
		boolean birthDateValid = dateValidator.isValid(birthDate, "ddMMyyy");
		boolean phoneValid = phoneValidator.isValid(phone);
		
		if(!civiliteValid){message += "Civilité invalide<br/>";}
		if(!lastNameValid){message += "Nom invalide<br/>";}
		if(!firstNameValid){message += "Prénom invalide<br/>";}
		
		if(!emailValid){message += "Email invalide<br/>";
		} else{
			if(userBus.getUserByLogin(email) != null){
				emailNotAlreadySubcribed = false;
				message += "Email déja utilisé<br/>";
				}
			}
		
		if(!birthDateValid){message += "Date de naissance invalide<br/>";}
		if(!phoneValid){message += "Téléphone invalide<br/>";}
		if(!passwordValid){message += "Les mots de passe ne correspondent pas ou sont vides.<br/>";}
			
		if(lastNameValid && firstNameValid && passwordValid && emailValid && emailNotAlreadySubcribed && birthDateValid && phoneValid){
			DateFormat dateFormater = new SimpleDateFormat("ddMMyyyy"); 
			
			Date formattedDate = null;
			try {
				formattedDate = dateFormater.parse(birthDate);
			} catch (ParseException e) {
				System.out.println("ERREUR - Parsing birthDate in AddUserController - action()" + e.getMessage());
			}
			User user = userBus.createUser(civilite, lastName, firstName, email, phone, password1,formattedDate );
			resetFields();
			returnAddress = "inscription-ok";
			message = "Utilisateur créé avec succes!" +"  " + "Id du nouvel utilisateur: " + user.getId();
		}	
		return returnAddress;		
	}
	
	public void resetFields(){
		
		civilite = null;
		lastName = "";
		firstName = "";
		birthDay = "";
		birthMonth = "";
		birthYear = "";
		email = "";
		phone = "";		
	}
	
	
	
	public String generateUsers(){
		Date dateDebut = new Date();
		listUsersGenerated = dataGen.generateUsers(nbUsersToGenerate);
		listUsersGenerated = userBus.generateUsers(listUsersGenerated);
		Date dateFin = new Date();
		long totalTime = dateFin.getTime() - dateDebut.getTime();
		messageUsersGenerate = "Succes - "+ nbUsersToGenerate + " utilisateurs ajoutés à la base! - Effectué en " + totalTime + " millisecondes.";
		return null;
		
	}
	
	
	public String generateOrders(){
		Date dateDebut = new Date();
		List<User> allUsers = userBus.getAllUsers();
		List<Item> allItems = itemBus.getAllItems();
		
		listOrdersGenerated = dataGen.gernerateOrder(allUsers, allItems, nbOrdersToGenerate);
		for (Order order : listOrdersGenerated) {
			order = orderBus.createOrder(order);
			for (OrderLine orderLine : order.getOrderLines()) {
				orderLine.setOrder(order);
				orderLineBus.createOrderLine(orderLine);
			}
		}
		Date dateFin = new Date();
		long totalTime = dateFin.getTime() - dateDebut.getTime();
		messageOrdersGenerate = "Succes - "+ nbOrdersToGenerate + " orders ajoutées à la base! - Effectué en " + totalTime + " millisecondes.";
		
		return null;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Civilite getCivilite() {
		return civilite;
	}
	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getBirthMonth() {
		return birthMonth;
	}
	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}
	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public List<String> getDayList() {
		return dayList;
	}

	public void setDayList(List<String> dayList) {
		this.dayList = dayList;
	}

	public List<String> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<String> monthList) {
		this.monthList = monthList;
	}

	public List<String> getYearList() {
		return yearList;
	}

	public void setYearList(List<String> yearList) {
		this.yearList = yearList;
	}

	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Civilite[] getListeCivilite() {
		return listeCivilite;
	}
	public void setListeCivilite(Civilite[] listeCivilite) {
		this.listeCivilite = listeCivilite;
	}

	public DataGeneration getDataGen() {
		return dataGen;
	}

	public void setDataGen(DataGeneration dataGen) {
		this.dataGen = dataGen;
	}

	public int getNbUsersToGenerate() {
		return nbUsersToGenerate;
	}

	public void setNbUsersToGenerate(int nbUsersToGenerate) {
		this.nbUsersToGenerate = nbUsersToGenerate;
	}

	public String getMessageUsersGenerate() {
		return messageUsersGenerate;
	}

	public void setMessageUsersGenerate(String messageUsersGenerate) {
		this.messageUsersGenerate = messageUsersGenerate;
	}

	public UserBusApi getUserBus() {
		return userBus;
	}

	public void setUserBus(UserBusApi userBus) {
		this.userBus = userBus;
	}

	public List<User> getListUsersGenerated() {
		return listUsersGenerated;
	}

	public void setListUsersGenerated(List<User> listUsersGenerated) {
		this.listUsersGenerated = listUsersGenerated;
	}

	public int getNbOrdedsToGenerate() {
		return nbOrdersToGenerate;
	}

	public void setNbOrdedsToGenerate(int nbOrdedsToGenerate) {
		this.nbOrdersToGenerate = nbOrdedsToGenerate;
	}

	public String getMessageOrdersGenerate() {
		return messageOrdersGenerate;
	}

	public void setMessageOrdersGenerate(String messageOrdersGenerate) {
		this.messageOrdersGenerate = messageOrdersGenerate;
	}

	public List<Order> getListOrdersGenerated() {
		return listOrdersGenerated;
	}

	public void setListOrdersGenerated(List<Order> listOrdersGenerated) {
		this.listOrdersGenerated = listOrdersGenerated;
	}
	
	
}
