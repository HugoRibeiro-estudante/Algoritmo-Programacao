import java.util.Scanner;
public class Main
{
    static Scanner s = new Scanner(System.in);
	public static void main(String[] args) {
	    
        System.out.print("Informe a quantidade de clientes:\n");
        int n = s.nextInt();
        
        String vetNome[] = new String[n];
        String vetTel[] = new String[n];
        int vetTipo[] = new int[n];
        int vetMin[] = new int[n];
        double vetValor[] = new double[n];
        
        //lendo informações
        for(int i = 0; i < n; i ++){
            
            s.nextLine(); //Limpar buffer
            System.out.printf("Dados do %dº cliente:\n", i+1);

            System.out.print("Nome:\n");
            vetNome[i] = s.nextLine();
            System.out.print("Telefone:\n");
            vetTel[i] = s.nextLine();
            
            do{
                System.out.print("Tipo:     //(entre 0 e 2)\n");
                vetTipo[i] = s.nextInt();
            }while(vetTipo[i] > 2 || vetTipo[i] < 0);
            
            System.out.print("Minutos:\n");
            vetMin[i] = s.nextInt();
            
        }
        
        //Chamando função para ler matriz de valores de cada plano
        // Passando valores lidos para a função que calculaPreco
        // vetValor recebendo valores calculados
        vetValor = calculaPreco(lerPreco(), vetMin, vetTipo);
        
        int opcao;
        
        do{
        
        //Recendo valor da função que exibe o Menu e recebe o valor
        opcao = mostraMenu();
        System.out.println();
        
        //Direcionando a execução da função escolhida pelo usuário
	    switch(opcao){
	        
            case 1: relatorio(vetNome, vetTel, vetTipo, vetMin, vetValor);
            break;  
            
            case 2: receita(vetValor);
            break;  
            
            case 3: maisBarata(vetNome, vetTel, vetValor);
            break; 
            
            case 4: mediaTipo1(vetTipo, vetValor);
            break; 
            
            case 5: acima(vetMin);
            break; 
            
            case 6: porcTipo2(vetTipo);
           
            }
        
        }while(opcao != 7);    
	    
	   System.out.println(); 
	}
	
	//Lendo valores de cada plano
	public static double[][] lerPreco(){
	    
	    double mat[][] = new double[3][2];
	    System.out.println("Informe o preco basico e excedente de cada tipo de conta:");
	    for (int i = 0; i < 3; i++){
	        
	       System.out.printf("Tipo %d:\n", i);
	        
	       for (int j = 0; j < 2; j++ ) {
	   
	            mat[i][j] = s.nextDouble();
	       } 
	   } 
	    
	   return mat;
	}
	
	public static int mostraMenu(){
	    
	    System.out.println("MENU DE OPCOES:");
        System.out.println("1) Relatorio de clientes");
        System.out.println("2) A receita total");
        System.out.println("3) Conta foi mais barata");
        System.out.println("4) Consumo medio de clientes tipo 1.");
        System.out.println("5) Clientes que consumiram acima de 120 pulsos.");
        System.out.println("6) A porcentagem de clientes tipo 2");
        System.out.println("7) Sair");
        System.out.print("Informe uma opcao: ");
        
	    return s.nextInt();
	   
	}
	

	
	public static double[] calculaPreco(double[][] mat, int[] vetMin, int[] vetTipo){
	    
	    double vetPreco[] = new double[vetTipo.length];
	    
	    //Lendo matriz de preços de planos
	     for (int i = 0; i < 3; i++){
	        for (int j = 0; j < 2; j++ ) {
	            
	            //"For" para receber valor a pagar, onde cada index representa o valor de um cliente 
	            for (int x = 0; x < vetTipo.length ; x++ ){ 
    	            if(vetMin[x] <= 90){
    	                                       // Se Minutos < 90 então o valor sera o preço básico
    	               switch(vetTipo[x]){
    	                    case 0: vetPreco[x] = mat[0][0];
    	                    break;     
    	                    case 1: vetPreco[x] = mat[1][0];
    	                    break;     
    	                    case 2: vetPreco[x] = mat[2][0];
    	                   } 
    	                   
    	                }else{
    	                                        // Acresentando acréscimos por minuto excedente
    	                  switch(vetTipo[x]){
    	                    case 0: vetPreco[x] = (double) ((vetMin[x] - 90) * mat[0][1] ) + mat[0][0];
    	                    break;     
    	                    case 1: vetPreco[x] = (double) ((vetMin[x] - 90) * mat[1][1] ) + mat[1][0];
    	                    break;     
    	                    case 2: vetPreco[x] = (double) ((vetMin[x] - 90) * mat[2][1] ) + mat[2][0];
    	                    
    	                    }
    	                    
    	                }
	                }     
	           } 
	        } 
	        
	        return vetPreco;
	}
	
