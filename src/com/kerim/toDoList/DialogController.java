package com.kerim.toDoList;

import com.kerim.toDoList.datamodel.ToDoData;
import com.kerim.toDoList.datamodel.ToDoList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DialogController {
    @FXML
    private TextField shortDescriptionField;
    @FXML
    private TextArea detailsArea;
    @FXML
    private DatePicker deadlinePicker;

    public ToDoList processResults(){
        String shortDescription = shortDescriptionField.getText().trim();
        String details = detailsArea.getText().trim();
        LocalDate deadlineValue = deadlinePicker.getValue();

        ToDoList newItem = new ToDoList(shortDescription,details,deadlineValue);
        ToDoData.getInstance().addToDoItem(newItem);
        return newItem;
    }
}
