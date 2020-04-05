package gow.gui.cell;

import gow.generator.Item;
import gow.generator.trait.UniqueTrait;
import gow.gui.ExceptionWithDialog;
import gow.gui.WindowUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class ItemCell<T extends Item> extends ListCell<T> {
    private FXMLLoader mLLoader;
    @FXML
    private GridPane rootGrid;
    @FXML
    private ImageView imgItemType;
    @FXML
    private Label lblTraitsSize;
    @FXML
    private Label lblPrice;
    @FXML
    private Pane paneItemDetails;
    @FXML
    private Label lblWeight;

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/item-generators/custom-cells/ItemCell.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                    root().getChildren().add(mainBlock());
                    rootGrid.setOnMouseEntered(event -> rootGrid.setEffect(new InnerShadow(5, Color.BROWN)));
                    rootGrid.setOnMouseExited(event -> rootGrid.setEffect(null));
                } catch (IOException e) {
                    throw new ExceptionWithDialog(e);
                }
            }
            rootGrid.setOnMouseClicked(event -> copyToClipboard(item, event));
            lblPrice.setText(String.valueOf(item.getPrice()));
            lblWeight.setText(String.valueOf(item.getWeight()));
            imgItemType.setImage(itemTypeImage(item));
            int traitsNum = item.getTraits().size();
            lblTraitsSize.setText(traitsNum + " traits");
            if (traitsNum > 0) {
                makeLabelActive(item);
            } else {
                makeLabelPassive();
            }
            mainBlockUpdate(item);
            setText(null);
            setGraphic(rootGrid);
        }
    }

    private void copyToClipboard(T item, MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .setContents(
                            new StringSelection(item.toGowItemDescription()),
                            null
                    );
            showMessage();
        }
    }

    private void showMessage() {
        Popup popup = new Popup();
        popup.setAutoHide(true);
        Label label = new Label("Copied to clipboard!");
        Pane pane = new Pane(label);
        popup.getContent().add(pane);
        WindowUtils.showPopup(rootGrid.getScene().getWindow(), popup, 2, TimeUnit.SECONDS);
    }

    private void makeLabelPassive() {
        lblTraitsSize.setEffect(null);
        lblTraitsSize.setTextFill(Color.BLACK);
        lblTraitsSize.setStyle("-fx-cursor: pointer");
        lblTraitsSize.setOnMouseEntered(event -> lblTraitsSize.setEffect(null));
        lblTraitsSize.setOnMouseClicked(event -> {
        });
    }

    private void makeLabelActive(T item) {
        lblTraitsSize.setTextFill(Color.ROYALBLUE);
        lblTraitsSize.setOnMouseExited(event -> lblTraitsSize.setEffect(null));
        lblTraitsSize.setOnMouseEntered(event -> lblTraitsSize.setEffect(new Glow(10)));
        lblTraitsSize.setStyle("-fx-cursor: hand");
        lblTraitsSize.setOnMouseClicked(event -> {
            displayTraits(item, event);
            event.consume();
        });
        lblTraitsSize.setEffect(null);
    }

    private void displayTraits(T item, MouseEvent event) {
        ContextMenu contextMenu = new ContextMenu();
        for (UniqueTrait trait : item.getTraits()) {
            contextMenu.getItems().add(new MenuItem(trait.getName() + "; " + trait.getPrice()));
        }
        contextMenu.show(lblTraitsSize, event.getScreenX(), event.getScreenY());
    }

    protected abstract Node mainBlock();

    protected abstract void mainBlockUpdate(T item);

    protected abstract Image itemTypeImage(T item);

    protected Pane root() {
        return paneItemDetails;
    }
}
