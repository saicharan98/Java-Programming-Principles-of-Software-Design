
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class LargestQuakes {
    public ArrayList<QuakeEntry> getLargestMag(ArrayList<QuakeEntry> quakeData,
    int howMany){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        while(answer.size() < howMany || copy.size() == 0){
            int index = indexOfLargest(copy);
            answer.add(copy.get(index));
            copy.remove(index);
        }
        return answer;
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> quakeData){
        int indexMax = 0;
        double maxMag = 0.0;
        for(QuakeEntry qe  :quakeData){
            if(maxMag < qe.getMagnitude()){
                maxMag = qe.getMagnitude();
                indexMax = quakeData.indexOf(qe);
            }
        }
        return indexMax;
    }
        
    public void findLargestQuakes(){
    EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        ArrayList<QuakeEntry> fanswer = getLargestMag(list, 5);
        for(QuakeEntry qe : fanswer){
            System.out.println(qe);
        }
        System.out.println(fanswer.size());
        
    }
}
