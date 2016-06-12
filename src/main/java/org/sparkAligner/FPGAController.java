package org.sparkAligner;

public class FPGAController {
	
	
	static final int FPGA_NUM = 2;
	static boolean available[] = new boolean[FPGA_NUM];
	static Object mutex = new Object();
		
	static {
		for(int i = 0; i < FPGA_NUM; i++) available[i] = true;
    }
	
	static int getFPGAGrant(){
		synchronized (mutex) {
			while(true) {
				for(int i = 0; i < FPGA_NUM; i++) {
					if(available[i] == true) {
						available[i] = false;
						System.out.println("Get dfe: " + i);
						return i;
					}
				}
				
				try {
					mutex.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	static int releaseFPGAGrant(int i){
		synchronized (mutex) {
			System.out.println("Release FPGA" + i);
			available[i] = true;
			mutex.notify();
			return 0;
		}
	}
}
