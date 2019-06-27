package br.ufc.crateus.test;

import java.util.Random;

import br.ufc.crateus.st.SepareteChainingHashMap;

public class questao10 {

	public static void main(String[] args) {
		Random gerador = new Random();

		
		double n = Math.pow(10, 3);
		SepareteChainingHashMap<Integer, Integer> lista3 = new SepareteChainingHashMap<Integer, Integer>(Math.pow(10, 3)/100);
		SepareteChainingHashMap<Integer, Integer> lista4= new SepareteChainingHashMap<Integer, Integer>(Math.pow(10, 4)/100);
		SepareteChainingHashMap<Integer, Integer> lista5 = new SepareteChainingHashMap<Integer, Integer>(Math.pow(10, 5)/100);
		SepareteChainingHashMap<Integer, Integer> lista6 = new SepareteChainingHashMap<Integer, Integer>(Math.pow(10, 6)/100);

		
		for(int i=0;i<Math.pow(10, 3);i++) {
			lista3.put(gerador.nextInt(), 0);
		}
		for(int i=0;i<Math.pow(10, 4);i++) {
			lista4.put(gerador.nextInt(), 0);
		}
		for(int i=0;i<Math.pow(10, 5);i++) {
			lista5.put(gerador.nextInt(), 0);
		}
		for(int i=0;i<Math.pow(10, 6);i++) {
			lista6.put(gerador.nextInt(), 0);
		}
		
		System.out.print("Tamanho da lista de 10^3: ");
		int tam[] = lista3.getLength();
		System.out.println("Maior "+tam[0] +" Menor "+tam[1] + " Media " + ((double)(tam[0] + tam[1])/2));
				
		System.out.print("Tamanho da lista de 10^4: ");
		tam = lista4.getLength();
		System.out.println("Maior "+tam[0] +" Menor "+tam[1] + " Media " + ((double)(tam[0] + tam[1])/2));
		
		System.out.print("Tamanho da lista de 10^5: ");
		tam = lista5.getLength();
		System.out.println("Maior "+tam[0] +" Menor "+tam[1] + " Media " + ((double)(tam[0] + tam[1])/2));
		
		System.out.print("Tamanho da lista de 10^6: ");
		tam = lista6.getLength();
		System.out.println("Maior "+tam[0] +" Menor "+tam[1] + " Media " + ((double)(tam[0] + tam[1])/2));

		
		
		
		
		//Primeiramente é preciso retirar a linha de código do resize no método put para que o hash não fique mudando de tamanho. Assim, como estamos dividindo o N por 100,
		//teremos o N/100 celulas de vetores, e em cada uma teremos em média 100 células de listas encadeadas. Ao tirarmos a média entre a maior e o menor lista o número
		//se ficará bem próximo de 100, ou seja o numero esperado.
		
	}

}
