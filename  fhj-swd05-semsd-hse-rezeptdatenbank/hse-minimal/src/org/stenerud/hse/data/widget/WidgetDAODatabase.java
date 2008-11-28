package org.stenerud.hse.data.widget;

import java.util.List;

import org.stenerud.hse.base.data.CriteriaBuffer;
import org.stenerud.hse.base.data.ExtendedHibernateDaoSupport;

public class WidgetDAODatabase extends ExtendedHibernateDaoSupport implements WidgetDAO
{
	// ========== CONSTANTS ==========
	private static final String STANDARD_FROM = "FROM Widget widget";
	private static final String STANDARD_ORDER = "ORDER BY widget.name";

	// ========== IMPLEMENTATION ==========

	public void create(Widget widget)
	{
		getHibernateTemplate().save(widget);
	}

	public void delete(Widget widget)
	{
		getHibernateTemplate().delete(widget);
	}

	public Widget getWidget(String name)
	{
		CriteriaBuffer criteria = new CriteriaBuffer(STANDARD_FROM);
		criteria.addCriteria("widget.name =", name);

		return (Widget)getFirst(criteria.getQuery());
	}

	public List getWidgets()
	{
		CriteriaBuffer criteria = new CriteriaBuffer(STANDARD_FROM,
			STANDARD_ORDER);
		return getHibernateTemplate().find(criteria.getQuery());
	}

	public Widget refresh(Widget widget)
	{
		getHibernateTemplate().refresh(widget);
		return widget;
	}

	public void update(Widget widget)
	{
		getHibernateTemplate().update(widget);
	}
}

