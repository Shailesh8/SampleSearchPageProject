import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;


public class Planin {
/*
 *Below Data structure helps to find whether each key of query is present or not in page in O(1) order
 *   
 */
static ArrayList< HashMap< String, HashMap<String,Integer>  > > arr=new ArrayList<>();
	
static class PageStrength{
int total;
String str;
public PageStrength(int total,String str){
this.total=total;
this.str=str;
}  }
	
/*
 * 
 * 
 * */
public static void findQuery(String[] s,int kount){	
	
ArrayList<PageStrength> pageList=new ArrayList<>();
int sum,numQuery;
String p;
for(int j=0;j<arr.size();j++){
sum=0;	 numQuery=j+1;
p="";

for(int i=1;i<s.length;i++){	

HashMap< String, HashMap<String,Integer>  >  mapPageToStrengthKeywords= arr.get(j);

p="P"+numQuery;

HashMap<String,Integer> mapKeywordToStrength=mapPageToStrengthKeywords.get(p);

if((mapKeywordToStrength.get(s[i])!=null)){

sum=sum+mapKeywordToStrength.get(s[i])*(8+1-i);
} }

if(sum>0){
PageStrength ans=new PageStrength(sum,p);
pageList.add(ans);
} }
/*
 * Sort the pages in ascending order if strength of two or more pages will be equal 
 */
Collections.sort(pageList, new Comparator<PageStrength>() {

	@Override
	public int compare(PageStrength o1, PageStrength o2) {
		// TODO Auto-generated method stub
		
		if(o2.total-o1.total==0){
		 return o1.str.compareToIgnoreCase(o2.str);	
		
		}
		else{
		return 	o2.total-o1.total;
		}
	}
});
/*
 * If searching pages is less than 5 then k=finding pages or k=5 
 */
int k=(pageList.size()>5)?5:pageList.size();

System.out.print("Q"+kount+": ");
for(int i=0;i<k;i++){
System.out.print(pageList.get(i).str+" ");	
	}
System.out.println("");

}
/*
 * 
 */
public static void groupOfPages(String[] s,int maxKeysStrength,int count){
	int n=maxKeysStrength;
	String sb;
	HashMap<String, Integer> mapKeysStrength=new HashMap<>();  
  /*
   * Here mapping keywords of page according to their position
   */
	for(int i=1;i<s.length;i++){
		mapKeysStrength.put(s[i], n);
	   n=n-1;
	}
      /*
       * Mapping page to given strength keys 
       */
	HashMap< String, HashMap<String,Integer>  > mapPageToKeys=new HashMap<>();

	sb=s[0];

	sb=sb+ count;

	count=count+1;

	mapPageToKeys.put(sb, mapKeysStrength); 
	/*
	 * adding pages in arraylist   
	 */
	arr.add(mapPageToKeys);

}

/*
 * main function 
 */

public static void main(String[] args) throws IOException{

Scanner sc=new Scanner(System.in);

String line;

int maxKeysStrength=8,kount=1,count=1;

while(true){

	line=sc.nextLine();
 /*
  * If hit the enter then exit the while loop
  */
	if(line.isEmpty()){
	break;
    }

String[] s=line.split(" ");

if(s[0].equals("Q")){
	/*
	 * calling query function to find top 5 or less matching pages 
	 */
	findQuery(s,kount);
	kount=kount+1;
}
else{
	/*
	 * Construct the array of pages
	 */
	groupOfPages(s,maxKeysStrength,count);
	count=count+1;
} } } 




}




