package org.sparkAligner;

public class Clock {
	
    long timeMark = -1;
    
    void start() {
    	timeMark = System.currentTimeMillis();
    	
    }
    
    String elapsedTimeInSeconds(String action){
        long timeNow = System.currentTimeMillis();
        long elapsedTime = (timeNow - timeMark);
        timeMark = timeNow;
        
        return String.format( action + " : %.3f seconds", elapsedTime/1000.0);
    }
}
