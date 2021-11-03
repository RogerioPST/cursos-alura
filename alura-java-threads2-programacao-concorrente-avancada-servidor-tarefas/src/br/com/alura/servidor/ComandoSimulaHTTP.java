package br.com.alura.servidor;

public class ComandoSimulaHTTP implements Comparable<ComandoSimulaHTTP> {
    private String tipo;
    private int prioridade;
    private String params;

    public ComandoSimulaHTTP(String tipo, int prioridade, String params) {
        this.tipo = tipo;
        this.prioridade = prioridade;
        this.params = params;
    }

    // construtor e getters

    @Override
    public int compareTo(ComandoSimulaHTTP outroComando) {
        return outroComando.prioridade - prioridade;
    }

	public String getTipo() {
		return tipo;
	}
	
	public int getPrioridade() {
		return prioridade;
	}
}