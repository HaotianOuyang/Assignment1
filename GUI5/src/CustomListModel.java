import javax.swing.AbstractListModel;
import java.util.List;

/**
 * CustomListModel.java
 * This is an implementation of ListModel that supports using a List
 * collection as the underlying data.
 */
public class CustomListModel<E> extends AbstractListModel {
    List<E> list;

    public CustomListModel(List<E> list) {
        this.list = list;
    }

    public void addElement(E element) {
        list.add(element);
        int index = list.size();
        fireContentsChanged(element, index, index);
    }

    public void fireDateChanged() {
        int index = list.size();
        fireContentsChanged(list.get(index -1), index, index);
    }

    public int getSize() {
        return list.size();
    }

    public E getElementAt(int index) {
        return list.get(index);
    }
}
