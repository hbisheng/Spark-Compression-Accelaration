package org.fmIndex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class CPointerWrapper {
	
	byte[] backingArray = null;
	ByteBuffer bb = null;
	
	public CPointerWrapper(String path) {
		this( CommonUtils.readFile(path) );	
	}
	
	public CPointerWrapper(byte[] indexContent){
		backingArray = indexContent;
		bb = ByteBuffer.wrap(backingArray);
		bb.order(ByteOrder.LITTLE_ENDIAN);
	}
	
	public boolean IsLoaded(){
		return backingArray != null;
	}
	
	public int get(int idx) {
		return bb.get(idx) & 0x0FF;
	}
}
