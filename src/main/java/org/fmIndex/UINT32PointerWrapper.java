package org.fmIndex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class UINT32PointerWrapper {
	byte[] backingArray = null;
	ByteBuffer bb = null;
	
	public UINT32PointerWrapper(String path) {
		this( CommonUtils.readFile(path) );	
	}
	
	public UINT32PointerWrapper(byte[] indexContent){
		backingArray = indexContent;
		bb = ByteBuffer.wrap(backingArray);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		//System.out.println("Sai size in bytes: " + backingArray.length);
	}
	
	public boolean IsLoaded(){
		return backingArray != null;
	}
	
	public long getUINT32(int idx) {
		return bb.getInt(idx * Constant.SIZE_OF_UINT32) & 0x0FFFFFFFF;
	}

}
