package main.interfacce;

import java.io.File;

public interface ScuolaInt {

	public String getPromossi();
	public String getStudenti();
	public String getStudenteMigliore();
	public void salvaStudenti(File file );
}
