package fr.afcepf.al29.groupem.entities;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="account")
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String description;

    private BigDecimal balance;
    @Column
	@Enumerated(EnumType.STRING)
    private TypeCarte typeCarte;
  
    private String numberCard;

    private String cryptogram;

    private static BigDecimal MONTANT_OPDEBIT;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	

	public TypeCarte getTypeCarte() {
		return typeCarte;
	}

	public void setTypeCarte(TypeCarte typeCarte) {
		this.typeCarte = typeCarte;
	}

	public String getNumberCard() {
		return numberCard;
	}

	public void setNumberCard(String numberCard) {
		this.numberCard = numberCard;
	}	

	public String getCryptogram() {
		return cryptogram;
	}

	public void setCryptogram(String cryptogram) {
		this.cryptogram = cryptogram;
	}

	public static BigDecimal getMONTANT_OPDEBIT() {
		return MONTANT_OPDEBIT;
	}

	public static void setMONTANT_OPDEBIT(BigDecimal mONTANT_OPDEBIT) {
		MONTANT_OPDEBIT = mONTANT_OPDEBIT;
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

	public BigDecimal getBalance() {
		return balance;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", description=" + description + ", balance=" + balance + ", typeCarte="
				+ typeCarte + ", numberCard=" + numberCard + ", cryptogram=" + cryptogram + ", dateExpiredCarte="
				+ dateExpiredCarte + ", dateCreationAccount=" + dateCreationAccount + "]";
	}    

}