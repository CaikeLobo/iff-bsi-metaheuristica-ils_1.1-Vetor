package main.java.com.mochilabinaria;

import java.util.*;

public class MetodosILS {
	private Mochila mochila;
	private int criterioDeParadaDoILS;
	private int criterioDeParadaDaBuscaLocal;
	private int tamanhoPertubação;
    private Random random = new Random();
    
    // Construtor da classe
    public MetodosILS(Mochila mochila, int criterioDeParadaDoILS, int criterioDeParadaDaBuscaLocal, int tamanhoPertubação) {
    	this.mochila = mochila;
    	this.criterioDeParadaDoILS = criterioDeParadaDoILS;
    	this.criterioDeParadaDaBuscaLocal = criterioDeParadaDaBuscaLocal;
    	this.tamanhoPertubação = tamanhoPertubação;
    }
    
	// Função de avaliação da mochila
    private int verificarMochila(int[] solucao) {
        int valorTotal = 0;
        
        for (int i = 0; i < solucao.length; i++) {
            if (solucao[i] == 1) {
                valorTotal += this.mochila.getItens()[i].valor;
            }
        }
        return valorTotal;
    }
    
    private int verificarPesoMochila(int[] solucao) {
        int PesoTotal = 0;
        
        for (int i = 0; i < solucao.length; i++) {
            if (solucao[i] == 1) {
                PesoTotal += this.mochila.getItens()[i].peso;
            }
        }
        return PesoTotal;
    }
    
    private boolean verificarValidadeMochila(int[] solucao) {
        int pesoTotal = 0;
        
        for (int i = 0; i < solucao.length; i++) 
        {
            if (solucao[i] == 1) {
                pesoTotal += this.mochila.getItens()[i].peso;
            }            
        }
        if (pesoTotal > this.mochila.getCapacidade()) {
        	//System.out.println("Busca Binaria Conseguiu!: " + novoValor + ">" + melhorValor);
        	
        	return false;
        }
        else {
        	return true;
        }
    }

    // Busca local: tenta melhorar a solução alterando um item de cada vez
    private int[] buscaLocal(int[] solucao) {
    	ArquivoUtils.adicionarTextoAoArquivo("Busca Local INICIADA");
        int[] melhorSolucao = Arrays.copyOf(solucao, solucao.length);
        int melhorValor = 0;
        if (verificarValidadeMochila(melhorSolucao) == true) {
        	melhorValor = verificarMochila(melhorSolucao);             
        }
        
        
        for (int i = 0; i < criterioDeParadaDaBuscaLocal; i++) {
            int[] novaSolucao = Arrays.copyOf(melhorSolucao, melhorSolucao.length);
            for (int o = 0; o < random.nextInt(2) + 1; o++) {
            	int indice = random.nextInt(novaSolucao.length);
                novaSolucao[indice] = 1 - novaSolucao[indice]; // Alterna o valor de 0 para 1 ou de 1 para 0            	
            }
            

            int novoValor = verificarMochila(novaSolucao);
            
			if (verificarValidadeMochila(novaSolucao) == true) {
				
            	//System.out.println("é valido?: " + novoValor + ">" + melhorValor + " "+ this.verificarPesoMochila(novaSolucao));
            	if (novoValor > melhorValor) { 
            		ArquivoUtils.adicionarTextoAoArquivo("Busca Binaria Conseguiu!: " + novoValor + ">" + melhorValor + " "+ this.verificarPesoMochila(novaSolucao));
            		//System.out.println("Busca Binaria Conseguiu!: " + novoValor + ">" + melhorValor + " "+ this.verificarPesoMochila(novaSolucao));
            		melhorSolucao = novaSolucao;
                    melhorValor = novoValor;
            	}
            	
            }
			if (verificarValidadeMochila(novaSolucao) == false) {
        		if (this.verificarPesoMochila(melhorSolucao)> this.verificarPesoMochila(novaSolucao)) {
        			ArquivoUtils.adicionarTextoAoArquivo("Busca Binaria está Chegando Perto: Peso Atual - " + this.verificarPesoMochila(novaSolucao));
        			//System.out.print("o"); 
        			melhorSolucao = novaSolucao;
                    melhorValor = novoValor;
        		}            			
        	}
        }
        if (verificarValidadeMochila(melhorSolucao) == false) {
        	ArquivoUtils.adicionarTextoAoArquivo("A MELHOR SOLUÇÃO AINDA ESTÁ ACIMA DO PESO MAXIMO" + this.verificarPesoMochila(melhorSolucao));
        }
        	return melhorSolucao;  	
    }

