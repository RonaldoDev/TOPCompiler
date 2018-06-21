package symtable;

/**
 * @author jean
 *
 */
public class Symtable {

	/**
	 * Apontador para o topo da tabela (mais recente)
	 */
	public EntryTable top;

	/**
	 * Número que controla o escopo
	 */
	public int scptr;

	/**
	 * Apontador para a entrada EntryClass de nível superior
	 */
	public EntryClass levelup;

	/**
	 * Cria uma tabela vazia
	 */
	public Symtable() {
		top = null;
		scptr = 0;
		levelup = null;
	}

	/**
	 * Cria uma tabela contendo apontador para nível superior
	 */
	public Symtable(EntryClass up) {
		top = null;
		scptr = 0;
		levelup = up;
	}

	/**
	 * Adiciona uma entrada à tabela
	 */
	public void add(EntryTable x) {
		x.next = top;
		top = x;
		x.scope = scptr;
		x.mytable = this;
	}

	/**
	 * Inicia novo aninhamento de variáveis
	 */
	public void beginScope() {
		scptr++;
	}

	/**
	 * Finaliza aninhamento corrente
	 */
	public void endScope() {
		while (top != null && top.scope == scptr) {
			top = top.next;
		}
		scptr--;
	}

	/**
	 * Busca por uma classe já declarada
	 */
	public EntryTable classFindUp(String x) {
		EntryTable p = top;
		// Para cada elemento da tabela corrento
		while (p != null) {
			// Verifica se é uma entrada de classe ou tipo simples e então compara o nome
			if (((p instanceof EntryClass) || (p instanceof EntrySimple)) && p.name.equals(x)) {
				return p;
			}
			p = p.next; // Próxima entrada
		}
		if (levelup == null) { // Se não achou e é o nível mais externo retorna null
			return null;
		}
		return levelup.mytable.classFindUp(x);
	}

	public EntryMethod methodFind(String x, EntryRec r) {
		EntryTable p = top;
		EntryClass q;

		while (p != null) {
			if (p instanceof EntryMethod && p.name.equals(x)) {
				EntryMethod t = (EntryMethod) p;

				if (t.param == null) {
					if (r == null) {
						return t;
					}
				} else {
					if (t.param.equals(r)) {
						return t;
					}
				}
			}

			p = p.next;
		}

		q = levelup;

		if (q.parent == null) {
			return null;
		}

		return q.parent.nested.methodFind(x, r);
	}

	public EntryMethod methodFindInclass(String x, EntryRec r) {
		EntryTable p = top;

		// para cada entrada da tabela
		while (p != null) {
			// verifica se e EntryMethod e compara o nome
			if (p instanceof EntryMethod && p.name.equals(x)) {
				EntryMethod t = (EntryMethod) p;

				// compara os parametros
				if (t.param == null) {
					if (r == null) {
						return t;
					} else {
						if (t.param.equals(r)) {
							return t;
						}
					}
				}
			}
			p = p.next; // proxima entrada
		}
		return null; // nao achou
	}

	public EntryVar varFind(String x) {
		return varFind(x, 1);
	}

	public EntryVar varFind(String x, int n) {
		EntryTable p = top;
		EntryClass q;

		while (p != null) {
			if (p instanceof EntryVar && p.name.equals(x)) {
				if (--n == 0) {
					return (EntryVar) p;
				}
			}

			p = p.next;
		}

		q = levelup;

		if (q.parent == null) {
			return null;
		}

		return q.parent.nested.varFind(x, n);
	}
}
