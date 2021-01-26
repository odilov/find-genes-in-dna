package com.duke.findgene;

public class FindSimpleGene {
    
    public String findSimpleGene(String DNA){
        // finds gene with start codon ATG and stop codon TAA
        int startIdx = DNA.indexOf( "ATG" );
        int stopIdx  = DNA.indexOf( "TAA", startIdx + 3 );
        if( startIdx != -1 && stopIdx != -1 )
            return DNA.substring( startIdx, stopIdx + 3 );
        return "no gene was found";
    }



    public void testFindSimpleGene(){

        String DNA = "AATGCGTAATATGGT";
        System.out.println( "DNA: " + DNA );

        System.out.println( "Gene: " + findSimpleGene( DNA ) );

        DNA = "AATGCTAGGGTAATATGGT";
        System.out.println( "DNA: " + DNA );

        System.out.println( "Gene: " + findSimpleGene( DNA ) );

        DNA = "ATCCTATGCTTCGGCTGCTCTAATATGGT";
        System.out.println( "DNA: " + DNA );

        System.out.println( "Gene: " + findSimpleGene( DNA ) );

        DNA = "ATGTAA";
        System.out.println( "DNA: " + DNA );

        System.out.println( "Gene: " + findSimpleGene( DNA ) );

    }

}
