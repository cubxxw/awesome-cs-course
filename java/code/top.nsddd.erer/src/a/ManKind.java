package a;

public class ManKind {
    private int sex;
    private int salary;
    public ManKind(int sex, int salary){
        sex = 12;
        salary = 1;
    }
    public ManKind(){}

    public void manOrWoman(){
        if(sex == 1 ){
            System.out.println("main");
        }else if(sex == 0){
            System.out.println("womain");
        }
    }
    public void emloyeed(){
        String s = (salary == 0) ? "no jobs" : "job";
        System.out.println("jobInfo");
    }
}
