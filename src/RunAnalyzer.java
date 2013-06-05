import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
public class RunAnalyzer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub	
		Analyzer a = new Analyzer();
		String filename = args[0];
		String result[] = a.getDependencies(filename);
		System.out.println(Arrays.toString(result));
	}
}

interface AnalyzerInterface {
	
	public String[] getDependencies(String filename) throws IOException;
}


class Analyzer implements AnalyzerInterface {

	 public String[] getDependencies(String filename) throws IOException {
		 /**
		  * Returns a string with a list of dependencies.
		  */
		// TODO Auto-generated method stub

		/*Start a new process in the default working directory with the first argument as the filename*/
		ProcessBuilder pb = new ProcessBuilder(System.getProperty("user.dir")+"/src/generate_list.sh",filename);
		Process p =pb.start();
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		StringBuilder builder = new StringBuilder();
		String line;
		while ( (line = br.readLine()) != null) {
		   builder.append(line);
		   builder.append(System.getProperty("line.separator"));
		}
		String result = builder.toString();
		String output[] = result.split(System.getProperty("line.separator"));
		return output;
	}
	 
}
