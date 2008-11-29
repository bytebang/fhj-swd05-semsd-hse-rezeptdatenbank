package org.stenerud.hse.data.widget;

import java.util.List;

/**
 * Data access for widgets.
 * 
 * @author You
 */
public interface WidgetDAO
{
	/**
	 * Get all widgets. Widgets are returned in alphabetical order.
	 * 
	 * @return a list of all widgets.
	 */
	@SuppressWarnings("unchecked")
	public List getWidgets();

	/**
	 * Get a widget by name.
	 * 
	 * @param name the name of the widget.
	 * @return the widget or null if not found.
	 */
	public Widget getWidget(String name);

	/**
	 * Refresh a widget with a version from permanent storage.
	 * 
	 * @param widget the widget to refresh.
	 * @return a reference to the widget passed in.
	 */
	public Widget refresh(Widget widget);

	/**
	 * Create a new widget
	 * 
	 * @param widget the widget to create.
	 */
	public void create(Widget widget);

	/**
	 * Update a widget.
	 * 
	 * @param widget the widget to update.
	 */
	public void update(Widget widget);

	/**
	 * Delete a widget.
	 * 
	 * @param widget the widget to delete.
	 */
	public void delete(Widget widget);
}

