package No;

/**
 * Guarda as informações necessárias de um Nó da Árvore Binária.
 * Armazena o tipo <code> int </code>
 * 
 * @author João Guilherme M. Guimarães
 */
public class No
{
    private int m_Item;
    private No m_Esquerda, m_Direita;
    
    /**
     * Abstração do objecto Nó.
     * 
     * @param p_Item valor a ser armazenado no Nó
     */
    public No(int p_Item)
    {
        this.m_Item = p_Item;
        m_Esquerda = null;
        m_Direita = null;
    }
    
    /**
     * @return o valor inteiro armazenado no Nó
     */
    public int getItem()
    {
        return this.m_Item;
    }
    
    /**
     * @param p_Item atribuição do valor do Item
     */
    public void setItem(int p_Item)
    {
        this.m_Item = p_Item;
    }
    
    /**
     * @return o Nó a esquerda
     */
    public No getNoEsquerda()
    {
        return this.m_Esquerda;
    }
    
    /**
     * @param p_Esquerda atribuição do Nó a esquerda
     */
    public void setNoEsquerda(No p_Esquerda)
    {
        this.m_Esquerda = p_Esquerda;
    }
    
    /**
     * @return o Nó a direita
     */
    public No getNoDireita()
    {
        return this.m_Direita;
    }
    
    /**
     * @param p_Direita atribuição do Nó a direita
     */
    public void setNoDireita(No p_Direita)
    {
        this.m_Direita = p_Direita;
    }
}
