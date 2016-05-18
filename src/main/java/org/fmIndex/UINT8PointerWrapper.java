package org.fmIndex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class UINT8PointerWrapper {
	
	byte[] backingArray = null;
	ByteBuffer bb = null;
	
	public UINT8PointerWrapper(String path) {
		this( CommonUtils.readFile(path) );	
	}
	
	public UINT8PointerWrapper(byte[] indexContent){
		backingArray = indexContent;
		bb = ByteBuffer.wrap(backingArray);
		bb.order(ByteOrder.LITTLE_ENDIAN);
	}
	
	public boolean IsLoaded(){
		return backingArray != null;
	}
	
	public int getUINT8(int idx) {
		return bb.get(idx) & 0x0FF;
	}
}
