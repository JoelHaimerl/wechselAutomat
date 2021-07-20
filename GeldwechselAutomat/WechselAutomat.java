
/*
 * @Author Joel Haimerl
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class WechselAutomat {

	public static ArrayList<Waehrung> moeglicheWaehrungen = new ArrayList<>();
	public static HashMap<Integer, Integer> muenzzaehlerMap = new HashMap<>();

	private static void configCurrencies() {
		moeglicheWaehrungen.add(new Waehrung("Euro", new int[] { 200, 100, 50, 20, 10, 5, 1 }, "€"));
		moeglicheWaehrungen.add(new Waehrung("Dollar", new int[] { 100, 50, 25, 10, 5, 1 }, "$"));
	}

	private static void addCoin(Integer muenzwert) {
		Integer aktuelleAnzahl = muenzzaehlerMap.get(muenzwert);
		if (aktuelleAnzahl != null) {
			aktuelleAnzahl = aktuelleAnzahl + 1;
			muenzzaehlerMap.put(muenzwert, aktuelleAnzahl);
		} else {
			muenzzaehlerMap.put(muenzwert, 1);
		}
	}

	private static void listResultCoins(Waehrung vonUserausgewaehlteWaehrung) {
		Set<Integer> keys = muenzzaehlerMap.keySet();
		for (Integer integer : keys) {
			String anzuzeigendeMuenze = "";
			if (integer >= 100) {
				anzuzeigendeMuenze = (integer / 100) + " " + vonUserausgewaehlteWaehrung.getZeichen();
			} else {
				anzuzeigendeMuenze = integer + " Cent";
			}
			System.out.println(anzuzeigendeMuenze + " = " + muenzzaehlerMap.get(integer));
		}
	}

	private static void showPossibleCurrencies() {
		for (Waehrung waehrung : moeglicheWaehrungen) {
			System.out.println(waehrung.getName() + " " + waehrung.getZeichen());
		}

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		configCurrencies();

		Waehrung vonUserausgewaehlteWaehrung = null;
		System.out.println("Bitte geben Sie ihr Währung ein, moeglich sind: ");
		showPossibleCurrencies();
		String waehrungsWunschUser = scanner.next();
		System.out.print("Bitte geben Sie den gegebenen Geldbetrag in Cent ein:");
		int gegeben = scanner.nextInt();

		int rueckGeldBetrag = gegeben;

		if (rueckGeldBetrag < 0) {
			System.out.print("Sie haben zu wenig Geld gegeben," + " es fehlen noch " + (-rueckGeldBetrag) + " Cent!");
		} else if (rueckGeldBetrag == 0) {
			System.out.print("Kein Rueckgeld.");
		}

		for (Waehrung w : moeglicheWaehrungen) {
			if (waehrungsWunschUser != null && waehrungsWunschUser.length() > 0
					&& w.getName().equalsIgnoreCase(waehrungsWunschUser)) {
				vonUserausgewaehlteWaehrung = w;
				while (rueckGeldBetrag > 0) //
				{
					for (int münze : w.coins) {
						if (rueckGeldBetrag >= münze) {
							addCoin(münze);
							rueckGeldBetrag = rueckGeldBetrag - münze;
							break;
						}
					}
				}

			}

		}
		listResultCoins(vonUserausgewaehlteWaehrung);
		scanner.close();
	}
}
