package huffman;

public class ZipFile {

	public static void main(String[] args) {
		
		String srcFile = "C:\\Users\\DELL\\Desktop\\1.png";
		String dstFile = "C:\\Users\\DELL\\Desktop\\1.zip";
		HuffmanCode.zipFile(srcFile, dstFile);
		System.out.println("压缩文件成功");
	}
}
