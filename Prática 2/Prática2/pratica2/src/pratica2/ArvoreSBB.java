package pratica2;

import No.Inclinacao;
import No.No;

/**
 * Árvore SBB
 * 
 * @author João Guilherme M. Guimarães
 */
public class ArvoreSBB {
    private No m_Raiz;
    private boolean m_SBB;
    private int m_NumComparacoes = 0;
    
    /**
     * Inicializa a Árvore com a Raiz igual a <code>null</code>.
     */
    public ArvoreSBB()
    {
        this.m_Raiz = null;
        this.m_SBB = true;
    }
    
    /**
     * @return número de comparações executadas na última pesquisar realizada
     */
    public int getNumeroDeComparacoes()
    {
        return this.m_NumComparacoes;
    }
    
    /**
     * @return propriedade SBB da Árvore
     */
    private boolean getPropiedadeSBB()
    {
        return this.m_SBB;
    }
    
    /**
     * @param p_SBB valor atual da propriedade SBB.
     */
    private void setPropriedadeSBB(boolean p_SBB)
    {
        this.m_SBB = p_SBB;
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
     * Pesquisar se o elemento se encontra na Árvore.
     * 
     * @param p_Item valor a ser pesquisado na Árvore
     * @return caso a pesquisar seja concluída com sucesso, será retornado o Nó
 no qual o elemento foi encontrado, caso contrário, será retornado o valor
 <code>null</code>
     */
    public No pesquisar(int p_Item)
    {
        m_NumComparacoes = 0;
        return this.pesquisa(p_Item, this.getRaiz());
    }
    
    /**
     * Pesquisa recursiva até encontrar o item <code>p_Item</code> ou encontrar
     * um Nó Folha igual à <code>null</code>.
     * 
     * @param p_Item item a ser pesquisado na Árvore
     * @param p_No Nó no qual se dará o início da pesquisar
     * @return caso a pesquisar seja concluída com sucesso, será retornado o Nó
 no qual o elemento foi encontrado, caso contrário, será retornado o valor
 <code>null</code>
     */
    private No pesquisa(int p_Item, No p_No)
    {
        m_NumComparacoes++;
        if(p_No == null)
            return null;
        else if(p_Item > p_No.getItem())
        {
            m_NumComparacoes++;
            return this.pesquisa(p_Item, p_No.getNoDireita());
        }
        else if(p_Item < p_No.getItem())
        {
            m_NumComparacoes += 2;
            return this.pesquisa(p_Item, p_No.getNoEsquerda());
        }
        else
        {
            m_NumComparacoes += 2;
            return p_No;
        }
    }
    
    /**
     * Inserção de um novo valor na Árvore SBB.
     * 
     * O local da inserção ocorre no momento em que se obtém uma pesquisar sem
 sucesso. O processo de inserção por recursão é inicializado na Raiz.
     * 
     * @param p_Item valor inteiro a ser inserido na Árvore caso este não exista
     */
    public void inserir(int p_Item)
    {
        // Como o valor de p_Pai é igual a null, o último parâmetro nunca será
        // utilizado
        this.setRaiz(this.insere(p_Item, null, this.getRaiz(), true));
    }
    
    /**
     * Método recursivo para inserir um item na Árvore, dado um Nó anterior.
     * 
     * @param p_Item valor inteiro a ser inserido na Árvore caso este não exista
     * @param p_Pai Nó antecessor ao que esta sendo verificado a possível
     * inserção
     * @param p_Filho possível Nó no qual será atribuido o valor de <code>p_Item</code>
     * @param p_FilhoEsquerdo valor do tipo <code>boolean</code>, para saber
     * se o Nó <code>p_Filho</code> é maior ou não que o Nó <code>p_Pai</code>
     * @return Nó onde foi realizado a pesquisar/inserção
     */
    private No insere(int p_Item, No p_Pai, No p_Filho, boolean p_FilhoEsquerdo)
    {
        if(p_Filho == null) // Encontrado o Nó para inserção do valor p_Item
        {
            p_Filho = new No(p_Item);
            
            // Caso o Nó de inserção não seja o Nó Raiz, converter para um
            // inclinação Horizontal
            if(p_Pai != null)
            {
                if(p_FilhoEsquerdo)
                    p_Pai.setIncEsquerda(Inclinacao.HORIZONTAL);
                else
                    p_Pai.setIncDireita(Inclinacao.HORIZONTAL);
            }
            
            // Propriedade SBB (estrutural) foi violada
            this.setPropriedadeSBB(false);
        }
        else if(p_Filho.getItem() < p_Item)
        {
            p_Filho.setNoDireita(this.insere(p_Item, p_Filho, p_Filho.getNoDireita(), false));
            
            // É realizado uma recurssão para validação estrutural da Árvore até
            // a Raiz
            if(!this.getPropiedadeSBB())
                if(p_Filho.getIncDireita() == Inclinacao.HORIZONTAL)
                {
                    if(p_Filho.getNoDireita().getIncDireita() == Inclinacao.HORIZONTAL)
                    {
                        p_Filho = this.dd(p_Filho);
                        
                        if(p_Pai != null)
                            if(p_FilhoEsquerdo)
                                p_Pai.setIncEsquerda(Inclinacao.HORIZONTAL);
                            else
                                p_Pai.setIncDireita(Inclinacao.HORIZONTAL);
                    }
                    else if(p_Filho.getNoDireita().getIncEsquerda() == Inclinacao.HORIZONTAL)
                    {
                        p_Filho = this.de(p_Filho);
                        
                        if(p_Pai != null)
                            if(p_FilhoEsquerdo)
                                p_Pai.setIncEsquerda(Inclinacao.HORIZONTAL);
                            else
                                p_Pai.setIncDireita(Inclinacao.HORIZONTAL);
                    }
                }
                else
                    this.setPropriedadeSBB(true);
        }
        else if(p_Filho.getItem() > p_Item)
        {
            p_Filho.setNoEsquerda(this.insere(p_Item, p_Filho, p_Filho.getNoEsquerda(), true));
            
            if(!this.getPropiedadeSBB())
                if(p_Filho.getIncEsquerda() == Inclinacao.HORIZONTAL)
                {
                    if(p_Filho.getNoEsquerda().getIncEsquerda() == Inclinacao.HORIZONTAL)
                    {
                        p_Filho = this.ee(p_Filho);

                        if(p_Pai != null)
                            if(p_FilhoEsquerdo)
                                p_Pai.setIncEsquerda(Inclinacao.HORIZONTAL);
                            else
                                p_Pai.setIncDireita(Inclinacao.HORIZONTAL);
                    }
                    else if(p_Filho.getNoEsquerda().getIncDireita() == Inclinacao.HORIZONTAL)
                    {
                        p_Filho = this.ed(p_Filho);
                        
                        if(p_Pai != null)
                            if(p_FilhoEsquerdo)
                                p_Pai.setIncEsquerda(Inclinacao.HORIZONTAL);
                            else
                                p_Pai.setIncDireita(Inclinacao.HORIZONTAL);
                    }
                }
            else
                this.setPropriedadeSBB(true);
        }
        else
        {
            System.out.println("Registro já existente.");
            this.setPropriedadeSBB(true);
        }
        
        return p_Filho;
    }
    
    /**
     * Correção do problema de se ter duas inclinações horizontais seguidas do
     * tipo Esquerda-Esquerda.
     * 
     * @param p_No Nó onde foi encontrado o problema Esquerda-Esquerda
     * @return Nó central após correção
     */
    private No ee(No p_No)
    {
        No v_NoCentral = p_No.getNoEsquerda();
        
        p_No.setNoEsquerda(v_NoCentral.getNoDireita());
        v_NoCentral.setNoDireita(p_No);
        
        p_No.setIncEsquerda(Inclinacao.VERTICAL);
        v_NoCentral.setIncEsquerda(Inclinacao.VERTICAL);
        
        p_No = v_NoCentral;
        
        return p_No;
    }
    
    /**
     * Correção do problema de se ter duas inclinações horizontais seguidas do
     * tipo Esquerda-Direita.
     * 
     * @param p_No Nó onde foi encontrado o problema Esquerda-Esquerda
     * @return Nó central após correção
     */
    private No ed(No p_No)
    {
        No v_No1 = p_No.getNoEsquerda();
        No v_No2 = v_No1.getNoDireita();
        
        p_No.setNoEsquerda(v_No2.getNoDireita());
        v_No1.setNoDireita(v_No2.getNoEsquerda());
        
        v_No2.setNoDireita(p_No);
        v_No2.setNoEsquerda(v_No1);
        
        p_No.setIncEsquerda(Inclinacao.VERTICAL);
        v_No1.setIncDireita(Inclinacao.VERTICAL);
        
        p_No = v_No2;
        
        return p_No;
    }
    
    /**
     * Correção do problema de se ter duas inclinações horizontais seguidas do
     * tipo Direita-Direita.
     * 
     * @param p_No Nó onde foi encontrado o problema Esquerda-Esquerda
     * @return Nó central após correção
     */
    private No dd(No p_No)
    {
        No v_NoCentral = p_No.getNoDireita();
        
        p_No.setNoDireita(v_NoCentral.getNoEsquerda());
        v_NoCentral.setNoEsquerda(p_No);
        
        p_No.setIncDireita(Inclinacao.VERTICAL);
        v_NoCentral.setIncDireita(Inclinacao.VERTICAL);
        
        p_No = v_NoCentral;
        
        return p_No;
    }
    
    /**
     * Correção do problema de se ter duas inclinações horizontais seguidas do
     * tipo Direita-Esquerda.
     * 
     * @param p_No Nó onde foi encontrado o problema Esquerda-Esquerda
     * @return Nó central após correção
     */
    private No de(No p_No)
    {
        No v_No1 = p_No.getNoDireita();
        No v_No2 = v_No1.getNoEsquerda();
        
        p_No.setNoDireita(v_No2.getNoEsquerda());
        v_No1.setNoEsquerda(v_No2.getNoDireita());
        
        v_No2.setNoEsquerda(p_No);
        v_No2.setNoDireita(v_No1);
        
        p_No.setIncDireita(Inclinacao.VERTICAL);
        v_No1.setIncEsquerda(Inclinacao.VERTICAL);
        
        p_No = v_No2;
        
        return p_No;
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
        Object v_Esquerda = "N", v_Direita = "N";
        
        if(p_No.getNoEsquerda() != null)
        {
            if(p_No.getIncEsquerda() == Inclinacao.HORIZONTAL)
                v_Esquerda = p_No.getNoEsquerda().getItem() + "h";
            else
                v_Esquerda = p_No.getNoEsquerda().getItem() + "v";
        }
        
        if(p_No.getNoDireita() != null)
        {
            if(p_No.getIncDireita() == Inclinacao.HORIZONTAL)
                v_Direita = p_No.getNoDireita().getItem() + "h";
            else
                v_Direita = p_No.getNoDireita().getItem() + "v";
        }
        
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