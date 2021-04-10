
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapred.FileInputFormat;
//import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class FruitCountDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException 
	{
		Configuration conf = new Configuration();
		
		Job j = new Job();
		j.setJobName("My First Job");
		j.setJarByClass(FruitCountDriver.class );
		j.setMapperClass(FruitCountMapper.class );
		
		j.setReducerClass(FruitCountReducer.class);
		
		j.setOutputKeyClass(Text.class);
		j.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(j, new Path(args[0]));
		FileOutputFormat.setOutputPath(j, new Path(args[1]));
		
		URI uri = new URI(args[1].toString());
		
		FileSystem fs =  FileSystem.get(uri, conf);
		
		boolean p = fs.delete(new Path(uri),true);
		
		
		int w =  j.waitForCompletion(true) ? 0 : 1;
		
	}

}
