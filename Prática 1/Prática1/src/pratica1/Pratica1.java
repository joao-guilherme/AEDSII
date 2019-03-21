package pratica1;

import MyRandom.MyRandom;
import java.util.HashSet;

public class Pratica1 {
    
    public static void main(String[] args)
    {
        // Código não utilizado para evitar performance do Java.
        // for(int c_Tamanho = 1000; c_Tamanho <= 9000; c_Tamanho += 1000)
        // {
        //     gerarDados(c_Tamanho);
        // }
        
//        gerarDados(1000);
//        gerarDados(2000);
//        gerarDados(3000);
//        gerarDados(4000);
//        gerarDados(5000);
//        gerarDados(6000);
//        gerarDados(7000);
//        gerarDados(8000);
        gerarDados(9000);
    }
    
    /**
     * Gerar os dados a serem plotados.
     * 
     * @param p_Tamanho número de itens a serem inseridos na Árvore
     */
    public static void gerarDados(int p_Tamanho)
    {
        // É necessário que somente um esteja executando, devido aos ganhos de
        // performance do Java.
        
        // dadosOrdenados(p_Tamanho);
        dadosAleatorios(p_Tamanho);
    }
    
    public static void dadosOrdenados(int p_Tamanho)
    {
        int v_Valor;
        MyRandom v_Random = new MyRandom();
        ArvoreBinaria v_Arvore = new ArvoreBinaria();
        HashSet<Integer> v_Itens = listaOrdenada(p_Tamanho);
        
        v_Itens.stream().forEach((c_Item) -> { v_Arvore.inserir(c_Item); });
        
        // Obter um valor que não existe na Árvore
        do
        {
            v_Valor = v_Random.nextInt(p_Tamanho * p_Tamanho);
        } while(v_Itens.contains(v_Valor));
        
        long v_Inicio = System.nanoTime();
        
        v_Arvore.pesquisa(v_Valor);
        
        long v_Fim = System.nanoTime();
        
        System.out.println("Tamanho: " + p_Tamanho);
        System.out.println("Item: " + v_Valor);
        System.out.println("Comparações: " + v_Arvore.getComparacoes());
        System.out.printf("Tempo Gasto: %.4f \n\n", (v_Fim - v_Inicio) * Math.pow(10, -6));
    }
    
    public static void dadosAleatorios(int p_Tamanho)
    {
        int v_Valor;
        MyRandom v_Random = new MyRandom();
        ArvoreBinaria v_Arvore = new ArvoreBinaria();
        HashSet<Integer> v_Itens = listaAleatoria(p_Tamanho);
        
        v_Itens.stream().forEach((c_Item) -> { v_Arvore.inserir(c_Item); });
        
        // Obter um valor que não existe na Árvore
        do
        {
            v_Valor = v_Random.nextInt(p_Tamanho * p_Tamanho);
        } while(v_Itens.contains(v_Valor));
        
        long v_Inicio = System.nanoTime();
        
        v_Arvore.pesquisa(v_Valor);
        
        long v_Fim = System.nanoTime();
        
        System.out.println("Tamanho: " + p_Tamanho);
        System.out.println("Item: " + v_Valor);
        System.out.println("Comparações: " + v_Arvore.getComparacoes());
        System.out.printf("Tempo Gasto: %.4f \n\n", (v_Fim - v_Inicio) * Math.pow(10, -6));
    }
    
    /**
     * Cria uma lista ordenada de elementos começando pelo número Zero.
     * 
     * @param p_Tamanho tamanho da lista a ser criada
     * @return lista ordena de inteiros de tamanho <code>p_Tamanho</code>
     */
    public static HashSet<Integer> listaOrdenada(int p_Tamanho)
    {
        HashSet<Integer> v_Itens = new HashSet(p_Tamanho);
        
        for(int c_Index = 0; c_Index < p_Tamanho; c_Index++)
        {
            v_Itens.add(c_Index);
        }
        
        return v_Itens;
    }
    
    /**
     * Cria uma lista de números inteiros aleatórios.
     * 
     * @param p_Tamanho tamanho da lista a ser criada
     * @return lista de núemeros aleatórios de tamanho <code>p_Tamanho</code>
     */
    public static HashSet<Integer> listaAleatoria(int p_Tamanho)
    {
        MyRandom v_Random = new MyRandom();
        HashSet<Integer> v_Itens = new HashSet(p_Tamanho);
        
        while(v_Itens.size() != p_Tamanho)
        {
            v_Itens.add(v_Random.nextPositiveInt());
        }
        
        return v_Itens;
    }
}
