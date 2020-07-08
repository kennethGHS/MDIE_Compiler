package com.comparators;

public class TypeComparator {
    /**
     * get what kind of type the instructions belongs to
     * @param operation
     * @return
     */
    public static int type(String operation) {
        switch (operation) {
            case "add":
            case "sub":
            case "mul":
            case "ldw":
            case "ldh":
            case "stw":
            case "sth":
            case "jr":
            case "sl":
            case "and":
            case "or":
                return 1;
            case "addi":
            case "subi":
            case "be":
            case "bgt":
                return 2;
            case "jmp":
            case "call":
                return 3;
            case "vmul":
            case "vsr":
            case "vsub":
            case "vcsub":
            case "vldw":
            case "vldh":
            case "vstw":
            case "vsth":
                return 4;
            default:
                return -1;

        }
    }
}
