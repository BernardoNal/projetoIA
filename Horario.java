package principal;

public class Horario {
	public int dia, inicio, duracao;
	private int l, r;
	
	/* mapeamento de dias
	 * 0 <=> segunda
	 * 1 <=> terça
	 * 2 <=> quarta
	 * 3 <=> quinta
	 * 4 <=> sexta
	 * 5 <=> sábado
	 */
	
	// início entre 0 e 23
	
	public void calcIntervalo() {
		this.l = 24 * this.dia + this.inicio;
		this.r = 24 * this.dia + this.inicio + this.duracao;
	}
	
	public Horario(int dia, int inicio, int duracao) {
		this.dia = dia;
		this.inicio = inicio;
		this.duracao = duracao;
		
		calcIntervalo();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		
		if (o.getClass() != this.getClass())
			return false;
		
		final Horario b = (Horario)o;
		return (
			// inicia no meio
			b.l >= this.l && b.l < this.r ||
			// termina no meio
			b.r > this.l && b.r <= this.r ||
			// inicia antes, mas termina depois
			b.l < this.l && b.r > this.r
		);
	}
	
	/*public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		if (inicio < 0 || inicio > 23)
			System.out.println("falha ao mudar início");
		else
			this.inicio = inicio;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		if (dia < 0 || dia > 5)
			System.out.println("falha ao mudar dia");
		else
			this.dia = dia;
	}*/
}
