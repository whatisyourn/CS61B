package bomb;

import common.IntList;

public class BombMain {
    public static void main(String[] args) {
        int phase = 2;
        if (args.length > 0) {
            phase = Integer.parseInt(args[0]);
        }
        // TODO: Find the correct passwords to each phase using debugging techniques
        Bomb b = new Bomb();
        if (phase >= 0) {
            b.phase0("39291226");
        }
        if (phase >= 1) {
            IntList p = IntList.of(0,9,3,0,8);
            b.phase1(p); // Figure this out too
        }
        if (phase >= 2) {
            String number = "";
            int i =0 ;
            while (i < 100000) {
                if(i == 1337){
                    number += "-81201430 ";
                }else {
                    number += "1 ";
                }
                ++i;
            }
            b.phase2(number);
        }
    }
}
