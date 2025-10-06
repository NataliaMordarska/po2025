public class Choinka{
   public static void main(String[] args){
	String znak="*";
        int i=Integer.parseInt(args[0]);
	
	for(int x=0;x<i;x++) {
	   for(int y=0;y<i * 2; y++){
		if( y<(i-x) || y>(i+x)) {
		   System.out.print(" ");
		}else{
		 System.out.print(znak);
			}
		}
		System.out.println();
		}
	}
}