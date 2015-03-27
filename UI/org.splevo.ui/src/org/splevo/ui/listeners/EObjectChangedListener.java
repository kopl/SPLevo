package org.splevo.ui.listeners;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;

import com.google.common.base.Predicate;

/**
 * A listener for EObject changes that can be integrated via the adapter interface.
 */
public abstract class EObjectChangedListener extends EContentAdapter {
    
    private final Predicate<Notification> relevantNotificationFilter;
    
    /**
     * Constructs a new EObject change listener.
     * @param relevantNotificationFilter The filter for marking relevant notifications that shall be processed.
     */
    public EObjectChangedListener(Predicate<Notification> relevantNotificationFilter) {
        this.relevantNotificationFilter = relevantNotificationFilter;
    }
    
    @Override
    public void notifyChanged(Notification notification) {
        super.notifyChanged(notification);
        
        if (notification.getEventType() == Notification.REMOVING_ADAPTER) {
            return;
        }
        
        if (!relevantNotificationFilter.apply(notification)) {
            return;
        }
        
        reactOnChange(notification);
    }

    /**
     * Reacts on a given notification.
     * @param notification The notification to react on.
     */
    protected abstract void reactOnChange(Notification notification);
    
}
