package main.java.com.mochilabinaria;

import java.io.FileWriter;
import java.io.IOException;
//Caike,Ivanilso,Matheus,Pedro 
public class ArquivoUtils {
    
	 public static void adicionarTextoAoArquivo(String texto) {
	        // Especifica o caminho do arquivo
	        String caminhoArquivo = "C:\\Users\\caike\\Downloads\\Output.txt"; // ALTERAR PARA ALGUM DIRETORIO
	        
	        // Tenta abrir o arquivo no modo de anexar (append)
	        try (FileWriter escritor = new FileWriter(caminhoArquivo, true)) {
	            escritor.write(texto + System.lineSeparator()); 
	        } catch (IOException e) {
	            e.printStackTrace(); 
	        }
	    }
	 // Método para apagar todo o conteúdo do arquivo
	    public static void apagarArquivo() {
	        // Especifica o caminho do arquivo
	        String caminhoArquivo = "C:\\Users\\caike\\Downloads\\Output.txt"; // MESMA COISA AQUI
	        
	        // Tenta abrir o arquivo no modo de sobrescrever (append = false)
	        try (FileWriter escritor = new FileWriter(caminhoArquivo, false)) {
	    
	        } catch (IOException e) {

	        }
	    } 
}
