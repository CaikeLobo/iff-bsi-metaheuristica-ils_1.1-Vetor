import java.io.File;
import java.io.FileNotFoundException;

import main.java.com.mochilabinaria.ArquivoUtils;
import main.java.com.mochilabinaria.MetodosILS;
import main.java.com.mochilabinaria.Mochila;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
    	File arquivo = new File(System.getProperty("user.dir")+"\\src\\input1.txt");
    	ArquivoUtils.apagarArquivo();
      
    	// Parâmetros do ILS
    	int criterioDeParadaDoILS = 3000;
    	int criterioDeParadaDaBuscaLocal = 5000;
    	int tamanhoPertubação = 500;

    	// Iniciação da mochila com os itensMochila
    	Mochila mochila = new Mochila(arquivo);
    	// Iniciação do ils com a mochila e o criterio de parada
    	MetodosILS ils = new MetodosILS(mochila, criterioDeParadaDoILS, criterioDeParadaDaBuscaLocal, tamanhoPertubação);
    	// Encontrar e exibir a solução
    	ils.exibirSolucao(ils.encontrarSolucao());
    }
}
