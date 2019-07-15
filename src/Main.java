import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.attribute.standard.Finishings;





public class Main {
	static String htmlstring="";
	public static void main(String[] args) throws IOException {
		
		
		String[] paragraph = null;
		int paragrapharrayindex=0;
		
		
		String file="PD_CLNotes_platformplus.Build.43";
		String[] tableheader= findfirstline(file);
		List<tabledata> htmltable=new ArrayList<>();
		
		try {
			BufferedReader bf = new BufferedReader(new FileReader("C:\\guitarlesson\\"+file+".txt"));
			String line = bf.readLine();
			int linenumber=0;
			Pattern p= Pattern.compile("\\|"); ;
			
			
			while(line !=null)
			{
				Matcher linematch=p.matcher(line);
				System.out.println("-----------LineNumber:"+linenumber++);
				
				System.out.println(line);
				String[] tempsplitarray=line.split("\\|");
				System.out.println(tempsplitarray.length);
				if(tempsplitarray.length > 1)
				{
					tabledata td = new tabledata();
					
					String[] splitarray=line.split("\\|");
					
				
					td.setTabledata(splitarray);
					
					for(int i=0;i<td.getTabledata().length;i++)
					{
						System.out.println("splitarray "+i+"--->"+td.getTabledata()[i]);
					
					
					}
					htmltable.add(td);
				}
				else
				{	
					//paragraph[paragrapharrayindex]=line;
					paragrapharrayindex++;
					System.out.println("line in else"+line);
					//addparagraphdata(line);
					if(line.contains("Tag"))
					{
					addtagdata(line);
					}
					else if(line.contains("The CL report"))
					{
						addclbetweendata(line);
					}
					
					
				}
			line= bf.readLine();
			}
			
			bf.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addtabledata(htmltable);
		
		
		
		System.out.println("htmlstring --->"+htmlstring);
		writetofile(file);
	}
	
	public static void addtabledata(List<tabledata> htmltable)
	{
		
		System.out.println("Entering the addtabledate");
		//System.out.println(htmlstring);
		String temp=htmlstring+"\n"+"<HTML>\n"+"<table style="+" HEIGHT: 77px"+" bordercolor="+"00008B"+" cellspacing="+"1"+" cellPadding="+"3"+" align="+"center"+" border="+"1"+">";
	 htmlstring=temp;
	// System.out.println(htmlstring);
	
	 
	 String nextlinesplit="\n";
	 String starttable="<tr bgcolor=ccccff>";
	 String starttabledata="<td><p align=center>";
	 String endtabledata="</p></td>";
	 String endtablerow="</tr>";
	 temp=htmlstring+nextlinesplit;
	 htmlstring=temp;
	// System.out.println(htmlstring);
	 for(int i=0;i<htmltable.size();i++)
	 {
		 System.out.println("entering htmltable forloop");
		 tabledata td = htmltable.get(i);
		 temp=htmlstring+starttable;
		 htmlstring=temp;
		 for(int j=0;j<td.getTabledata().length;j++)
		 {
			 
			 temp=htmlstring+starttabledata+td.getTabledata()[j]+endtabledata;
			 htmlstring=temp;
			// System.out.println(htmlstring);
			 
		 }
		 temp=htmlstring+endtablerow+nextlinesplit;
		 htmlstring=temp;
		 //for (int j=0;j<td.getTabledata().length;j++)
		 //{
			 
			// String tablerow="\n<tr bgcolor=ccccff><td><p align=center>"+td.getTabledata()[0]+"</p></td><td><p align=center>"+td.getTabledata()[2]+"</p></td><td><p align=center>"+td.getTabledata()[3]+"</p></td><td><p align="+td.getTabledata()[4]+">Product</p></td><td><p align=center>"+td.getTabledata()[5]+"</p></td><td><p align=center>CLs</p></td><td><p align=center>"+td.getTabledata()[6]+"</p></td><td><p align=center>"+td.getTabledata()[7]+"</p></td><td><p align=center>"+td.getTabledata()[8]+"</p></td><td><p align=center>"+td.getTabledata()[9]+"</p></td><tr bgcolor=cc9999 ><td><p align=left>"+td.getTabledata()[10]+"</p></tr>";
			// String tempdata=htmlstring+tablerow;
			// htmlstring=tempdata;
		 //}
		 
		 
	 }
	// String endtable=htmlstring+"\n</table>";
	String endtmp=htmlstring+"\n</table>";
	htmlstring=endtmp;
	 
	
	 String tablerow="\n<tr bgcolor=ccccff><td><p align=center>"+"Bug ID / User Story ID"+"</p></td><td><p align=center>"+"Bug Status"+"</p></td><td><p align=center>"+"Client"+"</p></td><td><p align=center>"+"Components"+"</p></td><td><p align="+"center"+">Product</p></td><td><p align=center>"+"Version"+"</p></td><td><p align=center>CLs</p></td><td><p align=center>"+"Description"+"</p></td><td><p align=center>"+"UserName"+"</p></td><td><p align=center>"+"Checked-In Date(PST)"+"</p></td><td><p align=center>"+"CLsBranch"+"</p></td><tr bgcolor=cc9999 ><td><p align=left></p></tr>";
		/*<tr bgcolor="ccccff"><td><p align="center">Bug ID / User Story ID</p></td><td><p align="center">Bug Status</p></td><td><p align="center">Client</p></td><td><p align="center">Components</p></td><td><p align="center">Product</p></td><td><p align="center">Version</p></td><td><p align="center">CLs</p></td><td><p align="center">Description</p></td><td><p align="center">UserName</p></td><td><p align="center">Checked-In Date(PST)</p></td><td><p align="center">CLsBranch</p></td><tr bgcolor="cc9999" ><td><p align="left">
		</p></td><td><p align="left">
		</p></td><td><p align="left">
		</p></td><td><p align="left">
		</p></td><td><p align="left">
		</p></td><td><p align="left">
		</p></td><td><p align="left">4365713
		</p></td><td><p align="left">Updating Manifest File with build information
		</p></td><td><p align="left">builder
		</p></td><td><p align="left">2019/07/05 19:27:54
		</p></td><td><p align="left">platform
		</p></td></tr>*/
	}
	
	public static void addtagdata(String line)
	{
		System.out.println("line in addpar"+line);
		String temp=htmlstring+"<p align="+"center"+"><strong><font size="+"4"+" COLOR="+"green"+" >"+line+"</font></strong></p>";
		htmlstring=temp;
	}
	public static void addclbetweendata(String line)
	{
		System.out.println("line in addpar"+line);
		String temp=htmlstring+"<p align="+"center"+"><strong><font size="+"4"+" COLOR="+"#cc6600"+" >"+line+"</font></p>";
		htmlstring=temp;
	}
	
	public static void writetofile(String filename) throws IOException
	{
		String currentDirectory = System.getProperty("user.dir");
		 String ips ;
		 ips= htmlstring;
		 File file = new File(currentDirectory+"/"+filename+".html");
		 BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		 bw.write(ips);
		 bw.close();
		
		
	}
	
	public static String[] findfirstline(String file)
	{
		String[] tableheader = null;
		try {
			BufferedReader bf = new BufferedReader(new FileReader("C:\\guitarlesson\\"+file+".txt"));
			String line = bf.readLine();
			int linenumber=0;
			//Pattern p= Pattern.compile("\\|"); ;
			
			while(line !=null)
			{
				
				System.out.println("-----------LineNumber:"+linenumber++);
				
				System.out.println(line);
				String[] tempsplitarray=line.split("\\|");
				System.out.println(tempsplitarray.length);
				if(tempsplitarray.length > 1)
				{
					
					
					
					tableheader=line.split("\\|");
					
				
					
					
					for(int i=0;i<tableheader.length;i++)
					{
						System.out.println("tableheader "+i+"--->"+tableheader[i]);
					
					
					}
					break;
				}	
				line = bf.readLine();
				
			}
			
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		return tableheader;
		
		
	}
	
	
	

}
