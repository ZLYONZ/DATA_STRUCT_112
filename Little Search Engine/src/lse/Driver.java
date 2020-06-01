package lse;

//package search;

import java.io.*;

public class Driver {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
     // TODO Auto-generated method stub
    
     System.out.println("Enter doument title ");
     String docs = br.readLine();
     System.out.println("Enter noise words file ");
     String noise = br.readLine();
     LittleSearchEngine lol = new LittleSearchEngine();
     lol.makeIndex(docs, noise);
     System.out.println("Enter first search word ");
     String first = br.readLine();
     System.out.println("Enter second search world ");
     String second = br.readLine();
     System.out.println(lol.top5search(first, second));
    }

}

