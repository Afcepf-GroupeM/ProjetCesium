package fr.afcepf.groupem.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="adresse")
public class Adresse {
	


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String lastname;
	private String firstname;
	private int numero;
	private String complement;
	
	@Column(name="typevoie")
	private String typeVoie;
	
	@Column(name="nomvoie")
	private String nomVoie;
	private String city;
	private String zipcode;
	private String country;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="adresse")
	private List<Livraison> livraisons;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	public String getTypeVoie() {
		return typeVoie;
	}
	public void setTypeVoie(String typeVoie) {
		this.typeVoie = typeVoie;
	}
	public String getNomVoie() {
		return nomVoie;
	}
	public void setNomVoie(String nomVoie) {
		this.nomVoie = nomVoie;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public List<Livraison> getLivraisons() {
		return livraisons;
	}
	public void setLivraisons(List<Livraison> livraisons) {
		this.livraisons = livraisons;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adresse other = (Adresse) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (complement == null) {
			if (other.complement != null)
				return false;
		} else if (!complement.equals(other.complement))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (nomVoie == null) {
			if (other.nomVoie != null)
				return false;
		} else if (!nomVoie.equals(other.nomVoie))
			return false;
		if (numero != other.numero)
			return false;
		if (typeVoie == null) {
			if (other.typeVoie != null)
				return false;
		} else if (!typeVoie.equals(other.typeVoie))
			return false;
		if (zipcode == null) {
			if (other.zipcode != null)
				return false;
		} else if (!zipcode.equals(other.zipcode))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Adresse [id=" + id + ", lastname=" + lastname + ", firstname=" + firstname + ", numero=" + numero
				+ ", complement=" + complement + ", typeVoie=" + typeVoie + ", nomVoie=" + nomVoie + ", city="
				+ city + ", zipcode=" + zipcode + ", country=" + country + "]";
	}

}
