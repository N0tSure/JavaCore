package ekkel.book.innerclasses.hardtask;

/**
 * Created by cresh on 01.07.16.
 */
class MethArray {
    private Methods[] methes;
    private int index;

    private MethArray(final int size) {
        methes = new Methods[size];
        index=0;
    }

    void addMeth(Methods meth) {
        if (index<10) methes[index++] = meth;
    }

    int size() {
        return methes.length;
    }

    void delMeth(final int index) {
        if (index<0 || index>(methes.length-1)) throw new RuntimeException();
        Methods[] result = new Methods[methes.length-1];

        System.arraycopy(methes,0,result,0,index);
        System.arraycopy(methes,index+1,result,index,(methes.length-(index+1)));

        methes=result;
        if (index>0) this.index--;
    }

    void runMeth() {
        for (int i = 0; i < index; i++) {
            methes[i].first();
            methes[i].second();
            methes[i].third();
            System.err.println();
        }
    }

    public static void main(String[] args) {
        MethArray array = new MethArray(10);
        Anon anon = new Anon();
        for (int i = 0; i < 5; i++) array.addMeth(anon.getMethods());
        array.runMeth();

        System.err.println("trimmed\n");
        array.delMeth(3);
        array.runMeth();
    }
}
