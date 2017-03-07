package ekkel.book.innerclasses.callbacks;

/**
 * Created by cresh on 01.07.16.
 */
class CallBacks {
    public static void main(String[] args) {
        SimpleCallee simpleCallee = new SimpleCallee();
        Callee callee = new Callee();
        MyIncrement.f(callee);
        Caller callerOne = new Caller(simpleCallee);
        Caller callerSec = new Caller(callee.getCallBackReference());
        callerOne.go();
        callerOne.go();
        callerSec.go();
        callerSec.go();
    }
}
