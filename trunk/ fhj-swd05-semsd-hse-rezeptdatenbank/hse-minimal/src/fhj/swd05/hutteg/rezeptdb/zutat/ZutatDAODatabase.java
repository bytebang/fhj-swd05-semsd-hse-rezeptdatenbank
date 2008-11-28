package fhj.swd05.hutteg.rezeptdb.zutat;


import java.util.List;

import org.stenerud.hse.base.data.CriteriaBuffer;
import org.stenerud.hse.base.data.ExtendedHibernateDaoSupport;

public class ZutatDAODatabase extends ExtendedHibernateDaoSupport implements ZutatDAO
{
	// ========== CONSTANTS ==========
	private static final String STANDARD_FROM = "FROM Zutat zutat";
	private static final String STANDARD_ORDER = "ORDER BY zutat.name";

	// ========== IMPLEMENTATION ==========

	@Override
	public void create(Zutat zutat) {
		getHibernateTemplate().save(zutat);
	}

	@Override
	public void delete(Zutat zutat) {
		getHibernateTemplate().delete(zutat);
	}

	@Override
	public Zutat getZutat(String name) {
		CriteriaBuffer criteria = new CriteriaBuffer(STANDARD_FROM);
		criteria.addCriteria("zutat.name =", name);

		return (Zutat)getFirst(criteria.getQuery());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getZutatStartingWith(String name) {
		
		String wildcardedSearchString = name + "%";
		CriteriaBuffer criteria = new CriteriaBuffer(STANDARD_FROM,
				STANDARD_ORDER);
		
		criteria.addCriteria("zutat.name like", wildcardedSearchString);
		return getHibernateTemplate().find(criteria.getQuery());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getZutaten() {
		CriteriaBuffer criteria = new CriteriaBuffer(STANDARD_FROM,
				STANDARD_ORDER);
			return getHibernateTemplate().find(criteria.getQuery());
	}

	@Override
	public Zutat refresh(Zutat zutat) {
		getHibernateTemplate().refresh(zutat);
		return zutat;
	}

	@Override
	public void update(Zutat zutat) {
		getHibernateTemplate().update(zutat);
	}
}

