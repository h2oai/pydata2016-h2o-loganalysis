package cybersec;
import java.io.*;
import hex.genmodel.easy.RowData;
import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.prediction.*;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutionException;

public class MyClass {
	private static String modelClassNameOne = "cybersec.log_drf_1";
	private static String modelClassNameTwo = "cybersec.log_gbm_1";

//	private String[] stringSplitter(String jspString){
//	String returnString[] = jspString.split("\\,");
//	return returnString;
//}
//
//	
//
//	private  RowData rowFeeder(String[] sAFRF){
//	//sAFRF - stringArrayForRowFeeder
//	RowData row = new RowData();
//      row.put("word",sAFRF[0]);
//      row.put("trx_code",sAFRF[1]);
//      row.put("add_trx_code",sAFRF[2]);
//
//	return row;
//}
//	
//	
//	public String predictReturn(String jspIncomingString) throws Exception {
////	public static void main(String[] args) throws Exception {
//		hex.genmodel.GenModel rawModel;
//	rawModel = (hex.genmodel.GenModel) Class.forName(modelClassName).newInstance();
//	EasyPredictModelWrapper model  = new EasyPredictModelWrapper(rawModel);
//	
////	String jspIncomingString = new String();	
//	String[] jspSplitString = new String[106];
//	jspSplitString = this.stringSplitter(jspIncomingString);
//	RowData row = new RowData();
//	row = this.rowFeeder(jspSplitString);
//
//	BinomialModelPrediction p = model.predictBinomial(row);
//	String sendOutString = new String();
//	System.out.println("Is this transaction suspicious:" + p.label);
//	sendOutString += "Is this transaction suspicious? ";
//	if(p.label == "1"){
//		sendOutString += " YES";
//	}
//	else{
//		sendOutString += " NO";
//	}
//	System.out.println("Class probabilities:");
//	sendOutString += "<br>Class Probabilities:";
//	for (int i = 0; i < p.classProbabilities.length; i++) {
//	if (i >0) {
//	System.out.println(',');
//	}
//	System.out.println(p.classProbabilities[i]);
//	sendOutString += p.classProbabilities[i];
//	if ( i != p.classProbabilities.length){
//	sendOutString +=", ";
//	}
//	}
//	System.out.println("");
//	sendOutString +="<br>";
////	sendOutString +="<br>Something Works";
//	return sendOutString;
//	}
//}

public static void main(String args[]) throws Exception{
	
	ExecutorService executor = Executors.newFixedThreadPool(10);
//	Future<String> fut = new Future<String>();
	Callable<String> callable = new MyCallable();
	Future<String> fut = executor.submit(callable);

	hex.genmodel.GenModel rawModel;
	rawModel = (hex.genmodel.GenModel) Class.forName(modelClassNameOne).newInstance();
	EasyPredictModelWrapper model = new EasyPredictModelWrapper(rawModel);

	RowData row = new RowData();
	row.put("vlan","166");
	row.put("type_of_time","4");
	row.put("no_of_characters","138");
	row.put("no_of_ips","3");
	row.put("no_of_macs","2");
	row.put("4_letter_word","0.5");
	row.put("5_letter_word","0.05");
	row.put("6_letter_word","0.05");
	row.put("7_letter_word","0.05");
	row.put("8_letter_word","0.05");
	row.put("9_letter_word","0.05");
	row.put("10_letter_word","0.1");
	row.put("11_letter_word","0.05");
	row.put("12_letter_word","0.05");
	row.put("13_letter_word","0.05");
	row.put("14_letter_word","0");
	row.put("15_letter_word","0");

	MultinomialModelPrediction p = model.predictMultinomial(row);
	System.out.println("What kind of log is this?" + p.label);
	System.out.println("Class probabilities");
	for (int i = 0; i < p.classProbabilities.length;i++)
	{
		System.out.println(",");
		System.out.println(p.classProbabilities[i]);
	}
	System.out.println(fut.get());
	executor.shutdown();
	
	
	}

}
