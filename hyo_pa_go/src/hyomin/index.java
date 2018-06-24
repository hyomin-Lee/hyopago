package hyomin;

import java.io.UnsupportedEncodingException;

public class index extends check_source implements hyomin.finishindex{
	void print_index() throws UnsupportedEncodingException{
		System.out.print("================================================");
		System.out.print("\n\n\n\n  HYO PA GO ( hyomin translater ) - 20218 "+new String("이효민".getBytes("ksc5601"), "euc-kr")+"\n\n\n\n");
		System.out.print("================================================\n");
		System.out.print("[*] Input imageFile PATH >>> ");
	}
	
	void check(){ //abstract use
		System.out.println("[*] SELECT : 1.English->korean  2.Korean->English  ");
	}
	
	public void findex() {
		System.out.println("Thank you!!! Bye!");
	}
}
