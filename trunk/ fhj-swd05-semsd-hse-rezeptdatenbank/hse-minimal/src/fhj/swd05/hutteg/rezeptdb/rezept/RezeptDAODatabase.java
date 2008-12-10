package fhj.swd05.hutteg.rezeptdb.rezept;


import java.util.List;

import org.stenerud.hse.base.data.CriteriaBuffer;
import org.stenerud.hse.base.data.ExtendedHibernateDaoSupport;

public class RezeptDAODatabase extends ExtendedHibernateDaoSupport implements RezeptDAO
{
	// ========== CONSTANTS ==========
	private static final String STANDARD_FROM = "FROM Rezept rezept";
	private static final String STANDARD_ORDER = "ORDER BY rezept.name";

	// ========== IMPLEMENTATION ==========


	@Override
	public void create(Rezept rezept) {
		getHibernateTemplate().save(rezept);
	}

	@Override
	public void delete(Rezept rezept) {
		getHibernateTemplate().delete(rezept);	
	}

	@Override
	public Rezept getRezept(String name) {
		CriteriaBuffer criteria = new CriteriaBuffer(STANDARD_FROM);
		criteria.addCriteria("rezept.name =", name);

		return (Rezept) getFirst(criteria.getQuery());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRezeptStartingWith(String name) {
		
		String wildcardedSearchString = name + "%";
		CriteriaBuffer criteria = new CriteriaBuffer(STANDARD_FROM,
				STANDARD_ORDER);
		
		criteria.addCriteria("rezept.name like", wildcardedSearchString);
		return getHibernateTemplate().find(criteria.getQuery());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getRezepte() {
		CriteriaBuffer criteria = new CriteriaBuffer(STANDARD_FROM,
				STANDARD_ORDER);
		return getHibernateTemplate().find(criteria.getQuery());
	}

	@Override
	public Rezept refresh(Rezept rezept) {
		getHibernateTemplate().refresh(rezept);
		return rezept;
	}

	@Override
	public void update(Rezept rezept) {
		getHibernateTemplate().update(rezept);
	}
}

