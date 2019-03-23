package MyRandom;

import java.util.Random;

/**
 * Gerar números aleatórios positivos
 * 
 * @author João Guilherme M. Guimarães
 */
public class MyRandom extends Random
{
    public MyRandom() { super(); }

    /**
     * Retorna um número inteiro positivo aleatório.
     * {@code Integer.SIZE - 1}, retorna o número de bits de um número inteiro
     * sem levar em consideração o bit de sinal.
     * 
     * @return número inteiro positivo aleatório
     */
    public int nextPositiveInt()
    {
        return next(Integer.SIZE - 1);
    }
}
