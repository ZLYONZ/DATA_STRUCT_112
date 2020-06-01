package structures;

//implementation of the class Friend
class Friend {
	//declare the variables
	int fnum;
	Friend next;
	// argument constructor
	Friend(int fnum, Friend next) {
		this.fnum = fnum;
		this.next = next;
	}
}