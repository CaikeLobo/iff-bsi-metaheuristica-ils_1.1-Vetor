import java.io.File;
import java.io.IOException;

//Caike,Ivanilso,Matheus,Pedro 

import main.java.com.mochilabinaria.ArquivoUtils;
import main.java.com.mochilabinaria.ArquivoUtils2;
import main.java.com.mochilabinaria.MetodosILS;
import main.java.com.mochilabinaria.Mochila;

public class Main {
    public static void main(String[] args) throws IOException {
    	File arquivo = new File(System.getProperty("user.dir")+"\\src\\mochila.txt");
    	int[] solucao;
    	int valor;
    	//input0.txt 1000 itens
    	//input1.txt 5000 itens
    	//input2.txt 10000 itens
    	
    	//É NECESSARIO ALTERAR O DIRETORIO NO ARQUIVOUTILS TAMBEM, LÁ VOCE IRÁ COLOCAR UM LOCAL PARA O Output.txt
    	
    	ArquivoUtils.apagarArquivo();
    
      
    	// Parâmetros do ILS
    	int criterioDeParadaDoILS = 1000;
    	int criterioDeParadaDaBuscaLocal = 1000;
    	int tamanhoPertubação = 25; // 20 = 20% da Solução foi retirada

    	// Iniciação da mochila com os itensMochila
    	Mochila mochila = new Mochila(arquivo);
    	// Iniciação do ils com a mochila e o criterio de parada
    	MetodosILS ils = new MetodosILS(mochila, criterioDeParadaDoILS, criterioDeParadaDaBuscaLocal, tamanhoPertubação);
    	// Encontrar e exibir a solução
    	
    	solucao = ils.encontrarSolucao();
    	valor = ils.verificarValorMochila(solucao);
    	//ImprimirSoluçãoExtra.Garantia0_5Extra(solucao, mochila);;
    	ArquivoUtils2.escreverVetorNoArquivo(solucao, "C:\\Users\\caike\\Downloads\\Solucao.txt", valor);
    	ils.verificarValorMochila(solucao);
    	
    	ils.exibirSolucao(solucao);
    }
}
