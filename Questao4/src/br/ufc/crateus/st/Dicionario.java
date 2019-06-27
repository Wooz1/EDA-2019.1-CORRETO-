package br.ufc.crateus.st;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Dicionario {

	public SepareteChainingHashMap<String, String> dicionarioHash() {
		try {
			File arq = new File("Dicionario.txt");
			FileReader filereader = new FileReader(arq);
			BufferedReader br = new BufferedReader(filereader);

			SepareteChainingHashMap<String, String> dic = new SepareteChainingHashMap<String, String>();
			String tmp;

			while (br.ready()) {
				tmp = br.readLine();
				dic.put(tmp, tmp);
			}

			filereader.close();
			br.close();

			return dic;
		} catch (IOException e) {
			System.out.println("Erro ao abrir o arquivo Dicionario");
		}
		return null;
	}

	public void verificaPalavra() {
		SepareteChainingHashMap<String, String> dic = dicionarioHash();
		try {
			File arq = new File("DicionarioPessoal.txt");
			int countLine = 0;
			Scanner s4 = new Scanner(arq);
			String tmp;

			while (s4.hasNext()) {
				tmp = s4.nextLine();
				if (dic.get(tmp) == null) {
					countLine++;
					System.out.println("A palavra " + tmp + " na linha " + countLine
							+ " não existe no dicionario. As opções de correção são:");
					Iterable<String> aux = addChar(tmp, dic);
					for (String s : aux) {
						System.out.println(s);
					}
					aux = removeChar(tmp, dic);
					for (String s : aux) {
						System.out.println(s);
					}
					aux = invertChar(tmp, dic);
					for (String s : aux) {
						System.out.println(s);
					}
				}
			}
			s4.close();
		} catch (FileNotFoundException e) {

			System.out.println("Erro");
		}
	}

	public Iterable<String> addChar(String tmp, SepareteChainingHashMap<String, String> dic) {

		Queue<String> fila = new LinkedList<String>();
		for (char i = 0; i < 256; i++) {
			for (int j = 0; j < tmp.length(); j++) {
				String sub1 = tmp.substring(0, j);
				String sub2 = tmp.substring(j, tmp.length());
				String nova = sub1 + i + sub2;

				if (dic.get(nova) != null) {
					fila.add(nova);
				}
			}
		}
		return fila;
	}

	public Iterable<String> removeChar(String tmp, SepareteChainingHashMap<String, String> dic) {

		Queue<String> fila = new LinkedList<String>();
		for (int j = 0; j < tmp.length(); j++) {
			String sub1 = tmp.substring(0, j);
			String sub2 = tmp.substring(j + 1, tmp.length());
			String nova = sub1 + sub2;

			if (dic.get(nova) != null) {
				fila.add(nova);
			}
		}
		return fila;
	}

	public Iterable<String> invertChar(String tmp, SepareteChainingHashMap<String, String> dic) {

		Queue<String> fila = new LinkedList<String>();
		for (int j = 0; j < tmp.length() - 1; j++) {

			char aux = tmp.charAt(j);
			char aux2 = tmp.charAt(j + 1);
			String sub1 = tmp.substring(0, j);
			String sub2 = tmp.substring(j + 2, tmp.length());
			String nova = sub1 + aux2 + aux + sub2;

			if (dic.get(nova) != null) {
				fila.add(nova);
			}
		}
		return fila;
	}

}
