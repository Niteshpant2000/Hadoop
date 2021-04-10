


import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FruitCountMapper extends Mapper<LongWritable,  Text, Text, IntWritable>

{

	@Override
	protected void map(LongWritable key, Text value,
			org.apache.hadoop.mapreduce.Mapper.Context context)
			throws IOException, InterruptedException {
String inputstring = value.toString();

for(String fruit: inputstring.split(" "))

{	fruit=fruit.toLowerCase();
	if(fruit.equals("apple") || fruit.equals("banana") || fruit.equals("grapes")){
		context.write(new Text(fruit),new IntWritable(1));
		
	}

}
	}

}
