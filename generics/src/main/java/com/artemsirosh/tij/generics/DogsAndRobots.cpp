#include <iostream>
using namespace std;

class Dog {
public:
	void speak() {
		cout << "Gav!" << endl;
	}
	void sit() {
		cout << "Sitting" << endl;
	}
	void reproduce() {}
};

class Robot {
public:
	void speak() {
		cout << "Click!" << endl;
	}
	void sit() {
		cout << "Clank!" << endl;
	}
	void oilChange() {}
};

class Mime {
public:
	void sit() {
		cout << "Pretend to sit" << endl;
	}
	void pushInvisibleWall() {}
};

template<class T> void perform(T anything) {
	anything.speak();
	anything.sit();
}

int main() {
	Dog d;
	Robot r;
	Mime m;
	
	perform(d);
	perform(r);
	//perform(m);
}
