package huffman;

public class UnZipFile {

	public static void main(String[] args) {

		String zipFile = "C:\\Users\\DELL\\Desktop\\1.zip";
		String dstFile = "C:\\Users\\DELL\\Desktop\\2.png";
		HuffmanCode.unZipFile(zipFile, dstFile);
		System.out.println("解压文件成功");
	}

}
