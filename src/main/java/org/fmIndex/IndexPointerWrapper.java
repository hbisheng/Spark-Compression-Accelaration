package org.fmIndex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Wrapper for pointer that pointed to the C struct below
 * 
 * struct index_t {
 *     uint32_t counters[64];
 *     uint8_t bwt[CEIL(BUCKET_SIZE*7,8)];
 *     uint32_t pad;
 * };
 * 
 */

public class IndexPointerWrapper {
	
	public static final int SIZE_OF_COUNTERS = Constant.SIZE_OF_UINT32 * 64; 
	public static final int SIZE_OF_BWT = Constant.SIZE_OF_BYTE * ( (Constant.BUCKET_SIZE * 7 + 7) / 8); 
	
	public static final int SIZE_OF_INDEX_T = SIZE_OF_COUNTERS + SIZE_OF_BWT + Constant.SIZE_OF_UINT32; 
	
	byte[] backingArray = null;
	ByteBuffer bb = null;
	
	public IndexPointerWrapper(String indexPath) {
		this( CommonUtils.readFile(indexPath) );	
	}
	
	public IndexPointerWrapper(byte[] indexContent){
		backingArray = indexContent;
		bb = ByteBuffer.wrap(backingArray);
		bb.order(ByteOrder.LITTLE_ENDIAN);
	}
	
	public boolean IsLoaded(){
		return backingArray != null;
	}
	
	public long get_counters(int struct_idx, int idx){
		return bb.getInt(struct_idx * SIZE_OF_INDEX_T + idx * Constant.SIZE_OF_UINT32) & 0x0FFFFFFFF;
		//return extractNumber(struct_idx * SIZE_OF_INDEX_T + idx * Constant.SIZE_OF_UINT32, 4);
	}
	
	public long get_bwt(int struct_idx, int idx){
		return bb.get(struct_idx * SIZE_OF_INDEX_T + SIZE_OF_COUNTERS + idx * Constant.SIZE_OF_BYTE) & 0x0FF;
		//return extractNumber(struct_idx * SIZE_OF_INDEX_T + SIZE_OF_COUNTERS + idx * Constant.SIZE_OF_BYTE, 1);
	}
	
	public long get_pad(int struct_idx) {
		return bb.getInt(struct_idx * SIZE_OF_INDEX_T + SIZE_OF_COUNTERS + SIZE_OF_BWT) & 0x0FFFFFFFF;
		//return extractNumber(struct_idx * SIZE_OF_INDEX_T + SIZE_OF_COUNTERS + SIZE_OF_BWT, 4);
	}
	
	public long extractNumber(int pos, int byteLen) {
		byte[] tmp = new byte[byteLen];
		for(int i = 0; i < byteLen; i++)
			tmp[i] = backingArray[pos + i]; 
		return CommonUtils.bytesToLong(tmp);
	}
}