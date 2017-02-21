package fr.afcepf.al29.groupem.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al29.groupem.business.impl.SecurityManagerBCrypt;
import fr.afcepf.al29.groupem.dao.api.UserDaoApi;
import fr.afcepf.al29.groupem.dao.impl.UserDaoImpl;
import fr.afcepf.al29.groupem.entities.Civilite;
import fr.afcepf.al29.groupem.entities.User;


@Component
public class DataGeneration {
	
	@Autowired
	private UserDaoApi userDao;
	
	Civilite civilite;
	Date birthDate;
	String email;
	String phone;
	String hashPassword;
	Random rand = new Random();
	SecurityManagerBCrypt secuMan = new SecurityManagerBCrypt();
	
	

	String[] firstNames = {"Liliana","Conchita","Janita","Piedad","Jeffery","Zenia","Mose","Travis","Carlena","Norma","Audry","Bruna","Sheba","Keitha","Jule",
			"Berenice","Roy","Lenore","Alane","Georgia","Dangelo","Kelton","Jonah","Carsen","Jaron","Ellis","Connor","Leo","Abram","Braylen","Braylon","Stephen",
			"Ismael","Marvin","Adolfo","Cassius","Clayton","Johan","Ramon","Armani","Jovanni","Matthias","Bailey","Jamar","Lee","Willie","Jean","Branson",
			"Damian","Antony","Salvatore","Cole","Braiden","Francisco","Dashawn","Justice","Krish","Savion","Devon","Kolton","Alvaro","Leonidas","Keaton",
			"Urijah","Jakobe","Brody","Corbin","Dereon","Billy","Immanuel","Turner","Raphael","Fernando","Braedon","Jaquan","Lance","Jaden","Devin","Jordyn",
			"Cayden","Keegan","Jaylon","Gilbert","Quinton","Osvaldo","Trystan","Derrick","Slade","Adrien","Davis","Steve","Ari","Kaeden","Zachariah","Phillip",
			"Josh","Gregory","Kieran","Camron","Demarcus","Layne","Maurice","Braydon","Carlos","Jerry","Jayvon","Isai","Lorenzo","Brogan","Brian","Mark","Pedro",
			"Rex","Alexis","Brayan","Maximillian","Davion","Alvin","Harold","Bronson","Jaiden","Julian","Aaron","Harley","Coleman","Alfonso","Rey","Maverick",
			"Barrett","Jonathan","Maximo","Heath","Reuben","Hayden","Dustin","Emery","Joe","Vaughn","Jovan","Uriel","Jasiah","Demarion","Davian","Ricky",
			"Markus","Kamren","Eric","Leon","Brenden","Gunnar","Dante","Bennett","Asher","Jairo","Max","Gunner","Darnell","Darien","Julien","Lawson","Jalen",
			"Skylar","Albert","Cash","Larry","Jaeden","Jayvion","Kylan","Camden","Nikolai","Edward","Peyton","Kenyon","Aiden","Zackary","Levi","Cael","Antonio",
			"Rodrigo","Isaac","Luciano","Skyler","Gaven","Kale","Izaiah","Simeon","Demetrius","Chace","Jesus","Andre","Logan","Waylon","Tripp","Raymond","Ty",
			"Oscar","Darrell","Jonas","Nathaniel","Brandon","Ahmed","Dakota","Jadon","Tyler","Ryland","Carson","Robert","Boston","Gordon","Ibrahim","Alec",
			"Justin","Kane","Bridger","Steven","Jarrett","Lincoln","Kyson","Esteban","Rafael","Sharyl","Maryanna","Terence","Dorcas","Dimple","Kenyatta","Mertie","Treasa","Verdie",
			"Genny","Marie","Arturo","Daisey","Toi","Zula","Doris","Cory","Hershel","Kristel","Shizuko","Kerry","Princess","Laci","Noe","Desiree",
			"Wendell","Brent","Lajuana","Constance","Gretchen","Jimmie","Delbert","Arline","Margie","Isaias","Theresa","Bailey","Ester","Rebecka",
			"Kory","Brice","Phillip","Angela","Herminia","Mckinley","Gregg","Beatris","Delana","Glynda","Jina","Ruthann","Raquel","Gaylord",
			"Roderick","Syreeta","Sara","Xiomara","Petronila","Wilson","Debroah","Fabiola","Starla","Krista","Jo","Jordan","Addie","Sherril",
			"Fermin","Preston","Youlanda","Kristie","Regine","So","Dacia","Marvin","Vivan","Caleb","Epifania","Lynelle","Jade","Lynell","Damaris",
			"Desmond","Marcella","Veola","Ariana","Rosenda","Carlota","Arnita","Dorie","Larraine","Cristi","Kathryn","Celsa","Sarah","Lindy",
			"Reginia","Luciano","Golden","Edward","Dave","Rey","Ignacia","Marquerite","Trevor","Allan","Elfrieda","Tawnya","Romana","Gretchen",
			"Bret","Dottie","Bonny","Lynelle","Cindie","Kit","Janessa","Trang","Rachelle","Yolando","Monica","Izetta","Michale","Luvenia","Marget",
			"Michel","Sabina","Katie","Justin","Winnifred","Eden","Rasheeda","Deidre","Rossie","Hee","Joana","Jerilyn","Shameka","Ferdinand",
			"Kristan","Lou","Danial","Corina","Annette","Kyoko","Brendan","Glynda","Magdalen","Leatrice","Diane","Shiloh","Micheline","Jared",
			"Elouise","Ngoc","Addie","Tamara","Erik","Martin","Hue","Rodger","Chase","Saundra","Kristin","Dorthy","Maryellen","Tyra","Erin",
			"Collin","Andra","Celina","Nicholle","Daisey","Clair","Tempie","Sherri","Larue","Tawana","Macy","Dawne","Gale","Riley","Jackson",
			"Howard","Dan","Lonny","Brandon","Mohammad","Elvin","Jared","Chang","Sal","Josh","Fabian","Dylan","Franklin","Margarito","Terrell",
			"Merlin","Ismael","Gail","Cedrick","Ty","Normand","Scott","Sandy","Gayle","Arnulfo","Elwood","Otto","Jamey","Abdul","Cyril","Guy",
			"Lyman","Norberto","Deandre","Vincent","Issac","Coy","Owen","Trevor","Rick","Dallas","Abe","Jasper","Quintin","Edwardo","Alan",
			"William"}; //450

