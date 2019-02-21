import java.util.Scanner;

public class TUI {

   // databaselag d = new databaselag();

    Scanner sc = new Scanner(System.in);

    public String insert1(){
        System.out.println("vælg id: ");
        return sc.next();
    }
    public String insert2(){
        System.out.println("vælg indhold: ");
        return sc.next();
    }
    public void menu(){
        /*
        System.out.println("\n Muligheder"+ "\n1. Opret bruger \n" + "2. Vis brugere\n"+ "3. Opdater bruger\n"+ "4. slet bruger\n");
        System.out.println("hvad er dit valg: ");
        int valg=sc.nextInt();
        if(valg==1){d.opretBruger();}
        else if(valg==2){d.visBrugere();}
        else if(valg==3){d.opdaterBruger();}
        else if(valg==4){d.sletBruger();}
        */
    }
}
