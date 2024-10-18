package main.java.com.mochilabinaria;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ArquivoUtils2 {

    // Método para escrever o vetor no arquivo
    public static void escreverVetorNoArquivo(int[] vetor, String nomeArquivo, int valorF) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
        	writer.write(Integer.toString(valorF));
        	writer.newLine(); // Adiciona uma nova linha
            for (int valor : vetor) {
            	System.out.print(valor + " ");
                writer.write(Integer.toString(valor) + " "); // Converte o valor para String
                //writer.newLine(); // Adiciona uma nova linha
            }
            System.out.println("Vetor escrito com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}