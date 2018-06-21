package symtable;

/**
 * Lista de EntryClass usada para representar os tipos de uma lista de
 * par�metros
 * 
 * @author jean
 */
public class EntryRec extends EntryTable {

	/**
	 * Tipo de um objeto
	 */
	public EntryTable type;

	/**
	 * Dimens�o
	 */
	public int dim;

	/**
	 * Apontador para o resto da lista
	 */
	public EntryRec next;

	/**
	 * Número de elemetnos a partir daquele elemento
	 */
	public int count;

	/**
	 * Define se um par�metro � opcional
	 */
	public boolean isOptional;

	/**
	 * Cria elemento
	 * 
	 * @param p
	 * @param d
	 * @param c
	 */
	public EntryRec(EntryTable p, int d, int c, boolean o) {
		type = p;
		count = c;
		dim = d;
		isOptional = o;
		next = null;
	}

	/**
	 * Cria elemento e p�e no in�cio da lista
	 */
	public EntryRec(EntryTable p, int d, int c, boolean o, EntryRec t) {
		type = p;
		count = c;
		dim = d;
		isOptional = o;
		next = t;
	}

	/**
	 * Cria elemento e põe no in�cio da lista
	 */
	public EntryRec(EntryTable p, int d, int c, EntryRec t) {
		type = p;
		count = c;
		dim = d;
		next = t;
	}

	public EntryRec inverte(EntryRec ant) {
		EntryRec r = this;

		if (next != null) {
			r = next.inverte(this);
		}

		count = ant.count + 1;
		next = ant;

		return r;
	}

	public EntryRec inverte() {
		EntryRec r = this;

		count = 1;

		if (next != null) {
			r = next.inverte(this);
		}

		next = null;

		return r;
	}

	public String toStr() {
		String s;

		s = type.name;

		for (int i = 0; i < dim; i++)
			s += "[]";

		if (next != null) {
			s += (", " + next.toStr());
		}

		return s;
	}
	
    public String dscJava() {
        String s;

        s = strDim(dim);
        s += type.dscJava();

        if (next != null) {
            s += next.dscJava();
        }

        return s;
    }
    
    public boolean equals(EntryRec x) {
        EntryRec p;
        EntryRec q;

        if ((x == null) || (count != x.count)) {
            return false;
        }

        p = this;
        q = x;

        do {
            if ((p.type != q.type) || (p.dim != q.dim)) {
                return false;
            }

            p = p.next;
            q = q.next;
        } while ((p != null) && (q != null));

        return p == q; // null
    }
}
