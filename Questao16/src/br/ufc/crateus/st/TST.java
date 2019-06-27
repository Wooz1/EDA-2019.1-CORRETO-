package br.ufc.crateus.st;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class TST<V>{
	private class Node {
		V value;
		char ch;
		Node left;
		Node middle;
		Node right;

		Node(char ch) {
			this.ch = ch;
		}
	}

	private Node root = null;

	private Node put(Node r, String key, V value, int h) {
		char ch = key.charAt(h);
		if (r == null)
			r = new Node(ch);
		if (ch < r.ch)
			r.left = put(r.left, key, value, h);
		else if (ch > r.ch)
			r.right = put(r.right, key, value, h);
		else {
			if (h == key.length() - 1) {
				r.value = value;
				return r;
			}
			r.middle = put(r.middle, key, value, h + 1);
		}
		return r;
	}

	private Node get(Node r, String key, int h) {
		if (r == null)
			return r;
		char ch = key.charAt(h);
		if (ch < r.ch)
			return get(r.left, key, h);
		else if (ch > r.ch)
			return get(r.right, key, h);
		else {
			if (h == key.length() - 1)
				return r;
			else
				return get(r.middle, key, h + 1);
		}
	}

	public void put(String key, V value) {
		root = put(root, key, value, 0);
	}

	public V get(String key) {
		Node r = get(root, key, 0);
		return r == null ? null : r.value;
	}

	private void collect(Queue<String> keys, Node r, String s) {
		if (r != null) {
			collect(keys, r.left, s);
			if (r.value != null)
				keys.add(s + r.ch);
			collect(keys, r.middle, s + r.ch);
			collect(keys, r.right, s);
		}
	}

	public Iterable<String> keys() {
		Queue<String> keys = new LinkedList<String>();
		collect(keys, root, "");
		return keys;
	}

	public Iterable<String> keysWithPrefix(String prefix) {
		Node r = get(root, prefix, 0);
		Queue<String> keys = new LinkedList<String>();
		if(r != null)
			collect(keys, r.middle, prefix);
		return keys;
	}

	private int search(Node r, String s, int h, int lenght) {
		if (r == null)
			return lenght;
		if (s.length() == h)
			return lenght;
		char ch = s.charAt(h);
		if (ch < r.ch)
			return search(r.left, s, h, lenght);
		else if (ch > r.ch)
			return search(r.right, s, h, lenght);
		else {
			if (r.value != null)
				lenght = h;
			return search(r.middle, s, h + 1, lenght);
		}

	}

	
	public String longestPrefixOf(String str) {
		int lenght = search(root, str, 0, -1);
		if (lenght == -1)
			return "";
		return str.substring(0, ++lenght);
	}

	
	public boolean contains(String key) {
		return get(key) != null;
	}

	private void searchKeysThatMatch(Node r, String str, Queue<String> keys, int h, String acc) {
		if (r != null && str.length() != h) {
			char ch = str.charAt(h);
			if (ch == r.ch || ch == '.') {
				String accM = acc + r.ch;
				searchKeysThatMatch(r.left, str, keys, h, acc);
				if (r.value != null && str.length() == h+1)
					keys.add(accM);
				searchKeysThatMatch(r.middle, str, keys, h + 1, accM);
				searchKeysThatMatch(r.right, str, keys, h, acc);
			} else if (ch > r.ch) {
				searchKeysThatMatch(r.right, str, keys, h, acc);
			} else {
				searchKeysThatMatch(r.left, str, keys, h, acc);
			}
		}
	}
	public Iterable<String> keysThatMatch(String str) {
		Queue<String> keys = new LinkedList<String>();
		searchKeysThatMatch(root, str, keys, 0, "");
		return keys;
	}
	public Iterable<String> q16(String query){
		Node r = get(root, query, 0);
		Queue<String> keysPrefixOf = new LinkedList<String>();
		if(r != null)
			collect(keysPrefixOf, r.middle, query);
		
		String logestPrefixOf = longestPrefixOf(query);
		
		Queue<String> keysThatMatchOf = new LinkedList<String>();
		searchKeysThatMatch(root, query, keysThatMatchOf, 0, "");
		
		Set<String> keysSearchMovie = new TreeSet<>(keysPrefixOf);
		keysSearchMovie.addAll(keysThatMatchOf);
		keysSearchMovie.add(logestPrefixOf);
		
		return keysSearchMovie;
	}
}