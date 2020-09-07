package com.ftninformatika.novinarska_agencija;

import java.time.LocalDate;

public class Vest {
	private int id;
	private String naslov;
	private String tekst;
	private LocalDate datumPublikovanja;
	private String imeAutora;
	private String prezimeAutora;
	private String oblast;
	private int brojPregleda;
	
	public Vest() {
		
	}

	public Vest(int id, String naslov, String tekst, LocalDate datumPublikovanja, String imeAutora,
			String prezimeAutora, String oblast, int brojPregleda) {
		this.id = id;
		this.naslov = naslov;
		this.tekst = tekst;
		this.datumPublikovanja = datumPublikovanja;
		this.imeAutora = imeAutora;
		this.prezimeAutora = prezimeAutora;
		this.oblast = oblast;
		this.brojPregleda = brojPregleda;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public LocalDate getDatumPublikovanja() {
		return datumPublikovanja;
	}

	public void setDatumPublikovanja(LocalDate datumPublikovanja) {
		this.datumPublikovanja = datumPublikovanja;
	}

	public String getImeAutora() {
		return imeAutora;
	}

	public void setImeAutora(String imeAutora) {
		this.imeAutora = imeAutora;
	}

	public String getPrezimeAutora() {
		return prezimeAutora;
	}

	public void setPrezimeAutora(String prezimeAutora) {
		this.prezimeAutora = prezimeAutora;
	}

	public String getOblast() {
		return oblast;
	}

	public void setOblast(String oblast) {
		this.oblast = oblast;
	}
	
	public int getBrojPregleda() {
		return brojPregleda;
	}

	public void setBrojPregleda(int brojPregleda) {
		this.brojPregleda = brojPregleda;
	}

	@Override
	public String toString() {
		return String.format("%5s %15s %15s %20s %15s %15s %15s %15s", this.id, this.naslov, this.tekst, this.datumPublikovanja, this.imeAutora, this.prezimeAutora, this.oblast, this.brojPregleda);
	}

	
}