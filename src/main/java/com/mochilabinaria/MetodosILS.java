package main.java.com.mochilabinaria;

import java.util.*;
//Caike,Ivanilso,Matheus,Pedro 
public class MetodosILS {
	private Mochila mochila;
	private int criterioDeParadaDoILS;
	private int criterioDeParadaDaBuscaLocal;
	private float tamanhoPertubação;
    private Random random = new Random();
    
    // Construtor da classe
    public MetodosILS(Mochila mochila, int criterioDeParadaDoILS, int criterioDeParadaDaBuscaLocal, float tamanhoPertubação) {
    	this.mochila = mochila;
    	this.criterioDeParadaDoILS = criterioDeParadaDoILS;
    	this.criterioDeParadaDaBuscaLocal = criterioDeParadaDaBuscaLocal;
    	this.tamanhoPertubação = tamanhoPertubação;
    }
    
    private int verificarNumeroDeItens(int[] solucao) {
        int Total = 0;
        
        for (int i = 0; i < solucao.length; i++) {
            if (solucao[i] == 1) {
                Total ++;
            }
        }
        return Total;
    }
     
    
	// Função de avaliação da mochila
    public int verificarValorMochila(int[] solucao) {
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
        	
        	return false;
        }
        else {
        	return true;
        }
    }
    
    private int[] encherVetor (int[] solucao) {
    	
    	
    	System.out.println("ENCHER VETOR INICIADO, AGUARDE");
    	int[] melhorSolucao = Arrays.copyOf(solucao, solucao.length);
        int melhorValor = 0;
        int numeroDeErros = 0;
        boolean ocorreuErro = true;
        int NumeroDeVezesEV = 0;
        if (verificarValidadeMochila(melhorSolucao) == true) {
        	melhorValor = verificarValorMochila(melhorSolucao);             
        }
        
        
        while (numeroDeErros <= criterioDeParadaDaBuscaLocal) {
        	NumeroDeVezesEV++;
        	ocorreuErro = true;
            int[] novaSolucao = Arrays.copyOf(melhorSolucao, melhorSolucao.length);
            
            
                                  
                              
            for (int o = 0; o < random.nextInt(2) + 1; o++) {
            	int indice = random.nextInt(novaSolucao.length);
                novaSolucao[indice] = 1 - novaSolucao[indice]; // Alterna o valor de 0 para 1 ou de 1 para 0            	
            }
            

            int novoValor = verificarValorMochila(novaSolucao);
            
			if (verificarValidadeMochila(novaSolucao) == true) {
				
            	if (novoValor > melhorValor) { 
            		ArquivoUtils.adicionarTextoAoArquivo("EV De Numero: " + NumeroDeVezesEV +  " Conseguiu!: " + novoValor + ">" + melhorValor + " "+ this.verificarPesoMochila(novaSolucao));
            		melhorSolucao = novaSolucao;
                    melhorValor = novoValor;
                    ocorreuErro = false;
                    numeroDeErros = 0;
            	}
            	
            }
			if (verificarValidadeMochila(novaSolucao) == false) {
        		if (this.verificarPesoMochila(melhorSolucao)> this.verificarPesoMochila(novaSolucao)) {
        			ArquivoUtils.adicionarTextoAoArquivo("BUSCA LOCAL está Chegando Perto: Peso Atual - " + this.verificarPesoMochila(novaSolucao)); 
        			melhorSolucao = novaSolucao;
                    melhorValor = novoValor;
                    ocorreuErro = false;
                    numeroDeErros = 0;
        		}            			
        	}
			
			if (ocorreuErro == true) {
				numeroDeErros++;				
			}

			
        }
        if (verificarValidadeMochila(melhorSolucao) == false) {
        	ArquivoUtils.adicionarTextoAoArquivo("A MELHOR SOLUÇÃO AINDA ESTÁ ACIMA DO PESO MAXIMO" + this.verificarPesoMochila(melhorSolucao));
        }
        ArquivoUtils.adicionarTextoAoArquivo("INICIALIZAÇÃO DO VETOR FINALIZADA " + this.verificarPesoMochila(melhorSolucao));
        System.out.println("ENCHER VETOR FINALIZADO, INICIANDO ILS");
        	return melhorSolucao;  	
    }
    	
    	
    

    // Busca local: tenta melhorar a solução alterando um item de cada vez
    private int[] buscaLocal(int[] solucao) {

    	ArquivoUtils.adicionarTextoAoArquivo("Busca Local INICIADA");
        int[] melhorSolucao = Arrays.copyOf(solucao, solucao.length);
        int melhorValor = 0;
        int numeroDeErros = 0;
        int NumeroDeVezes = 0;
        boolean ocorreuErro = true;
        boolean Achou = false;
        if (verificarValidadeMochila(melhorSolucao) == true) {
        	melhorValor = verificarValorMochila(melhorSolucao);             
        }
        
        
        while (numeroDeErros <= criterioDeParadaDaBuscaLocal) { // O codigo só vai parar quando a quantidade de erros chegar até o criterioDeParadaDaBuscaLocal
        	NumeroDeVezes++;
        	ocorreuErro = true;
            int[] novaSolucao = Arrays.copyOf(melhorSolucao, melhorSolucao.length);
            
            
            int Decisao = random.nextInt(3) + 1;
            
			switch (Decisao) {
            case 1: // Adicionar 1 Item

            	while (Achou == false) {
            		int indice = random.nextInt(novaSolucao.length);
            		if (solucao[indice] == 0) {
            			novaSolucao[indice] = 1 - novaSolucao[indice]; // Alterna o valor de 0 para 1 ou de 1 para 0 
            			Achou = true;
            		}                            		
            	}
            	Achou = false;
                break;
            case 2: // Adicionar 1 e Retirar 1 item


            	while (Achou == false) {
            		//System.out.println("Case 2 1");
            		int indice = random.nextInt(novaSolucao.length);
            		if (solucao[indice] == 0) {
            			novaSolucao[indice] = 1 - novaSolucao[indice]; // Alterna o valor de 0 para 1 ou de 1 para 0 
            			Achou = true;
            		}                            		
            	}
            	Achou = false;
            	while (Achou == false) {
            		//System.out.println("Case 2 2");
            		int indice2 = random.nextInt(novaSolucao.length);
            		//System.out.println("INDICE = " + solucao[indice2] );
            		if (solucao[indice2] == 1) {
            			//System.out.println("Case 2 3");
            			novaSolucao[indice2] = 1 - novaSolucao[indice2]; // Alterna o valor de 0 para 1 ou de 1 para 0 
            			Achou = true;
            		}                            		
            	}
            	Achou = false;
                break;
            case 3: // Adicionar 2 e Retirar 1 item
            	for (int i = 0; i < 2; i++) {
            		Achou= false;
            		while (Achou == false) {
            		//System.out.println("Case 2 1");
            			int indice = random.nextInt(novaSolucao.length);
            			if (solucao[indice] == 0) {
            				novaSolucao[indice] = 1 - novaSolucao[indice]; // Alterna o valor de 0 para 1 ou de 1 para 0 
            				Achou = true;
            			}                            		
            		}
            	}
            	Achou = false;
            	while (Achou == false) {
            		//System.out.println("Case 2 2");
            		int indice2 = random.nextInt(novaSolucao.length);
            		//System.out.println("INDICE = " + solucao[indice2] );
            		if (solucao[indice2] == 1) {
            			//System.out.println("Case 2 3");
            			novaSolucao[indice2] = 1 - novaSolucao[indice2]; // Alterna o valor de 0 para 1 ou de 1 para 0 
            			Achou = true;
            		}                            		
            	}
            	Achou = false;
                break; 
            default:
                System.out.println("Opção inválida!");
                  }
                           
            int novoValor = verificarValorMochila(novaSolucao);
            
			if (verificarValidadeMochila(novaSolucao) == true) {
				
            	if (novoValor > melhorValor) { 
            		ArquivoUtils.adicionarTextoAoArquivo("BUSCA LOCAL De Numero: " + NumeroDeVezes + " Case "+ Decisao +  " Conseguiu!: " + novoValor + ">" + melhorValor + " "+ this.verificarPesoMochila(novaSolucao));
            		melhorSolucao = novaSolucao;
                    melhorValor = novoValor;
                    ocorreuErro = false;
                    numeroDeErros = 0;
            	}
            	
            }
			if (verificarValidadeMochila(novaSolucao) == false) {
        		if (this.verificarPesoMochila(melhorSolucao)> this.verificarPesoMochila(novaSolucao)) {
        			ArquivoUtils.adicionarTextoAoArquivo("BUSCA LOCAL está Chegando Perto: Peso Atual - " + this.verificarPesoMochila(novaSolucao)); 
        			melhorSolucao = novaSolucao;
                    melhorValor = novoValor;
                    ocorreuErro = false;
                    numeroDeErros = 0;
        		}            			
        	}
			
			if (ocorreuErro == true) {
				numeroDeErros++;				
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

        int tamanhoPerturbacao = random.nextInt((int) Math.round(this.verificarNumeroDeItens(solucaoPerturbada) * (tamanhoPertubação /100) )) + 1;
        ArquivoUtils.adicionarTextoAoArquivo("PERTUBAÇAO OCORREU: TAMANHO- " + tamanhoPerturbacao);
        //System.out.println("PERTUBAÇÃO ATUAL: " + tamanhoPerturbacao);
        int i = 0;
        while (i < tamanhoPerturbacao) {

        	int indice = random.nextInt(solucaoPerturbada.length);
        	if (solucaoPerturbada[indice] == 1) {
        		solucaoPerturbada[indice] = 0;
        		i++;
        	}
            
            

        }

        
        return solucaoPerturbada;
    }


 // ILS: combina a busca local e a perturbação
    public int[] encontrarSolucao() {
    	/*
    	 * Array com base na lista de itens que podem entrar na mochila
    	 * 0 significa que o item não esta na mochila
    	 * 1 significa que o item esta na mochila
    	 */
        int[] solucaoAtual = new int[this.mochila.getItens().length];
        int[] melhorSolucao = encherVetor(solucaoAtual);

        /*
         * Repete a pertubação e busca local ate atender
         * o criterio de parada da ILS, nesse caso uma quantidade
         * de interações
         */
        for (int i = 0; i < criterioDeParadaDoILS; i++) {
            int[] solucaoPerturbada = perturbacao(melhorSolucao);
            int[] novaSolucao = buscaLocal(solucaoPerturbada);
            
            if((i*100/criterioDeParadaDoILS) != (i - 1)*100/criterioDeParadaDoILS) {
            	ArquivoUtils.adicionarTextoAoArquivo("PROGRESSO: " + (i*100/criterioDeParadaDoILS) + "%");
          	  System.out.println("Progresso: " + (i*100/criterioDeParadaDoILS) + "%");
            }

            // Criterio de aceitação
           
            if (verificarValorMochila(novaSolucao) > verificarValorMochila(melhorSolucao)) {
            	//System.out.println("Tentativa de Inserção de Melhor Solução no EC");
            	 if(this.verificarValidadeMochila(novaSolucao) == true) {
            		 ArquivoUtils.adicionarTextoAoArquivo("MELHOR SOLUÇÃO FOI ENCONTRADA! " + this.verificarValorMochila(novaSolucao));
            		 System.out.println("UMA NOVA SOLUÇÃO OTIMA FOI ENCONTRADA: " + this.verificarValorMochila(novaSolucao));
            		 System.out.println("VALOR: " + this.verificarValorMochila(novaSolucao));
            		 System.out.println("PESO: " + this.verificarPesoMochila(novaSolucao));
            		 
            		 melhorSolucao = novaSolucao;
                 }
                
            }
            else {
            	ArquivoUtils.adicionarTextoAoArquivo("MELHOR SOLUCAO NAO FOI ENCONTRADA " + this.verificarValorMochila(melhorSolucao));
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
        System.out.println("Numero de Itens Na Mochila: " + this.verificarNumeroDeItens(solucao));
        System.out.println("Peso: " + pesoTotal);
        System.out.println("Valor: " + valorTotal);
        System.out.println("Criterio de parada da pertubação: " + criterioDeParadaDoILS);
        System.out.println("Criterio de parada da busca local: " + criterioDeParadaDaBuscaLocal);
        System.out.println("Tamanho da pertubação: " + tamanhoPertubação + "%");
        System.out.println("Numero de Items Totais: " + solucao.length );
    }
}