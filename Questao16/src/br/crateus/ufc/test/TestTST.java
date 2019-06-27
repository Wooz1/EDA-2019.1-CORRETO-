package br.crateus.ufc.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import br.ufc.crateus.st.TST;

public class TestTST {

	public static void main(String[] args) throws IOException {
		TST<Integer> trie = new TST<Integer>();
		InputStream in = new FileInputStream("filmes.txt");
		Scanner scanner = new Scanner(in);
		while(scanner.hasNextLine())
		{
			String s = scanner.nextLine();
			String [] vet = s.split("/", 2);
			String [] palavras = vet[0].split(" ");
			String name = "";
			boolean x = true;
			for(int i = 0; i < palavras.length-1;i++) {
				if(x) {
					name = palavras[i];
					x = false;
					continue;
				}
				name = name + " " + palavras[i];
			}
			name = converte(name);
			trie.put(name, 12);
		}
		scanner.close();
		scanner = new Scanner(System.in);
		
		while(true) {
		System.out.print("Procure por um filme: ");
			String filme = scanner.nextLine();		
			System.out.println("Sugestoes: ");
			for(String i : trie.q16(filme))
				System.out.println(i);
		}		
	}
//Existe um arquivo que foi baixado na internet chamado filmes, primeiramente � passado para dentro da TST todos os filmes 
//existentes l� para uma TST,entretanto os arquivos inseridos s�o convertidos de acordo com a fun��o convert.Ap�s isso � s�
//pedir	para a pessoa digitar o nome do filme que desaja buscar e ser� entregue as sugest�es. A fun��o est� funcionando
//corretamente pois ele retorna todas as palavras que existem no arquivo filmes que possuem o prefixo digitado.

	public static String converte(String str) {
		str = str.toLowerCase();
		char[] filme = str.toCharArray();
		for (int i = 0; i < str.length(); i++) {
			if ((filme[i] >= 'a' && filme[i] <= 'z') || filme[i] == ' ' || (filme[i] >= '0' && filme[i] <= '9'))
				continue;
			else if (filme[i] == '�')
				filme[i] = 'c';
			else if (filme[i] == '�')
				filme[i] = 'n';
			else if (filme[i] == '�' || filme[i] == '�' || filme[i] == '�' || filme[i] == '�' || filme[i] == '�'
					|| filme[i] == 230 || (filme[i] >= '�' && filme[i] <= '�'))
				filme[i] = 'u';
			else if (filme[i] == '�' || (filme[i] >= '�' && filme[i] <= '�'))
				filme[i] = 'e';
			else if ((filme[i] == '�' && filme[i] <= 134) || filme[i] == 142 || filme[i] == 143 || filme[i] == '�'
					|| filme[i] == 166 || filme[i] == '�')
				filme[i] = 'a';
			else if ((filme[i] == '�' && filme[i] <= 141) || filme[i] == '�')
				filme[i] = 'i';
			else if ((filme[i] == '�' && filme[i] <= '�') || filme[i] == '�' || filme[i] == '�' || filme[i] == 167
					|| filme[i] == 208)
				filme[i] = 'o';
			else if (filme[i] == '�' || filme[i] == '�')
				filme[i] = 'y';
			else if (filme[i] == 158)
				filme[i] = 'x';
			else if (filme[i] == 159)
				filme[i] = 'f';
			else if (filme[i] == 169)
				filme[i] = 'r';
			else if (filme[i] == 184 || filme[i] == 189)
				filme[i] = 'c';
			else if (filme[i] == 251)
				filme[i] = '1';
			else if (filme[i] == 252)
				filme[i] = '2';
			else if (filme[i] == 253)
				filme[i] = '3';
			else
				filme[i] = '?';
		}
		String str2 = "";
		for (int i = 0; i < str.length(); i++) {
			str2 += filme[i];
		}
		return str2;
	}
}