
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class MagnitudeFilter implements Filter{
    private double minMag;
    private double maxMag;
    private String filename;
    public MagnitudeFilter(double min, double max, String n){
        minMag = min;
        maxMag = max;
        filename = n;
    }
    public boolean satisfies(QuakeEntry qe){
        return qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag;
    }
    
    public String getName(){
        return filename;
    }
}