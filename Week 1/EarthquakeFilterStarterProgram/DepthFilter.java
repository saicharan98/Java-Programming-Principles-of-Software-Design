
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter {
    private double minDep;
    private double maxDep;
    private String filename;
    public DepthFilter (double minDep, double maxDep, String fname){
        this.minDep = minDep;
        this.maxDep = maxDep;
        filename = fname;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return qe.getDepth() >= minDep && qe.getDepth() <= maxDep;
    }
    
    public String getName(){
        return filename;
    }
}
