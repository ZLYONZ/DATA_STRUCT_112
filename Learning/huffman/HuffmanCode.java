package huffman;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanCode {
	
	public static void main(String[] args) {

		String content = "i like like like java do you like a java";
		byte[] contentBytes = content.getBytes();
		System.out.println("未压缩的长度为：" + contentBytes.length); // 40

		byte[] huffmanCodesBytes = huffmanZip(contentBytes);
		System.out.println("压缩后的结果是：" + Arrays.toString(huffmanCodesBytes));
		System.out.println("长度= " + huffmanCodesBytes.length);
				
		byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes); 
		System.out.println("原来的字符串= " + new String(sourceBytes));
		
		// 压缩率：(40-17)/40 = 57.5%
		// 分布过程
		/*
		 * List<Node1> nodes = getNodes(contentBytes); System.out.println("nodes= " +
		 * nodes);
		 * 
		 * System.out.println("对应的HuffmanTree的"); Node1 huffmanTreeRoot =
		 * CreateHuffmanTree(nodes); System.out.println("前序遍历后：");
		 * huffmanTreeRoot.preOrder();
		 * 
		 * Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
		 * System.out.println("生成的Huffman Code是：" + huffmanCodes);
		 * 
		 * byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
		 * System.out.println("huffmanCodeBytes= " + Arrays.toString(huffmanCodeBytes));
		 * 
		 */
	}

	// 封装前面的方法，便于调用
	/**
	 * @param bytes 原始的字符串对应的字节数组
	 * @return 返回的是经过Huffman Code处理后的数组（压缩后的数组）
	 */
	private static byte[] huffmanZip(byte[] bytes) {
		List<Node1> nodes = getNodes(bytes);
		
		// 根据nodes，创建Huffman Tree
		Node1 huffmanTreeRoot = CreateHuffmanTree(nodes);
		
		// 根据HuffmanTree，生成对应的Huffman Code
		Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
		
		// 根据生成的编码，压缩得到压缩后的Huffman Code 字节数组
		byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);

		return huffmanCodeBytes;
	}

	/**
	 * @param bytes 接收一个字节（字符）数组
	 * @return 返回List，形式为[Node[data= 97, weight= 5], Node[data= 32, weight= 9] ...
	 */
	private static List<Node1> getNodes(byte[] bytes) {

		// 创建一个ArrayList
		ArrayList<Node1> nodes = new ArrayList<Node1>();

		// 遍历bytes，统计每个bytes出现的次数 -> map[key,value]
		HashMap<Byte, Integer> counts = new HashMap<>();
		for (byte b : bytes) {
			Integer count = counts.get(b);
			if (count == null) { // map还没有这个字符数据，第一次
				counts.put(b, 1);
			} else {
				counts.put(b, count + 1);
			}
		}
		// 把每个键值对转成一个Node对象，并加入到nodes集合
		// 遍历map
		for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
			nodes.add(new Node1(entry.getKey(), entry.getValue()));
		}
		return nodes;
	}

	// 通过List创建HuffmanTree
	public static Node1 CreateHuffmanTree(List<Node1> nodes) {
		while (nodes.size() > 1) {
			
			// 排序，从小到大
			Collections.sort(nodes);
			// 取出第一颗最小的二叉树
			Node1 leftNode1 = nodes.get(0);
			// 取出第二颗最小的二叉树
			Node1 rightNode1 = nodes.get(1);
			
			// 创建一个新的二叉树，它的根节点没有data，只有权值
			Node1 parent = new Node1(null, leftNode1.weight + rightNode1.weight);
			parent.left = leftNode1;
			parent.right = rightNode1;
			
			// 将处理过的二叉树从nodes中删除
			nodes.remove(leftNode1);
			nodes.remove(rightNode1);
			
			// 将新的二叉树（parent）加入到nodes
			nodes.add(parent);
		}
		// nodes最后的节点就是huffmantree的根节点
		return nodes.get(0);
	}

	// 生成HuffmanTree对应的coding
	// 1. 将赫夫曼编码表存放在 Map<Byte, String> 中
	static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
	// 形式如下：32 -> 01 97 -> 100 10 -> 11000 ...
	
	// 2. 在生成Huffman code时，需要拼接路径，定义一个 StringBuilder 存储某个叶子节点的路径
	static StringBuilder stringBuilder = new StringBuilder();

	// 重载getCodes
	private static Map<Byte, String> getCodes(Node1 root) {
		if (root == null) {
			return null;
		}
		// 处理root的左子树
		getCodes(root.left, "0", stringBuilder);
		// 处理root的左子树
		getCodes(root.right, "1", stringBuilder);
		return huffmanCodes;
	}

	/**
	 * 功能：将传入的node节点的所有叶子节点的coding，并放入到huffmanCodes
	 * 
	 * @param node          传入的节点，默认从根节点
	 * @param code          代表路径：左是0，右是1
	 * @param stringBuilder 用于拼接路径
	 */
	private static void getCodes(Node1 node, String code, StringBuilder stringBuilder) {
		StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
		// 将传入的code加入到stringBuilder2
		stringBuilder2.append(code);
		if (node != null) { // 如果node == bull 不处理
			// 判断当前node是叶子节点还是非叶子节点
			if (node.data == null) { // 非叶子节点
				// 递归处理
				// 向左
				getCodes(node.left, "0", stringBuilder2);
				// 向右
				getCodes(node.right, "1", stringBuilder2);
			} else { // 说明是叶子节点
				// 表示找到了某个叶子节点的最后
				huffmanCodes.put(node.data, stringBuilder2.toString());
			}
		}
	}

	// 编写一个方法，将字符串对应的byte[]数组，用过生成的赫夫曼编程表，返回一个压缩后的byte数组
	// 举例：String content = "i like like like java do you like a java" => byte[]
	// contentBytes
	/**
	 * @param bytes        原始字符串对应的byte[]
	 * @param huffmanCodes 生成的赫夫曼编码Map
	 * @return 返回赫夫曼编码处理后的byte[] 返回的是这个字符串"101010001011111~~" => 对应的byte[]数组
	 *         huffmanCodeBytes，8位对应一个byte huffmanCodeBytes[0] = 10101000(补码) =>
	 *         byte => [推导 => 10101000 - 1 => 10100111(反码) => 11011000 => -88]
	 */
	public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {

		// 1. 利用huffmanCode将Bytes转成赫夫曼编码对应的字符串
		StringBuilder stringBuilder = new StringBuilder();
		// 遍历bytes[]数组
		for (byte b : bytes) {
			stringBuilder.append(huffmanCodes.get(b));
		}

		System.out.println("stringBuilder= " + stringBuilder.toString());

		// 将"101010001011111~~"转成byte[]数组
		// 统计返回的byte[]数组 huffmanCodeBytes的长度
		// 用一句话 int length = (stringBuilder.length() + 7) / 8;
		int length;
		if (stringBuilder.length() % 8 == 0) {
			length = stringBuilder.length() / 8;
		} else {
			length = stringBuilder.length() / 8 + 1;
		}

		// 创建一个存储压缩后的bytes数组
		byte[] huffmanCodeBytes = new byte[length];
		int index = 0; // 记录是第几个byte
		
		for (int i = 0; i < stringBuilder.length(); i += 8) { // 每8位对应一个byte，所以布长+8
			String strByte;
			if (i + 8 > stringBuilder.length()) { // 不够8位
				strByte = stringBuilder.substring(i);
			} else {
				strByte = stringBuilder.substring(i, i + 8);
			}
			// 将strByte转成一个byte，放入到huffmanCodeBytes
			huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
			index++;
		}
		return huffmanCodeBytes;
	}

	// 完成数据的解压
	// 1. 将HuffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
	//    重写转成，Huffman Code对应的二进制的字符串 "1010100010111..."
	// 2. 将对应字符串 =》 对照Huffman Code =》 "i like like like java do you like a java"

	/**
	 * 将一个byte转换成一个二进制的字符串
	 * 
	 * @param b
	 * @param flag 标示是否需要高位，如果是true表示补高位，否则不补；如果是最后字节，无需补高位
	 * @return 是该b对应的二进制的字符串（注意是按补码返回）
	 */
	private static String byteToBitString(boolean flag, byte b) {
		// 使用变量保存b
		int temp = b; // 将b转成int

		// 如果是正树我们还存在补高位
		if (flag) {
			temp |= 256; // 按位或 256 1 0000 0000 | 0000 0001 => 1 0000 0001
		}

		String str = Integer.toBinaryString(temp); // 返回的是temp对应的二进制的补码

		if (flag) {
			return str.substring(str.length() - 8);
		} else {
			return str;
		}

	}

	// 压缩数据的解码
	/**
	 * 
	 * @param huffmanCodes 赫夫曼编码表 Map
	 * @param huffmanBytes 赫夫曼编码得到的字节数组
	 * @return 返回原来的字符串对应的数组
	 */
	public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {

		// 1. 先得到HuffmanBytes对应的二进制的字符串"1010100010111..."
		StringBuilder stringBuilder = new StringBuilder();

		// 将byte数组转成二进制的字符串
		for (int i = 0; i < huffmanBytes.length; i++) {
			byte b = huffmanBytes[i];
			// 判断是否为最后字节
			boolean flag = (i == huffmanBytes.length - 1);
			stringBuilder.append(byteToBitString(!flag, b));
		}
		System.out.println("赫夫曼字节数组对应的二进制字符串= " + stringBuilder.toString());

		// 把字符串按照指定的Huffman Code进行编码
		// 把Huffman Coding进行调换，反向查询 a->100 100->a
		Map<String, Byte> map = new HashMap<String, Byte>();
		for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
			map.put(entry.getValue(), entry.getKey());
		}
		System.out.println("Map= " + map);

		// 创建一个集合，存放byte
		List<Byte> list = new ArrayList<Byte>();
		// i 可以理解成索引，扫描stringBuilder
		for (int i = 0; i < stringBuilder.length(); ) {
			int count = 1; // 小的计数器，扫描编码
			boolean flag = true;
			Byte b = null;

			while (flag) {
				// 递增地取出key，'1'或者'0'
				String key = stringBuilder.substring(i, i + count); // i不动，让count移动，指定匹配到一个字符
				b = map.get(key);
				if (b == null) { // 说明没有匹配到
					count++;
				} else {
					// 匹配到
					flag = false;
				}
			}
			list.add(b);
			i += count; // 让i直接移动到count
		}
		// 当for循环结束后，list中存放了所有的字符 "i like like like java do you like a java"
		// 把list中的数据放到byte数组，并返回
		byte[] b = new byte[list.size()];
		for (int i = 0; i < b.length; i++) {
			b[i] = list.get(i);
		}
		return b;
	}
	
	// 将文件进行压缩
	/**
	 * 
	 * @param srcFile 希望压缩的文件的全路径
	 * @param dstFile 压缩后存放文件的目录
	 */
	public static void zipFile(String srcFile, String dstFile) {

		// 创建输出流
		OutputStream os = null;
		ObjectOutputStream oos = null;
		// 创建文件的输入流
		FileInputStream is = null;

		try {
			is = new FileInputStream(srcFile);
			
			// 创建一个和源文件大小一样的byte[]
			byte[] b = new byte[is.available()];
			
			// 读取文件
			is.read(b);
			
			// 直接对源文件压缩
			byte[] huffmanBytes = huffmanZip(b);
			
			// 创建文件的输入流，存放压缩文件
			os = new FileOutputStream(dstFile);
			
			// 创建一个和文件输出流关联的ObjectOutputStream
			oos = new ObjectOutputStream(os);
			
			//  把Huffman Coding后的字节数组，写入压缩数组
			oos.writeObject(huffmanBytes); 
			
			// 这里以对象流的方式写入Huffman Coding，为了以后恢复源文件时使用
			// 一定要把Huffman Coding写入压缩文件
			oos.writeObject(huffmanCodes);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {
			try {
				is.close();
				os.close();
				oos.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
	}
	
	// 解压文件
	/**
	 * 
	 * @param zipFile 准备解压的文件
	 * @param dstFile 解压后存放文件的目录
	 */
	@SuppressWarnings("unchecked")
	public static void unZipFile(String zipFile, String dstFile) {

		// 定义文件输入流
		InputStream is = null;
		// 定义对象输入流
		ObjectInputStream ois = null;
		// 定义文件输出流
		OutputStream os = null;

		try {
			// 创建文件输入流
			is = new FileInputStream(zipFile);
			// 创建一个和 is 关联的对象输入流
			ois = new ObjectInputStream(is);

			// 读取byte数组：huffmanBytes
			byte[] huffmanBytes = (byte[]) ois.readObject();

			// 读取Huffman Code
			Map<Byte, String> huffmanCodes = (Map<Byte, String>)ois.readObject();

			// 解码
			byte[] bytes = decode(huffmanCodes, huffmanBytes);

			// 将bytes写入到目标文件，创建文件输出流
			os = new FileOutputStream(dstFile);

			// 写出数据到文件中
			os.write(bytes);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {
			
			try {
				os.close();
				ois.close();
				is.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
	}
}

class Node1 implements Comparable<Node1> {
	Byte data; // 存放数据（字符）本身，比如'a' => 97 ; ' ' => 32
	int weight; // 权值，表示字符出现的字数
	Node1 left;
	Node1 right;

	public Node1(Byte data, int weight) {
		this.data = data;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node1 o) {
		// 从小到大排序
		return this.weight - o.weight;
	}

	@Override
	public String toString() {
		return "Node [data= " + data + ", weight= " + weight + "]";
	}

	// 前序遍历
	public void preOrder() {
		System.out.println(this);
		if (this.left != null) {
			this.left.preOrder();
		}
		if (this.right != null) {
			this.right.preOrder();
		}
	}
}