    // Perturbação: modifica a solução atual para escapar de ótimos locais
    private int[] perturbacao(int[] solucao) {
        int[] solucaoPerturbada = Arrays.copyOf(solucao, solucao.length);
        /*
         * Define quantos elementos da solução serão 
         * alterados (perturbados) aleatoriamente.
         * Neste caso de pelo menos um até a metade de 
         * elementos da solução
         */
//        int tamanhoPerturbacao = random.nextInt(tamanhoMaximoPertubação) + 1;

        for (int i = 0; i < this.tamanhoPertubação; i++) {
        	/*
        	 * Inverte todos os elementos na faixa do tamanho da
        	 * pertubação
        	 */
            int indice = random.nextInt(solucaoPerturbada.length);
            solucaoPerturbada[indice] = 1 - solucaoPerturbada[indice];
        }
        //System.out.println(this.verificarPesoMochila(solucaoPerturbada));
        
        return solucaoPerturbada;
    }

    // ILS: combina a busca local e a perturbação
 // ILS: combina a busca local e a perturbação
    public int[] encontrarSolucao() {
    	/*
    	 * Array com base na lista de itens que podem entrar na mochila
    	 * 0 significa que o item não esta na mochila
    	 * 1 significa que o item esta na mochila
    	 */
        int[] solucaoAtual = new int[this.mochila.getItens().length];
        int[] melhorSolucao = buscaLocal(solucaoAtual);

        /*
         * Repete a pertubação e busca local ate atender
         * o criterio de parada da ILS, nesse caso uma quantidade
         * de interações
         */
        for (int i = 0; i < criterioDeParadaDoILS; i++) {
            int[] solucaoPerturbada = perturbacao(melhorSolucao);
            int[] novaSolucao = buscaLocal(solucaoPerturbada);
            
            if((i*100/criterioDeParadaDoILS) != (i - 1)*100/criterioDeParadaDoILS) {
          	  System.out.println("Progresso: " + (i*100/criterioDeParadaDoILS) + "%");
            }

            // Criterio de aceitação
           
            if (verificarMochila(novaSolucao) > verificarMochila(melhorSolucao)) {
            	//System.out.println("Tentativa de Inserção de Melhor Solução no EC");
            	 if(this.verificarValidadeMochila(novaSolucao) == true) {
            		 System.out.println("UMA NOVA SOLUÇÃO OTIMA FOI ENCONTRADA: " + this.verificarMochila(novaSolucao));
            		 System.out.println("VALOR: " + this.verificarMochila(novaSolucao));
            		 System.out.println("PESO: " + this.verificarPesoMochila(novaSolucao));
            		 melhorSolucao = novaSolucao;
                 }
                
            }
        }

        return melhorSolucao;
    }

    // Exibe a solução final
    public final void exibirSolucao(int[] solucao) {
        int pesoTotal = 0;
        int valorTotal = 0;
        System.out.println("Itens na mochila:");
        for (int i = 0; i < solucao.length; i++) {
            if (solucao[i] == 1) {
                Item item = this.mochila.getItens()[i];
                System.out.println(item.nome + " (Peso: " + item.peso + ", Valor: " + item.valor + ")");
                pesoTotal += item.peso;
                valorTotal += item.valor;
            }
        }
        
        System.out.println();
        System.out.println("Peso: " + pesoTotal);
        System.out.println("Valor: " + valorTotal);
        System.out.println("Criterio de parada da pertubação: " + criterioDeParadaDoILS);
        System.out.println("Criterio de parada da busca local: " + criterioDeParadaDaBuscaLocal);
        System.out.println("Tamanho da pertubação: " + tamanhoPertubação);
        System.out.println("Numero de Items: " + solucao.length);
    }
}
