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
        		.setMaster("local[4]")
        		.set("spark.kryoserializer.buffer.max", "2047m")
        		.set("spark.rdd.compress", "true");  
        conf.registerKryoClasses(
                new Class<?>[] {
                    Class.forName("org.apache.hadoop.io.IntWritable"),
                    Class.forName("org.apache.hadoop.io.BytesWritable")
                }
            ); 
        //conf.set("spark.hadoop.io.compression.codecs", "org.sparkAligner.TmpGzipCodec");
        
        String outputPath = args[2];
        
        long startTime = System.currentTimeMillis();
        int readPartition = 1;		
		/*
		IndexPointerWrapper idx = new IndexPointerWrapper(CommonUtils.readFile(arg0 + ".idx"));
		IvalPointerWrapper ival1 = new IvalPointerWrapper(CommonUtils.readFile(arg0 + ".1sai"));  
		IvalPointerWrapper ival2 = new IvalPointerWrapper(CommonUtils.readFile(arg0 + ".2sai"));
		UINT32PointerWrapper sai = new UINT32PointerWrapper(CommonUtils.readFile(arg0 + ".sai"));
		UINT8PointerWrapper ref  = new UINT8PointerWrapper(CommonUtils.readFile(arg0));
		
		ArrayList<ReadTWrapper> reads = new ArrayList<ReadTWrapper>();
		CommonUtils.loadReadsFromFile(reads, arg1);
		 */
        
        JavaSparkContext context = new JavaSparkContext(conf);
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
        
        IndexPointerWrapper idx = new IndexPointerWrapper(CommonUtils.readFile(args[0] + ".idx"));
		IvalPointerWrapper ival1 = new IvalPointerWrapper(CommonUtils.readFile(args[0] + ".1sai"));  
		IvalPointerWrapper ival2 = new IvalPointerWrapper(CommonUtils.readFile(args[0] + ".2sai"));
		UINT32PointerWrapper sai = new UINT32PointerWrapper(CommonUtils.readFile(args[0] + ".sai"));
		UINT8PointerWrapper ref  = new UINT8PointerWrapper(CommonUtils.readFile(args[0]));
        
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
				
				return ""+arg0._1.get() + " " + read.sym + " " + read.len + " " + read.high + " " + read.low + " " + read.is_align;
			}
		};
		clonedReadsRDD.map(f).saveAsTextFile(outputPath);
        
        //lines1.coalesce(1).saveAsTextFile(args[2], org.sparkAligner.Lz77FPGACodec.class);
        //lines1.coalesce(1).saveAsTextFile(args[2], org.apache.hadoop.io.compress.DefaultCodec.class);
        //lines1.coalesce(1).saveAsTextFile(args[2], org.apache.spark.io.SnappyCompressionCodec.class);
        //lines1.coalesce(1).saveAsTextFile(args[2], org.apache.hadoop.io.compress.Lz4Codec.class);
        //lines1.coalesce(1).saveAsTextFile(args[2], org.apache.hadoop.io.compress.DeflateCodec.class);
        //lines1.coalesce(1).saveAsTextFile(args[2], org.apache.hadoop.io.compress.GzipCodec.class);
        //lines1.coalesce(1).saveAsTextFile(args[2], org.apache.hadoop.io.compress.SnappyCodec.class); 
        
		System.out.println("\n----------------Total Elapsed time: " + (System.currentTimeMillis()-startTime)/1000.0 + " seconds.\n");
        context.stop();
        context.close();
    }
}
