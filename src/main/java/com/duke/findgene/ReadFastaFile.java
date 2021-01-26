package com.duke.findgene;

import java.util.Scanner;
import java.io.*;

public class ReadFastaFile {

    public static void main(String[] args) throws FileNotFoundException {
        boolean first = true;
        String dna = "";
        try (Scanner sc = new Scanner(new File("path/to/FastaFile"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.charAt(0) == '>') {
                    if (first)
                        first = false;
                    dna = dna.concat( line.substring(1) );
                }
                else
                    dna = dna.concat( line );
            }
        }
        FindAllGenes genes = new FindAllGenes();
        genes.getAllGenes( dna );
    }

}
