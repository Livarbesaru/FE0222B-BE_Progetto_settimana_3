package main.scuola;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;

import main.Enum.Materia;
import main.interfacce.ScuolaInt;
import main.scuola.studente.Studente;

public class Scuola implements ScuolaInt{
	private static final DecimalFormat df=new DecimalFormat("0.00");
	
	List<Studente> studenti;
	
	public Scuola(List<Studente> studenti){
		this.studenti=studenti;
		this.impostaId();
	}
	/*Funzione usata per impostare automaticamente gli id ad gli studenti
	 * */
	private void impostaId() {
		int id=0;
		for(int i=0;i<getStudentiLista().size();i++) {
			this.getStudentiLista().get(i).setId(id);
			id++;
		}
	}

	/*Metodo usato per consegnare sottoforma di Stringa, le informazione del singolo studente
	 * */
	public String getStudente(Studente alunno) {
		String studente="";
			studente+=(
					"\n"+"=============Studente: " + alunno.getNome() + " =============" 
					+"\n"+"Genere:"+alunno.getGenere()
					+"\n"+"Voti:"
					+"\n"+"Media voto: "+df.format(alunno.mediaVoto())
					+"\n");
			for(Materia materia:alunno.getVoti().keySet()){
				studente+=(
						"Materia: "
								+materia
								+" Voto medio: "
								+df.format(alunno.mediaVotoMateria(materia))
								+" |---> Voti materia:"+alunno.listaVotiMateria(materia)
								+" |---> Voto piu' alto:"+df.format(alunno.votoMiglioreMateria(materia))
								+"\n");
			}
			studente+=("Promosso: "+alunno.promosso());
		return studente;
	}
	
	@Override
	public String getPromossi() {
		List<Studente> studentiPromossi =this.getStudentiLista().stream().filter(x->x.promosso()).collect(Collectors.toList());
		String stringa="";
		for(Studente studente:studentiPromossi) {
			stringa+=getStudente(studente);
		}
		return stringa;
	}

	/*consegna le informazioni di tutti gli studenti presenti nella scuola
	 * */
	@Override
	public String getStudenti() {
		String students="";
		for(Studente studente:this.studenti) {
			students+=getStudente(studente);
		}
		return students;
	}

	/*Metodo utilizzato per consegnare le informazioni dello studente migliore nella scuola
	 * */
	@Override
	public String getStudenteMigliore() {
		Studente studenteMigliore= getStudentiLista().stream().max(Comparator.comparingDouble(Studente::mediaVoto)).get();
		return getStudente(studenteMigliore);
	}

	/*Metodo utilizzato per salvare le informazione di tutti gli studenti della scuola su di un file 
	 * */
	@Override
	public void salvaStudenti(File file) {
		File elencoStudenti=file;
		
		try {
			FileUtils.writeStringToFile(elencoStudenti, getStudenti());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Studente> getStudentiLista() {
		return studenti;
	}

}
