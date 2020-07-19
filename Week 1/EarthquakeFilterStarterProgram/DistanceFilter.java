
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private double maxDist;
    private Location loc;
    private String filename;
    public DistanceFilter (double maxDist,Location l, String fname){
        this.maxDist = maxDist;
        loc = l;
        filename = fname;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return qe.getLocation().distanceTo(loc)/1000 < maxDist;
    }
    
    public String getName(){
        return filename;
    }

}
