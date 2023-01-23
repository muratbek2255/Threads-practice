package java_util_concurent;

import java.util.concurrent.Exchanger;

public class ExgrDemo {
    public static void main(String[] args) {

    }
}


class MakeString extends Thread {
    Exchanger<String> ex;
    String str;
    MakeString(Exchanger<String> ex) {
        this.ex = ex;
        str = new String();
    }

    @Override
    public void run() {
        char ch = 'A';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                str += (char) ch++;
            }
        }
        try {
            str = ex.exchange(str);
        }catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
}
