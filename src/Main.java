import java.io.File;
import java.io.FileNotFoundException;

import main.java.com.mochilabinaria.ArquivoUtils;
import main.java.com.mochilabinaria.MetodosILS;
import main.java.com.mochilabinaria.Mochila;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
    	File arquivo = new File(System.getProperty("user.dir")+"\\src\\input2.txt");
    	ArquivoUtils.apagarArquivo();
      
    	// Parâmetros do ILS
    	int criterioDeParadaDoILS = 1000;
    	int criterioDeParadaDaBuscaLocal = 2000;
    	int tamanhoInicialPertubação = 250; // NAO PODE SER MUITO GRANDE, ~10 DO TOTAL DE ITENS, JÁ SEI QUAL É O PROBLEMA, CONSERTO DPS

    	// Iniciação da mochila com os itensMochila
    	Mochila mochila = new Mochila(arquivo);
    	// Iniciação do ils com a mochila e o criterio de parada
    	MetodosILS ils = new MetodosILS(mochila, criterioDeParadaDoILS, criterioDeParadaDaBuscaLocal, tamanhoInicialPertubação);
    	// Encontrar e exibir a solução
    	ils.exibirSolucao(ils.encontrarSolucao());
    }
}
