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
        //conf.set("spark.hadoop.io.compression.codecs", "org.sparkAligner.TmpGzipCodec");
        long startTime = System.currentTimeMillis();
        JavaSparkContext context = new JavaSparkContext(conf);
        JavaRDD<String> lines1 = context.textFile(args[0]);
        
        lines1.coalesce(1).saveAsTextFile(args[2], org.sparkAligner.Lz77FPGACodec.class);
        //lines1.coalesce(1).saveAsTextFile(args[2], org.apache.hadoop.io.compress.DefaultCodec.class);
        //lines1.coalesce(1).saveAsTextFile(args[2], org.apache.spark.io.SnappyCompressionCodec.class);
        //lines1.coalesce(1).saveAsTextFile(args[2], org.apache.hadoop.io.compress.Lz4Codec.class);
        //lines1.coalesce(1).saveAsTextFile(args[2], org.apache.hadoop.io.compress.DeflateCodec.class);
        //lines1.coalesce(1).saveAsTextFile(args[2], org.apache.hadoop.io.compress.GzipCodec.class);
        //lines1.coalesce(1).saveAsTextFile(args[2], org.apache.hadoop.io.compress.SnappyCodec.class); 
        
		System.out.println("\n----------------Total Eclapsed time: " + (System.currentTimeMillis()-startTime)/1000.0 + " seconds.\n");
        context.stop();
        context.close();
    }
}