	String[] lastNames = {"Potts","Stark","Parrish","Tran","Blevins","Friedman","Benitez","Gillespie","Harding","Long","Church","Fritz","Cherry","Wilkins",
			"Anderson","Fox","Yates","Meyers","Krause","Ortega","Wright","Fry","Moreno","Forbes","Beard","Cobb","Love","Drake","Vega","Carter","Farmer",
			"Figueroa","Osborn","Wheeler","Blake","Livingston","Berg","Melton","Dillon","Krueger","Kidd","Campos","Hill","Compton","Clements","Wong","Frost",
			"Lewis","Costa","Nolan","Noble","Rollins","Hatfield","Li","Roman","Green","Suarez","Hunter","Vaughn","Navarro","Gross","Hensley","Jordan",
			"Mccarty","Meyer","Stevenson","Goodman","Sandoval","Gill","Sutton","Giles","Sullivan","Garner","Spence","Joseph","Acosta","Patrick","Obrien",
			"Hickman","Carey","Ali","Esparza","Norris","Welch","Mcbride","Serrano","Reese","Dudley","Skinner","Leblanc","Duncan","Sweeney","Knight","Steele",
			"Simon","Moses","Parker","Powell","Mcclure","Mcmahon","Proctor","Tanner","Melendez","Robinson","Callahan","Golden","Peterson","Quinn","Hayden",
			"Kim","Reynolds","Pugh","Little","Combs","Hicks","Douglas","Pollard","Meza","Reynolds","Evans","Randolph","Maddox","Dunn","Watts","Bishop",
			"Jennings","Francis","Roy","Gilmore","Nixon","Gordon","Walsh","Allison","Kim","Wang","Mercado","Hawkins","Walter","Reese","Avila","Petty","Bernard",
			"Knight","Stanton","Huffman","Cantrell","Suarez","Fields","Mcintosh","Barry","Le","Aguilar","Snow","Potts","Powell","Harris","Mccormick","Donaldson",
			"Vasquez","Alvarado","Mckinney","Lam","Ellis","Guerrero","Callahan","Arroyo","Pratt","Cruz","Mclean","Cannon","Daniels","Bradford","Short","Burton",
			"Williamson","Blanchard","Juarez","Barnes","Humphrey","Pugh","Hancock","Valenzuela","Boone","Valentine","Gutierrez","Orozco","Horton","Mcmahon",
			"Everett","Carey","Dunlap","Gay","Werner","Little","Meadows","Barton","Cochran","Mckay","Moss","Pace","Alexander","Colon","Santiago","Braun",
			"Crawford","Holland","Anthony","Pitts","Bautista","Soto","Kelly","Webster","Howell","Taylor","Palmer","Collier","Foley","Archer","Armstrong","Noble",
			"Trujillo","Branch","Wells","Zhang","Nash","Myers","Obrien","Pope","Lowery","Perry","Brooks","Stephens","Shields","Sweeney","Bradley","Gallegos",
			"Nunez","Ferguson","Montoya","Sanchez","Valdez","Burgess","Baldwin","Spence","Vargas","Harrell","Grant","Hunter","Briggs","Gates","Shannon","Arnold",
			"Wade","Miller","Schaefer","Massey","Kaufman","Li","Snyder","Esparza","Austin","Reeves","Cooke","Russell","Clarke","Contreras","Jefferson","Gentry",
			"Sparks","Fowler","Nelson","Todd","Craig","Barr","Keith","Marks","Christian","Carrillo","Gray","Fitzpatrick","Ware","Mitchell","Melton","Cline",
			"Hanson","Bird","Fleming","Howard","Aguirre","Stokes","Foster","Phelps","Miles","Brewer","Solis","Nicholson","Holder","Tapia","Dorsey","Cox","Durham",
			"Harrington","Shea","Silva","Leonard","Dillon","Velasquez","Doyle","Hahn","Hines","Villarreal","Bright","Wilkins","Ashley","Gould","Hale","Huber",
			"Cooper","Mann","Hodges","Munoz","Daugherty","Beltran","Daniel","Jones",
			"Mcfarland","Bentley","Castaneda","Nguyen","Medina","Mckee","Ellison","Petty","Middleton","Baird","Leon","Knox","Brady","Nunez","Sharp","Blair",
			"Berger","Dickson","Mcdaniel","Moon","Moody","Bush","Potter","Watts","Johns","Madden","Mccann","Foley","Lynch","Caldwell","Bell","Burch","White",
			"Boone","Wolf","Adkins","Thompson","Conley","Heath","Jackson","Johnson","Hodge","Richard","Park","Rodgers","Wood","Pace","Calhoun","Hoover",
			"Kaiser","Reilly","Elliott","Chase","Lindsey","Coleman","Keith","Tate","Gaines","Lam","Blackwell","Small","Duarte","Allison","Burns","Berry",
			"Curry","Duke","Rivas","Nielsen","Hebert","Palmer","Valencia","Christensen","Cole","Baker","Holden","James","Molina","Diaz","Morris","Matthews",
			"Macias","Simmons","Cordova","Santana","Rice","Mclean","Banks","Montes","Graham","Jennings","Hubbard","Case","Gordon","Donaldson","Singh",
			"Fuentes","Hines","Cisneros","Moyer","Branch","Villarreal","Mccormick","Mcgrath","Flynn","Zimmerman","Ritter","Rivera","Mercado","Sanford",
			"Lawson","Huang","Dominguez","Lin","Booth","Brewer","Bean","Francis","Salinas","Malone","Parks","Vargas","Nash","Dyer","Vance"}; //450


