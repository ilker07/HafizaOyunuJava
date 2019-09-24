package swingHafizaOyunu;

public class kart {
	
	
	private char harf;
	private boolean durum=false;
	public kart(char harf) {
		
		this.harf = harf;
		
	}
	public char getHarf() {
		return harf;
	}
	public void setHarf(char harf) {
		this.harf = harf;
	}
	public boolean isDurum() {
		return durum;
	}
	public void setDurum(boolean durum) {
		this.durum = durum;
	}
	
}
