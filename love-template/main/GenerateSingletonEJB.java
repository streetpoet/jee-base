import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import convertor.EJBSingletonConvertor;

public class GenerateSingletonEJB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new GenerateSingletonEJB().execute();
	}

	private void execute() {
		copy(Configuration.OUTPUT_FOLDER + "src" + File.separator + "ejb", Configuration.OUTPUT_FOLDER + "output" + File.separator + "ejb");
	}

	private void copy(String src, String des) {
		File file1 = new File(src);
		File[] fs = file1.listFiles();
		File file2 = new File(des);
		if (!file2.exists()) {
			file2.mkdirs();
		}
		for (File f : fs) {
			if (f.isFile()) {
				fileCopy(f.getPath(), des + File.separator + f.getName().toLowerCase());
			} else if (f.isDirectory()) {
				copy(f.getPath(), des + File.separator + f.getName().toLowerCase());
			}
		}

	}


	private void fileCopy(String src, String des) {

		BufferedReader br = null;
		PrintStream ps = null;

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(src)));
			ps = new PrintStream(new FileOutputStream(des));
			String s = null;
			while ((s = br.readLine()) != null) {
				ps.println(EJBSingletonConvertor.convert(s));
				ps.flush();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (br != null)
					br.close();
				if (ps != null)
					ps.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
