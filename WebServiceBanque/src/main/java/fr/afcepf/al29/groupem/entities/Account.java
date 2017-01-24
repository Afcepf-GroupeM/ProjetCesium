package fr.afcepf.al29.groupem.entities;


import java.math.BigDecimal;
import java.util.Date;

public class Account {

    private Integer id;

    private String typeCompte;

    private String description;

    private BigDecimal balance;

    private Enum typeCarte;
  
    private Integer numberCarte;

    private Integer crytogram;

    private static BigDecimal MONTANT_OPDEBITER;

    private Date dateExpiredCarte;

    private Date dateCreationAccount;

    public Account() {
    }

    public void Debit(BigDecimal amount) {
        // TODO implement here
    }

    public void Credit(BigDecimal amount) {
        // TODO implement here
    }

    public void getBalance() {
        // TODO implement here
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeCompte() {
		return typeCompte;
	}

	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Enum getTypeCarte() {
		return typeCarte;
	}

	public void setTypeCarte(Enum typeCarte) {
		this.typeCarte = typeCarte;
	}

	public Integer getNumberCarte() {
		return numberCarte;
	}

	public void setNumberCarte(Integer numberCarte) {
		this.numberCarte = numberCarte;
	}

	public Integer getCrytogram() {
		return crytogram;
	}

	public void setCrytogram(Integer crytogram) {
		this.crytogram = crytogram;
	}

	public static BigDecimal getMONTANT_OPDEBITER() {
		return MONTANT_OPDEBITER;
	}

	public static void setMONTANT_OPDEBITER(BigDecimal mONTANT_OPDEBITER) {
		MONTANT_OPDEBITER = mONTANT_OPDEBITER;
	}

	public Date getDateExpiredCarte() {
		return dateExpiredCarte;
	}

	public void setDateExpiredCarte(Date dateExpiredCarte) {
		this.dateExpiredCarte = dateExpiredCarte;
	}

	public Date getDateCreationAccount() {
		return dateCreationAccount;
	}

	public void setDateCreationAccount(Date dateCreationAccount) {
		this.dateCreationAccount = dateCreationAccount;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
    
    

}