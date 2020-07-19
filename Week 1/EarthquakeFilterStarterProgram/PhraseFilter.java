
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    private String with;
    private String phrase;
    private String filename;
    public PhraseFilter (String with, String phrase, String fname){
        this.with = with;
        this.phrase = phrase;
        filename = fname;
    }
    
    public boolean satisfies(QuakeEntry qe){
        if(with.equals("start")){
            return qe.getInfo().startsWith(phrase);
        }
        else if(with.equals("end")){
            return qe.getInfo().endsWith(phrase);
        }
        else if(with.equals("any")){
            return qe.getInfo().contains(phrase);
        }
        else{
            return false;
        }
    }
    
    public String getName(){
        return filename;
    }

}
