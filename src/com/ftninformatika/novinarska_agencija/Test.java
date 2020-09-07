package com.ftninformatika.novinarska_agencija;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
	public static Scanner scanner = new Scanner(System.in);
	public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
	
	public static boolean proveriDaLiJeBroj(String broj) {
		try {
			Integer.parseInt(broj);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean proveraZaDodavanje(String string, Agencija agencija) {
		try {
			int broj = Integer.parseInt(string);
			if(broj < 1) {
				return false;
			}
			ArrayList<Vest> lista = agencija.getListaVesti();
			for(int i = 0; i < lista.size(); i++) {
				if(broj == lista.get(i).getId()) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean proveriDatum(String datum) {

		try {
			LocalDate.parse(datum, dtf);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean proveriOblast(String oblast, Agencija agencija) {
		for(int i = 0; i < agencija.getDozvoljeneOblasti().length; i++) {
			if(oblast.equals(agencija.getDozvoljeneOblasti()[i])) {
				return true;
			}
		}
		return false;
	}

	public static boolean proveraZaBrisanjeIIzmenu(String string, Agencija agencija) {

		try {
			int broj = Integer.parseInt(string);
			if(broj < 1) {
				return false;
			}
			ArrayList<Vest> lista = agencija.getListaVesti();
			for(int i = 0; i < lista.size(); i++) {
				if(broj == lista.get(i).getId()) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}

	}
	
	public static boolean daLiJeVeciDatum(LocalDate datumPocetni, String datum) {

		LocalDate datumKrajnji = null;
		try {
			datumKrajnji = LocalDate.parse(datum, dtf);
			if(datumKrajnji.compareTo(datumPocetni) >= 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public static void unesiAgenciju(Agencija agencija) {

		System.out.println("Unesite naziv agencije:");
		String naziv = scanner.nextLine();
		agencija.setNaziv(naziv);
		System.out.println("Unesite web adresu agencije:");
		String adresa = scanner.nextLine();
		agencija.setWebAdresa(adresa);
		System.out.println("Unesite broj telefona agencije:");
		String telefon = scanner.nextLine();
		agencija.setTelefon(telefon);
		System.out.println("Podaci o agenciji su uspesno dodati.");
	}

	public static void unesiVest(Agencija agencija) {
		int id = 0;
		String naslov = null;
		String tekst = null;
		LocalDate datumPublikovanja = null;
		String imeAutora = null;
		String prezimeAutora = null;
		String oblast = null;
		int brojPregleda = 0;

		String idVesti = null;
		String datumStr = null;
		String broj = null;

		do {
			System.out.print("Identifikacioni broj: ");
			idVesti = scanner.nextLine();
		} while(!proveraZaDodavanje(idVesti, agencija));
		id = Integer.parseInt(idVesti);

		System.out.print("Naslov vesti: ");
		naslov = scanner.nextLine();

		System.out.print("Tekst vesti: ");
		tekst = scanner.nextLine();

		do {
			System.out.print("Datum publikacije: ");
			datumStr = scanner.nextLine();
		} while(!proveriDatum(datumStr));

		try {
			datumPublikovanja = LocalDate.parse(datumStr, dtf);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.print("Ime autora: ");
		imeAutora = scanner.nextLine();

		System.out.print("Prezime autora: ");
		prezimeAutora = scanner.nextLine();

		do {
			System.out.print("Oblast: ");
			oblast = scanner.nextLine();
		} while (!proveriOblast(oblast, agencija));
		
		do {
			System.out.println("Unesi broj pregleda: ");
			broj = scanner.nextLine();
		} while (!proveriDaLiJeBroj(broj));
		brojPregleda = Integer.parseInt(broj);


		Vest vest = new Vest(id, naslov, tekst, datumPublikovanja, imeAutora, prezimeAutora, oblast, brojPregleda);

		boolean provera = agencija.dodavanjeVesti(vest);
		if(provera) {
			System.out.println("Vest je uspešno dodata.");
		} else {
			System.out.println("Vest nije uspešno dodata.");
		}
	}
	
	public static void ispisSvihVesti(Agencija agencija) {
		agencija.ispisVesti();
	}
	
	public static void izmenaVesti(Agencija agencija) {
		int id = 0;
		String naslov = null;
		String tekst = null;
		LocalDate datumPublikovanja = null;
		String imeAutora = null;
		String prezimeAutora = null;
		String oblast = null;
		int brojPregleda = 0;

		String idVesti = null;
		String datumStr = null;
		String broj = null;

		do {
			System.out.print("Identifikacioni broj: ");
			idVesti = scanner.nextLine();
		} while(!proveraZaBrisanjeIIzmenu(idVesti, agencija));
		id = Integer.parseInt(idVesti);

		System.out.print("Naslov vesti: ");
		naslov = scanner.nextLine();

		System.out.print("Tekst vesti: ");
		tekst = scanner.nextLine();

		do {
			System.out.print("Datum publikacije: ");
			datumStr = scanner.nextLine();
		} while(!proveriDatum(datumStr));

		try {
			datumPublikovanja = LocalDate.parse(datumStr, dtf);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.print("Ime autora: ");
		imeAutora = scanner.nextLine();

		System.out.print("Prezime autora: ");
		prezimeAutora = scanner.nextLine();

		do {
			System.out.print("Oblast: ");
			oblast = scanner.nextLine();
		} while (!proveriOblast(oblast, agencija));
		
		do {
			System.out.println("Unesi broj pregleda: ");
			broj = scanner.nextLine();
		} while (!proveriDaLiJeBroj(broj));
		brojPregleda = Integer.parseInt(broj);


		Vest vest = new Vest(id, naslov, tekst, datumPublikovanja, imeAutora, prezimeAutora, oblast, brojPregleda);
		Vest provera = agencija.izmenaVesti(vest);
		if(provera != null) {
			System.out.println("Vest je uspešno izmenjena.");
		} else {
			System.out.println("Vest nije uspešno izmenjena.");
		}
	}
	
	public static void brisanjeVesti(Agencija agencija) {
		int id = 0;
		String idVesti = null;
		do {
			System.out.println("Identifikacioni broj transakcije za brisanje: ");
			idVesti = scanner.nextLine();
		} while(!proveraZaBrisanjeIIzmenu(idVesti, agencija));
		id = Integer.valueOf(idVesti);
		Vest provera = agencija.brisanjeVesti(id);
		if(provera == null) {
			System.out.println("Vest sa zadatim brojem ne postoji.");
		}
	}

	public static void ispisPodatakaOAgenciji(Agencija agencija) {
		System.out.println(agencija);
	}

	public static void pretragaNaslovaVestiPoZadatomTekstu(Agencija agencija) {
		System.out.println("Unesite tekst za pretragu");
		String tekst = scanner.nextLine();
		agencija.pretragaNaslovaVestiPoZadatomTekstu(tekst);
	}
	
	public static void pretragaVestiZaAutora(Agencija agencija) {
		LocalDate datumMin = null;
		String datumMinS = null;
		LocalDate datumMax = null;
		String datumMaxS = null;
		
		System.out.println("Unesite ime autora za pretragu");
		String imeAutora = scanner.nextLine();
		
		System.out.println("Unesite prezime autora za pretragu");
		String prezimeAutora = scanner.nextLine();
		
		do {
			System.out.print("Unesite minimalni datum za pretragu: ");
			datumMinS = scanner.nextLine();
		} while(!proveriDatum(datumMinS));
		datumMin = LocalDate.parse(datumMinS, dtf);
		do {
			System.out.print("Unesite maksimalni datum za pretragu: ");
			datumMaxS = scanner.nextLine();
		} while(!daLiJeVeciDatum(datumMin, datumMaxS));
		datumMax = LocalDate.parse(datumMaxS, dtf);
		
		agencija.pretragaVestiZaAutora(imeAutora, prezimeAutora, datumMin, datumMax);
	}
	
	public static void izracunavanjeOdnosa(Agencija agencija) {
		LocalDate datumMin = null;
		String datumMinS = null;
		LocalDate datumMax = null;
		String datumMaxS = null;
		
		System.out.println("Unesite oblast za pretragu");
		String oblast = scanner.nextLine();
		
		do {
			System.out.print("Unesite minimalni datum za pretragu: ");
			datumMinS = scanner.nextLine();
		} while(!proveriDatum(datumMinS));
		datumMin = LocalDate.parse(datumMinS, dtf);
		do {
			System.out.print("Unesite maksimalni datum za pretragu: ");
			datumMaxS = scanner.nextLine();
		} while(!daLiJeVeciDatum(datumMin, datumMaxS));
		datumMax = LocalDate.parse(datumMaxS, dtf);
		
		agencija.izracunavanjeOdnosa(oblast, datumMin, datumMax);
	}
	
	public static void najcitanijaVestZaOblastIPeriod(Agencija agencija) {
		LocalDate datumMin = null;
		String datumMinS = null;
		LocalDate datumMax = null;
		String datumMaxS = null;
		
		System.out.println("Unesite oblast za pretragu");
		String oblast = scanner.nextLine();
		
		do {
			System.out.print("Unesite minimalni datum za pretragu: ");
			datumMinS = scanner.nextLine();
		} while(!proveriDatum(datumMinS));
		datumMin = LocalDate.parse(datumMinS, dtf);
		do {
			System.out.print("Unesite maksimalni datum za pretragu: ");
			datumMaxS = scanner.nextLine();
		} while(!daLiJeVeciDatum(datumMin, datumMaxS));
		datumMax = LocalDate.parse(datumMaxS, dtf);
		
		agencija.najcitanijaVestZaOblastIPeriod(oblast, datumMin, datumMax);
	}
	
	public static void main(String[] args) {

		Agencija agencija =  new Agencija();
		agencija.load("agencija.txt");

		String answer = null;

		do {
			System.out.println();
			System.out.println("Meni:");
			System.out.println("1. Unos agencije");
			System.out.println("2. Unos nove vesti");
			System.out.println("3. Ispis svih vesti");
			System.out.println("4. Izmena vesti");
			System.out.println("5. Brisanje vesti");
			System.out.println("6. Pretraga naslova vesti po zadatom tekstu");
			System.out.println("7. Pretraga vesti odredjenog autora za prosledjeni vremenski period");
			System.out.println("8. Izracunavanje odnosa (broj vesti iz oblasti u vremenskom periodu/ukupan broj vesti u oblasti)");
			System.out.println("9. Najcitanija vest iz odredjene oblasti za prosledjeni vremenski period");
			System.out.println("10. Ispis podataka o agenciji");
			System.out.println("x. Izlaz");

			answer = scanner.nextLine();

			switch (answer) {
			case "1":
				unesiAgenciju(agencija);
				break;
			case "2":
				unesiVest(agencija);
				break;
			case "3":
				ispisSvihVesti(agencija);
				break;
			case "4":
				izmenaVesti(agencija);
				break;
			case "5":
				brisanjeVesti(agencija);
				break;
			case "6":
				pretragaNaslovaVestiPoZadatomTekstu(agencija);
				break;
			case "7":
				pretragaVestiZaAutora(agencija);
				break;
			case "8":
				izracunavanjeOdnosa(agencija);
				break;
			case "9":
				najcitanijaVestZaOblastIPeriod(agencija);
				break;
			case "10":
				ispisPodatakaOAgenciji(agencija);
				break;
			case "x":
				break;
			default:
				System.out.println("Izabrana opcija ne postoji. Probajte ponovo");
				break;
			}

		} while (!answer.equals("x"));

		agencija.save("agencija.txt");

	}

}
