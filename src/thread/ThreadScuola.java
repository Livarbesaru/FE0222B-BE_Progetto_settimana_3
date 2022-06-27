package thread;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.scuola.Scuola;
import main.scuola.studente.Studente;

public class ThreadScuola implements Runnable{
	private static final Logger Logger=LoggerFactory.getLogger(Scuola.class);

	Scuola scuola;
	public ThreadScuola(Scuola scuola){
		this.scuola=scuola;
	}

	/*Le informazioni di tutti gli studenti vengono stampate per singolo studente con un distacco di due secondi
	 * */
	@Override
	public void run() {
		try {
			List<Studente> studenti=this.scuola.getStudentiLista();
			for(Studente studente:studenti) {
				String stringa=this.scuola.getStudente(studente);
				Logger.info(stringa);
				Thread.sleep(2000);
			}
		} catch (InterruptedException e) {
			Logger.error("Errore durante l'esecuzione del Thread");
		}
	}

}
