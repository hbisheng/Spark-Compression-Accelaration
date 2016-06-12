package org.sparkAligner;

public class Clock {
	
    static long timeMark = -1;
    
    static void start() {
    	timeMark = System.currentTimeMillis();
    	
    }
    
    static String elapsedTimeInSeconds(String action){
        long timeNow = System.currentTimeMillis();
        long elapsedTime = (timeNow - timeMark);
        timeMark = timeNow;
        
        return String.format( action + " : %.3f seconds", elapsedTime/1000.0);
    }
}
