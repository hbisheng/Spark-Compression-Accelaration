package org.fmIndex;

import java.util.ArrayList;

public class FmIndex {
	
	public static void main(String args[]) throws Exception{
		
		String arg0 = "chr1.fmt";
		String arg1 = "chr1.fastq";
		
		IndexPointerWrapper idx = new IndexPointerWrapper(CommonUtils.readFile(arg0 + ".idx"));
		IvalPointerWrapper ival1 = new IvalPointerWrapper(CommonUtils.readFile(arg0 + ".1sai"));  
		IvalPointerWrapper ival2 = new IvalPointerWrapper(CommonUtils.readFile(arg0 + ".2sai"));
		UINT32PointerWrapper sai = new UINT32PointerWrapper(CommonUtils.readFile(arg0 + ".sai"));
		UINT8PointerWrapper ref  = new UINT8PointerWrapper(CommonUtils.readFile(arg0));
		
		ArrayList<ReadTWrapper> reads = new ArrayList<ReadTWrapper>();
		CommonUtils.loadReadsFromFile(reads, arg1);
		
		long startTime = System.currentTimeMillis();
		
		/*
		int threadNum = 2;
		int part = reads.size() / threadNum;
		Thread[] processes = new Thread[threadNum];
		for(int thread = 0; thread < threadNum; thread++) {
			if(thread != threadNum - 1)
				processes[thread] = new Thread(new alignThread(idx, ival1, ival2, reads, part * thread, part*(thread+1) ));
			else 
				processes[thread] = new Thread(new alignThread(idx, ival1, ival2, reads, part * thread, reads.size() ));
			processes[thread].start();
		}
		try{
			for(int i = 0; i < threadNum; i++) {	
				processes[i].join();
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		
		for(int i = 0; i < reads.size(); i++) {
			if(reads.get(i).is_align == false) {
				System.out.println("Error with align information");
				break;
			}
		} */
		
		for(int i = 0; i < reads.size(); i++) {
			FmIndex.alignFunc(reads.get(i), idx, ival1, ival2);
			if(reads.get(i).is_align == false) {
				System.out.println("test bench 1 failed at " + i);
    			return;
			}
						
	        long sa_size = reads.get(i).high - reads.get(i).low + 1;
	        for (int j = 0; j < sa_size; j++) {
	        	long pos = sai.getUINT32( (int) (reads.get(i).low + j) );
	        	for(int copy_i = 0; copy_i < reads.get(i).len; copy_i ++) {
	        		if(reads.get(i).sym[copy_i] != ref.getUINT8(  (int)(pos + copy_i)) ) {
	        			System.out.println("test bench 2 failed at " + i);
	        			return;
	        		}
	        	}
	        }
			
			if(i % 50000 == 0)
				System.out.println("i reachs " + i);
		}
		System.out.println("Elaspsed time: " + (System.currentTimeMillis() - startTime) / 1000.0 );
	}

	static void alignFunc(ReadTWrapper read,
			IndexPointerWrapper idx, IvalPointerWrapper ival1,
			IvalPointerWrapper ival2) {
		int len = read.len;
		int offset = len % 3;

		int sym1_init;
		int sym2_init;
		switch (read.sym[len-2]) {
			case 'A' : sym2_init = 0; break;
			case 'C' : sym2_init = 4; break;
			case 'G' : sym2_init = 8; break;
			case 'T' : sym2_init = 12; break;
			default : return;
		}
		switch (read.sym[len-1]) {
			case 'A' : sym2_init += 0; sym1_init = 0; break;
			case 'C' : sym2_init += 1; sym1_init = 1; break;
			case 'G' : sym2_init += 2; sym1_init = 2; break;
			case 'T' : sym2_init += 3; sym1_init = 3; break;
			default : return;
		}
	 
		long low = offset == 0 ? 0 :
		    offset == 1 ? ival1.get_low(sym1_init) : ival2.get_low(sym2_init);
	    long high = offset == 0 ? ival1.get_high(3) : 
		    offset == 1 ? ival1.get_high(sym1_init) : ival2.get_high(sym2_init);

	    for (int i = offset; i < len; i+=3) {
		    // get read symbol
    		int sym;
    		switch (read.sym[len-i-3]) {
                case 'A' : sym = 0; break;
			    case 'C' : sym = 16; break;
			    case 'G' : sym = 32; break;
			    case 'T' : sym = 48; break;
			    default : return;
		    }
    		switch (read.sym[len-i-2]) {
		    	case 'A' : sym += 0; break;
		    	case 'C' : sym += 4; break;
		    	case 'G' : sym += 8; break;
		    	case 'T' : sym += 12; break;
		    	default : return;
    		}
    		switch (read.sym[len-i-1]) {
		    	case 'A' : sym += 0; break;
		    	case 'C' : sym += 1; break;
		    	case 'G' : sym += 2; break;
		    	case 'T' : sym += 3; break;
		    	default : return;
    		}

		    long low_tmp = low == 0 ? 0 : low - 1;
		    boolean same_bucket = high - low_tmp < Constant.BUCKET_SIZE/Constant.OS_FACTOR ? true : false;
		    
		    // update low
		    long low_addr = low_tmp/ (Constant.BUCKET_SIZE/ Constant.OS_FACTOR); 
		    long low_idx = low_tmp - ((low_tmp/(Constant.BUCKET_SIZE/Constant.OS_FACTOR))*(Constant.BUCKET_SIZE/Constant.OS_FACTOR));
		    long low_counter = idx.get_counters((int)low_addr, sym);
		    
		    long low_count = getOcc(sym, idx, low_addr, 0, low_idx);
		    low = low == 0 ? low_counter : low_counter + low_count;
	    
		    // update high
		    long high_idx;
		    if (same_bucket) {
		    	high_idx = high - ((low_tmp/(Constant.BUCKET_SIZE/Constant.OS_FACTOR))*(Constant.BUCKET_SIZE/Constant.OS_FACTOR));
		    	high = low_counter + low_count + 
		    		  getOcc(sym, idx, low_addr, low_idx+1, high_idx) - 1;
		    }   
		    else {
		    	long high_addr = high/(Constant.BUCKET_SIZE/Constant.OS_FACTOR);
		    	high_idx = high - ((high/(Constant.BUCKET_SIZE/Constant.OS_FACTOR))*(Constant.BUCKET_SIZE/Constant.OS_FACTOR));
		    	high = idx.get_counters((int)high_addr, sym) + 
		    		  getOcc(sym, idx, high_addr, 0, high_idx) - 1;
		    }
		    
		    // terminal condition
		    if (low > high)
		      return;
	  }
	  
	  // store suffix array interval
	  read.low = low;
	  read.high = high;
	  read.is_align = true;
	}

	// count occurrence from BWT
	private static long getOcc(int sym, IndexPointerWrapper idx, long addr,
			long s_idx, long e_idx) {
		long cnt = 0;
		for(long i = s_idx; i <= e_idx; i++) {
			if(sym == getVal(idx, addr, i)) {
				cnt += 1;
			}
		}
		return cnt;
	}
	
	// get value in packed bwt
	private static int getVal(IndexPointerWrapper idx_bwt, long addr, long idx) {
		int a = (int)idx_bwt.get_bwt( (int) addr, (int) ( ((idx*7)/8)+1) ) << 8;
		int b = (int)idx_bwt.get_bwt( (int) addr, (int) ((idx*7)/8)) ;
		int tmp = a | b;
		tmp = (tmp >> ((idx*7)%8)) & 0x07f;
		return tmp;
	}
		
} 
