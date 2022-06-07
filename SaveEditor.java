import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class SaveEditor {
	final static String spotpass = "FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF 00 00 00 00 "
			+ "00 00 00 00 00 00 00 00 00 00 00 00 00 0A 10 DE 34 01 FF 0F 00 00 00 00 "
			+ "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 "
			+ "00 00 04 D0 46 A5 01 FF FF 3F 00 00 00 00 00 00 00 00 00 00 00 00 00 00 "
			+ "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C FB FC 41 01 3F 00 00 00 "
			+ "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 "
			+ "00 00 00 00 57 C0 60 65 01 FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF "
			+ "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0A 10 DE 34 01 FF 0F";
	final static String search = "AD 55 0A 19 01";
	public static void main(String[] args) throws IOException {
		FileInputStream in = new FileInputStream(args[0]);
		int c;
		StringBuilder s = new StringBuilder();
		boolean first = true;
		while((c = in.read()) != -1) {
			String temp = Integer.toHexString(c).toUpperCase();
			if(temp.length() == 1) {
				temp = "0" + temp;
			}
			
			s.append(temp + " ");
		}
		s.deleteCharAt(s.length() - 1);
		int index = 0;
		for(int i = 0; i < 4; i++) {
			index = s.indexOf(search, index) + 1;
		}
		s.replace(index + 16, index + 17 + spotpass.length(), spotpass);
		Scanner output = new Scanner(s.toString());
		FileOutputStream f = new FileOutputStream(args[0] + "_unlocked");
		while(output.hasNext()) {
			f.write(Integer.parseInt(output.next(), 16));
		}
	}

}
