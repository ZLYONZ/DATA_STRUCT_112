package hashtable;

//表示一个雇员
class Employee {

	public int id;
	public String name;
	public Employee next; // 默认为null

	public Employee(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}