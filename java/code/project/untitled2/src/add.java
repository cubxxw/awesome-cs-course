public class add {
    public static void main(String[] args) {
        int inta = 5;
        int intb = 34;
        System.out.println(add(inta,intb));
        System.out.println(add(inta,intb));
        System.out.println(add(inta,intb));
        System.out.println(add(inta,intb));

    }
    public static int add(int inta,int intb) {
        return inta + intb;
    }
    public double add(double inta,double intb) {
        return inta + intb;
    }
    public double add(double  inta,int intb) {
        return inta + intb;
    }
    public double add(int inta,double intb) {
        return inta + intb;
    }
}
