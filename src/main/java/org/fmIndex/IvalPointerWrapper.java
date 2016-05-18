package org.fmIndex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Wrapper for pointer that pointed to the C struct below
 * 
 * struct ival_t {
 * 	  uint32_t low;
 *    uint32_t high;
 * };
 * 
 */

public class IvalPointerWrapper {
	
	public static final int SIZE_OF_IVAL = Constant.SIZE_OF_UINT32 * 2; 
	
	byte[] backingArray = null;
	ByteBuffer bb = null;
	
	public IvalPointerWrapper(String indexPath) {
		this( CommonUtils.readFile(indexPath) );	
	}
	
	public IvalPointerWrapper(byte[] indexContent){
		backingArray = indexContent;
		bb = ByteBuffer.wrap(backingArray);
		bb.order(ByteOrder.LITTLE_ENDIAN);
	}
	
	public boolean IsLoaded(){
		return backingArray != null;
	}
	
	public long get_low(int struct_idx){
		return bb.getInt(struct_idx * SIZE_OF_IVAL) & 0x0FFFFFFFF;
	}
	
	public long get_high(int struct_idx) {
		return bb.getInt(struct_idx * SIZE_OF_IVAL + Constant.SIZE_OF_UINT32) & 0x0FFFFFFFF;
	}
	
}