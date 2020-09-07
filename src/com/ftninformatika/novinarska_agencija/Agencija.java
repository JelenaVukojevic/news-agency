package com.ftninformatika.novinarska_agencija;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Agencija {
	private String naziv;
	private String webAdresa;
	private String telefon;
	private ArrayList<Vest> listaVesti;
	private String[] dozvoljeneOblasti = {"politika", "sport", "turizam", "drustvo", "tehnologija", "ostalo"};

	public Agencija() {
		this.naziv = "";
		this.webAdresa = "";
		this.telefon = "";
		this.listaVesti = new ArrayList<>();
	}

	public Agencija(String naziv, String webAdresa, String telefon, ArrayList<Vest> listaVesti) {
		this.naziv = naziv;
		this.webAdresa = webAdresa;
		this.telefon = telefon;
		this.listaVesti = listaVesti;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getWebAdresa() {
		return webAdresa;
	}

	public void setWebAdresa(String webAdresa) {
		this.webAdresa = webAdresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public ArrayList<Vest> getListaVesti() {
		return listaVesti;
	}

	public void setListaVesti(ArrayList<Vest> listaVesti) {
		this.listaVesti = listaVesti;
	}

	public String[] getDozvoljeneOblasti() {
		return this.dozvoljeneOblasti;
	}

	public void load(String path) {
		this.listaVesti = new ArrayList<Vest>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
			for (String line: lines) {
				String[] attributes = line.split(";");

				int id = Integer.parseInt(attributes[0]); 
				String naslov = attributes[1];
				String tekst = attributes[2];
				LocalDate datumPublikovanja = null;
				try {
					datumPublikovanja = LocalDate.parse(attributes[3], dtf);
				} catch (Exception e) {
					e.printStackTrace();
				}
				String imeAutora = attributes[4];
				String prezimeAutora = attributes[5];
				String oblast = attributes[6];
				int brojPregleda = Integer.parseInt(attributes[7]);

				Vest vest = new Vest(id, naslov, tekst, datumPublikovanja, imeAutora, prezimeAutora, oblast, brojPregleda);
				this.listaVesti.add(vest);

			}
		} catch (java.io.IOException e) {
			System.out.println("Datoteka " + path + " nije pronađena.");
		}
	}

	public void save(String path) {
		ArrayList<String> lines = new ArrayList<String>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		for (int i = 0; i < this.listaVesti.size(); i++) {
			Vest vest = this.listaVesti.get(i);
			int id = vest.getId();
			String naslov = vest.getNaslov();
			String tekst = vest.getTekst();
			String datumPublikovanja = dtf.format(vest.getDatumPublikovanja());
			String imeAutora = vest.getImeAutora();
			String prezimeAutora = vest.getPrezimeAutora();
			String oblast = vest.getOblast(); 
			int brojPregleda = vest.getBrojPregleda();
			String line = id  + ";" + naslov + ";" + tekst + ";" + datumPublikovanja + ";" + imeAutora + ";" + prezimeAutora + ";" + oblast + ";" + brojPregleda;
			lines.add(line);
		}

		try {
			Files.write(Paths.get(path), lines, Charset.defaultCharset(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
		} catch (java.io.IOException e) {
			System.out.println("Datoteka " + path + " nije pronađena.");
		}
	}

	public boolean dodavanjeVesti(Vest vest) {

		for (int i = 0; i < this.listaVesti.size(); i++) {
			if(vest.getId() == this.listaVesti.get(i).getId()) {
				return false;
			}
		}
		this.listaVesti.add(vest);
		return true;
	}

	public Vest izmenaVesti(Vest vest) {
		for (int i = 0; i < this.listaVesti.size(); i++) {
			if(this.listaVesti.get(i).getId() == vest.getId()) {
				Vest vestKojiSeMenja = this.listaVesti.set(i, vest);
				return vestKojiSeMenja;
			}
		}
		return null;
	}

	public Vest brisanjeVesti(int id) {

		int indeks = -1;
		for (int i = 0; i < this.listaVesti.size(); i++) {
			if(this.listaVesti.get(i).getId() == id) {
				indeks = i;
			}
		}

		if(indeks != -1) {
			Vest transakcijaKojaSeBrise = this.listaVesti.remove(indeks);
			return transakcijaKojaSeBrise;
		}
		return null;
	}

	public ArrayList<Vest> pretragaNaslovaVestiPoZadatomTekstu(String string) {
		System.out.printf("%5s %15s %15s %20s %15s %15s %15s %15s\n", "Id", "Naslov", "Tekst", "Datum publikovanja", "Ime autora", "Prezime autora", "Oblast", "Broj pregleda");
		ArrayList<Vest> listaRezultata = new ArrayList<>();
		for (int i = 0; i < this.listaVesti.size(); i++) {
			if(this.listaVesti.get(i).getNaslov().toLowerCase().contains(string.toLowerCase())) {
				listaRezultata.add(this.listaVesti.get(i));
				System.out.println(this.listaVesti.get(i));
			}
		}
		return listaRezultata;

	}

	public ArrayList<Vest> pretragaVestiZaAutora(String ime, String prezime, LocalDate minDatum, LocalDate maxDatum) {
		System.out.printf("%5s %15s %15s %20s %15s %15s %15s %15s\n", "Id", "Naslov", "Tekst", "Datum publikovanja", "Ime autora", "Prezime autora", "Oblast", "Broj pregleda");
		ArrayList<Vest> listaRezultata = new ArrayList<>();
		for (int i = 0; i < this.listaVesti.size(); i++) {
			if(this.listaVesti.get(i).getImeAutora().equals(ime) && this.listaVesti.get(i).getPrezimeAutora().equals(prezime) && this.listaVesti.get(i).getDatumPublikovanja().compareTo(minDatum) >= 0
					&& this.listaVesti.get(i).getDatumPublikovanja().compareTo(maxDatum) <= 0) {
				listaRezultata.add(this.listaVesti.get(i));
				System.out.println(this.listaVesti.get(i));
			}
		}
		return listaRezultata;
	}
	
	public void izracunavanjeOdnosa(String oblast, LocalDate minDatum, LocalDate maxDatum) {
		int ukupanBrojVesti = 0;
		int brojVestiUOpsegu = 0;
		ArrayList<Vest> listaVestiZaZadatuOblast = new ArrayList<>();
		
		for(int i = 0; i < this.listaVesti.size(); i++) {
			if(oblast.equals(this.listaVesti.get(i).getOblast())) {
				ukupanBrojVesti++;
				listaVestiZaZadatuOblast.add(this.listaVesti.get(i));
			}
		}
		
		for(int i = 0; i < listaVestiZaZadatuOblast.size(); i++) {
			if(listaVestiZaZadatuOblast.get(i).getDatumPublikovanja().compareTo(minDatum) >= 0
					&& listaVestiZaZadatuOblast.get(i).getDatumPublikovanja().compareTo(maxDatum) <= 0) {
				brojVestiUOpsegu++;
			}
		}
		
		System.out.println((double)brojVestiUOpsegu/ukupanBrojVesti);
	}
	
	public void najcitanijaVestZaOblastIPeriod(String oblast, LocalDate minDatum, LocalDate maxDatum) {
		ArrayList<Vest> listaVesti = new ArrayList<>();
		for(int i = 0; i < this.listaVesti.size(); i++) {
			if(oblast.equals(this.listaVesti.get(i).getOblast()) && this.listaVesti.get(i).getDatumPublikovanja().compareTo(minDatum) >= 0
					&& this.listaVesti.get(i).getDatumPublikovanja().compareTo(maxDatum) <= 0) {
				listaVesti.add(this.listaVesti.get(i));
			}
		}
		Vest najcitanijaVest = listaVesti.get(0);
		for(int i = 1; i < listaVesti.size(); i++) {
			if(najcitanijaVest.getBrojPregleda() < listaVesti.get(i).getBrojPregleda()) {
				najcitanijaVest = listaVesti.get(i);
			}
		}
		System.out.printf("%5s %15s %15s %20s %15s %15s %15s %15s\n", "Id", "Naslov", "Tekst", "Datum publikovanja", "Ime autora", "Prezime autora", "Oblast", "Broj pregleda");
		System.out.println(najcitanijaVest);
	}

	public void ispisVesti() {
		System.out.printf("%5s %15s %15s %20s %15s %15s %15s %15s\n", "Id", "Naslov", "Tekst", "Datum publikovanja", "Ime autora", "Prezime autora", "Oblast", "Broj pregleda");
		for(int i = 0; i < this.listaVesti.size(); i++) {
			System.out.println(this.listaVesti.get(i));
		}
	}

	@Override
	public String toString() {
		String temp = "";
		temp += "Naziv agencije: " + this.naziv + "\n";
		temp += "Web adresa agencije: " + this.webAdresa + "\n";
		temp += "Telefon agencije: " + this.telefon + "\n";

		return temp;
	}
}
