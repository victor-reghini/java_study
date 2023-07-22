public class ExercicioControleFluxo {
    public static void main(String[] args) {
//        salário < 1000 => 5% imposto
//        salário >= 1000 e < 2000 => 10% imposto
//        salário >= 2000 e < 4000 => 15% imposto
//        salário >= 4000 20% imposto
        int salario = 900;
        if(salario < 1000) {
            System.out.println("Salário: " + salario + " Imposto (5%): " + (salario * 0.05));
        } else if(salario >= 1000 && salario < 2000) {
            System.out.println("Salário: " + salario + " Imposto (10%): " + (salario * 0.1));
        } else if(salario >= 2000 && salario < 4000) {
            System.out.println("Salário: " + salario + " Imposto (15%): " + (salario * 0.15));
        } else {
            System.out.println("Salário: " + salario + " Imposto (20%): " + (salario * 0.2));
        }
    }
}
