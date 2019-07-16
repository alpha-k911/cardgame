/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign2;

/**
 *
 * @author Nandishwar
 */
public class try1 {
    static int p = 0;
    public static void main(String[] args) {
        System.out.println("init p: "+p);
        try1 i = new try1();
        i.fn();
        System.out.println("fin p: "+p);
    }
    private void fn(){
        p = 1;
        System.out.println("p: "+p);
    }
}
