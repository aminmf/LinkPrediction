package metaPath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 		This is to generate dataset for logistic regression using labels and features in different text files.
 * 
 * @author aminmf
 */
public class DataSetGenerator {


	public static void main(String[] args) 
	{	 
		String currentLineString;


		try{
			BufferedReader apvpa = new BufferedReader(new FileReader("APVPA_1996_2002.txt"));
			BufferedReader apapa = new BufferedReader(new FileReader("APAPA_1996_2002.txt"));
			BufferedReader predfile = new BufferedReader(new FileReader("temporalPredictFeature_2003_2009.txt"));
			BufferedReader lfile = new BufferedReader(new FileReader("shuffledlabels_1996_2002_newLinkIn_2003_2009.txt"));
			BufferedWriter dataset1 = new BufferedWriter(new FileWriter(new File("dataset1_1996_2002.txt")));
			BufferedWriter dataset2 = new BufferedWriter(new FileWriter(new File("dataset2_1996_2002.txt")));
			BufferedWriter dataset3 = new BufferedWriter(new FileWriter(new File("dataset3_1996_2002.txt")));

			String f1, f2, f3, label;

			//for (int i=0; i<8645734; i++){
			while ((currentLineString = lfile.readLine()) != null) {
				label = currentLineString.substring(currentLineString.indexOf(":")+1);

				f1 = apvpa.readLine();
				f2 = apapa.readLine();
				f3 = predfile.readLine();


				if (f1.contains("-1") || f2.contains("-1"))
					continue;


				//dataset1.write(f1 + "\t" + f2 + "\t" + f3 + "\t" + label +"\n");
				//dataset2.write(f1 + "\t" + f2 + "\t" + label +"\n");
				//dataset3.write(f3 + "\t" + label +"\n");



				// For SVM setting
				if (label.equals("1"))
					label = "+1";
				else
					label = "-1";

				//+1 1:0 2:0.661 3:0.037 4:0.500 5:0.199 6:0.006 7:0.000 8:0.015 9:0.100

				dataset1.write(label + " 1:" + f1 + " 2:" + f2 + " 3:" + f3 + "\n");
				dataset2.write(label + " 1:" + f1 + " 2:" + f2 + "\n");
				dataset3.write(label + " 1:" + f3 + "\n");

			}


			apvpa.close();
			apapa.close();
			predfile.close();
			lfile.close();
			dataset1.close();
			dataset2.close();
			dataset3.close();

		}catch (IOException e) 
		{
			e.printStackTrace();
		} 

	}


}
