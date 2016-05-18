package org.fmIndex;

import java.util.ArrayList;

class alignThread implements Runnable {
    
	IndexPointerWrapper idx;
	private IvalPointerWrapper ival1;
	private IvalPointerWrapper ival2;
	private ArrayList<ReadTWrapper> reads;
	private int start_idx;
	private int end_idx;
	
    public alignThread(
			IndexPointerWrapper idx, IvalPointerWrapper ival1, IvalPointerWrapper ival2, 
			ArrayList<ReadTWrapper> reads, int start_idx, int end_idx){
    	this.idx = idx;
    	this.ival1 = ival1;
    	this.ival2 = ival2;
    	this.reads = reads;
    	this.start_idx = start_idx;
    	this.end_idx = end_idx;
    }
	
    public void run() {
    	System.out.println("Thread aligning from " + start_idx +" to " + end_idx);
    	for(int i = start_idx; i < end_idx; i++) {
    		FmIndex.alignFunc(reads.get(i), idx, ival1, ival2);
    	}
    }
}