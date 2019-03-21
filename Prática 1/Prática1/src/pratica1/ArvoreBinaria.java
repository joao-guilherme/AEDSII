package pratica1;

import No.No;

/**
 * Árvore Binária não balanceada.
 * 
 * @author João Guilherme M. Guimarães
 */
public class ArvoreBinaria
{
    private No m_Raiz;
    
    private int m_Comparacoes;
    
    /**
     * Inicializa a Árvore com a Raiz igual a null
     */
    public ArvoreBinaria()
    {
        this.m_Raiz = null;
    }
    
    /**
     * @return número de comparações realizadas na última pesquisa
     */
    public int getComparacoes()
    {
        return this.m_Comparacoes;
    }
    
    /**
     * @return o Nó que esta localizado na Raiz
     */
    private No getRaiz()
    {
        return this.m_Raiz;
    }
    
    /**
     * @param p_Raiz atribuição do Nó Raiz
     */
    private void setRaiz(No p_Raiz)
    {
        this.m_Raiz = p_Raiz;
    }
    
    /**
     * Inserção de um novo valor na Árvore Binária.
     * 
     * O local da inserção ocorre no momento em que se obtém uma pesquisa sem
     * sucesso. O processo de inserção por recursão é inicializado na Raiz.
     * 
     * @param p_Item valor inteiro a ser inserido na Árvore caso este não exista
     */
    public void inserir(int p_Item)
    {
        this.setRaiz(this.insere(p_Item, this.getRaiz()));
    }
    
    /**
     * Método recursivo para inserir um item na Árvore, dado um Nó para
     * pesquisar.
     * 
     * @param p_Item valor inteiro a ser inserido na Árvore caso este não exista
     * @param p_No Nó a ser realizado a pesquisa a procura do local onde será
     * inserido o novo item
     * @return Nó onde foi realizado a pesquisa/inserção
     */    
    private No insere(int p_Item, No p_No)
    {
        if(p_No == null)
        {
            p_No = new No(p_Item);
        }
        else if(p_Item < p_No.getItem())
        {
            p_No.setNoEsquerda(insere(p_Item, p_No.getNoEsquerda()));
        }
        else if(p_Item > p_No.getItem())
        {
            p_No.setNoDireita(insere(p_Item, p_No.getNoDireita()));
        }
        else
        {
            System.out.println("Registro já existente.");
        }
        
        return p_No;
    }
    
    /**
     * Pesquisar se o elemento se encontra na Árvore.
     * 
     * @param p_Item valor a ser pesquisado na Árvore
     * @return caso a pesquisa seja concluída com sucesso, será retornado o Nó
     * no qual o elemento foi encontrado, caso contrário, será retornado o valor
     * <code> null </code>
     */
    public No pesquisa(int p_Item)
    {
        m_Comparacoes = 0;
        
        return this.pesquisa(p_Item, this.getRaiz());
    }
    
    private No pesquisa(int p_Item, No p_No)
    {
        m_Comparacoes++;
        
        if(p_No == null) return null;
        
        if(p_Item < p_No.getItem())
        {
            m_Comparacoes++;
            
            return this.pesquisa(p_Item, p_No.getNoEsquerda());
        }
        else if(p_Item > p_No.getItem())
        {
            m_Comparacoes += 2;
            
            return this.pesquisa(p_Item, p_No.getNoDireita());
        }
        else
        {
            m_Comparacoes += 2;
            
            return p_No;
        }
    }
    
    /**
     * Exibição da estrutura atual da Árvore.
     */
    public void exibir()
    {
        this.exibir(this.getRaiz());
    }
    
    /**
     * Método recursivo para exibição da estrutara da Árvore dado um Nó de início.
     * 
     * @param p_No local onde será dado o início da exibição da estrutura da
     * Árvore
     */
    private void exibir(No p_No)
    {   
        Object v_Esquerda = p_No.getNoEsquerda() == null ? "N" : p_No.getNoEsquerda().getItem();
        Object v_Direita = p_No.getNoDireita() == null ? "N" : p_No.getNoDireita().getItem();
        
        System.out.println("| " + v_Esquerda.toString() + " | " + p_No.getItem() + " | "
                + v_Direita.toString() + " |");
        
        if(p_No.getNoEsquerda() != null)
        {
            this.exibir(p_No.getNoEsquerda());
        }
        
        if(p_No.getNoDireita() != null)
        {
            this.exibir(p_No.getNoDireita());
        }
    }
}
