This is an Eclispse Project. The main source file is src/RunAnalyzer.java 
Below is the class interface for the Analyzer Class

interface AnalyzerInterface {
	
	public String[] getDependencies(String filename) throws IOException; //Returns a string with a list of dependencies,given a filename.
}

