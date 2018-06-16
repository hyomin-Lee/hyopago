package hyomin;

import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.protobuf.ByteString;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GoogleVisionApiTester {
	static String OCR_STRING;
	static int num;
	public static void main(String[] args) throws Exception {
		remem re = new remem();
		index get_index = new index();
		url parsing = new url();
		get_index.print_index();
		re.remember(get_OCR());
		parsing.connection();
		
	}
	
	
	
	public static String get_OCR() {
		Scanner scan = new Scanner(System.in);
		String leak_string="asd";
		String imageFilePath = scan.nextLine();
		System.out.println("[*] SELECT : 1.English->korean  2.Korean->English  ");
		num = scan.nextInt();
		try {
			System.err.close();
		    System.setErr(System.out);
			
			
			List<AnnotateImageRequest> requests = new ArrayList<>();
		
			ByteString imgBytes = ByteString.readFrom(new FileInputStream(imageFilePath));
		
			Image img = Image.newBuilder().setContent(imgBytes).build();
			Feature feat = Feature.newBuilder().setType(Type.TEXT_DETECTION).build();
			AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
			requests.add(request);
		
			try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
				BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
			    List<AnnotateImageResponse> responses = response.getResponsesList();
		
			    for (AnnotateImageResponse res : responses) {
			    	if (res.hasError()) {
			    		System.out.printf("Error: %s\n", res.getError().getMessage());
			    		
			    	}
		
			    	System.out.print("[*] String in Image file : ");
			    	leak_string = res.getTextAnnotationsList().get(0).getDescription();
			    	System.out.println(new String(leak_string.getBytes("ksc5601"), "euc-kr"));
			      
			    }
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return leak_string;
	}
	
	
}
