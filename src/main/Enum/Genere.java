package main.Enum;

public enum Genere {
	
	Male('M'),
	Female('F');
	private char genere;

	Genere(char c) {
		this.genere=c;
	}
	
	public char getGenere(){
		return this.genere;
	}
	
	
}
