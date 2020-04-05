package gow.gui.generator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NoneSelectionModel extends javafx.scene.control.MultipleSelectionModel {
    @Override
    public ObservableList<Integer> getSelectedIndices() {
        return FXCollections.observableArrayList();
    }

    @Override
    public ObservableList getSelectedItems() {
        return FXCollections.observableArrayList();
    }

    @Override
    public void selectIndices(int index, int... indices) {

    }

    @Override
    public void selectAll() {

    }

    @Override
    public void clearAndSelect(int index) {

    }

    @Override
    public void select(int index) {

    }

    @Override
    public void select(Object obj) {

    }

    @Override
    public void clearSelection(int index) {

    }

    @Override
    public void clearSelection() {

    }

    @Override
    public boolean isSelected(int index) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void selectPrevious() {

    }

    @Override
    public void selectNext() {

    }

    @Override
    public void selectFirst() {

    }

    @Override
    public void selectLast() {

    }
}
