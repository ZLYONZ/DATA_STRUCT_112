package hashtable;

import java.util.Scanner;

public class HashTable {

	public static void main(String[] args) {

		// 创建hashtable
		HashTab hashtab = new HashTab(7);

		// menu
		String key = "";
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("(a)dd：添加雇员");
			System.out.println("(l)ist：显示雇员");
			System.out.println("(f)ind：查找雇员");
			System.out.println("(d)elete：删除雇员");
			System.out.println("(e)xit：退出程序");

			key = scanner.next();
			switch (key) {
			case "a":
				System.out.println("输入id");
				int id = scanner.nextInt();
				System.out.println("输入名字");
				String name = scanner.next();

				// create employee
				Employee emp = new Employee(id, name);
				hashtab.add(emp);
				break;
			case "l":
				hashtab.list();
				break;
			case "f":
				System.out.println("请输入要查找的id");
				id = scanner.nextInt();
				hashtab.findEmpByID(id);
				break;
			case "d":
				System.out.println("请输入要删除的id");
				id = scanner.nextInt();
				hashtab.deleteEmp(id);
				break;
			case "e":
				scanner.close();
				System.exit(0);
			default:
				break;
			}
		}
	}
}