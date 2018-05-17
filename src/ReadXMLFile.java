import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
import java.net.*;
import java.util.*;
public class ReadXMLFile {


	public static void main(String[] args)throws Exception
	  {

		
		ArrayList a1 = new ArrayList();
		File file = new File("C:\\Users\\Burkan\\Desktop\\CMPE232-master\\Project\\babel.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String st;

		while ((st = br.readLine()) != null)
			a1.add(st);
	       
	  


	
	
	
}
}
	

