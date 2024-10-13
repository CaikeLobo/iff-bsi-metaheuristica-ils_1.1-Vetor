package main.java.com.mochilabinaria;

import java.io.FileWriter;
import java.io.IOException;

public class ArquivoUtils {
    
	 public static void adicionarTextoAoArquivo(String texto) {
	        // Especifica o caminho do arquivo
	        String caminhoArquivo = "C:\\Users\\caike\\Downloads\\iff-bsi-mochila-binaria-master\\src\\Output.txt";
	        
	        // Tenta abrir o arquivo no modo de anexar (append)
	        try (FileWriter escritor = new FileWriter(caminhoArquivo, true)) {
	            escritor.write(texto + System.lineSeparator()); // Adiciona a nova linha ao final do arquivo
	        } catch (IOException e) {
	            e.printStackTrace(); // Lida com possíveis exceções de I/O
	        }
	    }
	 // Método para apagar todo o conteúdo do arquivo
	    public static void apagarArquivo() {
	        // Especifica o caminho do arquivo
	        String caminhoArquivo = "C:\\Users\\caike\\Downloads\\iff-bsi-mochila-binaria-master\\src\\Output.txt";
	        
	        // Tenta abrir o arquivo no modo de sobrescrever (append = false)
	        try (FileWriter escritor = new FileWriter(caminhoArquivo, false)) {
	            // Não escrevemos nada, apenas abrimos o arquivo no modo de sobrescrever, o que apaga todo o conteúdo
	        } catch (IOException e) {
	            e.printStackTrace(); // Lida com possíveis exceções de I/O
	        }
	    }
}
