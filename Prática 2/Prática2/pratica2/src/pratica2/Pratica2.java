package pratica2;

import MyRandom.MyRandom;
import java.util.HashSet;

public class Pratica2
{
    public static void main(String[] p_Args)
    {
//        gerarDados(10000);
//        gerarDados(20000);
//        gerarDados(30000);
//        gerarDados(40000);
//        gerarDados(50000);
//        gerarDados(60000);
//        gerarDados(70000);
//        gerarDados(80000);
//        gerarDados(90000);
        gerarDados(100000);
    }
    
    /**
     * Encapsulador dos métodos de geração de dados.
     * 
     * facilitador da execução dos testes
     * 
     * @param p_Quantidade quantidade de números que serão inseridos na Árvore
     */
    private static void gerarDados(int p_Quantidade)
    {
//        dadosOrdenados(p_Quantidade);
        dadosAleatorios(p_Quantidade);
    }
    
    /**
     * Geração dos dados de Número de Comparações e Tempo Gasto, de uma Árvore
     * SBB com inserções de valores Ordenados de forma crescente.
     * 
     * @param p_Quantidade quantidade de elementos a serem inseridos na Árvore
     */
    private static void dadosOrdenados(int p_Quantidade)
    {
        int v_Valor;
        MyRandom v_Random = new MyRandom();
        ArvoreSBB v_ArvoreSBB = new ArvoreSBB();
        HashSet<Integer> v_Lista = listaOrdenada(p_Quantidade);
        
        v_Lista.stream().forEach((c_Item) -> { v_ArvoreSBB.inserir(c_Item); });
        
        // Obter um valor que não existe na Árvore
        do
        {
            v_Valor = v_Random.nextPositiveInt();
        } while(v_Lista.contains(v_Valor));
        
        long v_Inicio = System.nanoTime();
        
        v_ArvoreSBB.pesquisar(v_Valor);
        
        long v_Fim = System.nanoTime();
        
        System.out.println("Tamanho: " + p_Quantidade);
        System.out.println("Item: " + v_Valor);
        System.out.println("Comparações: " + v_ArvoreSBB.getNumeroDeComparacoes());
        System.out.printf("Tempo Gasto: %.4f \n\n", (v_Fim - v_Inicio) * Math.pow(10, -6));
    }
    
    /**
     * Geração dos dados de Número de Comparações e Tempo Gasto, de uma Árvore
     * SBB com inserções de valores Aleatórios.
     * 
     * @param p_Quantidade quantidade de elementos a serem inseridos na Árvore
     */
    private static void dadosAleatorios(int p_Quantidade)
    {
        int v_Valor;
        MyRandom v_Random = new MyRandom();
        ArvoreSBB v_ArvoreSBB = new ArvoreSBB();
        HashSet<Integer> v_Lista = listaAleatoria(p_Quantidade);
        
        v_Lista.stream().forEach((c_Item) -> { v_ArvoreSBB.inserir(c_Item); });
        
        // Obter um valor que não existe na Árvore
        do
        {
            v_Valor = v_Random.nextPositiveInt();
        } while(v_Lista.contains(v_Valor));
        
        long v_Inicio = System.nanoTime();
        
        v_ArvoreSBB.pesquisar(v_Valor);
        
        long v_Fim = System.nanoTime();
        
        System.out.println("Tamanho: " + p_Quantidade);
        System.out.println("Item: " + v_Valor);
        System.out.println("Comparações: " + v_ArvoreSBB.getNumeroDeComparacoes());
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
