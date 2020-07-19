
/**
 * Write a description of MatchAllFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class MatchAllFilters implements Filter{
    private ArrayList<Filter> filter;
    public MatchAllFilters(){
        filter = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f){
        filter.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe){
        for(Filter f : filter){
            if(!f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
    
    public String getName() {
        String names = "";
        
        for (Filter f : filter) {
            String filterName = f.getName() + " ";
            names += filterName;
        }
        
        return names;
    }
}
