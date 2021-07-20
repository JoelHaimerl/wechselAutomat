
public class Waehrung {
	
	public String name;
	public String zeichen;
	public int[] coins;
	
	
	public Waehrung(String name, int[] coins, String zeichen) {
		this.coins = coins;
		this.name = name;
		this.zeichen= zeichen; 
	}


	
	public String getZeichen() {
		return zeichen;
	}



	public void setZeichen(String zeichen) {
		this.zeichen = zeichen;
	}



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int[] getCoins() {
		return coins;
	}


	public void setCoins(int[] coins) {
		this.coins = coins;
	}
	
	
	
}