	String[] years = {"1920","1921","1922","1923","1924","1925","1926","1927","1928","1929","1930","1931","1932","1933","1934","1935","1936","1937","1938",
					  "1939","1940","1941","1942","1943","1944","1945","1946","1947","1948","1949","1950","1951","1952","1953","1954","1955","1956","1957",
					  "1958","1959","1960","1961","1962","1963","1964","1965","1966","1967","1968","1969","1970","1971","1972","1973","1974","1975","1976",
					  "1977","1978","1979","1980","1981","1982","1983","1984","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995",
					  "1996","1997","1998","1999","2000","2001","2002","2003","2004"};
	
	String[] months = {"01","02","03","04","05","06","07","08","09","10","11","12"};
	
	String[] days = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"};
	

	

	
	public List<User> generateUsers(int numberToGenerate){
		
		List<User> usersGenerated = new ArrayList<>();
		
		for (int i = 0; i < numberToGenerate; i++) {
			User user = new User();
			user.setCivilite(Civilite.values()[rand.nextInt(2)]);
			user.setfirstName(firstNames[rand.nextInt(449)]);
			user.setlastName(lastNames[rand.nextInt(449)]);
			user.setEmail(user.getfirstName() + rand.nextInt(100) + "@" + user.getlastName() + ".com");	
			user.setphone( "0" + (rand.nextInt(6-1) + 1) 
							   + (rand.nextInt(10-1) + 1)+ (rand.nextInt(10-1) + 1)
							   + (rand.nextInt(10-1) + 1)+ (rand.nextInt(10-1) + 1)
							   + (rand.nextInt(10-1) + 1)+ (rand.nextInt(10-1) + 1)
							   + (rand.nextInt(10-1) + 1)+ (rand.nextInt(10-1) + 1));
			
			user.setpasswordHash(secuMan.hashPassword(user.getEmail().split("-")[0]));
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date bdate = new Date();
			try {
				bdate= dateFormat.parse(years[rand.nextInt(84)] +"-"+ months[rand.nextInt(12-1)+1] +"-"+ days[rand.nextInt(27-1)+1]);
			} catch (ParseException e) {
				System.out.println("Generate Users date format error: " + e.getMessage());
			}
			user.setBirthDate(bdate);
			
			System.out.println("DataGeneration - generateUsers - User to add: "+ user.toString());
			user = userDao.createUser(user);
			usersGenerated.add(user);
			
			
		}
		System.out.println("Succes - "+numberToGenerate + " utilisateurs ajoutés a la base.");


		return usersGenerated;

	}




