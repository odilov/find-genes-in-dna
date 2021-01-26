package com.duke.findgene;

public class FindAllGenes {

    public int findStopCodon( String DNA, int StartIdx, String stopCodon ){
        // finds stop codon
        int currIdx = DNA.indexOf( stopCodon, StartIdx + 3 );

        while( currIdx != -1 ){
            if( ( currIdx - StartIdx ) % 3 == 0 )
                return currIdx;
            currIdx = DNA.indexOf( stopCodon, currIdx + 1 );
        }

        return -1;

    }

    public String findGene( String DNA, int where ){

        int startIdx = DNA.indexOf( "ATG", where );

        if( startIdx == -1 )
            return "";

        int taaIdx = findStopCodon( DNA, startIdx, "TAA" );
        int tagIdx = findStopCodon( DNA, startIdx, "TAG" );
        int tgaIdx = findStopCodon( DNA, startIdx, "TGA" );


        int minIdx = 0;

        if( taaIdx == -1 || ( tgaIdx != -1 && tgaIdx < taaIdx ) )
            minIdx = tgaIdx;
        else
            minIdx = taaIdx;

        if( minIdx == -1 || ( tgaIdx != -1 && tagIdx < minIdx ) )
            minIdx = tagIdx;

        if( minIdx == -1 )
            return "";

        return DNA.substring( startIdx, minIdx + 3 );

    }

    public double getCGRatio( String DNA ){

        int cnt = 0;

        for(int i = 0; i < DNA.length(); ++i){
            char c = DNA.charAt( i );
            if( c == 'C' || c == 'G' )
                ++cnt;
        }

        return (double)cnt/DNA.length();

    }

    public void getAllGenes( String DNA ){

        int startIdx = 0;

        while( true ){
            String currGene = findGene( DNA, startIdx );
            if( currGene.isEmpty() )
                break;

            System.out.println( currGene );
            startIdx = DNA.indexOf( currGene, startIdx ) + currGene.length();
        }

    }

    public void testFindStopCodon(){
        String DNA = "xxxzzzyyyTAAxxxzzzyyyTAAxx";
        if( findStopCodon( DNA, 0, "TAA" ) != 9 )
            System.out.println( "Test 1 failed" );

        if( findStopCodon( DNA, 9, "TAA" ) != 21 )
            System.out.println( "Test 2 failed" );

        if( findStopCodon( DNA, 1, "TAA" ) != -1 )
            System.out.println( "Test 3 failed" );

        if( findStopCodon( DNA, 0, "TAG" ) != -1 )
            System.out.println( "Test 4 failed" );

        System.out.println( "tests finished" );

    }

    public void testFindGene(){
        String DNA = "ATGCCCGGGAAATAACCC";
        String gene = findGene( DNA , 0 );

        if( ! gene.equals( "ATGCCCGGGAAATAA" ) )
            System.out.println( "Test 5 failed" );

        System.out.println( "test finished" );
    }

}
