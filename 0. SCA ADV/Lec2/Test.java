class Emp{
    public Emp(){
        System.out.println("Constructor Called...");
    }
    static{
        System.out.println("Static block called");
    }
}

class Test{
    public static void main(String[] args) {
        // Emp e = new Emp();
        // Emp f = new Emp();

        try{
            Class.forName("Empl");

        }catch(ClassNotFoundException ex){
            System.out.println("Sorry! Class not loaded");
        }
    }
}