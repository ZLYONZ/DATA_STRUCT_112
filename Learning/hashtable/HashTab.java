package hashtable;

//创建HashTab 管理多条链表
class HashTab {
	private EmpLinkedList[] empLinkedListArray;
	private int size;

	// 构造器
	public HashTab(int size) {
		this.size = size;
		// 初始化empLinkedListArray
		empLinkedListArray = new EmpLinkedList[size];
		for (int i = 0; i < size; i++) {
			empLinkedListArray[i] = new EmpLinkedList();
		}
	}

	// add employee
	public void add(Employee emp) {
		// 根据员工的id的得到该员工应当添加到哪条链表
		int empLinkListNum = hashFun(emp.id);
		// 将emp添加到对应的链表中
		empLinkedListArray[empLinkListNum].add(emp);
	}

	// 遍历所有链表
	public void list() {
		for (int i = 0; i < size; i++) {
			empLinkedListArray[i].list(i);
		}
	}

	// 使用散列函数，取模
	public int hashFun(int id) {
		return id % size;
	}

	// find employee using id
	public void findEmpByID(int id) {
		// 使用散列函数，确定在哪条链表中查找
		int empLinkListNum = hashFun(id);
		Employee emp = empLinkedListArray[empLinkListNum].findEmpByID(id);
		if (emp != null) {
			System.out.printf("在第 %d 条链表中找到雇员 id = %d\n", empLinkListNum + 1, id);
		} else {
			System.out.println("未找到该雇员~");
		}
	}

	// delete employee
	public void deleteEmp(int id) {

		// 使用散列函数，确定在哪条链表中查找
		int empLinkListNum = hashFun(id);
		Employee emp = empLinkedListArray[empLinkListNum].deleteEmp(id);

		empLinkedListArray[empLinkListNum].deleteEmp(id);

		while (empLinkedListArray[empLinkListNum] != null) { // 不等于空
			if (emp != null) {
				System.out.printf("在第 %d 条链表中删除雇员 id = %d\n", empLinkListNum + 1, id);
			} else {
				System.out.println("未找到该雇员~");
			}
		}
	}
}