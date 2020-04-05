package gow.gui.generator;

import gow.generator.Characteristic;
import gow.generator.Item;
import gow.generator.ItemGenerator;
import gow.generator.configuration.ItemType;
import gow.gui.ExceptionWithDialog;
import gow.gui.WindowUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public abstract class ItemGeneratorController<T extends Item> {
    public ChoiceBox<String> cbMainCharacteristic;
    public Label lblItemType;
    public ChoiceBox<String> cbItemType;
    public TextField tfieldMaxPrice;
    public TextField tfieldQuantity;
    public CheckBox chboxUseTraits;
    public ListView<T> lviewGenerated;
    private ObservableList<T> observableItems = FXCollections.observableArrayList();

    public void initialize() {
        fillChoiceBoxSetFirst(cbItemType, itemTypeValues());
        fillChoiceBoxSetFirst(cbMainCharacteristic, Characteristic.values());
        lblItemType.setText(itemTypeLocalizedName() + ":");
        lviewGenerated.setItems(observableItems);
        lviewGenerated.setCellFactory(param -> itemCellFactory());
        //noinspection unchecked
        lviewGenerated.setSelectionModel(new NoneSelectionModel());
    }

    protected abstract String itemTypeLocalizedName();

    protected abstract ListCell<T> itemCellFactory();

    /**
     * @return example BodyPart.values()
     */
    protected abstract Enum<?>[] itemTypeValues();

    private void fillChoiceBoxSetFirst(ChoiceBox<String> choiceBox, Enum<?>[] values) {
        ObservableList<String> types = FXCollections.observableArrayList();
        for (Enum<?> value : values) {
            types.add(value.name());
        }
        choiceBox.setItems(types);
        choiceBox.valueProperty().setValue(types.get(0));
    }

    private void generateAndFill(Enum<? extends ItemType> itemType, int price, int quantity, boolean useTraits) {

        for (int i = 0; i < quantity; i++) {
            T item = generator().generate(price, itemType, useTraits);
            observableItems.add(item);
        }
    }

    public void onConfigClick(ActionEvent ignored) {
        WindowUtils.launch(getClass().getClassLoader().getResource(generatorConfigFxmlPath()));
    }

    public void onGenerateClick(ActionEvent ignored) {
        try {
            generator().setMainCharacteristic(Characteristic.valueOf(cbMainCharacteristic.getValue()));
            int price = Integer.parseInt(tfieldMaxPrice.getText());
            int quantity = Integer.parseInt(tfieldQuantity.getText());
            boolean useTraits = chboxUseTraits.isSelected();
            generateAndFill(itemType(cbItemType.getValue()), price, quantity, useTraits);
        } catch (Exception e) {
            throw new ExceptionWithDialog(e);
        }
    }

    public void onClearClick(ActionEvent ignored) {
        observableItems.clear();
    }

    protected abstract String generatorConfigFxmlPath();

    protected abstract Enum<? extends ItemType> itemType(String value);

    protected abstract ItemGenerator<T> generator();
}
