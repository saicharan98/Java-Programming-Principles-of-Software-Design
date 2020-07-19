import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> copy = list;

        /*Filter f = new MinMagFilter(4.0); 
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        }*/
        /*Filter magnitudeOf = new MagnitudeFilter(4.0, 5.0,"magnitude");
        ArrayList<QuakeEntry> qem = filter(copy, magnitudeOf);
        Filter depthOf = new DepthFilter(-35000.0, -12000.0,"depth");
        ArrayList<QuakeEntry> qed = filter(qem, depthOf);
        for (QuakeEntry qe: qed) { 
            System.out.println(qe);
        }*/
        Location l = new Location(39.7392, -104.9903);
        //Filter magnitudeOf = new MagnitudeFilter(0.0, 5.0,"magnitude");
        //ArrayList<QuakeEntry> qem = filter(copy, magnitudeOf);
        Filter distanceOf = new DistanceFilter(1000000.0, l,"dist");
        ArrayList<QuakeEntry> qedis = filter(copy, distanceOf);
        Filter phraseOf = new PhraseFilter("end", "a","phrase");
        ArrayList<QuakeEntry> qep = filter(qedis, phraseOf);
        for (QuakeEntry qe: qep) { 
            System.out.println(qe);
        }
        System.out.println(qep.size());
    }
    
    public void testMatchAllFilters(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> copy = list;
        
        MatchAllFilters maf = new MatchAllFilters();
        
        /*maf.addFilter(new MagnitudeFilter(0.0, 2.0, "magnitude"));
        maf.addFilter(new DepthFilter(-100000.0, -10000.0, "depth"));
        maf.addFilter(new PhraseFilter("any", "a", "phrase"));
        
        ArrayList<QuakeEntry> filteredData = filter(copy,maf);
        for (QuakeEntry qe: filteredData) { 
            System.out.println(qe);
        }
        System.out.println(filteredData.size());
        System.out.println(maf.getName());*/
        Location city = new Location(55.7308, 9.1153);
        
        maf.addFilter(new MagnitudeFilter(0.0, 5.0, "magnitude"));
        maf.addFilter(new DistanceFilter(3000000.0 , city, "distance"));
        maf.addFilter(new PhraseFilter("any", "e", "phrase"));
        //maf.addFilter(new DepthFilter(-180000.0, -30000.0, "depth"));
        ArrayList<QuakeEntry> filteredData = filter(copy,maf);
        for (QuakeEntry qe: filteredData) { 
            System.out.println(qe);
        }
        System.out.println(filteredData.size());
        System.out.println(maf.getName());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
