
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        sortByMagnitudeWithCheck(list);
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        /*for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }*/
        
    }
    
    public int getLargestDepth(ArrayList <QuakeEntry> quakeData, int from){
        int maxIndex = from;
        for(int i=from+1; i<quakeData.size(); i++){
            if(quakeData.get(i).getDepth() > quakeData.get(maxIndex).getDepth()){
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void sortByLargestDepth(ArrayList <QuakeEntry> qe){
        for(int i=0; i<50; i++){
            int maxIdx = getLargestDepth(qe, i);
            QuakeEntry qi = qe.get(i);
            QuakeEntry qmax = qe.get(maxIdx);
            qe.set(i,qmax);
            qe.set(maxIdx,qi);
        }
    }
    
    public void onePassBubbleSort(ArrayList <QuakeEntry> quakeData, int sumSorted){
        for(int i=0;i<quakeData.size()-sumSorted-1;i++){
            QuakeEntry currQe = quakeData.get(i);
            QuakeEntry nextQe = quakeData.get(i+1);
            
            if(currQe.getMagnitude() > nextQe.getMagnitude()){
                quakeData.set(i+1,currQe);
                quakeData.set(i, nextQe);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        for(QuakeEntry qe:in){
            System.out.println(qe);
        }
        
        for(int i=0; i<in.size()-1;i++){
            onePassBubbleSort(in, i);
            System.out.println("Printing quakes after pass " + i);
            for(QuakeEntry qe: in){
                System.out.println(qe);
            }
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> qe){
        for(int i=0; i<qe.size()-1; i++){
            QuakeEntry currQe = qe.get(i);
            QuakeEntry nextQe = qe.get(i+1);
            if(currQe.getDepth() > nextQe.getDepth()){
                return false;
            }
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        for(int i=0; i<in.size()-1; i++){
            onePassBubbleSort(in,i);
            if(checkInSortedOrder(in)){
                System.out.println("Sorted with " + (i+1)+ " passes");
                break;
            }
        }
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        int i;
        for(i=0; i<in.size(); i++){
            if(checkInSortedOrder(in)){
                System.out.println("Sorted with "+ (i+1)+ " passes");
                break;
            }
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx, qi);
        }
        System.out.println("Passes required = " + i);
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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