	//Exibindo Relatório
	public static void relatorio(String[] vetNome, String[] vetTel, int[] vetTipo, int[] vetMin, double[] vetValor){
	    
	    System.out.println("\t-------Relatorio-------\n");
	    for (int i = 0; i < vetNome.length ; i++ ) 
	        
            System.out.printf("%d - %s, %s, Tipo: %d, Minutos: %d, Conta: R$%.2f\n",i , vetNome[i], vetTel[i], vetTipo[i], vetMin[i], vetValor[i]);
            System.out.println("\n\t-------//-------\n");
	
	    }
	
	//Receita total
	public static void receita(double[] vetValor){
	     
        double soma = 0;
        System.out.println("\t-------Receita total-------\n");
        
        for (int i = 0; i < vetValor.length ; i++ )
            soma += vetValor[i];
        
        System.out.printf("Soma de todas as receitas: %.2f\n", soma);
        System.out.println("\n\t-------//-------\n");
	}
	
	public static void maisBarata(String[] vetNome, String[] vetTel, double[] vetValor){
	   
	    System.out.println("\t-------Conta mais barata-------\n");
        double menor = 1000000;
        String nome = ""; 
        String tel = "";
         
        for (int i = 0; i < vetNome.length ; i++ ) 
            
            if(vetValor[i] < menor){
                
                menor = vetValor[i];
                nome = vetNome[i];
                tel = vetTel[i];
            }
                
        System.out.printf("A conta mais barata foi a do cliente: %s  tel: %s\n", nome, tel); 
        System.out.println("\n\t-------//-------\n");
	}
	
	public static void mediaTipo1(int[] vetTipo, double[] vetValor){
	     
        double media = 0;
        int count = 0;
        System.out.println("\t-------Media tipo 1-------\n");
        
        for (int i = 0; i < vetValor.length ; i++ ){
            if(vetTipo[i] == 1){
                
                media += vetValor[i];
                count++;
            }
                
             
        }    
        media = media/count; 
        System.out.printf("A media dos clientes de conta tipo 1: %.2f\n", media);
        System.out.println("\n\t-------//-------\n");
	}
	
	public static void acima(int[] vetMin){
	      
        int acima = 0;
        System.out.println("\t-------Acima de 120 minutos-------\n");
        
        for (int i = 0; i < vetMin.length ; i++ ) 
            if(vetMin[i] > 120) 
                acima++; 
           
        System.out.printf("Quantidade de clientes que consumiram acima de 120 minutos: %d\n", acima);
        System.out.println("\n\t-------//-------\n");
	}
	
	//Porcentagem do tipo 2
	public static void porcTipo2(int[] vetTipo){
	                
        double tipo2 = 0;
        System.out.println("\t-------Porcentagem tipo 2-------\n");
        
        for (int i = 0; i < vetTipo.length ; i++ ) 
            if(vetTipo[i] == 2) 
                tipo2++;
        
        tipo2 *=  100/vetTipo.length;
        System.out.printf("A porcentagem de clientes tipo 2: %.1f%%\n", tipo2);
        System.out.println("\n\t-------//-------\n");
                
	}
	
}









