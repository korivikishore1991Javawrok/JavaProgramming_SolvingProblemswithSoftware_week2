
import edu.duke.*;

public class proteinfinder {
public int findstopindex(String dna, int index){
    int stop1 = dna.indexOf("tga", index);
    int stop2 = dna.indexOf("taa", index);
    int stop3 = dna.indexOf("tag", index);
    if (stop1 == -1 || (stop1-index)%3 != 0){
        stop1 = dna.length();
    }
    if (stop2 == -1 || (stop2-index)%3 != 0){
        stop2 = dna.length();
    }
    if (stop3 == -1 || (stop3-index)%3 != 0){
        stop3 = dna.length();
    }
    int stop = Math.min(stop1, Math.min(stop2, stop3));
    return stop;
}



public StorageResource printallstarts(String dna){
    int start = 0;
    StorageResource store = new StorageResource();
    while(true){
        int loc = dna.toLowerCase().indexOf("atg", start);
        if (loc == -1){
            break;
        }
        //System.out.println("Starts at " +loc);
       int stop = findstopindex(dna.toLowerCase(), loc+3);
        if (stop != -1 && stop != dna.length()){
            //System.out.println(dna.substring(loc, stop+3));
            store.add(dna.substring(loc, stop+3));
            start = stop+3;
        }
        else{
            start = start+3;
        }
    }
    return store;
}

 public float cgRatio(String dna) {

        dna = dna.toLowerCase();

        int dnaLen = dna.length();
        int gCount = countLetterInWord('g', dna);
        int cCount = countLetterInWord('c', dna);
        return (float) (gCount + cCount) / dnaLen;

    }


public int countLetterInWord(char letter, String word) {

        int counter = 0;

        for (int i = 0; i < word.length(); i++) {

            if (word.charAt(i) == letter) {
                counter++;
            }

        }

        return counter;

    }

    

public void printGenes(StorageResource sr){
    int i = 0;
    int j = 0;
    for (String gene : sr.data()) {
        if (gene.length() > 60){
            System.out.println("Strings that are longer than 60 characters: "+gene);
            i = i+1;
        }
        
        if (cgRatio(gene) > 0.35){
           System.out.println("Strings whose C-G-ratio is higher than 0.35: "+gene);
           j = j+1;
        }        
	}
    System.out.println("number of Strings that are longer than 60 characters: "+i);
    System.out.println("number of Strings whose C-G-ratio is higher than 0.35: "+j);
}


public int countCTGCodon(String dna) {
        dna = dna.toLowerCase();
        int start = 0;
        int count = 0;
        while(true){
        int loc = dna.indexOf("ctg", start);
        if (loc == -1){
            break;
        }
        count = count + 1;
        start = start + 2;
        }
        return count;
    }
    
 public int longest_gene(StorageResource sr){
     int maxi = 0;
     for (String gene : sr.data()) {
         if (gene.length()>= maxi){
             maxi = gene.length();
         }
     }
     return maxi;
 }
    
public void testfinder(){
    
    FileResource fr = new FileResource("GRch38dnapart.txt");
    String DNA = fr.asString();
    int CTGCount = countCTGCodon(DNA);
    StorageResource sr = printallstarts(DNA);
    int largest_gene = longest_gene(sr);
    printGenes(sr);
    System.out.println("largest gene length: "+largest_gene);
    System.out.println("size: "+sr.size());
    System.out.println("CountCTG: "+CTGCount);
}
}