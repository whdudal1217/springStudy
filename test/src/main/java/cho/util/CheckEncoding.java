package cho.util;

import java.io.UnsupportedEncodingException;

public class CheckEncoding {

	public void EncodeCheck(String string) {
		String[] charSet = {"utf-8", "euc-kr", "ksc5601", "iso-8859-1"};
		for(int i=0; i<charSet.length; i++) {
			for(int j=0; i<charSet.length; j++) {
				try {
					System.out.println(charSet[i] + " -> " + charSet[j] + "\r\n" + new String(string.getBytes(charSet[i]),charSet[j]));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
