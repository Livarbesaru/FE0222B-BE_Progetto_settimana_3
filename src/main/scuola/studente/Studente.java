package main.scuola.studente;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import main.Enum.Genere;
import main.Enum.Materia;

public class Studente{
	
	private int id;
	private String nome;
	private String cognome;
	private Genere genere;
	private Map<Materia,List<Double>> voti;
	private int insufficienze;
	
	public Studente(String nome,String cognome,Genere genere){
		this.nome=nome;
		this.cognome=cognome;
		this.genere=genere;
		this.voti=assegnazioneVoti();
		this.assegnazioneInsufficienze();
	}
	/*Metodo utilizzato per assegnare allo studente, 5 voti random in range tra 4 e 10 per ogni materia
	 * */
	private Map<Materia,List<Double>> assegnazioneVoti(){
		Map<Materia,List<Double>> mappaVoti=new HashMap<>();
		
		for(Materia materia:Materia.values()) {
			List<Double> listaVoti=new ArrayList<>();
			for(int i=0;i<5;i++) {
				double votoRandom=10-(Math.random()*6);
				listaVoti.add(votoRandom);
			}
			mappaVoti.put(materia, listaVoti);
		}
		return mappaVoti;
	}
	
	/*Metodo che ritorna come stringa una lista con tutti i voti per la materia passata a parametro
	 * */
	public String listaVotiMateria(Materia m) {
		 List<String> voti=(List<String>) (this.getVoti().get(m).stream().map(op -> op.toString()).collect(Collectors.toList()));
		 String listaVoti=voti.stream().reduce(" ",(lista,voto)->lista+"|"+voto.toString().substring(0, 4));
		 return listaVoti;
	}
	
	/*Metodo che ritorna la media dei voti per singola materia
	 * */
	public double mediaVotoMateria(Materia m) {
			 double media=(this.getVoti().get(m).stream().reduce(0.0, (voto,elemento)->voto+elemento)/this.getVoti().get(m).size());
			 return media;
	}
	
	/*Metodo che ritorna la media dei voti per tutte le materie
	 * */
	public double mediaVoto() {
		double media=0;
		for(Materia materia:Materia.values()) {
			 media+=(this.getVoti().get(materia).stream().reduce(0.0, (voto,elemento)->voto+elemento)/this.getVoti().get(materia).size());
		}
		media/=Materia.values().length;
		return media;
}
	/*Metodo che ritorna il voto piu' alto per materia passata a parametro
	 * */
	public double votoMiglioreMateria(Materia m) {
		double votoMigliore=(this.getVoti().get(m).stream().reduce(0.0, (voto,elemento)->{
			Predicate<Double> maggiore=x->(x>voto);
			
			if(maggiore.test(elemento)) {
				return elemento;
			}
			else {
				return voto;
			}
		}));
		return votoMigliore;
	}
	/*Metodo che ritorna se lo studente e' promosso o bocciato in base alle insufficienze
	 * */
	public boolean promosso() {
		return !(this.insufficienze>=4);
	}
	
	/*Metodo utilizzato per l'assegnazione delle insufficienze in base alla media dei voti per materia
	 * */
	public void assegnazioneInsufficienze() {
		this.getVoti().keySet().forEach((x)->{
			Predicate<Materia> valutazione=(materia)->mediaVotoMateria(materia)>=6.0;
			if(!valutazione.test(x)) {
				this.insufficienze++;
			}
		});
	}

	public String getNome() {
		return (this.nome+" "+this.cognome);
	}

	public Genere getGenere() {
		return genere;
	}

	public Map<Materia,List<Double>> getVoti() {
		return voti;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
