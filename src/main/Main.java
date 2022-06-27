package main;

import java.io.File;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.Enum.Consonanti;
import main.Enum.Genere;
import main.Enum.Materia;
import main.Enum.Vocali;
import main.scuola.Scuola;
import main.scuola.studente.Studente;
import thread.ThreadScuola;

public class Main {
	private static final Logger Logger=LoggerFactory.getLogger(Scuola.class);
	
	public static void main(String[] args) {
		List<Studente> studenti1=new ArrayList<>();
		Scuola scuola1=new Scuola(generaStudenti());
		Scuola scuola2=new Scuola(generaStudenti());
		
		File file=new File("src/main/studenti/studenti.txt");
		scuola1.salvaStudenti(file);
		
		ThreadScuola tScuola1=new ThreadScuola(scuola1);
		ThreadScuola tScuola2=new ThreadScuola(scuola2);
		
		Thread t1=new Thread(tScuola1);
		Thread t2=new Thread(tScuola2);
		t1.start();
		t2.start();
	

	}
	/*Funzione usate per generare dei studenti
	 * */
	static List<Studente> generaStudenti() {
		List<Studente> studenti=new ArrayList<>();
		for(int i=0;i<5;i++) {
			int num=(int) (Math.random()*10);
			Genere genere;
			if(num>5) {
				genere=Genere.Male;
			}
			else {
				genere=Genere.Female;
			}
			studenti.add(new Studente(generaNomi(),generaNomi(), genere));
		}
		return studenti;
	}
	/*Funzione usate per generare i nome e cognomi degli alunni
	 * */
	static public String generaNomi() {
		String nome="";
		int lunghezza=(int)(10-(Math.random()*7));
		int num=0;
		for(int i=0;i<lunghezza;i++) {
			if((num%2)==0) {
				int lettera = (int)(Math.random() * Vocali.values().length);
				nome+=Vocali.values()[lettera];
				num++;
			}
			else{
				int lettera = (int)(Math.random() * Consonanti.values().length);
				nome+=Consonanti.values()[lettera];
				num++;
			}
		}
		return nome;
	}

}
