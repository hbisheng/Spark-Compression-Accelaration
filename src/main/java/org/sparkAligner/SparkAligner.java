package org.sparkAligner;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.spark.Partition;
import org.apache.spark.Partitioner;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.broadcast.Broadcast;
import org.fmIndex.CommonUtils;
import org.fmIndex.IndexPointerWrapper;
import org.fmIndex.IvalPointerWrapper;
import org.fmIndex.ReadTWrapper;
import org.fmIndex.UINT32PointerWrapper;
import org.fmIndex.UINT8PointerWrapper;
import org.fmIndex.FmIndex;

import scala.Serializable;
import scala.Tuple2;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class SparkAligner {
    
    public static void main(String[] args) throws Exception {
        
    	System.out.println("---------------------Start Java program------------------------------");
    	
        SparkConf conf = new SparkConf()
        		.setAppName("org.sparkexample.WordCount")
        		.setMaster("local[12]")
        		.set("spark.kryoserializer.buffer.max", "2047m")
        		.set("spark.rdd.compress", "true");  
        conf.registerKryoClasses(
                new Class<?>[] {
                    Class.forName("org.apache.hadoop.io.IntWritable"),
                    Class.forName("org.apache.hadoop.io.BytesWritable")
                }
            ); 
        
        System.loadLibrary("compression_core");
        compression_core.loadFPGAs(FPGAController.FPGA_NUM);
        
        String outputPath = args[2];
        int readPartition = 12;	
		int writePartition = 1;	
		
		/*
        IndexPointerWrapper idx = new IndexPointerWrapper(CommonUtils.readFile(args[0] + ".idx"));
		IvalPointerWrapper ival1 = new IvalPointerWrapper(CommonUtils.readFile(args[0] + ".1sai"));  
		IvalPointerWrapper ival2 = new IvalPointerWrapper(CommonUtils.readFile(args[0] + ".2sai"));
		UINT32PointerWrapper sai = new UINT32PointerWrapper(CommonUtils.readFile(args[0] + ".sai"));
		UINT8PointerWrapper ref  = new UINT8PointerWrapper(CommonUtils.readFile(args[0]));
        */
		JavaSparkContext context = new JavaSparkContext(conf);
		
		Clock t = new Clock();
		t.start();
		JavaRDD<String> lines1 = context.textFile(args[1], readPartition);
        Function<String, String> easyFunc = new Function<String, String>() {
			@Override
			public String call(String arg0) throws Exception {		
				return arg0;
			}
		};
		
		//lines1.map(easyFunc).coalesce(writePartition).saveAsTextFile(outputPath);
		//lines1.map(easyFunc).coalesce(writePartition).saveAsTextFile(outputPath, org.sparkAligner.Lz77FPGACodec.class);
		//lines1.map(easyFunc).coalesce(writePartition).saveAsTextFile(outputPath, org.apache.hadoop.io.compress.SnappyCodec.class);
		lines1.map(easyFunc).coalesce(writePartition).saveAsTextFile(outputPath, org.apache.hadoop.io.compress.Lz4Codec.class);
		//lines1.map(easyFunc).coalesce(writePartition).saveAsTextFile(outputPath, org.apache.hadoop.io.compress.GzipCodec.class);
		//lines1.map(easyFunc).coalesce(writePartition).saveAsTextFile(outputPath, org.apache.hadoop.io.compress.BZip2Codec.class);
		
        /*
        JavaPairRDD<IntWritable, BytesWritable> readsRDD = context.sequenceFile(args[1], IntWritable.class, BytesWritable.class, readPartition);
        PairFunction<Tuple2<IntWritable, BytesWritable>, IntWritable, BytesWritable> mapToClone 
        = new PairFunction<Tuple2<IntWritable,BytesWritable>, IntWritable, BytesWritable>() {
            @Override
            public Tuple2<IntWritable, BytesWritable> call(Tuple2<IntWritable, BytesWritable> arg0)
                    throws Exception {
                byte[] bytesCopied = new byte[arg0._2.getLength()];
                System.arraycopy(arg0._2.getBytes(), 0, bytesCopied, 0, bytesCopied.length);
                return new Tuple2<IntWritable, BytesWritable>(new IntWritable(arg0._1.get()), new BytesWritable(bytesCopied));
            }
        };
        
		final Broadcast< IndexPointerWrapper > bcIdx = context.broadcast(idx);
		final Broadcast< IvalPointerWrapper > bcIval1 = context.broadcast(ival1);
		final Broadcast< IvalPointerWrapper > bcIval2 = context.broadcast(ival2);
        
        JavaPairRDD<IntWritable, BytesWritable> clonedReadsRDD = readsRDD.mapToPair(mapToClone);
        
		Function<Tuple2<IntWritable, BytesWritable> , String> f = new Function<Tuple2<IntWritable,BytesWritable>, String>() {
			@Override
			public String call(Tuple2<IntWritable, BytesWritable> arg0)
					throws Exception {
				
				IndexPointerWrapper idx = bcIdx.value();
				IvalPointerWrapper ival1 = bcIval1.value();
				IvalPointerWrapper ival2 = bcIval2.value();
				
				ReadTWrapper read = new ReadTWrapper(arg0._2.getBytes());
				FmIndex.alignFunc(read, idx, ival1, ival2);
			
				//return ""+arg0._1.get() + " " + " " + read.len + " " + read.high + " " + read.low + " " + read.is_align;
				return ""+arg0._1.get() + " " + new String(read.sym) + " " + read.len + " " + read.high + " " + read.low + " " + read.is_align;
			}
		};
		*/
		
		//System.out.println("Read part = " + readPartition + " Write part = " + writePartition);
		//System.out.println("Compression: Lz4");
		//clonedReadsRDD.map(f).coalesce(writePartition).saveAsTextFile(outputPath);
		//clonedReadsRDD.map(f).coalesce(writePartition).saveAsTextFile(outputPath, org.apache.hadoop.io.compress.GzipCodec.class);
		//clonedReadsRDD.map(f).coalesce(writePartition).saveAsTextFile(outputPath, org.apache.hadoop.io.compress.DeflateCodec.class);
		//clonedReadsRDD.map(f).coalesce(writePartition).saveAsTextFile(outputPath, org.apache.hadoop.io.compress.BZip2Codec.class);
       	//clonedReadsRDD.map(f).coalesce(writePartition).saveAsTextFile(outputPath, org.sparkAligner.Lz77FPGACodec.class); 
       	//clonedReadsRDD.map(f).coalesce(writePartition).saveAsTextFile(outputPath, org.apache.hadoop.io.compress.Lz4Codec.class);
		//clonedReadsRDD.map(f).coalesce(writePartition).saveAsTextFile(outputPath, org.apache.hadoop.io.compress.SnappyCodec.class);
       	
		System.out.println(t.elapsedTimeInSeconds("----------------Total Elapsed time"));
		
		compression_core.unloadFPGAs(FPGAController.FPGA_NUM);
		context.stop();
        context.close();
    }
}
