#include <iostream>
using namespace std;

template<class T> class Manipulator {
	T t;
public:
	Manipulator(T x) {
		t = x;
	}
	void manipulate() {
		t.f();
	}
};

class HasF {
public:
	void f() {
		cout << "HasF::f()" << endl;
	}
};

int main() {
 HasF hF;
 Manipulator<HasF> manipulator(hF);
 manipulator.manipulate();
}
