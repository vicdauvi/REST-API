package com.example.hellorestapi.hellorestapi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ville_france")
public class Ville {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	String codeCommuneINSEE ;
	
	@Column(name = "Nom_commune", nullable = false)
	String nomCommune ;
	
	@Column(name = "Code_postal", nullable = false)
	String codePostal ;
	
	@Column(name = "Libelle_acheminement", nullable = false)
	String libelleAcheminement ;
	
	@Column(name = "Ligne_5", nullable = true)
	String ligne5 ;
	
	@Column(name = "Latitude", nullable = false)
	String latitude ;
	
	@Column(name = "Longitude", nullable = false)
	String longitude ;
	
	public Ville(String codeCommuneINSEE, String nomCommune, String codePostal, String libelleAcheminement,
			String ligne5, String latitude, String longitude) {
		this.codeCommuneINSEE = codeCommuneINSEE ;
		this.nomCommune = nomCommune ;
		this.codePostal = codePostal ;
		this.libelleAcheminement = libelleAcheminement ;
		this.ligne5 = ligne5 ;
		this.latitude = latitude ;
		this.longitude = longitude ;
	}

	public String getCodeCommuneINSEE() {
		return codeCommuneINSEE;
	}

	public void setCodeCommuneINSEE(String codeCommuneINSEE) {
		this.codeCommuneINSEE = codeCommuneINSEE;
	}

	public String getNomCommune() {
		return nomCommune;
	}

	public void setNomCommune(String nomCommune) {
		this.nomCommune = nomCommune;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getLibelleAcheminement() {
		return libelleAcheminement;
	}

	public void setLibelleAcheminement(String libelleAcheminement) {
		this.libelleAcheminement = libelleAcheminement;
	}

	public String getLigne5() {
		return ligne5;
	}

	public void setLigne5(String ligne5) {
		this.ligne5 = ligne5;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "'"+codeCommuneINSEE + "','" + nomCommune + "','"+ codePostal + "','" + libelleAcheminement 
				+ "','" + ligne5 + "','"+ latitude + "','" + longitude+"'";
	}
}