	public String[] getFirstName() {
		return firstNames;
	}

	public void setFirstName(String[] firstName) {
		this.firstNames = firstName;
	}

	public String[] getLastName() {
		return lastNames;
	}

	public void setLastName(String[] lastName) {
		this.lastNames = lastName;
	}

	public Civilite getCivilite() {
		return civilite;
	}

	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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

	public String getHashPassword() {
		return hashPassword;
	}

	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}




	public String[] getFirstNames() {
		return firstNames;
	}




	public void setFirstNames(String[] firstNames) {
		this.firstNames = firstNames;
	}




	public String[] getLastNames() {
		return lastNames;
	}




	public void setLastNames(String[] lastNames) {
		this.lastNames = lastNames;
	}




	public String[] getYears() {
		return years;
	}




	public void setYears(String[] years) {
		this.years = years;
	}




	public String[] getMonths() {
		return months;
	}




	public void setMonths(String[] months) {
		this.months = months;
	}




	public String[] getDays() {
		return days;
	}




	public void setDays(String[] days) {
		this.days = days;
	}




	public Random getRand() {
		return rand;
	}




	public void setRand(Random rand) {
		this.rand = rand;
	}




	public SecurityManagerBCrypt getSecuMan() {
		return secuMan;
	}




	public void setSecuMan(SecurityManagerBCrypt secuMan) {
		this.secuMan = secuMan;
	}




	public UserDaoApi getUserDao() {
		return userDao;
	}




	public void setUserDao(UserDaoApi userDao) {
		this.userDao = userDao;
	}

	
	
}
