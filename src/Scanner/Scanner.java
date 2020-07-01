package Scanner;

import java.util.LinkedList;
import java.util.StringTokenizer;

public class Scanner {
    
    LinkedList<LinkedList<String>> tokensList = new LinkedList<LinkedList<String>>();
    StringTokenizer t;
    
    public void tokenizer(String cad){
        LinkedList<String> l = new LinkedList<String>();
        t = new StringTokenizer(cad);
        while(t.hasMoreTokens()){
            l.add(t.nextToken());
        }
        tokensList.add(l);
    }
    
    public LinkedList<LinkedList<String>> getTokensList(){
        return tokensList;
    }    
}
